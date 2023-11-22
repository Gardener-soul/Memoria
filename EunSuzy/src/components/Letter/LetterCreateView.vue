<template>
  <div>
    <div id="write">
      <p>🎄편지를 남겨주세요🎄</p>
      <textarea
        v-model="content"
        :style="{
          fontFamily: selectedFont,
          color: selectedFontColor,
          backgroundColor: selectedBgColor,
        }"
        placeholder="마음을 적어주세요"
        rows="10"
      ></textarea>
    </div>

    <div class="color-selection-container">
      <button @click="toggleFontMenu">글씨체 선택</button>
      <button @click="toggleFontColorPicker">글씨 색상 선택</button>
      <button @click="toggleBgColorPicker">배경 색상 선택</button>
    </div>

    <div v-if="showFontMenu" class="btn-font-group">
      <button
        v-for="font in fonts"
        :key="font"
        @click="selectFont(font)"
        :style="{ fontFamily: font }"
      >
        {{ font }}
      </button>
    </div>

    <div class="btn-group" v-if="showFontColorPicker">
      <button
        v-for="color in colors"
        :key="color.name"
        :class="[
          'btn',
          `btn-${color.name}`,
          { 'btn-selected': selectedFontColor === color.name },
        ]"
        @click="selectFontColor(color.name)"
      >
        {{ color.label }}
      </button>
    </div>
    <div class="btn-bg-group" v-if="showBgColorPicker">
      <button
        v-for="bgColor in bgColors"
        :key="bgColor.name"
        :class="[
          'btn',
          `btn-${bgColor.name}`,
          { 'btn-selected': selectedBgColor === bgColor.name },
        ]"
        @click="selectBgColor(bgColor.name)"
      >
        {{ bgColor.label }}
      </button>
    </div>

    <div v-if="showModal" class="modal">
      <div class="modal-content">
        <span class="close" @click="showModal = false">&times;</span>
        <p>편지 내용을 입력해 주세요.</p>
      </div>
    </div>

    <div>
      <label for="image">이미지</label>
      <input type="file" id="image" @change="appendImage" />
      <br />
      <button type="button" @click="checkContentAndSend">전송</button>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import { useRoute, useRouter } from "vue-router";
import axios from "@/util/http-common";
import { useUserStore } from "@/stores/user.js";

const route = useRoute();
const router = useRouter();
const userStore = useUserStore();

const formData = new FormData();

const rollingPaperNo = route.query.id;
const writerNo = route.query.userNo;
const content = ref("");
const selectedFontColor = ref("#000000");
const selectedBgColor = ref("#FFFFFF");
const showFontMenu = ref("Ariel"); // 글씨체 메뉴 표시 여부
const selectedFont = ref(""); // 선택된 글씨체를 저장하는 ref

const fonts = ["Roboto", "Open Sans"];

const colors = [
  { name: "white", label: "흰색" },
  { name: "black", label: "검정" },
  { name: "#ff0000", label: "빨강" },
  { name: "#ff7f50", label: "주황" },
  { name: "#ffff00", label: "노랑" },
  { name: "#3cb371", label: "연두" },
  { name: "#00bfff", label: "하늘" },
  { name: "#4169e1", label: "파랑" },
  { name: "#9400d3", label: "보라" },
  { name: "#ff1493", label: "핑크" },
];
const bgColors = [
  { name: "white", label: "흰색" },
  { name: "black", label: "검정" },
  { name: "#ffdddd", label: "빨강" },
  { name: "#ffeedd", label: "주황" },
  { name: "#ffffdd", label: "노랑" },
  { name: "#ddf6dd", label: "연두" },
  { name: "#ddffff", label: "하늘" },
  { name: "#ddeeff", label: "파랑" },
  { name: "#ddddff", label: "보라" },
  { name: "#ffddf6", label: "핑크" },
];

function selectFont(font) {
  selectedFont.value = font;
}

const selectBgColor = (bgColor) => {
  selectedBgColor.value = bgColor;
};

const selectFontColor = (fColor) => {
  selectedFontColor.value = fColor;
};

const showFontColorPicker = ref(false);
const showBgColorPicker = ref(false);

function toggleFontMenu() {
  showFontMenu.value = !showFontMenu.value;
  if (showFontMenu.value) {
    showFontColorPicker.value = false;
    showBgColorPicker.value = false;
  }
}

function toggleFontColorPicker() {
  showFontColorPicker.value = !showFontColorPicker.value;
  if (showFontColorPicker.value) {
    showFontMenu.value = false;
    showBgColorPicker.value = false;
  }
}

function toggleBgColorPicker() {
  showBgColorPicker.value = !showBgColorPicker.value;
  if (showBgColorPicker.value) {
    showFontMenu.value = false;
    showFontColorPicker.value = false;
  }
}

const showModal = ref(false);

const checkContentAndSend = () => {
  if (content.value.trim() === "") {
    showModal.value = true;
  } else {
    if (useStore.isLoggedIn) {
      submit();
    } else {
      alert("로그인이 필요합니다.");
    }
  }
};

const appendImage = (e) => {
  formData.append("img", e.target.files[0]);
};

const submit = () => {
  const submitFormData = new FormData();

  submitFormData.append("rollingPaperNo", rollingPaperNo);
  submitFormData.append("writerNo", writerNo);
  submitFormData.append("content", content.value);
  submitFormData.append("font", selectedFont.value);
  submitFormData.append("fontColor", selectedFontColor.value);
  submitFormData.append("backColor", selectedBgColor.value);
  submitFormData.append("image", formData.get("img")); // 이미지 파일 추가

  axios({
    method: "post",
    url: "http://localhost:8080/letter/write",
    headers: { "Content-Type": "multipart/form-data" },
    data: submitFormData,
  })
    .then((response) => {
      router.push("/myroll/" + rollingPaperNo);
    })
    .catch((error) => {
      alert(`등록 실패: ${error.message}`);
      console.error("전송 실패", error);
    });
};
onMounted(() => {
  if (!userStore.isLoggedIn) {
    alert("로그인이 필요합니다.");
    router.push("/login");
  }
});
</script>

<style scoped>
.color-selection-container {
  display: flex;
  justify-content: space-around; /* 버튼을 양쪽으로 정렬 */
  margin-bottom: 10px;
}

#write {
  display: flex;
  justify-content: center; /* 수평 정렬 */
  align-items: center; /* 수직 정렬 */
}

p {
  font-size: 24px;
  color: #333;
  margin-bottom: 20px;
}

textarea {
  width: 50%;
  padding: 15px;
  margin-bottom: 20px;
  border: 1px solid #ddd;
  border-radius: 20px;
  font-size: 16px;
  box-sizing: border-box;
  resize: vertical;
}

button {
  padding: 10px 20px;
  border: none;
  border-radius: 5px;
  background-color: #bf94e4;
  color: white;
  cursor: pointer;
  margin: 0 auto;
  display: block;
}

.btn-group,
.btn-bg-group {
  display: flex;
  justify-content: center; /* 버튼들을 중앙 정렬 */
  flex-wrap: wrap; /* 버튼이 너무 많으면 다음 줄로 넘김 */
  margin-bottom: 10px;
}
.btn-font-group {
  display: flex; /* Flexbox 레이아웃 사용 */
  justify-content: center; /* 가운데 정렬 */
  flex-wrap: wrap; /* 필요시 줄바꿈 */
  margin-bottom: 10px;
}

.btn-font-group button {
  margin: 5px;
  padding: 10px 20px;
  border: none;
  border-radius: 5px;
  background-color: black; /* 배경색을 검은색으로 설정 */
  color: white; /* 글씨 색상을 흰색으로 설정 */
  cursor: pointer;
  font-size: 16px;
}
.my-button {
  background-color: #bf94e4;
  border: none;
  padding: 10px 15px;
  margin-right: 10px;
  cursor: pointer;
  color: white;
  font-weight: bold;
  text-align: center;
}

.my-button:last-child {
  margin-right: 0;
}

.btn {
  /* 공통 버튼 스타일 */
  margin: 5px;
  padding: 10px 20px;
  border: none;
  cursor: pointer;
  font-size: 16px;
}

.btn-selected {
  border: 2px solid #ff4500; /* 하이라이트 표시 */
  font-weight: bold; /* 선택된 버튼의 글씨 굵게 */
}

.modal {
  display: block;
  position: fixed;
  z-index: 1000;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.4);
}

.modal-content {
  background-color: #fefefe;
  margin: 15% auto;
  padding: 20px;
  border: 1px solid #888;
  width: 80%;
}

.close {
  color: #aaa;
  float: right;
  font-size: 28px;
  font-weight: bold;
}

.close:hover,
.close:focus {
  color: black;
  text-decoration: none;
  cursor: pointer;
}

.btn-white {
  background-color: white;
  color: black;
}
.btn-black {
  background-color: black;
  color: white;
}

/* { name: "#ffdddd", label: "빨강" },
{ name: "#ffeedd", label: "주황" },
{ name: "#ffffdd", label: "노랑" },
{ name: "#ddf6dd", label: "연두" },
{ name: "#ddffff", label: "하늘" },
{ name: "#ddeeff", label: "파랑" },
{ name: "#ddddff", label: "보라" },
{ name: "#ffddf6", label: "핑크" }, */
.btn-#ffdddd {
  background-color: #ffdddd;
  color: white;
}
.btn-purple {
  background-color: purple;
  color: white;
}
.btn-green {
  background-color: green;
  color: white;
}
.btn-navy {
  background-color: navy;
  color: white;
}
</style>
