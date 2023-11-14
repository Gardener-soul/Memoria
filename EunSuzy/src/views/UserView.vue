<template>
  <div>
    <h3>User</h3>
    <hr />
    <RouterLink to="login">LogIn</RouterLink> |
    <RouterLink to="signup">SignUp</RouterLink> |
  </div>
  <RouterView />
</template>

<script setup>
import { RouterLink, RouterView } from "vue-router"; // 수정된 부분
</script>

<style scoped>
/* 여기에 스타일 작성 */
</style>
