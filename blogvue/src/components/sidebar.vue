<template>
  <el-affix :offset="10">
    <div class="sp-a">
      <div class="sp-c">
        <div style="position: relative">
          <el-avatar :size="100" :src="user.img"></el-avatar>
        </div>
        <div class="fh1 verse" v-text="user.name"></div>
        <div></div>
      </div>
      <div class="sidebar-inner font-fly">
        <ul>
          <li>
            <a @click="go('/')"><i class="fa fa-home"></i> 首页</a>
          </li>
          <li>
            <a @click="go('/user')"><i class="fa fa-send-o"></i> 用户中心</a>
          </li>
        </ul>
      </div>
    </div>
  </el-affix>
</template>

<script setup>
import { useRouter } from "vue-router";
const router = useRouter();
const user = {
  name: "昔我往矣，杨柳依依",
  img: "http://q.qlogo.cn/headimg_dl?dst_uin=2902804559&spec=640&img_type=jpg",
};
function go(str) {
  router.push(str);
}
</script>

<style lang="less" scoped>
.sp-a {
  background: #fff;
}
.sp-c {
  text-align: center;
  box-shadow: 0 0 3px 1px rgb(0 0 0 / 10%);
}
.el-avatar {
  margin-top: 50px;
  margin-bottom: 10px;
}

.verse {
  border-bottom: 1px solid #e6e6e6;
  margin: 0 10px 10px;
}
.sidebar-inner {
  padding: 10px;
  ul {
    list-style: none;
    padding: 0;
    text-align: center;
  }
  li {
    margin: 5px 0;
    &:hover {
      background: #e6e6e6;
    }
  }
  a {
    display: block;
    text-decoration: none;
    color: #555;
    margin: 0 5px;
    height: 30px;
    line-height: 30px;
    cursor: pointer;
  }
}
</style>
