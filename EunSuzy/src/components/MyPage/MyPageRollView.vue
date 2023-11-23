<template>
  <div class="card-title">
    <h1>To. {{ title }}</h1>
  </div>
  <div class="cards-container">
    <div
      class="card"
      v-for="item in items"
      :key="item.letterNo"
      :style="{ backgroundColor: `#${item.backColor}` }"
    >
      <div class="card-body">
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
      </div>
      <div class="card-buttons">
        <button class="card-button" @click="goToThisLetter(item.letterNo)">
          보기
        </button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import axios from "@/util/http-common";
import { useRouter } from "vue-router";
import { useUserStore } from "@/stores/user.js";

const router = useRouter();
const useStore = useUserStore();
const items = ref([]);

const title = ref("");
const rollingPaperNo = useStore.userNo;

function goToThisLetter(letterNo) {
  router.push({ name: "myletter", params: { letterNo: letterNo } });
}

function date(regDate) {
  return regDate[0] + " / " + regDate[1] + " / " + regDate[2];
}

onMounted(() => {
  axios
    .get(`http://localhost:8080/user/mine?rollingPaperNo=${rollingPaperNo}`)
    .then((response) => {
      items.value = response.data;
      title.value = items.value[0].title;
      console.log("롤링페이퍼 받아오기 성공", response.date);
    })
    .catch((error) => {
      console.error(error);
    });
});
</script>

<style scoped>
.cards-container {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-around;
  gap: 10px;
  padding: 10px;
}

.card {
  flex: 1 1 200px; /* 유연한 크기 조정과 최소 너비 설정 */
  border: 1px solid black;
  padding: 10px;
  margin: 10px; /* 카드 간의 여백 추가 */
  max-width: calc(25% - 20px); /* 최대 너비 설정 */
}

img {
  max-width: 100%; /* 이미지가 카드 너비를 초과하지 않도록 설정 */
  height: auto; /* 이미지 비율 유지 */
}

.writer,
.date {
  text-align: right;
}

.card-buttons {
  align-self: flex-end; /* 버튼을 우측 하단에 위치시킴 */
  padding-top: 10px; /* 버튼과 내용 사이의 간격 */
}

.card-button {
  background-color: #bf94e4;
  color: white;
  border: none;
  padding: 5px 10px;
  margin-left: 5px; /* 버튼 간의 간격 */
  cursor: pointer;
}

.card-button:hover {
  background-color: #a885c2;
}
</style>
