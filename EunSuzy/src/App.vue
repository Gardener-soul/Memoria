<template>
  <div class="container">
    <HeaderVue />
    <!-- <div class="pollen" id="pollen"></div> -->
    <!-- <div class="pollen" id="pollen"></div> -->
    <div class="pollen" id="pollen"></div>
    <div class="pollen" id="pollen"></div>
    <div class="pollen" id="pollen"></div>
    <div class="pollen" id="pollen"></div>
    <!-- <div class="pollen" id="pollen"></div>
    <div class="pollen" id="pollen"></div>
    <div class="pollen" id="pollen"></div>
    <div class="pollen" id="pollen"></div> -->
    <RouterView class="content" />
  </div>
</template>

<script setup>
import HeaderVue from "@/components/common/HeaderView.vue";

document.addEventListener("mousemove", function (e) {
  const pollenElements = document.querySelectorAll(".pollen");

  pollenElements.forEach((pollen, index) => {
    // 약간의 지연 시간을 추가하여 꽃가루가 마우스를 따라오게 함
    setTimeout(() => {
      // 마우스 위치에 무작위 오프셋을 추가하여 꽃가루가 흩날리게 함
      const offsetX = (Math.random() - 0.5) * 50;
      const offsetY = (Math.random() - 0.5) * 50;

      pollen.style.left = e.pageX + offsetX + "px";
      pollen.style.top = e.pageY + offsetY + "px";
      pollen.style.opacity = 1;

      // 애니메이션 효과 추가
      pollen.style.animation = "float 2s ease-in-out infinite";
    }, index * 100); // 각 꽃가루마다 다른 지연 시간을 주어 자연스럽게 보이도록 함
  });
});
</script>

<style>
#pollen-container {
  position: relative;
}

.pollen {
  position: absolute;
  width: 5px; /* 꽃가루 크기 */
  height: 5px; /* 꽃가루 크기 */
  background-color: magenta; /* 꽃가루 색상 */
  border-radius: 50%; /* 원형으로 만들기 */
  opacity: 0; /* 초기에는 보이지 않게 설정 */
}

/* 애니메이션 효과 */
@keyframes float {
  0%,
  100% {
    transform: translateY(0);
  }
  50% {
    transform: translateY(-20px);
  }
}
</style>
