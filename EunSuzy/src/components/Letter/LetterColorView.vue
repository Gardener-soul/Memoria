<template>
    <div>
      <p>배경 색상</p>
      <hr>
      <div class="btn-group">
        <button
          v-for="bgColor in bgColors"
          :key="bgColor.name"
          :class="['btn', `btn-${bgColor.name}`, { 'btn-selected': selectedBgColor === bgColor.name }]"
          @click="selectBgColor(bgColor.name)"
        >
          {{ bgColor.label }}
        </button>
      </div>
    </div>
  </template>
  
  <script setup>
  import { ref } from 'vue';
  
  const emit = defineEmits(['backgroundSelected']);
  const selectedBgColor = ref('');
  
  const bgColors = [
    { name: 'black', label: '검정' },
    { name: 'blue', label: '파랑' },
    { name: 'purple', label: '보라' },
    { name: 'green', label: '초록' },
    { name: 'navy', label: '네이비' }
  ];
  
  const selectBgColor = (bgColor) => {
    selectedBgColor.value = bgColor;
    emit('backgroundSelected', bgColor);
  };
  </script>
  
  <style>
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
  
.btn-black { background-color: black; color: white; }
.btn-blue { background-color: blue; color: white; }
.btn-purple { background-color: purple; color: white; }
.btn-green { background-color: green; color: white; }
.btn-navy { background-color: navy;  color: white; }
</style>
  