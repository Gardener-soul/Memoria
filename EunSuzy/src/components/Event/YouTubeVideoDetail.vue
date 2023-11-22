<template>
  <div v-if="store.selectedVideo" class="video-container">
    <div class="video-wrapper">
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
    </div>
    <div class="checkbox-wrapper">
      <div>오늘의 미션</div>
      <div class="form-check" v-for="item in filteredItems" :key="item.eventNo">
        <input
          class="form-check-input"
          type="checkbox"
          :value="item.eventNo"
          :id="`flexCheck${item.eventNo}`"
          v-model="checkedEvents[item.eventNo]"
        />
        <label class="form-check-label" :for="`flexCheck${item.eventNo}`">
          {{ item.eventTitle }}
        </label>
      </div>
      <div class="button-wrapper">
        <button @click="handleSubmit">제출</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useYouTubeStore } from "@/stores/youtube";
import { useUserStore } from "@/stores/user";
import { ref, computed, onMounted, watch } from "vue";
import { useRoute } from "vue-router";
import axios from "axios";

const useStore = useUserStore();
const store = useYouTubeStore();
const route = useRoute();
const items = ref([]);

// 각 날짜에 해당하는 YouTube 영상 ID를 매핑합니다.
const dailyVideos = {
  1: "QhRcs9d2Y9E",
  2: "rIegQPbe6vE",
  3: "yyjOhsNEqtE",
  4: "CVlswN0tWrA",
  5: "IgsyvlKKFzM",
  6: "RMyfEJkBa88",
  7: "B9GsLAPeA2M",
};
const filteredItems = computed(() => {
  const routeId = parseInt(route.params.day, 10);
  const filtered = items.value.filter((item) => item.eventDay === routeId);
  return filtered;
});

const checkedEvents = ref({});
const allChecked = computed(() => {
  return filteredItems.value.every((item) => checkedEvents.value[item.eventNo]);
});

const handleSubmit = () => {
  if (allChecked.value) {
    const eventDay = parseInt(route.params.day, 10);
    const userNo = useStore.userNo;

    axios
      .put("http://localhost:8080/event/check", { eventDay, userNo })
      .then((response) => {
        useStore.eventDay = eventDay;
        console.log("Event checked:", response);
      })
      .catch((error) => {
        console.error("Error checking event:", error);
      });
  } else {
    alert("모든 이벤트를 완료하지 못했습니다.");
  }
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

const fetchEvent = () => {
  axios
    .get("http://localhost:8080/event/list")
    .then((response) => {
      items.value = response.data;
      console.log(items.value);
    })
    .catch((error) => console.error(error));
};

onMounted(fetchEvent);
</script>
<style scoped>
.video-container {
  display: flex;
  justify-content: center;
  align-items: flex-start; /* 영상과 체크박스의 상단을 맞춥니다 */
  gap: 20px; /* 요소들 사이의 간격 */
}

.video-wrapper {
  flex: 1; /* 영상 부분 */
  margin-left: 100px;
}

.checkbox-wrapper {
  flex: 1; /* 체크박스 부분 */
  display: flex;
  flex-direction: column;
  align-items: center; /* 체크박스와 버튼을 중앙에 정렬 */
  margin-top: 100px;
  margin-right: 100px;
}

.button-wrapper {
  width: 100px; /* 버튼의 너비를 조정 */
  margin-top: 20px; /* 버튼 위의 간격 */
}
/* 체크박스 스타일링 */
.form-check-input {
  width: 20px; /* 체크박스 크기 */
  height: 20px;
  margin-right: 10px; /* 체크박스와 레이블 간의 간격 */
  cursor: pointer;
  accent-color: blue;
  vertical-align: middle; /* 체크박스와 레이블의 수직 정렬 */
}

/* 체크박스 레이블 스타일링 */
.form-check-label {
  cursor: pointer;
  vertical-align: middle; /* 레이블의 수직 정렬 */
}

/* 버튼 스타일링 */
.button-wrapper {
  width: 150px; /* 버튼 크기 조정 */
}

button {
  width: 100%;
  padding: 12px 15px; /* 버튼의 패딩 증가 */
  background-color: blue;
  color: white;
  border: none;
  border-radius: 5px;
  box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.2);
  transition: all 0.3s;
}

/* 버튼 호버 효과 */
button:hover {
  background-color: darkblue;
  box-shadow: 4px 4px 10px rgba(0, 0, 0, 0.3);
}
</style>