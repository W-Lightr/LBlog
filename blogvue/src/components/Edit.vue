<template>
  <div class="m-content">
    <el-form
      :model="formData"
      :rules="rules"
      ref="ruleForm"
      label-width="50px"
      label-position="top"
      size="large"
    >
      <el-form-item label="标题" prop="title">
        <el-input
          v-model="formData.title"
          placeholder="请输入标题.."
        ></el-input>
      </el-form-item>

      <el-form-item label="摘要" prop="description">
        <el-input
          type="textarea"
          placeholder="请输入摘要.."
          v-model="formData.description"
        ></el-input>
      </el-form-item>

      <el-form-item label="内容" prop="content">
        <TextEdit
          @change="change"
          ref="editor"
          v-model:value="formData.content"
        ></TextEdit>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="beforeSubmit">立即创建</el-button>
        <el-button @click="reset">重置</el-button>
      </el-form-item>
      <el-dialog v-model="config.centerDialogVisible" title="提示" width="30%">
        <span>{{ config.prompt }}</span>
        <template #footer>
          <span class="dialog-footer">
            <el-button @click="config.centerDialogVisible = false"
              >取消</el-button
            >
            <el-button type="primary" @click="submit">提交</el-button>
          </span>
        </template>
      </el-dialog>
    </el-form>
  </div>
</template>

<script setup>
import { reactive, ref, inject,computed} from "vue";
import TextEdit from "./markdown";
import qs from "qs";

const props = defineProps({
  id:{
    type: String,
    default: ""
  }
});

let http = inject("http").http;
const ruleForm = ref(null);

let config = reactive({
  centerDialogVisible: false,
  prompt: "是否保存更改提交类容",
  loading: false,
});

let formData = reactive({
  id: "",
  title: "",
  description: "",
  content: "",
  userid: window.localStorage.getItem("userId"),
});
if (props.id!="") {
    console.log("ooo");
    http.get('/blogs/article',{params:{id:props.id}}).then(response=>{
      let res = response.data.data
      formData.id = res.id
      formData.title = res.title
      formData.description = res.description
      formData.content = res.content
    })
}

const rules = {
  title: [
    { required: true, message: "请输入标题", trigger: "blur" },
    { min: 1, max: 25, message: "长度在 1 到 25 个字符", trigger: "blur" },
  ],
  description: [{ required: true, message: "请输入摘要", trigger: "blur" }],
  content: [{ required: true, message: "请输入内容", trigger: "blur" }],
};
function beforeSubmit() {
  ruleForm.value.validate((flag, obj) => {
    if (!flag) {
      ElMessage.error("请填写内容！！");
    } else {
      config.centerDialogVisible = true;
    }
  });
}

async function submit() {
  config.centerDialogVisible = false;
  const loadingInstance = ElLoading.service();
  let data=null
  try {
    //请求后端
    data = await http.post("/blogs/edit",qs.stringify(formData));
  } catch (e) {
    ElMessage.error("请求失败,请重试!" + e);
  } finally {
    loadingInstance.close();
  }
  if (data.data.code == 200) {
      ElMessage.success(data.data.msg)
      reset()
  }
}
function reset() {
  try {
    ruleForm.value.resetFields();
    formData.content=""
  } catch (e) {
    console.log(e);
  }
}
function change(str) {
  formData.content = str;
}
</script>

<style lang="less" scoped>
</style>