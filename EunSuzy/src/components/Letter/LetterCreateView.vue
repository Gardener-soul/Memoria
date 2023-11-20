<template>
  <div>
    <p>편지 작성</p>
    <textarea v-model="content" placeholder="마음을 적어주세요" rows="10"></textarea>

    
  <div class="color-selection-container">
    <button @click="toggleFontColorPicker">글씨 색상 선택</button>
    <button @click="toggleBgColorPicker">배경 색상 선택</button>
  </div>

    <div class="btn-group" v-if="showFontColorPicker">
      <button
        v-for="color in colors"
        :key="color.name"
        :class="['btn', `btn-${color.name}`, { 'btn-selected': selectedFontColor === color.name }]"
        @click="selectFontColor(color.name)"
      >
        {{ color.label }}
      </button>
    </div>
    <div class="btn-bg-group" v-if="showBgColorPicker">
      <button
        v-for="bgColor in bgColors"
        :key="bgColor.name"
        :class="['btn', `btn-${bgColor.name}`, { 'btn-selected': selectedBgColor === bgColor.name }]"
        @click="selectBgColor(bgColor.name)"
      >
        {{ bgColor.label }}
      </button>
    </div>
    
    <div class="send-button-container">
      <button @click="checkContentAndSend">편지 보내기</button>
    </div>

    <div v-if="showModal" class="modal">
      <div class="modal-content">
        <span class="close" @click="showModal = false">&times;</span>
        <p>편지 내용을 입력해 주세요.</p>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { useRoute } from "vue-router";
import axios from "axios";

const route = useRoute();

const rollingPaperNo = route.query.id;
const writerNo = route.query.userNo;
const content = ref("");

const selectedFontColor = ref('');
const selectedBgColor = ref('');

const colors = [    
    { name: 'black', label: '검정' },
    { name: 'blue', label: '파랑' },
    { name: 'purple', label: '보라' },
    { name: 'green', label: '초록' },
    { name: 'navy', label: '네이비' } 
  ];
const bgColors = [     
    { name: 'white', label: '흰색' },   
    { name: 'black', label: '검정' },
    { name: 'blue', label: '파랑' },
    { name: 'purple', label: '보라' },
    { name: 'green', label: '초록' },
    { name: 'navy', label: '네이비' } 
  ];

  const selectBgColor = (bgColor) => {
    selectedBgColor.value = bgColor;
  };

  const selectFontColor = (fColor) => {
    selectedFontColor.value = fColor;
  };

const showFontColorPicker = ref(false);
const showBgColorPicker = ref(false);

const toggleFontColorPicker = () => {
  showFontColorPicker.value = !showFontColorPicker.value;
  if (showFontColorPicker.value) {
    showBgColorPicker.value = false;
  }
};

const toggleBgColorPicker = () => {
  showBgColorPicker.value = !showBgColorPicker.value;
  if (showBgColorPicker.value) {
    showFontColorPicker.value = false;
  }
};

const showModal = ref(false);

const checkContentAndSend = () => {
  if (content.value.trim() === '') {
    showModal.value = true;
  } else {
    sendLetter();
  }
};

function sendLetter() {
  const letterData = {
    rollingPaperNo,
    writerNo,
    content: content.value,
    fontColor: selectedFontColor.value,
    backColor: selectedBgColor.value
  };

  console.log("전송 데이터:", letterData); // 전체 데이터 로그

  axios.post('http://localhost:8080/letter/write', letterData)
    .then(response => {
      console.log("편지 전송 성공", response);
    })
    .catch(error => {
      console.error("편지 전송 실패", error);
    });
}
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

.btn-group, .btn-bg-group {
  display: flex;
  justify-content: center; /* 버튼들을 중앙 정렬 */
  flex-wrap: wrap; /* 버튼이 너무 많으면 다음 줄로 넘김 */
  margin-bottom: 10px;
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


.btn-white { background-color: white; color: black; }
.btn-black { background-color: black; color: white; }
.btn-blue { background-color: blue; color: white; }
.btn-purple { background-color: purple; color: white; }
.btn-green { background-color: green; color: white; }
.btn-navy { background-color: navy;  color: white; }
</style>