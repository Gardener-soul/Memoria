<template>
  <div>
    <HeaderVue />
    <RouterView />
  </div>
</template>

<script setup>
import HeaderVue from "@/components/common/HeaderView.vue";
</script>

<style>
    body {
      margin: 0;
    }
</style>
