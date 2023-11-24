<template>
  <div class="container">
    <div class="sketchbook-container">
      <h2 id="sketchbook-title">🔮 로그인 🔮</h2>
      <form class="sketchbook-form" @submit.prevent="login">
        <div class="sketchbook-group">
          <label for="username"></label>
          <input
            type="text"
            id="username"
            v-model="loginForm.userId"
            placeholder="아이디를 입력하세요"
          />
        </div>
        <div class="sketchbook-group">
          <label for="password"></label>
          <input
            type="password"
            id="password"
            v-model="loginForm.userPwd"
            placeholder="비밀번호를 입력하세요"
          />
        </div>
        <button type="submit" class="button">로그인</button>
        <br />
        <button type="button" @click="goToSignUp" class="button">
          회원가입
        </button>
      </form>
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
body {
  margin: 0;
  padding: 0;
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: #f0e6d6;
  font-family: "Comic Sans MS", cursive, sans-serif;
}

.container {
  height: 80vh;
  display: flex;
  justify-content: center;
  align-items: center;
}

.sketchbook-container {
  padding: 40px;
  background-color: #fffaf0;
  border-radius: 15px;
  box-shadow: 5px 5px 10px rgba(0, 0, 0, 0.2);
  width: 20%;
}

#sketchbook-title {
  font-family: "Gaegu", sans-serif;
  font-size: 50px;
  color: #333;
  text-align: center;
  margin-top: 0px;
  margin-bottom: 30px;
}

.sketchbook-form {
  text-align: center;
}

.sketchbook-group {
  margin-top: 20px;
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
  margin-top: 20px;
  font-size: 18px;
  width: 100%;
  padding: 12px 15px;
  background-color: #d8b6e2;
  color: white;
  border: none;
  border-radius: 10px;
  cursor: pointer;
  box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.2);
  transition: all 0.3s;
}

button:hover {
  background-color: #bf94e4;
  box-shadow: 4px 4px 10px rgba(0, 0, 0, 0.3);
  transform: scale(1.1); /* 호버 시 크기 증가 */
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
