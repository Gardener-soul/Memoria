<template>
  <div>
    <header>
      <nav class="navigation">
        <div class="nav-links">
          <RouterLink to="/"> Home </RouterLink> |
          <RouterLink to="/rollingpaper">Rolling Paper</RouterLink> |
          <RouterLink to="/event">Event</RouterLink>
        </div>
        <div class="user-links">
          <span v-if="userStore.isLoggedIn"
            >{{ userStore.userName }}님 환영합니다.</span
          >
          <RouterLink to="/mypage" v-if="userStore.isLoggedIn"
            >My Page</RouterLink
          >
          <RouterLink to="/" v-if="userStore.isLoggedIn" @click="logout"
            >Log out</RouterLink
          >
          <RouterLink to="login" v-else>Log in</RouterLink>
        </div>
      </nav>
    </header>
  </div>
</template>

<script setup>
import { useUserStore } from "@/stores/user.js";
import axios from "axios";
import { useRouter } from "vue-router";

const userStore = useUserStore();
const router = useRouter();

const logout = async () => {
  try {
    const response = await axios.get("http://localhost:8080/user/logout");
    // 로그인 성공 처리
    console.log("로그아웃 성공:");
    userStore.clearUser();
    router.push("/");
  } catch (error) {
    // 에러 처리
    console.error("로그아웃 실패:", error);
  }
};
</script>

<style scoped>
header {
  background-color: #d8b6e2;
  color: white;
  display: flex;
  justify-content: space-between;
  padding: 10px;
  width: 100%;
  box-sizing: border-box;
}

.navigation {
  display: flex;
  justify-content: space-between;
  width: 100%;
}

.nav-links,
.user-links {
  display: flex;
  align-items: center;
  list-style: none;
}

.nav-links a,
.user-links a {
  text-decoration: none;
  color: white; /* 링크 텍스트 색상도 흰색으로 설정 */
  padding: 0 10px;
}

.nav-links a:hover,
.user-links a:hover {
  background-color: rgba(255, 255, 255, 0.3); /* 호버 시 배경 효과 */
  border-radius: 4px; /* 호버 시 경계 반경 */
}

.user-links {
  margin-left: auto;
}
</style>
