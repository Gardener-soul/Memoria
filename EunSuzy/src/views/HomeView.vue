<template>
  <div>
    <!-- <div class="headerLower-container">
      <canvas></canvas>
    </div> -->
    <div class="container">
      <div class="memoria-title">
        <h1>Memoria</h1>
      </div>
      <div>
        <button
        @click="handleRollingPaperStart"
        class="start-rolling-paper-button"
      >
      롤링페이퍼 시작하기
    </button>
    </div>
    <!-- 로그인 요구 모달 -->
    <div v-if="showLoginModal" class="modal-overlay" @click="closeLoginModal">
      <div class="modal-content" @click.stop>
        <span class="close-button" @click="closeLoginModal">&times;</span>
        <p>롤링 페이퍼를 작성하려면 로그인이 필요합니다.</p>
        <p>계정이 없다면 회원가입을 진행해주세요</p>
        <button class="confirm-button" @click="goToLogin">로그인</button>
        <button class="confirm-button" @click="goToSignUp">회원가입</button>
      </div>
    </div>
    <footer class="footer">
      <div class="footer-content">
        <span>MEMORIA © 2023. All rights reserved.</span> |
        <span>연락처: info@rollingpaper.com</span>
        <div class="social-links">
          <a href="#" target="_blank">Facebook</a> |
          <a href="#" target="_blank">Twitter</a> |
          <a href="#" target="_blank">Instagram</a>
        </div>
      </div>
    </footer>
  </div>
  <button @click="toggleSnowEffect" class="snow-toggle-button">
    {{ snowEffect ? '눈 효과 끄기' : '눈 효과 켜기' }}
  </button>
  </div>
</template>

<script setup>
import { ref, onMounted,onUnmounted, watch } from "vue";
import { useRouter } from "vue-router";
import { useUserStore } from "@/stores/user.js";

const userStore = useUserStore();
const router = useRouter();
const showLoginModal = ref(false);
const snowEffect = ref(true);

function toggleSnowEffect() {
  snowEffect.value = !snowEffect.value; // 상태 토글
}

function handleRollingPaperStart() {
  if (userStore.isLoggedIn) {
    router.push("/create");
  } else {
    showLoginModal.value = true;
  }
}

function closeLoginModal() {
  showLoginModal.value = false;
}

function goToLogin() {
  router.push("/login");
}

function goToSignUp() {
  router.push("/signup");
}

// onMounted(()=>{    
//   var c = document.getElementsByTagName("canvas")[0];
//   var b = document.body;
//   var a = c.getContext("2d");
//   let D = b.style;
//   let f = window;
//   D.margin = 0;
//   D.overflow = "hidden";
//   let i = f.innerWidth;
//   let j = f.innerHeight;
//   c.width = i;
//   c.height = j;

//   let k = Math;
//   let l = k.random;
//   let n = k.sin;
//   let p = k.floor;
//   let q = 20;
//   let r = [];
//   let s = k.PI;

//   function t(d) {
//     d.b = d.x = (l() * i - i * 0.5) * q;
//     d.c = d.y = (l() * j - j * 0.2) * q;
//     d.a = q;
//     d.d = l();
//   }
//   for (var v = 0, w; v < 300; v++) {
//     w = {};
//     t(w);
//     r.push(w);
//   }
//   setInterval(function () {
//     a.fillStyle = "rgba(64,64,64,.8)";
//     a.fillRect(0, 0, i, j);
//     for (var d = 0; d < 300; d++) {
//       var b = r[d],
//         o = n(s) * 32 - 16,
//         u = b.x / b.a,
//         e = u + i / 2 - o,
//         g = (o = b.y / b.a),
//         m = l() * 360,
//         x = b.a > q - 5 ? (q - b.a) / 5 : 1,
//         h = q - b.a - 4;
//       a.fillStyle = "hsla(" + p(m) + ",50%,95%," + x + ")";
//       a.beginPath();
//       if (b.a < q - 6) {
//         a.save();
//         a.translate(e, g);
//         a.rotate(b.d * (d % 2 ? 1 : -1) * ((d % 3) / 2 + 0.1));
//         g = 0;
//         e = h / ((d % 4) + 2);
//         for (m = h / ((d % 3) + 1); g < 6; g++) {
//           a.lineTo(-e, m);
//           a.lineTo(0, h);
//           a.lineTo(e, m);
//           a.lineTo(0, 0);
//           a.rotate(k.PI / 3);
//         }
//         a.fill();
//         if (d % 2 == 0) {
//           g = 0;
//           for (e = h / ((d % 5) + 1); g < 6; g++) {
//             a.beginPath();
//             a.moveTo(0, h);
//             a.lineTo(-e, h - e);
//             a.lineTo(0, h - e);
//             a.lineTo(e, h - e);
//             a.fill();
//             a.rotate(k.PI / 3);
//           }
//         }
//         a.restore();
//       } else {
//         a.arc(e, g, (1 / b.a) * 5 + 1, 0, k.PI * 2, true);
//         a.fill();
//       }
//       b.b = u;
//       b.c = o;
//       b.a -= 0.15;
//       b.d += l() / 5;
//       if (b.a < 0.15 || b.b < -i / 2 || b.b > i / 2 || b.c > j) t(b);
//       s += k.PI / 2;
//     }
//   }, 25);
// });

</script>

<style scoped>
* {
  box-sizing: border-box;
  margin: 0;
  padding: 0;
  font-weight: 300;
}
body {
  font-family: "Comic Sans MS", cursive, sans-serif;
  background-color: #f0e6d6;
  margin: 0;
  padding: 0;
  /* overflow: hidden; 스크롤바를 숨깁니다 */
}
.container {
  position: relative; /* 콘테이너에 상대적 위치를 지정합니다 */
  z-index: 0; /* 캔버스보다 높은 z-index를 지정합니다 */
  display: flex;
  flex-direction: column;
  align-items: center;
  min-height: 90vh;
}

.headerLower-container {
  position: relative; /* 캔버스 컨테이너에 상대적 위치를 지정합니다 */
}

canvas {
  position: absolute; /* 캔버스를 절대적 위치에 놓습니다 */
  top: 0;
  left: 0;
  width: 100vw; /* 캔버스의 너비를 뷰포트 너비로 설정합니다 */
  height: 100vh; /* 캔버스의 높이를 뷰포트 높이로 설정합니다 */
  z-index: 1; /* 페이지의 다른 내용 뒤에 캔버스가 오도록 z-index를 설정합니다 */
}

.memoria-title {
  font-family: "Gaegu", sans-serif;
  font-size: 30px;
  margin-top: 250px;
}

h1 {
  margin: 0px;
}

.memoria-title,
.start-rolling-paper-button {
  display: block;
}
.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: rgba(0, 0, 0, 0.6);
  display: flex;
  justify-content: center;
  align-items: center;
}

.modal-content {
  background-color: white;
  padding: 20px;
  border-radius: 15px;
  text-align: center;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  position: relative;
  width: 80%;
  max-width: 400px;
}

.close-button {
  position: absolute;
  top: 10px;
  right: 10px;
  font-size: 25px;
  cursor: pointer;
}

.confirm-button {
  background-color: #d8b6e2;
  color: white;
  padding: 10px 20px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  margin-top: 15px;
  margin-right: 10px;
  transition: background-color 0.3s;
}

.confirm-button:hover {
  background-color: #bf94e4;
}
.footer {
  position: fixed; /* 화면 하단에 고정 */
  bottom: 0; /* 하단 0의 위치에 */
  width: 100%; /* 전체 너비 */
  background-color: #d8b6e2; /* 배경색 */
  color: white; /* 글자색 */
  text-align: center; /* 텍스트 가운데 정렬 */
  padding: 10px 0; /* 상하 패딩 */
}
.footer-content span,
.social-links a {
  margin: 5px 0;
  color: white;
  text-decoration: none;
}

.start-rolling-paper-button {
  font-size: 18px;
  font-weight: bold;
  width: 100%;
  padding: 15px 30px;
  background-color: #bf94e4;
  color: white;
  border: none;
  border-radius: 10px;
  cursor: pointer;
  box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.2);
  transition: all 0.3s;
}

.start-rolling-paper-button:hover {
  background-color: #ffb8c6;
  box-shadow: 4px 4px 10px rgba(0, 0, 0, 0.3);
  transform: scale(1.1);
}

</style>
