<template>
  <!-- :model : 绑定表单的数据域
        ref : 相当于div的id
        :rules：表单的验证规则
        :inline：表单的展示方向
  -->
  <el-main>
    <el-form
      :model="parms"
      ref="searchForm"
      label-width="80px"
      :inline="true"
      size="normal"
    >
      <el-form-item label="问卷标题">
        <el-input v-model="parms.username"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button icon="el-icon-search" @click="searchBtn">搜索</el-button>
        <el-button
          icon="el-icon-refresh-left"
          @click="resetBtn"
          style="color: #ff7670"
          >重置</el-button
        >
        <el-button
          type="primary"
          @click="addBtn"
          icon="el-icon-circle-plus-outline"
          >新增</el-button
        >
      </el-form-item>
    </el-form>

    <!-- 用户表格
      :data : 表格的数据
      el-table-column 中的 prop需要与返回的字段对应
      lable 字段的名称（自定义
    -->
    <el-table :height="tableHeight" :data="tableList" border stripe>
      <el-table-column prop="userId" label="编号"></el-table-column>
      <el-table-column prop="username" label="用户名"></el-table-column>
      <el-table-column prop="phone" label="手机号码"></el-table-column>
      <el-table-column prop="email" label="邮箱地址"></el-table-column>
      <el-table-column align="center" width="180" label="操作">
        <template slot-scope="scope">
          <el-button
            icon="el-icon-edit"
            type="primary"
            size="small"
            @click="editBtn(scope.row)"
            >编辑</el-button
          >
          <el-button
            icon="el-icon-delete"
            type="danger"
            size="small"
            @click="deleteBtn(scope.row)"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页
      @size-change: 页容量改变时触发的事件
      @current-change: 页数改变时触发的事件
      :current-page: 当前第几页
      :page-sizes: 
      :page-size: 每页查询几条
      :total: 总条数
    -->
    <el-pagination
      @size-change="sizeChange"
      @current-change="currentChange"
      :current-page.sync="parms.currentPage"
      :page-sizes="[10, 20, 40, 80, 100]"
      :page-size="parms.pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="parms.total"
      background
    >
      :pager-count="7">
    </el-pagination>

    <!-- 新增弹框 -->
    <sys-dialog
      :title="addDialog.title"
      :height="addDialog.height"
      :width="addDialog.width"
      :visible="addDialog.visible"
      @onClose="onClose"
      @onConfirm="onConfirm"
    >
      <template slot="content">
        <el-form
          :model="addModel"
          ref="addForm"
          :rules="rules"
          label-width="80px"
          :inline="true"
          size="small"
        >
          <el-form-item prop="phone" label="电话">
            <el-input v-model="addModel.phone"></el-input>
          </el-form-item>
          <el-form-item prop="email" label="邮箱">
            <el-input v-model="addModel.email"></el-input>
          </el-form-item>
          <el-form-item prop="username" label="用户名">
            <el-input v-model="addModel.username"></el-input>
          </el-form-item>
          <el-form-item prop="password" label="密码">
            <el-input v-model="addModel.password" v-if="this.addModel.editType === '1'" :disabled=true></el-input>
            <el-input v-model="addModel.password" v-if="this.addModel.editType === '0'"></el-input>
          </el-form-item>
        </el-form>
      </template>
    </sys-dialog>
  </el-main>
</template>

<script>
import SysDialog from "@/components/system/SysDialog.vue";
import { addApi, getListApi, editApi, deleteApi } from "@/api/user";

export default {
  components: { SysDialog },
  data() {
    return {
      // 表格的高度
      tableHeight: 0,
      // 表格的数据
      tableList: [],
      // 新增表单的验证规则
      rules: {
        phone: [
          {
            trigger: "change",
            message: "请填写电话号码",
            required: true,
          },
        ],
        email: [
          {
            trigger: "change",
            message: "请填写邮箱地址",
            required: true,
          },
        ],
        username: [
          {
            trigger: "change",
            message: "请填写用户名",
            required: true,
          },
        ],
        password: [
          {
            trigger: "change",
            message: "请填写密码",
            required: true,
          },
        ],
      },
      // 新增表单绑定的数据域
      addModel: {
        userId: "",
        editType: "", // 0: 新增 1: 编辑
        phone: "",
        email: "",
        username: "",
        password: "",
      },
      // 弹框属性
      addDialog: {
        title: "",
        height: 140,
        width: 650,
        visible: false,
      },
      parms: {
        username: "",
        currentPage: 1, // 从第几页开始
        pageSize: 10, // 每页查询的条数
        total: 0, // 总条数
      },
    };
  },
  created() {
    this.getList();
  },
  mounted() {
    this.$nextTick(() => {
      this.tableHeight = window.innerHeight - 200;
    });
  },
  methods: {
    // 页数改变时触发
    currentChange(val) {
      // console.log(val);
      this.parms.currentPage = val;
      // 重新获取列表
      this.getList();
    },
    // 页容量改变时触发
    sizeChange(val) {
      // console.log(val);
      this.parms.pageSize = val;
      this.parms.currentPage = 1;
      this.getList();
    },
    // 删除按钮
    async deleteBtn(row) {
      console.log(row);
      // 信息确认提示
      let confirm = await this.$myconfirm('确定删除该数据吗？');
      // console.log(confirm);
      if (confirm) {
        let res = await deleteApi({userId:row.userId});
        if (res && res.code === 200) {
          // 信息提示
          this.$message(res.msg);
          // 刷新表格
          this.getList();
        }
      }
    },
    // 编辑按钮
    editBtn(row) {
      // console.log(row);
      // 设置弹框属性
      this.addDialog.title = "编辑用户";
      this.addDialog.visible = true;
      // 数据回显
      this.$objCoppy(row, this.addModel);
      // 设置为编辑
      this.addModel.editType = "1";
    },
    // 获取表格列表
    async getList() {
      let res = await getListApi(this.parms);
      console.log(res);
      if (res && res.code === 200) {
        // 给表格数据赋值
        this.tableList = res.data.list;
        // 总条数
        this.parms.total = res.data.total;
      }
    },
    //搜索按钮
    searchBtn() {
      this.getList();
    },
    // 重置按钮
    resetBtn() {
      this.parms.username = "";
      this.getList();
    },
    // 新增按钮
    addBtn() {
      // 清空表单
      this.$resetForm('addForm', this.addModel);
      this.addModel.editType = "0";
      this.addDialog.title = "新增用户";
      this.addDialog.visible = true;
    },
    onClose() {
      this.addDialog.visible = false;
    },
    onConfirm() {
      this.$refs.addForm.validate(async (valid) => {
        if (valid) {
          let res = null;
          if (this.addModel.editType === "0") {
            res = await addApi(this.addModel);
          } else {
            res = await editApi(this.addModel);
          }
          console.log(res);
          if (res && res.code === 200) {
            // 信息提示
            this.$message.success(res.msg);
            // 刷新列表
            this.getList();
            this.addDialog.visible = false;
          }
        }
      });
    },
  },
};
</script>

<style lang="scss" scoped>

</style>