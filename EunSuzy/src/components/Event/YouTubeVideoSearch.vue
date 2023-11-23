<template>
  <div>
    <h4>운동 영상 검색 💻</h4>
    <input type="text" v-model="keyword" placeholder="당신이 원하는 운동 영상은 ?" />
    <button @click="search">검색</button>
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
h4 {
  margin-bottom: 20px;
  color: #333;
  font-size: 24px;
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
  padding: 10px 20px;
  border: none;
  border-radius: 5px;
  background-color: #d8b6e2;
  color: white;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

button:hover {
  background-color: #bf94e4;
}
</style>
