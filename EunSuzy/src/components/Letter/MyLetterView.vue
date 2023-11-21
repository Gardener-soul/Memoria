<template>
  <div>
    <div class="card-body" :style="{ backgroundColor: item.backColor }">
      <p class="text" :style="{ color: item.fontColor, fontFamily: item.font }">
        {{ item.content }}
      </p>
      <img
        v-if="item.img"
        :src="`http://localhost:8080/letter/image/${item.img}`"
      />
      <h5 class="writer">FROM. {{ item.userName }}</h5>
      <p class="date">{{ date(item.regDate) }}</p>
      <button class="card-button" id="one" @click="goBack">롤페 보기</button>
      <div v-if="isAuthor" class="card-buttons">
        <button class="card-button" @click="goModify(item.letterNo)">
          수정
        </button>

        <button class="card-button" @click="deleteLetter">삭제</button>
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
console.log(letterNo);

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
  position: relative;
  padding: 20px;
  background-color: #fff;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
  max-width: 600px;
  min-height: 200px; /* 최소 높이 설정 */
  margin: 20px auto;
  text-align: left;
}

.text {
  margin-bottom: 10px; /* 단락 사이 간격 */
  /* 필요한 경우 여기에 추가적인 스타일을 적용 */
}

.writer {
  position: absolute;
  left: 0;
  font-weight: bold; /* 글씨 굵게 */
  margin-left: 20px; /* 작성자와 날짜 사이 간격 */
}

.date {
  position: absolute;
  right: 0;
  margin-right: 20px; /* 날짜와 버튼 사이 간격 */
}

#one {
  position: absolute; /* 절대적 위치 설정 */
  bottom: 10px; /* 하단에서 10px 떨어진 위치 */
}

.card-buttons {
  position: absolute; /* 절대적 위치 설정 */
  bottom: 10px; /* 하단에서 10px 떨어진 위치 */
  right: 10px; /* 우측에서 10px 떨어진 위치 */
  display: flex; /* 버튼들을 가로로 배치 */
}
.card-button {
  background-color: #bf94e4; /* 배경색 */
  color: white; /* 글자색 */
  border: none; /* 테두리 없음 */
  padding: 10px 15px; /* 내부 여백 */
  border-radius: 5px; /* 테두리 둥글게 */
  cursor: pointer; /* 커서 모양 */
  margin-left: 10px; /* 버튼 사이 간격 */
}

.card-button:hover {
  background-color: #a476c1; /* 호버 시 배경색 변경 */
}
img {
  max-width: 100%; /* 이미지 최대 너비 */
  max-height: 300px; /* 이미지 최대 높이 설정 */
  object-fit: contain; /* 이미지를 비율에 맞게 조정하면서 `card-body`에 맞춤 */
  border-radius: 5px;
  margin-bottom: 10px;
}
</style>
