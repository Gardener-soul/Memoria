<template>
  <div class="container">
    <h2>My Page</h2>
    <img src="@/assets/기본 프로필 이미지.jpg" alt="Profile" />
    <h3>{{ userStore.userName }}</h3>
    <hr />
    <div class="button-container">
      <button class="my-button" @click="goRoll">내가 만든 롤</button>
      <button class="my-button" @click="goMessage">내가 쓴 메세지</button>
    </div>
    <hr />
    <tr></tr>
    <RouterView />
  </div>
</template>

<script setup>
import { useUserStore } from "@/stores/user.js";
import { useRouter } from "vue-router";

const userStore = useUserStore();
const router = useRouter();

function goRoll() {
  router.push("/mypage/myroll");
}

function goMessage() {
  router.push("/mypage/mymessage");
}
</script>

<style scoped>
.container {
  display: flex;
  flex-direction: column;
  align-items: center;
  text-align: center;
}

h2 {
  margin-bottom: 20px;
}

img {
  border-radius: 50%;
  width: 100px;
  height: 100px;
  object-fit: cover;
  margin-bottom: 20px;
}

h3 {
  margin-bottom: 10px;
}

hr {
  width: 80%;
  margin: 20px auto;
}

.button-container {
  display: flex; /* 버튼들을 가로로 정렬합니다. */
  margin-bottom: 20px; /* 버튼과 다음 요소 간격 조절 */
}

.my-button {
  background-color: #bf94e4; /* 버튼 배경색을 기본 색상으로 설정 */
  border: none;
  padding: 10px 15px;
  margin-right: 10px;
  cursor: pointer;
  color: white; /* 버튼 텍스트 색상을 흰색으로 설정 */
  font-weight: bold;
  text-align: center;
}

.my-button:last-child {
  margin-right: 0;
}
</style>
