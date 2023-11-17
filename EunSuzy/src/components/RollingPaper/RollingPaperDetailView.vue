<template>
  <div>
    <h4>To. {{ userStore.userName }}</h4>
    <span>{{ messageCount }}개 작성</span>
    <button @click="goToMessageCreate">글 작성</button>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import axios from "axios";
import { useRouter, useRoute } from "vue-router";
import { useUserStore } from "@/stores/user.js";

const route = useRoute();
const router = useRouter();
const messageCount = ref(0);
const userStore = useUserStore();

// DB에서 데이터를 가져오는 함수
// DB에서 데이터를 가져올 때 컬럼명과 맞출 것
function fetchData() {
  const rollingPaperNo = route.params.id; // 라우트 파라미터에서 rollingPaperNo 추출

  axios
    .get(`/rollingPaper/${rollingPaperNo}`) // 백엔드 API의 경로
    .then((response) => {
      messageCount.value = response.data.writerCount;
    })
    .catch((error) => {
      console.error("데이터를 가져오는 중 에러가 발생했습니다", error);
    });
}

function goToMessageCreate() {
  router.push("/messagecreate");
}

// 컴포넌트가 생성될 때 데이터를 가져옵니다.
onMounted(fetchData);
</script>

<style scoped>
/* 스타일 */
</style>
