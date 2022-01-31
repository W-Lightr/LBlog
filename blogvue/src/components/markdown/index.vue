<template>
  <div id="editor"></div>
</template>

<script setup>
import { Editor } from "@toast-ui/editor";
import { onMounted, ref,reactive,watch } from "vue";
import "@toast-ui/editor/dist/toastui-editor.css";
import "@toast-ui/editor/dist/i18n/zh-cn.js";

let editor = reactive({});
const emit = defineEmits(["change"]);
let props = defineProps({
  value: String
})

function initEditor(){
  editor = new Editor({
    el: document.querySelector("#editor"),
    events: {
      change: function () {
        if(editor.getHTML() != props.value){
          emit("change",editor.getHTML());
        }
      },
    },
    height: "500px",
    initialEditType: "wysiwyg",
    previewStyle: "vertical",
    usageStatistics: false,
    language: "zh-CN",
  });
}

watch(()=>props.value,(newValue, oldValue)=>{
  if (newValue!=oldValue) {
      editor.setHTML(newValue)
  }
  
  
})

onMounted(() => {
  initEditor()
});

</script>
<style lang="less" scoped>
#editor {
  width: 100%;
}
</style>