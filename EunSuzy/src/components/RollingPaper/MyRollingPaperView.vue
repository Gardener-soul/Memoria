<template>
  <div class="cards-container">
    <div
      class="card"
      v-for="item in items"
      :key="item.letterNo"
      :style="{ backgroundColor: item.backColor }"
    >
      <div class="card-body">
        <p
          class="text"
          :style="{ color: item.fontColor, fontFamily: item.font }"
        >
          {{ item.content }}
        </p>
        <h5 class="writer">FROM. {{ item.userName }}</h5>
        <p class="date">{{ item.regDate }}</p>
        <img src="이미지 URL" />
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
import { ref } from "vue";
import axios from "axios";
import { useRouter, useRoute } from "vue-router";

const router = useRouter();
const route = useRoute();
const items = ref([]);

const rollingPaperId = route.params.id;

axios
  .get(`http://localhost:8080/letter/list?rollingPaperNo=${rollingPaperId}`)
  .then((response) => {
    items.value = response.data;
    console.log("편지 받아오기 성공", response);
  })
  .catch((error) => {
    console.error(error);
  });

function goToThisLetter(letterNo) {
  router.push({ name: "myletter", params: { letterNo: letterNo } });
}
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
  display: flex;
  flex-direction: column;
  justify-content: space-between; /* 내용과 버튼을 양 끝에 배치 */
  position: relative;
  width: calc(25% - 10px);
  border: 1px solid black;
  padding: 10px; /* 카드 내부 여백 */
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
