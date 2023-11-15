<template>
  <div>
    <p>제목 입력</p>
    <input v-model="title" placeholder="제목을 입력하세요" />
    <br />
    <button @click="createAndNavigate">롤링 페이퍼 만들기</button>
  </div>
</template>

<script setup>
import { ref } from "vue";
import axios from "axios";
import { useRouter } from "vue-router";

const title = ref("");
const router = useRouter();

function createAndNavigate() {
  axios
    .post("/rollingPaper/write", { title: title.value })
    .then((response) => {
      // 여기서 백엔드로부터 받은 RollingPaperID를 사용
      const rollingPaperId = response.data.rollingPaperId;
      router.push(`/rollingPaper/write/${rollingPaperId}`);
    })
    .catch((error) => {
      console.error(error);
    });
}
</script>

<style scoped></style>
