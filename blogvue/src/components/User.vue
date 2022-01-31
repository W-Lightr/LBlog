<template>
  <div class="user-top">
    <el-card class="box-card">
      <template #header>
        <div class="card-header">
          <span v-if="config.loginState"
            >用户中心，欢迎
            <el-tag class="ml-2" type="success">{{
              config.userName
            }}</el-tag></span
          >
          <span v-else>您还没有登录,请登录!</span>
          <el-button @click="logout" v-if="config.loginState" type="danger"
            ><i class="fa fa-sign-in"></i
          ></el-button>
        </div>
      </template>
      <div v-if="config.loginState">
        <el-button type="success" @click="go('/user/edit')">发布文章</el-button>
        <el-button type="primary" @click="go('/user/manage')"
          >管理文章</el-button
        >
      </div>
      <div v-else>
        <el-form
          :rules="rules"
          :inline="true"
          :model="loginData"
          class="demo-form-inline"
        >
          <el-form-item label="用户名" prop="name">
            <el-input
              v-model="loginData.name"
              placeholder="输入账号..."
              @keyup.enter="onSubmit"
            ></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input
              v-model="loginData.password"
              placeholder="输入密码..."
              @keyup.enter="onSubmit"
            ></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="onSubmit">登录</el-button>
          </el-form-item>
        </el-form>
      </div>
    </el-card>
  </div>
  <div v-if="config.loginState" class="user-bottom">
    <el-card class="box-card">
      <router-view />
    </el-card>
  </div>
</template>

<script setup>
import { useRouter } from "vue-router";
import { reactive, inject,onMounted,computed } from "vue";
import { useStore } from "vuex";
import qs from "qs";
import edit from "./Edit.vue";
import manage from "./BlogManage.vue";

let http = inject("http").http;
const store = useStore()
const router = useRouter();
let config = reactive({
  loginState: computed(()=>store.state.loginState),
  userName: window.localStorage.getItem("name"),  
});

let loginData = reactive({
  name: "",
  password: "",
});
let rules = {
  name: [
    { required: true, message: "请输入用户名", trigger: "blur" },
    { min: 1, max: 25, message: "长度在 1 到 25 个字符", trigger: "blur" },
  ],
  password: [{ required: true, message: "请输入密码", trigger: "blur" }],
};

async function onSubmit() {
  const loadingInstance = ElLoading.service();
  let data = await http.post("/user/login", qs.stringify(loginData));
  loadingInstance.close();
  if (data.data.code == 200) {
    ElMessage.success("登录成功!");
    let res = data.data.data;
    window.localStorage.setItem("loginState", true);
    window.localStorage.setItem("token", res.token);
    window.localStorage.setItem("name", res.name);
    window.localStorage.setItem("userId", res.userId);
    store.commit("changeLogState",true);
    config.userName = window.localStorage.getItem("name");
    return;
  }
}
function logout() {
  config.userName = "";
  window.localStorage.removeItem("loginState");
  window.localStorage.removeItem("token");
  window.localStorage.removeItem("name");
  window.localStorage.removeItem("userId");
  store.commit("changeLogState",false);
  ElMessage.success("退出登录成功~");
}

function go(path) {
  router.push(path);
}
</script>

<style lang="less" scoped>
.user-top {
  margin-left: 10px;
}
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.user-bottom {
  margin-left: 10px;
  margin-top: 10px;
}
</style>