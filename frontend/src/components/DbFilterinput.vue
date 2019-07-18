<template>
  <el-form :inline="true" :model="formInline">

    <el-form-item label="系统名称">
      <el-select v-model="formInline.sysName" clearable placeholder="select systemName"
                 v-on:visible-change="selectSysNames">
        <el-option
          v-for="(item, index) in type_options"
          :key="index"
          :label="item.label"
          :value="item.value">
        </el-option>
      </el-select>
    </el-form-item>

    <el-form-item label="访客 IP">
      <el-input v-model="formInline.ip" placeholder="Please input ip"></el-input>
    </el-form-item>

  </el-form>
</template>

<script>
  import lodash from 'lodash'
  import Bus from '../eventBus'

  export default {
    name: 'db-filterinput',
    data() {
      return {
        type_options: [],
        formInline: {
          sysName: '',
          ip: ''
        },
        formLabelWidth: '120px'
      }
    },

    watch: {
      'formInline.sysName': 'filterResultData',
      'formInline.ip': 'filterResultData'
    },

    methods: {
      selectSysNames: function (params) {
        if (params) {
          this.$axios.get("http://127.0.0.1:8000/api/accessLog/sysNames")
          .then((response) => {
            this.type_options = response.data;
            console.log(response.data);
          }).catch(function (response) {
            console.log(response)
          });
        }

      },
      filterResultData: _.debounce(
        function () {
          this.$axios.get("http://127.0.0.1:8000/api/accessLog", {
            params: {
              sysName: this.formInline.sysName,
              ip: this.formInline.ip,
            }
          }).then((response) => {
            response.data['sysName'] = this.formInline.sysName;
            response.data['ip'] = this.formInline.ip;
            Bus.$emit('filterResultData', response.data);
            console.log(response.data);
          }).catch(function (response) {
            console.log(response)
          });

        },
        500
      ),
    }
  }


</script>
