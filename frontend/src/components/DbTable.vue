<template>
  <div>
    <el-table
      :data="tableData"
      border
      style="width: 100%"
      class="table">
      <el-table-column
        fixed
        prop="id"
        label="序号"
        width="80">
      </el-table-column>
      <el-table-column
        prop="ip"
        label="来源 IP"
        width="120">
      </el-table-column>
      <el-table-column
        prop="location"
        label="位置信息"
        width="120">
      </el-table-column>
      <el-table-column
        prop="module"
        label="访问模块"
        width="100">
      </el-table-column>
      <el-table-column
        prop="path"
        label="访问路径"
        width="100">
      </el-table-column>
      <el-table-column
        prop="createTime"
        label="访问时间"
        width="100"
        :formatter="formatter">
      </el-table-column>
      <el-table-column
        prop="causeTime"
        label="耗时(ms)"
        width="100">
      </el-table-column>
      <el-table-column
        prop="deviceType"
        label="设备类型"
        width="100">
      </el-table-column>
      <el-table-column
        prop="browserAndVersion"
        label="浏览器"
        width="120">
      </el-table-column>

      <el-table-column
        prop="sysName"
        label="系统名称"
        width="110">
      </el-table-column>
      <el-table-column
        fixed="right"
        label="操作"
        width="70">
        <template scope="scope">
          <el-button @click="editItem(scope.$index, tableData)" type="text" size="large">详情</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination class="pagination" layout="prev, pager, next" :total="total" :page-size="pageSize"
                   v-on:current-change="changePage">
    </el-pagination>
    <db-modal :dialogFormVisible="dialogFormVisible" :form="form" v-on:canclemodal="dialogVisible"></db-modal>
  </div>

</template>

<script>
  import Bus from '../eventBus'
  import DbModal from './DbModal.vue'

  export default {
    data() {
      return {
        tableData: [],
        apiUrl: 'http://127.0.0.1:8000/api/accessLog',
        total: 0,
        pageSize: 4,
        currentPage: 1,
        sysName: '',
        ip: '',
        dialogFormVisible: false,
        form: '',
      }
    },
    components: {
      DbModal
    },
    mounted() {
      this.getCustomers();
      Bus.$on('filterResultData', (data) => {
        this.tableData = data.results;
        this.total = data.total;
        this.pageSize = data.count;
        this.ip = data.ip;
        this.sysName = data.sysName;

      });
    },

    methods: {

      dialogVisible: function () {
        this.dialogFormVisible = false;
      },

      getCustomers: function () {
        this.$axios.get(this.apiUrl, {
          params: {
            page: this.currentPage,
            sysName: this.sysName,
            ip: this.ip
          }
        }).then((response) => {
          this.tableData = response.data.data.results;
          this.total = response.data.data.total;
          this.pageSize = response.data.data.count;
          console.log(response.data.data);
        }).catch(function (response) {
          console.log(response)
        });
      },
      changePage: function (currentPage) {
        this.currentPage = currentPage;
        this.getCustomers()
      },
      editItem: function (index, rows) {
        this.dialogFormVisible = true;
        const itemId = rows[index].id;
        const idurl = 'http://127.0.0.1:8000/api/accessLog/detail/' + itemId;
        this.$axios.get(idurl).then((response) => {
          this.form = response.data;
        }).catch(function (response) {
          console.log(response)
        });
      },

      formatter(row, column) {
        let data = this.$moment(row.createTime, this.$moment.ISO_8601);
        return data.format('YYYY-MM-DD HH:mm:ss')
      },
    }
  }
</script>

<style>
  .table {
    margin-top: 30px;
  }

  .pagination {
    margin-top: 10px;
    float: right;
  }

</style>
