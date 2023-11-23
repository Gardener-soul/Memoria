<template>
  <div class="container">
    <div class="sketchbook-container">
      <h2 class="sketchbook-title">🗝 회원 가입 🗝</h2>
      <form class="sketchbook-form" @submit.prevent="signUp">
        <!-- 각 입력 필드에 v-model 추가 -->
        <div class="sketchbook-group">
          <label for="userid">ID</label>
          <input
            type="text"
            id="userid"
            v-model="signupForm.userId"
            placeholder="아이디를 입력하세요"
            required
          />
        </div>
        <div class="sketchbook-group">
          <label for="password">Password</label>
          <input
            type="password"
            id="password"
            v-model="signupForm.userPwd"
            placeholder="비밀번호를 입력하세요"
            required
          />
          <p v-if="passwordIsValid">비밀번호가 유효합니다.</p>
          <p v-else id="desc">
            영문자, 숫자와 특수문자를 조합해서 8자리 이상의 비밀번호를 완성하세요.
          </p>
        </div>
        <div class="sketchbook-group">
          <label for="passwordConfirm">Password 확인</label>
          <input
            type="password"
            id="passwordConfirm"
            v-model="signupForm.userPwdConfirm"
            placeholder="비밀번호를 확인하세요"
            required
          />
          <p v-if="passwordsMatch && passwordIsValid">비밀번호가 일치합니다.</p>
          <p v-else-if="!passwordsMatch && passwordIsValid">
            비밀번호가 일치하지 않습니다.
          </p>
        </div>
        <div class="sketchbook-group">
          <label for="username">Name</label>
          <input
            type="text"
            id="username"
            v-model="signupForm.userName"
            placeholder="이름를 입력하세요"
            required
          />
        </div>
        <div class="sketchbook-group">
          <label for="email">Email</label>
          <input
            type="email"
            id="email"
            v-model="signupForm.email"
            placeholder="Email을 입력하세요"
            required
          />
        </div>
        <div class="sketchbook-group">
          <label for="nickname">Nickname</label>
          <input
            type="text"
            id="nickname"
            v-model="signupForm.nickname"
            placeholder="별명을 입력하세요"
            required
          />
        </div>
        <button type="submit">Sign Up</button>
        <br />
        <br />
        <button @click="toLogin">로그인하기</button>
      </form>
    </div>
  </div>
</template>

<script setup>
import { ref, computed } from "vue";
import axios from "axios";
import { useRouter } from "vue-router";

const signupForm = ref({
  userId: "",
  userPwd: "",
  userPwdConfirm: "",
  userName: "",
  email: "",
  nickname: "",
});

const router = useRouter();

// 비밀번호 유효성 검사를 위한 computed 속성
const passwordIsValid = computed(() => {
  return /^(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^&*])[A-Za-z\d!@#$%^&*]{8,}$/.test(
    signupForm.value.userPwd
  );
});

const passwordsMatch = computed(() => {
  return signupForm.value.userPwd === signupForm.value.userPwdConfirm;
});

const signUp = async () => {
  try {
    const response = await axios.post(
      "http://localhost:8080/user/signup",
      signupForm.value
    );
    // 회원가입 성공 처리
    console.log("회원가입 성공:", response.data);
    router.push("/");
  } catch (error) {
    // 에러 처리
    console.error("회원가입 실패:", error);
    alert("회원가입 실패");
  }
};

function toLogin() {
  router.push("/login");
}
</script>

<style scoped>
.container {
  height: 90vh;
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

label {
  text-align: left;
  margin-left: 5px;
}

.sketchbook-container {
  background-color: #fffaf0;
  padding: 40px;
  border-radius: 15px;
  box-shadow: 5px 5px 10px rgba(0, 0, 0, 0.2);
  width: 30%;
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

#desc {
  color: #bf94e4;
}

.sketchbook-group label {
  display: block;
  margin: 10px;
  font-size: 18px;
  color: #555;
}

.sketchbook-group input[type="text"],
.sketchbook-group input[type="password"],
.sketchbook-group input[type="email"] {
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
  margin-top: 20px;
  border: none;
  border-radius: 8px;
  background-color: #bf94e4;
  color: white;
  font-size: 18px;
  cursor: pointer;
  box-shadow: 3px 3px 5px rgba(0, 0, 0, 0.3);
}

button:hover {
  background-color: #ffb8c6;
}
</style>
