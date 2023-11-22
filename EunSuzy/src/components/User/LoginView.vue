<template>
  <div class="container">
    <div class="sketchbook-container">
      <h2 class="sketchbook-title">Login</h2>
      <form class="sketchbook-form" @submit.prevent="login">
        <div class="sketchbook-group">
          <label for="username">ID</label>
          <input
            type="text"
            id="username"
            v-model="loginForm.userId"
            placeholder="ID를 입력하세요"
          />
        </div>
        <div class="sketchbook-group">
          <label for="password">Password</label>
          <input
            type="password"
            id="password"
            v-model="loginForm.userPwd"
            placeholder="Password를 입력하세요"
          />
        </div>
        <button type="submit">Log In</button>
      </form>
      <br />
      <button type="button" @click="goToSignUp">Sign up</button>
    </div>
    <div v-if="showModal" class="modal-overlay" @click="closeModal">
      <div class="modal-content" @click.stop>
        <span class="close-button" @click="closeModal">&times;</span>
        <p>ID와 비밀번호를 다시 확인해주세요.</p>
        <button class="confirm-button" @click="closeModal">확인</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";
import axios from "axios";
import { useRouter } from "vue-router";
import { useUserStore } from "@/stores/user.js";

const userStore = useUserStore();

const loginForm = ref({
  userId: "",
  userPwd: "",
});
const showModal = ref(false);

const closeModal = () => {
  showModal.value = false;
};

const goToSignUp = () => {
  router.push({ name: "signup" });
};

const router = useRouter();

const login = async () => {
  try {
    const response = await axios.post(
      "http://localhost:8080/user/login",
      loginForm.value
    );

    // 로그인 성공 처리
    console.log("로그인 성공:", response.data);

    // User 객체에서 UserNo와 UserName을 가져옴
    const userNo = response.data.userNo;
    const userName = response.data.userName;
    const event = response.data.eventDay;

    userStore.setUser(response.data);
    router.push("/");
  } catch (error) {
    // 에러 처리
    console.error("로그인 실패:", error);
    showModal.value = true; // 모달 알림을 표시
  }
};
</script>

<style scoped>
.container {
  height: 70vh;
  display: flex;
  justify-content: center;
  align-items: center;
}

body {
  font-family: "Comic Sans MS", cursive, sans-serif;
  background-color: #f0e6d6;
  margin: 0;
  padding: 0;
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
}

.sketchbook-container {
  background-color: #fffaf0;
  padding: 40px;
  border-radius: 15px;
  box-shadow: 5px 5px 10px rgba(0, 0, 0, 0.2);
  width: 40%;
}

.sketchbook-title {
  font-size: 28px;
  color: #333;
  text-align: center;
  margin-bottom: 30px;
}

.sketchbook-form {
  text-align: center;
}

.sketchbook-group {
  margin-bottom: 20px;
}

.sketchbook-group label {
  display: block;
  margin-bottom: 5px;
  font-size: 18px;
  color: #555;
}

.sketchbook-group input[type="text"],
.sketchbook-group input[type="password"] {
  width: 100%;
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 8px;
  box-sizing: border-box;
  font-size: 16px;
}

button {
  width: 100%;
  padding: 10px;
  border: none;
  border-radius: 8px;
  background-color: #bf94e4;
  color: white;
  font-size: 18px;
  cursor: pointer;
  box-shadow: 3px 3px 5px rgba(0, 0, 0, 0.3);
}

button:hover {
  background-color: #bf94e4;
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
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  width: 300px;
  max-width: 80%;
  text-align: center;
}

.close-button {
  position: absolute;
  top: 10px;
  right: 10px;
  font-size: 25px;
  line-height: 25px;
  cursor: pointer;
}
</style>
