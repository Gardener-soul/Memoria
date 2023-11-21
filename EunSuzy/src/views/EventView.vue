<template>
  <div>
    <h2>YouTubeView입니다.</h2>
    <YouTubeVideoSearch />
    <hr />
    <YoutubeVideoDetail />
    <hr />
    <YoutubeVideoList />
  </div>
</template>

<script setup>
import YouTubeVideoSearch from "@/components/Event/YouTubeVideoSearch.vue";
import YoutubeVideoDetail from "@/components/Event/YouTubeVideoDetail.vue";
import YoutubeVideoList from "@/components/Event/YouTubeVideoList.vue";
</script>

<style scoped></style>
