<template>
  <div class="container">
    <p class="title">💌 {{ userStore.userName }}님의 편지함 💌</p>
    <input
      class="input-field"
      v-model="title"
      placeholder="당신의 한 줄을 남겨주세요."
    />
    <p>
      1학기 동안 당신과 함께해서 즐거웠어요.
      <br />당신의 앞길을 응원하고 축복해요 -은수지-
    </p>
    <button class="create-button" @click="loginTF">롤링 페이퍼 만들기</button>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import axios from "axios";
import { useRouter } from "vue-router";
import { useUserStore } from "@/stores/user.js";

const title = ref("");
const router = useRouter();
const userStore = useUserStore();

function loginTF() {
  if (title.value.length > 0 && userStore.isLoggedIn) {
    createAndNavigate();
  } else if (title.value.length == 0) {
    alert("제목을 빈 칸으로 하면 멋 없어요.");
  } else if (!userStore.isLoggedIn) {
    alert("Login이 필요한 기능입니다.");
  }
}

function createAndNavigate() {
  axios
    .post("http://localhost:8080/rollingPaper/write", {
      ownerNo: userStore.userNo,
      title: title.value,
    })
    .then((response) => {
      // 여기서 백엔드로부터 받은 RollingPaperID를 사용
      const rollingPaperId = response.data.rollingPaperNo;
      router.push(`rollingPaper/${rollingPaperId}`);
    })
    .catch((error) => {
      console.error(error);
    });
}
onMounted(() => {
  if (!userStore.isLoggedIn) {
    alert("로그인이 필요합니다.");
    router.push("/login");
  }
});
</script>

<style scoped>
/* CSS 파일 내에서 body 태그에 대한 스타일 */
body {
  margin: 0;
  padding: 0;
  height: 40vh; /* 뷰포트 높이에 맞춤 */
  display: flex; /* Flexbox 레이아웃 사용 */
  justify-content: center; /* 수평 방향으로 중앙 정렬 */
  align-items: center; /* 세로 방향으로 중앙 정렬 */
  background-color: #f0e6d6;
  font-family: "Comic Sans MS", cursive, sans-serif;
}

.container {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding: 40px;
  background-color: #fffaf0;
  border-radius: 15px;
  box-shadow: 5px 5px 10px rgba(0, 0, 0, 0.2);
  width: 100%;
  max-width: 400px;
  margin: auto; /* 자동 마진으로 중앙 정렬 */
  margin-top: 200px;
}

.title {
  color: #bf94e4; /* 제목 색상 유지 */
  font-size: 1.5em; /* 글꼴 크기 증가 */
  font-weight: bold; /* 글꼴 두께 */
  margin-top: 0px;
  margin-bottom: 20px; /* 여백 증가 */
}

.input-field {
  border: 1px solid #bf94e4; /* 테두리 색상 유지 */
  border-radius: 5px; /* 둥근 테두리 */
  padding: 10px; /* 내부 여백 증가 */
  width: 100%; /* 너비 유지 */
  box-sizing: border-box; /* 박스 모델 유지 */
}

p {
  text-align: center;
  font-family: "Gamja Flower", sans-serif;
  font-size: 20px;
}

.create-button {
  font-size: 18px;
  font-weight: bold;
  width: 100%;
  padding: 12px 15px;
  background-color: #d8b6e2;
  color: white;
  border: none;
  border-radius: 10px;
  cursor: pointer;
  box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.2);
  transition: all 0.3s;
  /* transition: background-color 0.3s, box-shadow 0.3s; */
}

.create-button:hover {
  background-color: #bf94e4;
  box-shadow: 4px 4px 10px rgba(0, 0, 0, 0.3);
}
</style>
