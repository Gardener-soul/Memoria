<template>
  <div>
    <h4>유튜브 검색</h4>
    <input type="text" v-model="keyword" placeholder="원하는 운동 영상 검색" />
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

<style scoped></style>
