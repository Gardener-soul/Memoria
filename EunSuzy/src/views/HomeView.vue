<template>
  <div>
    <h2>Memoria</h2>
    <br />
    <button @click="goToCreateRollingPaper">롤링페이퍼 시작하기</button>
    <p>나중에 로그인 해야 롤페 작성할 수 있다고 적어놓기</p>
  </div>
</template>

<script setup>
import { useRouter } from "vue-router";

const router = useRouter();

function goToCreateRollingPaper() {
  router.push("/create");
}
</script>

<style scoped></style>
