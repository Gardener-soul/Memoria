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
    <footer class="footer">
      <div class="footer-content">
        <span>MEMORIA © 2023. All rights reserved.</span> |
        <span>연락처: info@rollingpaper.com</span>
        <div class="social-links">
          <a href="#" target="_blank">Facebook</a> |
          <a href="#" target="_blank">Twitter</a> |
          <a href="#" target="_blank">Instagram</a>
        </div>
      </div>
    </footer>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { useRouter } from "vue-router";
import { useUserStore } from "@/stores/user.js";

const userStore = useUserStore();
const router = useRouter();
const showLoginModal = ref(false);

function handleRollingPaperStart() {
  if (userStore.isLoggedIn) {
    router.push("/create");
  } else {
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
  flex-direction: column;
  align-items: center;
  padding: 10px;
  min-height: 90vh;
  margin-top: 250px;
}

.memoria-title {
  font-family: "Gaegu", sans-serif;
  font-size: 30px;
  margin: 0;
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
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  position: relative;
  width: 80%;
  max-width: 400px;
}

.close-button {
  position: absolute;
  top: 10px;
  right: 10px;
  font-size: 25px;
  cursor: pointer;
}

.confirm-button {
  background-color: #d8b6e2;
  color: white;
  padding: 10px 20px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  margin-top: 15px;
  margin-right: 10px;
  transition: background-color 0.3s;
}

.confirm-button:hover {
  background-color: #bf94e4;
}
.footer {
  position: fixed; /* 화면 하단에 고정 */
  bottom: 0; /* 하단 0의 위치에 */
  width: 100%; /* 전체 너비 */
  background-color: #d8b6e2; /* 배경색 */
  color: white; /* 글자색 */
  text-align: center; /* 텍스트 가운데 정렬 */
  padding: 10px 0; /* 상하 패딩 */
}
.footer-content span,
.social-links a {
  margin: 5px 0;
  color: white;
  text-decoration: none;
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
