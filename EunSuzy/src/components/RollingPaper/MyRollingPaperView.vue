<template>
  <div>
    <div id="title">
      <h1>
        {{ rpMaster }}님 에게 {{ writerCount }}개의 편지가 도착했어요 !!💌
      </h1>
    </div>
    <div class="cards-container">
      <div
        class="card"
        v-for="item in items"
        :key="item.letterNo"
        :style="{ backgroundColor: `#${item.backColor}` }"
      >
        <div
          class="card-body"
          :class="{ 'with-img': item.img, 'without-img': !item.img }"
        >
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
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import axios from "@/util/http-common";
import { useRouter, useRoute } from "vue-router";
import { useUserStore } from "@/stores/user";

const useStore = useUserStore();
const router = useRouter();
const route = useRoute();
const items = ref([]);

const rpMaster = ref("");
const writerCount = ref("");
const rollingPaperId = route.params.id;
const userNum = ref("");

function goToThisLetter(letterNo) {
  router.push({ name: "myletter", params: { letterNo: letterNo } });
}

function date(regDate) {
  return regDate[0] + " / " + regDate[1] + " / " + regDate[2];
}

function master(title) {
  return title.substr(0, 3);
}

onMounted(() => {
  // 롤링페이퍼 주인 정보 받아오기
  axios
    .get(`http://localhost:8080/rollingPaper/${rollingPaperId}`)
    .then((response) => {
      console.log("롤페정보", response.data);
      rpMaster.value = response.data.userName;
    });

  axios
    .get(`http://localhost:8080/letter/list?rollingPaperNo=${rollingPaperId}`)
    .then((response) => {
      items.value = response.data;
      writerCount.value = items.value.length;
      userNum.value = items.value.ownerNo;
    })
    .catch((error) => {
      console.error(error);
    });
});
</script>

<style scoped>
@media (max-width: 1024px) {
  .cards-container {
    column-count: 3;
  }
}

@media (max-width: 768px) {
  .cards-container {
    column-count: 2;
  }
}

.cards-container {
  column-count: 4; /* 4개의 열로 나눕니다 */
  column-gap: 10px; /* 열 사이의 간격 */
  padding: 10px;
}

.card {
  break-inside: avoid; /* 카드가 열 사이에서 나누어지지 않도록 함 */
  margin-bottom: 10px; /* 카드 사이의 간격 */
  border: 1px solid black;
  padding: 10px;
  border-radius: 20px;
  background: white; /* 배경색 */
}

#title {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  font-weight: bold;
  font-size: x-large;
}

.writer {
  position: absolute;
  bottom: 0;
  left: 0;
  margin: 10px;
}

.date {
  position: absolute;
  bottom: 0;
  right: 0;
  margin: 10px;
}

.card-buttons {
  align-self: flex-end; /* 버튼을 우측 하단에 위치시킴 */
  padding-top: 10px; /* 버튼과 내용 사이의 간격 */
}

.card-button {
  padding: 5px 10px;
  margin-left: 5px;
  background-color: #d8b6e2;
  color: white;
  border: none;
  border-radius: 10px;
  cursor: pointer;
  box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.2);
  transition: all 0.3s;
}

.card-button:hover {
  background-color: #bf94e4;
  box-shadow: 4px 4px 10px rgba(0, 0, 0, 0.3);
}

.card-body {
  position: relative;
  padding: 20px;
  border-radius: 10px;
  max-width: 600px;
  margin: 0px;
  text-align: left;
}

/* 이미지가 있는 경우와 없는 경우에 대한 별도의 스타일 제거 */
.with-img,
.without-img {
  padding: 20px; /* 내부 여백 */
  border-radius: 10px; /* 둥근 테두리 */
}

img {
  max-width: 100%;
  max-height: 300px;
  object-fit: contain;
  border-radius: 5px;
  margin-bottom: 10px;
}
</style>
