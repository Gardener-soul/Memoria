<template>
  <div class="container">
    <div class="button-group">
      <button @click="goUser">User 정보 확인</button>
      <Button @click="goLetter">Letter 정보 확인</Button>
    </div>
    <RouterView />
  </div>
</template>

<script setup>
import { useRouter } from "vue-router";

const router = useRouter();

function goUser() {
  router.push("/admin/user");
}
function goLetter() {
  router.push("/admin/letter");
}
</script>

<style scoped>
/* 전체 컨테이너 스타일링 */
.container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: flex-start;
}

/* 버튼 그룹 스타일링 */
div {
  display: flex;
  align-items: center;
  justify-content: center;
}

/* 버튼 스타일링 */
button,
Button {
  padding: 15px 30px;
  margin: 10px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-size: 1.2em;
  font-weight: bold;
  transition: background-color 0.3s, transform 0.2s;
  color: white;
}

/* 호버 효과 */
button:hover,
Button:hover {
  transform: scale(1.05);
  box-shadow: 0 5px 15px rgba(0, 0, 0, 0.2);
}

/* User 버튼 색상 */
button {
  background-color: #007bff; /* 파란색 */
}

button:hover {
  background-color: #0056b3;
}

/* Letter 버튼 색상 */
Button {
  background-color: black; /* 초록색 */
}

Button:hover {
  background-color: gray;
}
</style>
