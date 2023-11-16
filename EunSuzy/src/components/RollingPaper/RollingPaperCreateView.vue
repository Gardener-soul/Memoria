<template>
  <div class="container">
    <p class="title">제목 입력</p>
    <input
      class="input-field"
      v-model="title"
      placeholder="당신의 한 줄을 남겨주세요."
    />
    <br />
    <button class="create-button" @click="createAndNavigate">
      롤링 페이퍼 만들기
    </button>
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
      router.push(`/rollingPaper/${rollingPaperId}`);
    })
    .catch((error) => {
      console.error(error);
    });
}
</script>

<style scoped>
.container {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 20px;
  background-color: #fffaf0; /* 배경색 변경 */
  border-radius: 15px;
  box-shadow: 5px 5px 10px rgba(0, 0, 0, 0.2);
  width: 100%;
  max-width: 600px; /* 컨테이너 최대 너비 */
}

.title {
  color: #bf94e4; /* 제목 색상 유지 */
  font-size: 1.5em; /* 글꼴 크기 증가 */
  font-weight: bold; /* 글꼴 두께 */
  margin-bottom: 20px; /* 여백 증가 */
}

.input-field {
  border: 1px solid #bf94e4; /* 테두리 색상 유지 */
  border-radius: 5px; /* 둥근 테두리 */
  padding: 10px; /* 내부 여백 증가 */
  margin-bottom: 15px; /* 아래쪽 여백 증가 */
  width: 100%; /* 너비 유지 */
  box-sizing: border-box; /* 박스 모델 유지 */
}

.create-button {
  background-color: #bf94e4; /* 배경색 유지 */
  color: white; /* 글자색 유지 */
  border: none; /* 테두리 없음 */
  padding: 12px 25px; /* 패딩 증가 */
  border-radius: 5px; /* 둥근 테두리 */
  font-size: 1.1em; /* 글꼴 크기 증가 */
  font-weight: bold; /* 글꼴 두께 */
  cursor: pointer; /* 커서 스타일 */
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2); /* 그림자 효과 */
  transition: background-color 0.3s, box-shadow 0.3s; /* 전환 효과 */
}

.create-button:hover {
  background-color: #a476c1; /* 호버 시 배경색 변경 */
  box-shadow: 0 6px 12px rgba(0, 0, 0, 0.3); /* 호버 시 그림자 변경 */
}
</style>
