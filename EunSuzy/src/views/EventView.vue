<template>
  <div>
    <h2>Event</h2>
    <div class="day-buttons">
      <button
        v-for="day in 7"
        :key="day"
        :class="{ 'not-clickable': notTodayImage(day) }"
        @click="goToDay(day)"
      >
        <img
          v-if="shouldShowImage(day)"
          :src="getImage(day)"
          alt="Day Image"
          class="day-button-image"
        />
        <img
          v-if="notTodayImage(day)"
          :src="getNTImage(day)"
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
    <div v-if="notTodayModal" class="modal">
      <div class="modal-content">
        <p>아직 열리지 않은 이벤트입니다!</p>
        <button class="modalButton" @click="closeNotTodayModal">닫기</button>
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
import notToday from "@/assets/아직피코피코니.png";

const router = useRouter();
const useStore = useUserStore();

const notTodayModal = ref(false);
const showModal = ref(false);

// goToDay 함수는 클릭된 날짜의 이벤트 페이지로 이동합니다.
const goToDay = (day) => {
  if (notTodayImage(day)) {
    notTodayModal.value = true;
    return;
  }
  if (useStore.eventDay < day) {
    router.push(`/event/${day}`);
  } else {
    showModal.value = true; // 모달을 표시
  }
};

// 이미지를 표시할지 여부를 결정하는 함수
const shouldShowImage = (day) => {
  return useStore.eventDay >= day; // 예시 조건
};

// 특정 일에 대한 이미지를 가져오는 함수
const getImage = (day) => {
  return myImage;
};

const notTodayImage = (day) => {
  return useStore.eventDay + 1 < day; // 예시 조건
};
// 특정 일에 대한 이미지를 가져오는 함수
const getNTImage = (day) => {
  return notToday;
};

const closeModal = () => {
  showModal.value = false; // 모달을 숨김
};
const closeNotTodayModal = () => {
  notTodayModal.value = false;
};
</script>

<style scoped>
h2 {
  font-family: 'Gaegu', sans-serif;
  font-size: 30px;
  color: #bf94e4;
  margin: 20px 10px;
  margin-left: 300px;
}

.day-buttons {
  display: flex;
  justify-content: center;
  margin-bottom: 20px;
}

.day-buttons button {
  margin: 0 10px;
  padding: 12px 15px;
  background-color: #ffc0cb;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.2);
  transition: background-color 0.3s, transform 0.3s;
}

.day-buttons button:hover {
  background-color: #ffb8c6;
  box-shadow: 4px 4px 10px rgba(0, 0, 0, 0.3);
  transform: scale(1.1);
}

.day-button-image {
  width: 100px;
  height: 100px;
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
