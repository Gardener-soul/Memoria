<template>
  <div>
    <h4>검색 결과</h4>
    <ul>
      <YouTubeVideoListItem
        v-for="video in store.videos"
        :key="video.id.videoId"
        :video="video"
      />
    </ul>
  </div>
</template>

<script setup>
import { useYouTubeStore } from "@/stores/youtube";
import YouTubeVideoListItem from "@/components/Event/YouTubeVideoListItem.vue";

const store = useYouTubeStore();
</script>

<style scoped></style>
