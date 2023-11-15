<template>
  <div>
    <header>
      <nav>
        <RouterLink to="/">Home</RouterLink> |
        <RouterLink to="user">User</RouterLink> |
        <RouterLink to="rollingpaper">롤링페이퍼</RouterLink>
        <button v-if="isLoggedIn" @click="logout">로그아웃</button>
      </nav>
    </header>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import axios from "axios";
import { useRouter } from "vue-router";

const isLoggedIn = ref(false);
const router = useRouter();

// 페이지 로드 시 로그인 상태 확인
onMounted(() => {
  isLoggedIn.value = !!localStorage.getItem("loginToken");
});

// 로그아웃 함수
const logout = async () => {
  try {
    await axios.post("백엔드 로그아웃 URL");
    localStorage.removeItem("loginToken");
    isLoggedIn.value = false;
    router.push("/"); // 로그아웃 후 홈으로 리디렉션
  } catch (error) {
    console.error("로그아웃 실패:", error);
  }
};
</script>

<style scoped></style>
