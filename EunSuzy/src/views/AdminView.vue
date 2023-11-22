<template>
  <div>
    <button @click="goUser">User 정보 확인</button>
    <Button @click="goLetter">Letter 정보 확인</Button>
    <RouterView />
  </div>
</template>

<script setup>
import { useRouter } from "vue-router";

const router = useRouter();

function goUser() {
  router.push("/admin/user");
}
function goLetter() {
  router.push("/admin/letter");
}
</script>

<style scoped></style>
