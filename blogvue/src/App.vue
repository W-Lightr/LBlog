<template>
  <div class="container">
    <aside class="container__sidebar"><sidebar /></aside>
    <main class="container__main">
      <router-view />
    </main>
  </div>
</template>


<script setup>
import sidebar from "./components/sidebar.vue";

</script>
<style lang="less">
</style>
