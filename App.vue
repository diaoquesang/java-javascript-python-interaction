<template>
  <div class="big">
    <el-upload
      v-model:file-list="fileList"
      class="upload-demo"
      action="https://run.mocky.io/v3/9d059bf9-4660-45f2-925d-ce80ad6c4d15"
      :on-preview="handlePreview"
      :on-remove="handleRemove"
      list-type="picture"
    >
      <el-button type="primary">Click to upload</el-button>
      <template #tip>
        <div class="el-upload__tip">少于500kb的jpg/png格式图片</div>
      </template>
    </el-upload>
  </div>
</template>

<script lang="ts" setup>
import { ref } from "vue";

import type { UploadProps, UploadUserFile } from "element-plus";

const fileList = ref<UploadUserFile[]>([
  {
    //示例图片
    name: "请稍候",
    url: "/加载动画.gif",
  },
]);

const handleRemove: UploadProps["onRemove"] = (uploadFile, uploadFiles) => {
  console.log(uploadFile, uploadFiles);
};

const handlePreview: UploadProps["onPreview"] = (file) => {
  console.log(file);
};
</script>

<style scoped>
:deep(.el-button) {
  width: 200px;
  height: 50px;
  margin-left: 100px;
}

.el-upload__tip {
  font-size: 16px;
  margin-left: 100px;
}

.big {
  width: 400px;
  height: 300px;
  border: 1px solid black;
  border-radius: 5px;
}
</style>