<template>
    <div>
        <div class="card-body" :style="{ backgroundColor: item.backColor }">
            <p class="text" :style="{ color: item.fontColor, fontFamily: item.font }">{{ item.content }}</p>
            <h5 class="writer">FROM. {{ item.userName }}</h5>
            <p class="date">{{ item.regDate }}</p>
            <div class="card-buttons">
              <button class="card-button" @click="goBack">롤페 보기</button>
              <button class="card-button">수정</button>
              <button class="card-button" @click="deleteLetter">삭제</button>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue';
import axios from 'axios';
import { useRoute } from "vue-router";
import router from '../../router';

const route = useRoute();
const item = ref({});

const letterNo = route.params.letterNo; // URL 파라미터에 맞춰 수정
console.log(letterNo);

axios.get(`http://localhost:8080/letter/${letterNo}`) // URL 형식 수정
  .then((response) => {
    item.value = response.data; // 단일 객체로 할당
    console.log("편지 받아오기 성공", response);
  })
  .catch((error) => {
    console.error(error);
  });

// 삭제 함수
const deleteLetter = () => {
  axios.delete(`http://localhost:8080/letter/delete/${letterNo}`)
    .then(() => {
      console.log(`Letter ${letterNo} deleted successfully`);
      router.back();
    })
    .catch(error => {
      console.error("Error deleting letter", error);
    });
};

function goBack{
  router.back();
}
</script>

<style scoped>

</style>
