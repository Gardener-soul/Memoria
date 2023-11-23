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
    <button id="photoBtn" @click="submit">사진 수정</button>
    <h3>{{ userStore.userName }}</h3>
    <hr />
    <div class="button-container">
      <button @click="goRoll">내 편지함</button>
      <button @click="goMessage">내가 남긴 편지</button>
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

img {
  border-radius: 50%;
  width: 100px;
  height: 100px;
  object-fit: cover;
  margin-bottom: 20px;
}

hr {
  width: 80%;
  margin: 20px auto;
  border: 1px solid #ddddff;
}

/* 버튼 스타일링 */
button {
  margin: 0 10px;
  font-weight: bold;
  width: 150px;
  padding: 12px 15px;
  background-color: #d8b6e2;
  color: white;
  border: none;
  border-radius: 10px;
  cursor: pointer;
  box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.2);
  transition: all 0.3s;
}

/* 버튼 호버 효과 */
button:hover {
  background-color: #bf94e4;
  box-shadow: 4px 4px 10px rgba(0, 0, 0, 0.3);
}

#photoBtn {
  width: 100px;
}
</style>
