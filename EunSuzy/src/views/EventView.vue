<template>
  <div>
    <h2>Event</h2>
    <div class="day-buttons">
      <button v-for="day in 7" :key="day" @click="goToDay(day)">
        Day {{ day }}
      </button>
    </div>
    <RouterView />
    <p>여기에 케러셀을 넣기</p>
  </div>
</template>

<script setup>
import { useRouter } from "vue-router";

const router = useRouter();

// goToDay 함수는 클릭된 날짜의 이벤트 페이지로 이동합니다.
const goToDay = (day) => {
  router.push(`/event/${day}`);
};
</script>

<style scoped>
.day-buttons {
  display: flex;
  justify-content: center;
  margin-bottom: 20px;
}

.day-buttons button {
  background-color: #bf94e4;; /* 버튼 배경색 */
  color: white; /* 버튼 텍스트 색상 */
  border: none; /* 테두리 제거 */
  padding: 10px 15px; /* 내부 여백 */
  margin: 0 10px; /* 각 버튼 사이의 간격 */
  cursor: pointer; /* 마우스 오버 시 커서 변경 */
  transition: background-color 0.3s, transform 0.3s; /* 부드러운 색상 및 변형 변화 */
  border-radius: 5px; /* 둥근 모서리 */
  margin-right: 5px;
}

.day-buttons button:hover {
  background-color: #45a049; /* 호버 시 배경색 변경 */
  transform: scale(1.1); /* 호버 시 크기 증가 */
}
</style>
