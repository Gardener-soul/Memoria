<template>
  <div>
    <p>편지 작성</p>
    <textarea
      v-model="content"
      :style="textareaStyle"
      placeholder="마음을 적어주세요"
      rows="10"
    ></textarea>
    <div>
      <label for="image">이미지</label>
      <input type="file" id="image" @change="appendImage" />
    </div>
    <br />
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
        :class="['btn', `btn-${color.name}`]"
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
      <button type="button" @click="checkContentAndSend">전송</button>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, computed } from "vue";
import { useRoute, useRouter } from "vue-router";
import axios from "@/util/http-common";
import { useUserStore } from "@/stores/user.js";

const route = useRoute();
const router = useRouter();
const useStore = useUserStore();

const formData = new FormData();

const rollingPaperNo = route.query.id;
const writerNo = route.query.userNo;
const content = ref("");

const selectedFontColor = ref("#000000");
const selectedBgColor = ref("#FFFFFF");
const fonts = [
  "Roboto",
  "Single Day",
  "Nanum Gothic",
  "Do Hyeon",
  "Nanum Pen Script",
];
const selectedFont = ref(fonts[0]);

const colors = [
  { name: "FFFFFF", label: "흰색" },
  { name: "000000", label: "검정" },
  { name: "ff0000", label: "빨강" },
  { name: "ff7f50", label: "주황" },
  { name: "ffff00", label: "노랑" },
  { name: "3cb371", label: "연두" },
  { name: "00bfff", label: "하늘" },
  { name: "4169e1", label: "파랑" },
  { name: "9400d3", label: "보라" },
  { name: "ff1493", label: "핑크" },
];
const bgColors = [
  { name: "FFFFFF", label: "흰색" },
  { name: "000000", label: "검정" },
  { name: "ffdddd", label: "빨강" },
  { name: "ffeedd", label: "주황" },
  { name: "ffffdd", label: "노랑" },
  { name: "ddf6dd", label: "연두" },
  { name: "ddffff", label: "하늘" },
  { name: "ddeeff", label: "파랑" },
  { name: "ddddff", label: "보라" },
  { name: "ffddf6", label: "핑크" },
];

// 스타일을 반응형으로 만들기 위한 computed 속성 생성
const textareaStyle = computed(() => ({
  fontFamily: selectedFont.value,
  color: `#${selectedFontColor.value}`, // 색상 코드 앞에 #을 붙임
  backgroundColor: `#${selectedBgColor.value}`, // 색상 코드 앞에 #을 붙임
}));

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
const showFontMenu = ref(false); // 글씨체 메뉴 표시 여부

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
  if (!useStore.isLoggedIn) {
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

.send-button-container {
  display: flex;
  justify-content: center;
  margin-bottom: 20px;
}
.container {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 20px;
  background-color: #f0f0f0;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  max-width: 600px;
  margin: 50px auto;
}

p {
  font-size: 24px;
  color: #333;
  margin-bottom: 20px;
}
textarea {
  width: 100%;
  padding: 15px;
  margin-bottom: 20px;
  border: 1px solid #ddd;
  border-radius: 5px;
  box-sizing: border-box;
  font-size: 16px;
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
  margin: 5px;
  padding: 10px 20px;
  /* border: 2px solid black; */
  font-style: inherit;
  box-shadow: 3px 3px 5px rgba(0, 0, 0, 0.3);
  cursor: pointer;
  font-size: 16px;
  color: black;
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

.btn-FFFFFF {
  background-color: #ffffff;
  color: #000000;
}
.btn-000000 {
  background-color: #000000;
  color: #ffffff;
}
.btn-ff0000 {
  background-color: #ff0000;
}
.btn-ffdddd {
  background-color: #ffdddd;
}
.btn-ff7f50 {
  background-color: #ff7f50;
}
.btn-ffff00 {
  background-color: #ffff00;
}
.btn-3cb371 {
  background-color: #3cb371;
}
.btn-00bfff {
  background-color: #00bfff;
}
.btn-4169e1 {
  background-color: #4169e1;
}
.btn-9400d3 {
  background-color: #9400d3;
}
.btn-ff1493 {
  background-color: #ff1493;
}
.btn-ffeedd {
  background-color: #ffeedd;
}

.btn-ffffdd {
  background-color: #ffffdd;
}
.btn-ddf6dd {
  background-color: #ddf6dd;
}
.btn-ddffff {
  background-color: #ddffff;
}
.btn-ddeeff {
  background-color: #ddeeff;
}
.btn-ddddff {
  background-color: #ddddff;
}
.btn-ffddf6 {
  background-color: #ffddf6;
}
</style>
