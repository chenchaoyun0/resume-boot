<template>
  <el-dialog title="详情" v-model="dialogFormVisible" :close-on-click-modal="false" :show-close="false">
    <el-form :model="form">
      <el-form-item label="序号" :label-width="formLabelWidth">
        <el-input :disabled="true" v-model="form.id" auto-complete="off"></el-input>
      </el-form-item>
      <el-form-item label="访客 IP" :label-width="formLabelWidth">
        <el-input :disabled="false" v-model="form.ip" auto-complete="off"></el-input>
      </el-form-item>
      <el-form-item label="IP 位置信息" :label-width="formLabelWidth">
        <el-input :disabled="false" v-model="form.location" auto-complete="off"></el-input>
      </el-form-item>
      <el-form-item label="经度" :label-width="formLabelWidth">
        <el-input :disabled="false" v-model="form.longitude" auto-complete="off"></el-input>
      </el-form-item>
      <el-form-item label="纬度" :label-width="formLabelWidth">
        <el-input :disabled="false" v-model="form.latitude" auto-complete="off"></el-input>
      </el-form-item>
      <el-form-item label="访问模块" :label-width="formLabelWidth">
        <el-input :disabled="false" v-model="form.module" auto-complete="off"></el-input>
      </el-form-item>
      <el-form-item label="访问路径" :label-width="formLabelWidth">
        <el-input :disabled="false" v-model="form.path" auto-complete="off"></el-input>
      </el-form-item>
      <el-form-item label="访问时间" :label-width="formLabelWidth">
        <el-input :disabled="false" v-model="form.createTime" auto-complete="off"></el-input>
      </el-form-item>
      <el-form-item label="访问耗时(ms)" :label-width="formLabelWidth">
        <el-input :disabled="false" v-model="form.causeTime" auto-complete="off"></el-input>
      </el-form-item>
      <el-form-item label="浏览器类型" :label-width="formLabelWidth">
        <el-input :disabled="false" v-model="form.browserType" auto-complete="off"></el-input>
      </el-form-item>
      <el-form-item label="浏览器名称和版本" :label-width="formLabelWidth">
        <el-input :disabled="false" v-model="form.browserAndVersion" auto-complete="off"></el-input>
      </el-form-item>
      <el-form-item label="浏览器引擎" :label-width="formLabelWidth">
        <el-input :disabled="false" v-model="form.renderingEngine" auto-complete="off"></el-input>
      </el-form-item>
      <el-form-item label="浏览器厂商" :label-width="formLabelWidth">
        <el-input :disabled="false" v-model="form.manufacturer" auto-complete="off"></el-input>
      </el-form-item>
      <el-form-item label="访问者系统名称" :label-width="formLabelWidth">
        <el-input :disabled="false" v-model="form.sysName" auto-complete="off"></el-input>
      </el-form-item>
      <el-form-item label="浏览器产品系列" :label-width="formLabelWidth">
        <el-input :disabled="false" v-model="form.operatingSystem" auto-complete="off"></el-input>
      </el-form-item>
      <el-form-item label="浏览器生成厂商" :label-width="formLabelWidth">
        <el-input :disabled="false" v-model="form.sysManufacturer" auto-complete="off"></el-input>
      </el-form-item>
      <el-form-item label="设备类型" :label-width="formLabelWidth">
        <el-input :disabled="false" v-model="form.deviceType" auto-complete="off"></el-input>
      </el-form-item>
      <el-form-item label="userAgent" :label-width="formLabelWidth">
        <el-input :disabled="false" v-model="form.userAgent" auto-complete="off"></el-input>
      </el-form-item>

    </el-form>

    <div slot="footer" class="dialog-footer">
      <el-button :plain="false" type="danger" v-on:click="canclemodal">关闭</el-button>
      <el-button :plain="true" @click="updateForm(form)">修改</el-button>
    </div>
  </el-dialog>
</template>


<script>
  export default {
    data() {
      return {
        formLabelWidth: '120px',
      }
    },
    props: ['dialogFormVisible', 'form'],

    methods: {
      updateForm: function (formName) {
        let itemId = formName.id;
        let locationIp = formName.location;
        let renderingEngine = formName.renderingEngine;
        this.$axios.put('http://127.0.0.1:8000/api/accessLog/update/' + itemId, {
          location: locationIp,
          renderingEngine: renderingEngine
        })
        .then(function (response) {
          console.log(response);
          this.form = response.data;

        })
        .catch(function (error) {
          console.log(error);
        });
        location.reload();
      },
      canclemodal: function () {
        this.$emit('canclemodal');
      }
    }

  }

</script>
