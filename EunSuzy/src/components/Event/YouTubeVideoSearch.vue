<template>
  <div>
    <h2>운동 영상 검색 💻</h2>
    <div id="container">
      <input type="text" v-model="keyword" placeholder="당신이 원하는 운동 영상은 ?" />
      <button @click="search">검색</button>
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { useYouTubeStore } from "@/stores/youtube";
const keyword = ref("");

const store = useYouTubeStore();

const search = function () {
  store.youtubeSearch(keyword.value);
};
</script>

<style scoped>/* 전체 컨테이너 스타일링 */
div {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 20px;
}

/* 제목 스타일링 */
h2 {
  margin: 0;
  color: #333;
}

/* 검색창 스타일링 */
input[type="text"] {
  width: 300px;
  padding: 10px;
  margin-bottom: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
  box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.1);
}

/* 버튼 스타일링 */
button {
  width: 100px;
  padding: 12px 15px; /* 버튼의 패딩 증가 */
  background-color: #d8b6e2;
  color: white;
  border: none;
  border-radius: 10px;
  cursor: pointer;
  box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.2);
  transition: all 0.3s;
}

button:hover {
  background-color: #bf94e4;
  box-shadow: 4px 4px 10px rgba(0, 0, 0, 0.3);
}
</style>
