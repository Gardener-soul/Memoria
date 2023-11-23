<template>
  <div class="container">
    <img src="@/assets/편지 봉투.png" />
    <h4>To. {{ userName }}</h4>
    <button @click="goToRoll" class="view-rollingpaper-btn">
      롤페 보러 가기
    </button>
    <button class="writebutton" @click="checkLoginAndGoToMessageCreate">
      편지 쓰기
    </button>
    <div v-if="showModal" class="modal">
      <div class="modal-content">
        <span class="close" @click="showModal = false">&times;</span>
        <p class="modal-message">로그인이 필요한 기능입니다.</p>
        <button @click="goToLogin" class="modalButton">로그인</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import axios from "axios";
import { useRouter, useRoute } from "vue-router";
import { useUserStore } from "@/stores/user.js";

const route = useRoute();
const router = useRouter();
const messageCount = ref(0);
const userName = ref("");
const userStore = useUserStore();
const showModal = ref(false);

// DB에서 데이터를 가져오는 함수
// DB에서 데이터를 가져올 때 컬럼명과 맞출 것
const rollingPaperNo = route.params.id; // 라우트 파라미터에서 rollingPaperNo 추출
function fetchData() {
  axios
    .get(`http://localhost:8080/rollingPaper/${rollingPaperNo}`) // 백엔드 API의 경로
    .then((response) => {
      userName.value = response.data.userName;
      messageCount.value = response.data.writerCount;
    })
    .catch((error) => {
      console.error("데이터를 가져오는 중 에러가 발생했습니다", error);
    });
}

function checkLoginAndGoToMessageCreate() {
  if (!userStore.isLoggedIn) {
    showModal.value = true;
  } else {
    goToMessageCreate();
  }
}

function goToMessageCreate() {
  // 로그인 상태가 아닐 경우, 글 작성 불가
  if (!userStore.isLoggedIn) {
    showModal.value = true; // 로그인하지 않았을 때 모달 창을 표시
    return;
  }

  // 로그인 상태일 경우, letterCreateView로 이동하면서 필요한 데이터 전달
  router.push({
    name: "lettercreate",
    query: {
      userNo: userStore.userNo,
      id: route.params.id, // 'id' 값을 query로 전달
    },
  });
}

function goToLogin() {
  router.push("/login");
}

function goToRoll() {
  router.push(`/myroll/${rollingPaperNo}`);
}
// 컴포넌트가 생성될 때 데이터를 가져옵니다.
onMounted(fetchData);
</script>

<style scoped>
.container {
  display: flex;
  justify-content: center;
  align-items: center;
  position: relative;
  height: 100vh;
}

.container img {
  max-width: 100%;
  height: auto;
  z-index: 1;
}

.container h4 {
  position: absolute;
  font-size: 30px;
  color: black;
  z-index: 2;
  margin: 0;
  top: 10%;
  left: 50%;
  transform: translate(-50%, -50%);
}

.container span {
  position: absolute;
  top: 10px;
  right: 10px;
  color: black;
}

.writebutton {
  position: absolute;
  z-index: 2;
  top: 47%;
  left: 50%;
  transform: translateX(-50%);
  color: white;
  background-color: #ffb2c1;
  border: none;
  padding: 10px 20px;
  cursor: pointer;
  border-radius: 50%; /* 버튼을 원형으로 만듭니다 */
  width: 110px; /* 버튼의 너비를 설정 */
  height: 100px; /* 버튼의 높이를 설정 */
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 15px; /* 필요한 경우 글꼴 크기 조정 */
}

.view-rollingpaper-btn {
  position: absolute;
  top: 10%;
  left: calc(50% + 100px); /* h4 태그 옆에 위치 */
  transform: translateY(-50%);
  background-color: #d8b6e2; /* 버튼 배경색 */
  color: white; /* 버튼 텍스트 색상 */
  padding: 10px 20px;
  border-radius: 5px;
  border: none;
  cursor: pointer;
}

.modal {
  display: block; /* 모달을 보여줍니다. */
  position: fixed; /* 모달을 화면 중앙에 위치시킵니다. */
  z-index: 1000; /* 모달이 다른 요소들 위에 나타나도록 합니다. */
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  overflow: auto; /* 필요하면 스크롤바를 추가합니다. */
  background-color: rgb(0, 0, 0); /* 모달 배경 */
  background-color: rgba(0, 0, 0, 0.4); /* 약간의 투명도를 추가합니다. */
}
.modal-message {
  text-align: center;
  margin-top: 30px;
  margin-bottom: 10px;
}

.close {
  position: absolute;
  top: 10px;
  right: 10px;
  color: #aaa;
  font-size: 28px;
  font-weight: bold;
}

.close:hover,
.close:focus {
  color: black;
  text-decoration: none;
  cursor: pointer;
}

:deep(.modal-button) {
  padding: 10px 20px;
  border: none;
  border-radius: 5px;
  background-color: black;
  color: white;
  margin-top: 20px;
  cursor: pointer;
}

.modal {
  position: fixed;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
}

.modal-content {
  background-color: white;
  padding: 20px;
  border-radius: 15px;
  text-align: center;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  position: relative;
}

.modalButton {
  background-color: #ffb8c6;
  color: white;
  padding: 10px 20px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  margin-top: 20px;
  margin-right: 10px; /* 오른쪽 마진 추가 */
}

.modalButton:hover {
  background-color: #bf94e4;
}
</style>
