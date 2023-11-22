<template>
  <div class="container">
    <h2>My Page</h2>
    <img :src="profileImage" alt="Profile" @click="openFilePicker" />
    <input
      type="file"
      ref="fileInput"
      @change="appendImage"
      style="display: none"
    />
    <button @click="submit">사진 수정</button>
    <h3>{{ userStore.userName }}</h3>
    <hr />
    <div class="button-container">
      <button class="my-button" @click="goRoll">내가 만든 롤</button>
      <button class="my-button" @click="goMessage">내가 쓴 메세지</button>
    </div>
    <hr />
    <tr></tr>
    <RouterView />
  </div>
</template>

<script setup>
import { ref, onMounted, computed } from "vue";
import { useUserStore } from "@/stores/user.js";
import { useRouter } from "vue-router";
import axios from "axios";
import defaultProfileImage from "@/assets/기본 프로필 이미지.jpg";

const userStore = useUserStore();
const router = useRouter();
const fileInput = ref(null);
const formData = new FormData();

const userNo = userStore.userNo;

const profileImage = computed(() => {
  return userStore.img
    ? `http://localhost:8080/letter/image/${userStore.img}`
    : defaultProfileImage;
});

const openFilePicker = () => {
  fileInput.value.click();
};

const appendImage = (event) => {
  if (event.target.files && event.target.files[0]) {
    formData.append("img", event.target.files[0]);
  }
};

const submit = () => {
  const submitFormData = new FormData();

  submitFormData.append("userNo", userNo);
  submitFormData.append("image", formData.get("img")); // 이미지 파일 추가

  axios({
    method: "put",
    url: "http://localhost:8080/user/update",
    headers: { "Content-Type": "multipart/form-data" },
    data: submitFormData,
  })
    .then((response) => {
      const updatedImage = response.data.img; // 응답 구조에 따라 수정 필요

      userStore.setimage({ img: updatedImage });
    })
    .catch((error) => {
      alert(`등록 실패: ${error.message}`);
      console.error("전송 실패", error);
    });
};

function goRoll() {
  router.push("/mypage/myroll");
}

function goMessage() {
  router.push("/mypage/myletter");
}
onMounted(() => {
  if (!userStore.isLoggedIn) {
    alert("로그인이 필요합니다.");
    router.push("/login");
  }
});
</script>

<style scoped>
.container {
  display: flex;
  flex-direction: column;
  align-items: center;
  text-align: center;
}

h2 {
  margin-bottom: 20px;
}

img {
  border-radius: 50%;
  width: 100px;
  height: 100px;
  object-fit: cover;
  margin-bottom: 20px;
}

h3 {
  margin-bottom: 10px;
}

hr {
  width: 80%;
  margin: 20px auto;
}

.button-container {
  display: flex; /* 버튼들을 가로로 정렬합니다. */
  margin-bottom: 20px; /* 버튼과 다음 요소 간격 조절 */
}

.my-button {
  background-color: #bf94e4; /* 버튼 배경색을 기본 색상으로 설정 */
  border: none;
  padding: 10px 15px;
  margin-right: 10px;
  cursor: pointer;
  color: white; /* 버튼 텍스트 색상을 흰색으로 설정 */
  font-weight: bold;
  text-align: center;
}

.my-button:last-child {
  margin-right: 0;
}
</style>
