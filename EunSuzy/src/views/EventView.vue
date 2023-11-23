<template>
  <div>
    <h2>Event</h2>
    <div class="day-buttons">
      <button v-for="day in 7" :key="day" @click="goToDay(day)">
        <img
          v-if="shouldShowImage(day)"
          :src="getImage(day)"
          alt="Day Image"
          class="day-button-image"
        />
        <span v-else>Day {{ day }}</span>
      </button>
    </div>
    <div v-if="showModal" class="modal">
      <div class="modal-content">
        <p>이미 클리어한 이벤트입니다.</p>
        <button class="modalButton" @click="closeModal">닫기</button>
      </div>
    </div>
    <RouterView />
  </div>
</template>

<script setup>
import { ref } from "vue";
import { useRouter } from "vue-router";
import { useUserStore } from "@/stores/user";
import myImage from "@/assets/피코피코니.png";

const router = useRouter();
const useStore = useUserStore();
const showModal = ref(false);

// goToDay 함수는 클릭된 날짜의 이벤트 페이지로 이동합니다.
const goToDay = (day) => {
  if (useStore.eventDay < day) {
    router.push(`/event/${day}`);
  } else {
    showModal.value = true; // 모달을 표시
  }
};

const closeModal = () => {
  showModal.value = false; // 모달을 숨김
};

// 이미지를 표시할지 여부를 결정하는 함수
const shouldShowImage = (day) => {
  return useStore.eventDay >= day; // 예시 조건
};

// 특정 일에 대한 이미지를 가져오는 함수
const getImage = (day) => {
  return myImage;
};
</script>

<style scoped>
.day-buttons {
  display: flex;
  justify-content: center;
  margin-bottom: 20px;
}

.day-buttons button {
  background-color: #ffc0cb; /* 버튼 배경색 */
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
  background-color: #ffb8c6; /* 호버 시 배경색 변경 */
  transform: scale(1.1); /* 호버 시 크기 증가 */
}

.day-button-image {
  width: 100px; /* 이미지의 너비를 50픽셀로 설정 */
  height: 100px; /* 이미지의 높이를 50픽셀로 설정 */
}
.modal {
  position: fixed;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
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

.modalButton {
  background-color: #ffb8c6;
  color: white;
  padding: 10px 20px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  margin-top: 20px;
  margin-right: 10px; /* 오른쪽 마진 추가 */
}

.modalButton:hover {
  background-color: #bf94e4;
}
</style>
