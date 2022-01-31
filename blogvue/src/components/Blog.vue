<template>
  <div class="block">
    <el-timeline>
      <el-timeline-item v-for="item in Article.records" :timestamp="item.createtime" placement="top" :key="item.id">
        <el-card>
          <a class="a1"  @click="goto(item.id)"><h3 v-text="item.title"></h3></a>
          <p v-text="item.description"></p>
        </el-card>
      </el-timeline-item>
      <el-pagination background layout="prev, pager, next" v-model:current-page="Article.current" :total='Article.total' :page-size="Article.size">
      </el-pagination>
    </el-timeline>
    
  </div>
</template>

<script setup>
import {inject, reactive,watch} from 'vue'
import { useRouter } from 'vue-router';
import qs from 'qs'

const router = useRouter()
const http  = inject('http').http
let Article = reactive({
  records:[],
  current:1,
  total:1,
  size:1,
})
watch(()=>Article.current,(newValue,oldValue)=>{
    console.log(newValue);
    getContent(newValue)
},{deep:true})
async function getContent(curr){
  let data = null
  try{
    data = await http.post('/blogs/blog' ,qs.stringify({currentPage:curr}))
  }catch(e){
    ElMessage.error(data.data.msg)
  }
  if (data.data.code = 200) {
    Article.records = data.data.records
    Article.current = data.data.current
    Article.total = data.data.total
    Article.size = data.data.size
  }else{
    ElMessage.error(data.data.msg)
  } 
}
function goto(page){
  router.push('/article/' + page)
}
getContent(1)
</script>

<style lang="less" scoped>
.block {
  margin-top: 20px;
  margin-right: 20px;
  margin-bottom: 100px;
}
</style>