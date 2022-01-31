<template>
  <div class="content">
    <el-card class="box-card">
      <template #header>
        <div class="card-header">
          <el-page-header title="返回" :content="articles.title" @back="goBack" />
        </div>
      </template>
      <div class="fh2 con-t" v-text="articles.title"></div>
      <div class="con-t">
          <el-tag class="ml-2" type="success" effect="dark"><i class="fa fa-clock-o"/> {{articles.createtime}}</el-tag>
      </div>
      <div class="fh1 con-c" v-html="articles.content">
      </div>
      
    </el-card>
  </div>
</template>

<script setup>
import {inject,reactive} from "vue"
import { useRouter } from 'vue-router';


const props = defineProps({
  id: String,
});
const router = useRouter();
const http = inject('http').http

const articles =reactive({
    title:null,
    createtime:null,
    content:null,
})

async function getArticleInfo(id){
    let con = null
    try {
        con = await http.get('/blogs/article',{params:{id:id}})
    }catch (e){
        ElMessage.error(e)
    }
    if(con.data.code ==200){
        let article = con.data.data;
        articles.title = article.title;
        articles.createtime = article.createtime;
        articles.content = article.content;
    }
    
}
getArticleInfo(props.id)
function goBack(){
    router.go(-1)
}
</script>

<style lang="less" scoped>
.content{
  padding-left:20px;
}
.con-t{
    text-align:center;
    margin-bottom: 13px;
}
.con-c{
    margin-top:50px;
    font-size:16px;
}
</style>