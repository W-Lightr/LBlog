<template>
  <div>
    <el-table
      :data="filterTableData"
      :default-sort="prop.value"
      style="width: 100%"
    >
      <el-table-column label="ID" sortable prop="id" />
      <el-table-column label="标题" prop="title" />
      <el-table-column label="时间" prop="createtime" />
      <el-table-column label="描述" prop="description" />
      <el-table-column align="right">
        <template #header>
          <el-input v-model="config.search" size="small" placeholder="搜索" />
        </template>
        <template #default="scope">
          <el-button size="small" @click="handleEdit(scope.$index, scope.row)"
            >编辑</el-button
          >
          <el-button
            size="small"
            type="danger"
            @click="handleDelete(scope.$index, scope.row)"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <div class="man-foot">
      <el-pagination
        background
        layout="prev, pager, next"
        v-model:current-page="config.current"
        :page-size="config.size"
        :total="config.total"
      >
      </el-pagination>
    </div>
  </div>
  <el-dialog v-model="config.centerDialogVisible" title="提示" width="30%">
    <span>{{ config.prompt }}</span>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="config.centerDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="del">提交</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script setup>
import { reactive, inject, computed, watch, ref } from "vue";
import qs from "qs";
import { useRouter } from "vue-router";
const http = inject("http").http;
const router = useRouter();

let tableData = reactive({
  data: null,
});
let prop = ref({ prop: "id", order: "descending" });
let config = reactive({
  search: "",
  total: 1,
  size: 1,
  current: 1,
  centerDialogVisible: false,
  prompt: "是否删除文章",
  delId: null,
  index: null,
});
const handleEdit = (index, row) => {
  router.push({ name: "edit", params: { id: row.id } });
};
const handleDelete = (index, row) => {
  config.centerDialogVisible = true;
  config.delId = row.id;
  config.index = index;
};
watch(
  () => config.current,
  (newValue, oldValue) => {
    getData(newValue);
  },
  { immediate: true, deep: true }
);

let filterTableData = computed(() => {
  let f = null;
  if (tableData.data != null) {
    f = tableData.data.filter(
      (data) =>
        !config.search ||
        data.title.toLowerCase().includes(config.search.toLowerCase())
    );
  }
  return f;
});

async function getData(current) {
  let con = null;
  try {
    let id = window.localStorage.getItem("userId");
    con = await http.post(
      "/blogs/manage",
      qs.stringify({ currentPage: current, userId: id })
    );
    tableData.data = con.data.records;
    config.size = con.data.size;
    config.total = con.data.total;
    config.current = con.data.current;
  } catch (e) {
    ElMessage.error(e);
  }
}
async function del() {
  let data = await http.get("/blogs/delete", { params: { id: config.delId } });
  let con = data.data;
  config.centerDialogVisible = false;
  if (con.code == 200) {
    tableData.data.splice(config.index, config.index);
    ElMessage.success(con.msg);
  }
}
</script>

<style lang="less" scoped>
.man-foot {
  margin-top: 30px;
}
</style>