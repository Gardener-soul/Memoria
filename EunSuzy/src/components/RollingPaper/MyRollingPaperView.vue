<template>
  <div class="cards-container">
    <div id="title">
      <p>{{  }}님 에게 {{ writerCount }}명의 편지가 도착했어요 !!💌</p>
    </div>
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

const writerCount = ref("");
const rollingPaperId = route.params.id;
const userNum = ref("");

function goToThisLetter(letterNo) {
  router.push({ name: "myletter", params: { letterNo: letterNo } });
}

function date(regDate) {
  return regDate[0] + " / " + regDate[1] + " / " + regDate[2];
}

onMounted(() => {
  axios
    .get(`http://localhost:8080/letter/list?rollingPaperNo=${rollingPaperId}`)
    .then((response) => {
      items.value = response.data;
      writerCount.value = items.value.length;
      userNum.value = items.value.ownerNo;
      console.log("편지 받아오기 성공", response);
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

#title {
  display: flex;
  justify-content: center; /* 수평 정렬 */
  align-items: center; /* 수직 정렬 */
  width: 100%;
  font-weight: bold;
  font-size: x-large;
}

.card {
  display: flex;
  flex-direction: column;
  justify-content: space-between; /* 내용과 버튼을 양 끝에 배치 */
  position: relative;
  width: calc(25% - 10px);
  border: 1px solid black;
  padding: 10px; /* 카드 내부 여백 */
  border-radius: 20px;
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
  background-color: #bf94e4;
  color: white;
  border: none;
  padding: 5px 10px;
  margin-left: 5px; /* 버튼 간의 간격 */
  cursor: pointer;
  border-radius: 20px;
}

.card-button:hover {
  background-color: #a885c2;
}

.card-body {
  position: relative;
  padding: 20px;
  border-radius: 10px;
  max-width: 600px;
  min-height: 200px; /* 최소 높이 설정 */
  margin: 0px;
  text-align: left;
}

.with-img {
  /* 이미지가 있는 경우 적용할 스타일 */
  min-height: 300px; /* 이미지의 최대 높이와 일치하도록 설정 */
}

.without-img {
  /* 이미지가 없는 경우 적용할 스타일 */
  min-height: 200px; /* 기본 최소 높이 */
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
