<template>
  <div class="container">
    <div class="memoria-title">
      <h1>Memoria</h1>
    </div>
    <div>
      <button
        @click="handleRollingPaperStart"
        class="start-rolling-paper-button"
      >
        롤링페이퍼 시작하기
      </button>
    </div>
    <!-- 로그인 요구 모달 -->
    <div v-if="showLoginModal" class="modal-overlay" @click="closeLoginModal">
      <div class="modal-content" @click.stop>
        <span class="close-button" @click="closeLoginModal">&times;</span>
        <p>롤링 페이퍼를 작성하려면 로그인이 필요합니다.</p>
        <p>계정이 없다면 회원가입을 진행해주세요</p>
        <button class="confirm-button" @click="goToLogin">로그인</button>
        <button class="confirm-button" @click="goToSignUp">회원가입</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { useRouter } from "vue-router";
import { useUserStore } from "@/stores/user.js";

const userStore = useUserStore();

const showLoginModal = ref(false);

const router = useRouter();

function handleRollingPaperStart() {
  if (userStore.isLoggedIn) {
    // 로그인 상태일 때 롤링 페이퍼 생성 페이지로 이동
    router.push("/create");
  } else {
    // 로그인 상태가 아닐 때 모달 창 표시
    showLoginModal.value = true;
  }
}

function closeLoginModal() {
  showLoginModal.value = false;
}

function goToLogin() {
  router.push("/login");
}
function goToSignUp() {
  router.push("/signup");
}
</script>

<style scoped>
body {
  font-family: "Comic Sans MS", cursive, sans-serif;
  background-color: #f0e6d6;
  margin: 0;
  padding: 0;
}
.container {
  display: flex;
  justify-content: center;
  flex-direction: column;
  align-items: center;
  height: 80vh;
}

.memoria-title {
  font-family: 'Gaegu', sans-serif;
  font-size: 30px;
}

h1 {
  margin: 0px;
}

.memoria-title,
.start-rolling-paper-button {
  display: block;
}
.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: rgba(0, 0, 0, 0.6);
  display: flex;
  justify-content: center;
  align-items: center;
}

.modal-content {
  background-color: white;
  padding: 20px;
  border-radius: 15px;
  text-align: center;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  position: relative;
}

.close-button {
  position: absolute;
  top: 10px;
  right: 10px;
  font-size: 25px;
  cursor: pointer;
}

.confirm-button {
  background-color: #ffb8c6;
  color: white;
  padding: 10px 20px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  margin-top: 20px;
  margin-right: 10px; /* 오른쪽 마진 추가 */
}

.confirm-button:hover {
  background-color: #bf94e4;
}

.start-rolling-paper-button {
  font-size: 18px;
  font-weight: bold;
  width: 100%;
  padding: 15px 30px;
  background-color: #bf94e4;
  color: white;
  border: none;
  border-radius: 10px;
  cursor: pointer;
  box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.2);
  transition: all 0.3s;
}

.start-rolling-paper-button:hover {
  background-color: #ffb8c6;
  box-shadow: 4px 4px 10px rgba(0, 0, 0, 0.3);
  transform: scale(1.1);
}
</style>
