<template>
  <div v-if="store.selectedVideo">
    <h4>추천 이벤트 영상</h4>
    <iframe
      width="560"
      height="315"
      :src="videoURL"
      title="YouTube video player"
      frameborder="0"
      allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
      allowfullscreen
    ></iframe>
    <checkbox>asd</checkbox>
  </div>
</template>
<script setup>
import { useYouTubeStore } from "@/stores/youtube";
import { computed, onMounted, watch } from "vue";
import { useRoute } from "vue-router";

const store = useYouTubeStore();
const route = useRoute();

// 각 날짜에 해당하는 YouTube 영상 ID를 매핑합니다.
const dailyVideos = {
  1: "uiz7EsMPsuw",
  2: "tbg3QAu-GnI",
  3: "IR7BIGeqHaI",
  4: "xW44MoAKzpQ",
  5: "IkS7zld2sDM",
  6: "GHk9wHv-hcc",
  7: "Ct36mme8eHQ",
};

// 페이지가 마운트될 때와 라우트가 변경될 때 영상을 업데이트합니다.
const updateVideo = () => {
  const day = route.params.day; // 라우트 매개변수에서 'day'를 가져옵니다.
  const videoId = dailyVideos[day] || "tbg3QAu-GnI"; // day에 해당하는 videoId 또는 기본값
  store.selectedVideo = { id: { videoId } };
};

onMounted(updateVideo);
watch(route, updateVideo);

// 영상 URL을 계산된 속성으로 생성합니다.
const videoURL = computed(() => {
  const videoId = store.selectedVideo?.id.videoId || "";
  return `https://www.youtube.com/embed/${videoId}`;
});
</script>

<style scoped></style>
