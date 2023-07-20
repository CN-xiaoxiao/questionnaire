<template>
  <el-main>
    <!-- 模糊查询 -->
    <el-form
      :model="parms"
      ref="searchForm"
      label-width="80px"
      :inline="true"
      size="small"
    >
      <el-form-item label="问卷标题">
        <el-input v-model="parms.questionTitle"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button icon="el-icon-search" @click="searchBtn">搜索</el-button>
        <el-button
          icon="el-icon-refresh-left"
          @click="resetBtn"
          style="color: #ff7670"
          >重置</el-button
        >
      </el-form-item>
    </el-form>
    <!-- 表格列表 -->
    <el-table :height="tableHeight" :data="tableList" border stripe>
      <el-table-column prop="questionOrder" label="问卷序号" width="50">
      </el-table-column>
      <el-table-column prop="questionTitle" label="问卷标题"> </el-table-column>
      <el-table-column prop="questionDesc" label="问卷描述"> </el-table-column>
      <el-table-column prop="questionImg" label="问卷图片" align="center">
        <template slot-scope="scope">
          <!-- <img :src="scope.row.questionImg" min-width="70" height="70" /> -->
          <el-image
            :src="scope.row.questionImg"
            fit="scale-down"
            :lazy="true"
            style="height: 70px"
          ></el-image>
        </template>
      </el-table-column>
      <el-table-column prop="questionStatus" label="问卷状态">
        <template slot-scope="scope">
          <el-tag
            v-if="scope.row.questionStatus == '0'"
            type="danger"
            size="normal"
            >已截止</el-tag
          >
          <el-tag
            v-if="scope.row.questionStatus == '1'"
            type="success"
            size="normal"
            >调查中</el-tag
          >
        </template>
      </el-table-column>
      <el-table-column align="center" width="150" label="操作">
        <template slot-scope="scope">
          <el-button type="primary" size="small" @click="lookBtn(scope.row)"
            >查看票数</el-button
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
    </el-pagination>
    <!-- 弹框 -->
    <sys-dialog
      :title="addDialog.title"
      :height="addDialog.height"
      :width="addDialog.width"
      :visible="addDialog.visible"
      :print="addDialog.print"
      @onClose="onClose"
      @onConfirm="onConfirm"
    >
      <template slot="content">
        <div id="printTotal">
          <div
            style="
              text-align: center;
              font-size: 20px;
              font-weight: 600;
              padding: 10px 0px;
            "
          >
            {{ questionTitle }}
          </div>
          <table style="width: 100%;" border="1">
            <thead>
              <th>题目</th>
              <th>选项</th>
              <th>票数</th>
            </thead>
            <tbody>
                <template v-for="item in lookTableList">
                    <tr v-for="(m, i) in item.paperChoice" :key="m.choiceId">
                        <td style="width: 40%; text-align: center;" v-if="i == 0" :rowspan="item.paperChoice.length">{{ item.paperTitle }}</td>
                        <td>{{ m.choiceText }}</td>
                        <td>{{ m.total }}</td>
                    </tr>
                </template>
            </tbody>
          </table>
        </div>
      </template>
    </sys-dialog>
  </el-main>
</template>
  
  <script>
import { getListApi, getTotalListApi } from "@/api/question";
import SysDialog from "@/components/system/SysDialog.vue";

export default {
  // 注册组件
  components: { SysDialog },
  data() {
    return {
      // 问卷标题
      questionTitle: "",
      // 表格的数据
      lookTableList: [],
      //  弹框属性
      addDialog: {
        title: "查看票数",
        height: 500,
        width: 950,
        visible: false,
        print: true,
      },
      // 表格的高度
      tableHeight: 0,
      // 表格数据域
      tableList: [],
      // 列表参数
      parms: {
        currentPage: 1,
        pageSize: 10,
        questionTitle: "",
        total: 0,
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
    // 弹框关闭
    onClose() {
      this.addDialog.visible = false;
    },
    // 弹框确定
    onConfirm() {
      this.addDialog.visible = false;
    },
    // 查看票数
    async lookBtn(row) {
      // console.log(row);
      // 查询统计的数据
      let res = await getTotalListApi({ questionId: row.questionId });

      if (res && res.code == 200) {
        this.lookTableList = res.data.listPaper;
        this.questionTitle = res.data.questionTitle;
      }
    //   console.log(res);
      this.addDialog.visible = true;
    },
    // 浏览图片
    handlePictureCardPreview(file) {
      // console.log(file);
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
    },
    // 重置按钮
    resetBtn() {
      this.parms.questionTitle = "";
      this.getList();
    },
    // 搜索按钮
    searchBtn() {
      this.getList();
    },
    // 页数改变时触发
    currentChange(val) {
      this.parms.currentPage = val;
      this.getList();
    },
    // 页容量改变时触发
    sizeChange(val) {
      this.parms.pageSize = val;
      this.parms.currentPage = 1;
      this.getList();
    },
    // 获取列表
    async getList() {
      let res = await getListApi(this.parms);
      // console.log(res);
      if (res && res.code === 200) {
        // 给表格数据赋值
        this.tableList = res.data.list;
        // 分页总条数
        this.parms.total = res.data.total;
      }
    },
  },
};
</script>
  
  <style lang="scss" scoped>
table {
  // width: 300px;
  text-align: center;
  border-collapse: collapse; /* 为table设置这个属性 */
}
</style>