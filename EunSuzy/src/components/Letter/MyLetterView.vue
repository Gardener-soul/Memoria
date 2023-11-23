<template>
  <div>
    <div class="card-body" :style="{ backgroundColor: `#${item.backColor}` }">
      <p
        class="text"
        :style="{ color: `#${item.fontColor}`, fontFamily: item.font }"
      >
        {{ item.content }}
      </p>
      <img
        v-if="item.img"
        :src="`http://localhost:8080/letter/image/${item.img}`"
      />
      <h5 class="writer">FROM. {{ item.userName }}</h5>
      <p class="date">{{ date(item.regDate) }}</p>
      <div class="card-buttons">
        <button class="card-button" @click="goBack">롤페 보기</button>
  <button v-if="isAuthor" class="card-button" @click="goModify(item.letterNo)">
    수정
  </button>
  <button v-if="isAuthor" class="card-button" @click="deleteLetter">삭제</button>
</div>
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";
import axios from "axios";
import { useRoute } from "vue-router";
import router from "@/router";
import { useUserStore } from "@/stores/user";

const useStore = useUserStore();
const route = useRoute();
const item = ref({});
const isAuthor = ref(false);

const letterNo = route.params.letterNo; // URL 파라미터에 맞춰 수정

axios
  .get(`http://localhost:8080/letter/${letterNo}`) // URL 형식 수정
  .then((response) => {
    item.value = response.data; // 단일 객체로 할당
    isAuthor.value = useStore.userNo === item.value.writerNo; // 사용자 번호 비교
    console.log("편지 받아오기 성공", response);
  })
  .catch((error) => {
    console.error(error);
  });

// 삭제 함수
const deleteLetter = () => {
  axios
    .delete(`http://localhost:8080/letter/delete/${letterNo}`)
    .then(() => {
      console.log(`Letter ${letterNo} deleted successfully`);
      router.back();
    })
    .catch((error) => {
      console.error("Error deleting letter", error);
    });
};

function date(regDate) {
  return regDate[0] + " / " + regDate[1] + " / " + regDate[2];
}

function goBack() {
  router.back();
}

function goModify(letterNo) {
  router.push({ name: "letterupdate", params: { id: letterNo } });
}
</script>

<style scoped>
.card-body {
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: flex-start;
  padding: 20px;
  background-color: #fff;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
  max-width: 600px;
  width: 100%; /* 너비를 100%로 설정하여 컨테이너의 너비에 맞춤 */
  margin: 20px auto;
  text-align: left;
  overflow: hidden; /* 내부 요소가 넘칠 경우 숨김 처리 */
}

.text {
  margin-bottom: 10px; /* 단락 사이 간격 */
}

.writer,
.date {
  align-self: flex-end; /* 오른쪽 정렬을 위해 flex-end 사용 */
  margin: 0 20px 10px 20px; /* 상단, 오른쪽, 하단, 왼쪽 마진 */
}

.date {
  align-self: flex-start; /* 왼쪽 정렬을 위해 flex-start 사용 */
}

.card-buttons {
  display: flex; /* 버튼들을 수평으로 정렬 */
  justify-content: center; /* 버튼들을 컨테이너 중앙으로 정렬 */
  align-items: center; /* 버튼들을 수직 방향으로 중앙 정렬 */
  margin-top: 10px; /* 버튼과 위 요소와의 간격 */
}

.card-button {
  background-color: #bf94e4;
  color: white;
  border: none;
  padding: 10px 15px;
  border-radius: 5px;
  cursor: pointer;
  margin-right: 10px; /* 버튼 오른쪽 간격 */
}

.card-button:last-child {
  margin-right: 0; /* 마지막 버튼 오른쪽 간격 제거 */
}

.card-button:hover {
  background-color: #a476c1;
}

img {
  width: 100%; /* 이미지 너비를 card-body에 맞춤 */
  max-height: 300px; /* 최대 이미지 높이 설정 */
  object-fit: cover; /* 이미지를 컨테이너에 맞게 채움 */
  border-radius: 5px; /* 이미지 모서리 둥글게 */
}
</style>
