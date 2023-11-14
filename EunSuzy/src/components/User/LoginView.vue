<template>
  <br />
  <div class="sketchbook-container">
    <h2 class="sketchbook-title">Login</h2>
    <form class="sketchbook-form" @submit.prevent="login">
      <div class="sketchbook-group">
        <label for="username">ID</label>
        <input
          type="text"
          id="username"
          v-model="loginForm.username"
          placeholder="ID를 입력하세요"
        />
      </div>
      <div class="sketchbook-group">
        <label for="password">Password</label>
        <input
          type="password"
          id="password"
          v-model="loginForm.password"
          placeholder="Password를 입력하세요"
        />
      </div>
      <button type="submit">Log In</button>
    </form>
  </div>
</template>

<script setup>
import { ref } from "vue";
import axios from "axios";

const loginForm = ref({
  username: "",
  password: "",
});

const login = async () => {
  try {
    const response = await axios.post(
      "http://localhost:8080/login",
      loginForm.value
    );
    // 로그인 성공 처리
    console.log("로그인 성공:", response.data);
  } catch (error) {
    // 에러 처리
    console.error("로그인 실패:", error);
  }
};
</script>

<style scoped>
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
  border: 2px dashed #000;
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
  background-color: #ff6347;
  color: white;
  font-size: 18px;
  cursor: pointer;
  box-shadow: 3px 3px 5px rgba(0, 0, 0, 0.3);
}

button:hover {
  background-color: #ff4500;
}
</style>
