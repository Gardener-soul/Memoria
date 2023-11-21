<template>
  <div>
    <h2>Memoria RollingPaper</h2>
    <table border="1">
      <tr>
        <th>롤페 번호</th>
        <th>롤페 주인</th>
        <th>롤페 제목</th>
        <th>작성한 사람 수</th>
        <th>작성 일자</th>
        <th>수정 / 삭제</th>
      </tr>
      <tr v-for="(item, index) in paginatedData" :key="index">
        <td>{{ item.rollingPaperNo }}</td>
        <td>{{ item.userName }}</td>
        <td
          @click="navigateToRollingPaper(item.rollingPaperNo)"
          class="clickable"
        >
          {{ item.title }}
        </td>
        <td>{{ item.writerCount }}</td>
        <td>{{ date(item.regDate) }}</td>
        <td>
          <div v-if="item.userName === useStore.userName">
            <button @click="showEditModal(item)">수정</button>
            <button @click="deleteRP(item.rollingPaperNo)">삭제</button>
          </div>
        </td>
      </tr>
    </table>
    <div class="pagination">
      <button @click="prevPage" :disabled="currentPage === 1">이전</button>
      <span>페이지 {{ currentPage }} / {{ totalPages }}</span>
      <button @click="nextPage" :disabled="currentPage === totalPages">
        다음
      </button>
    </div>
    <div v-if="showModal" class="modal">
      <div class="modal-content">
        <span class="close" @click="showModal = false">&times;</span>
        <p>우리의 추억은 되돌릴 수 없지만 제목은 수정 가능합니다.</p>
        <input v-model="editingItem.title" />
        <br />
        <button @click="updateRP">수정</button>
      </div>
    </div>
  </div>
</template>

<script>
import { ref, computed, onMounted } from "vue";
import axios from "axios";
import { useUserStore } from "@/stores/user.js";
import { useRouter } from "vue-router";

export default {
  setup() {
    const router = useRouter();
    const useStore = useUserStore();
    const items = ref([]);
    const currentPage = ref(1);
    const itemsPerPage = ref(10);
    const showModal = ref(false);
    const editingItem = ref(null);

    // 계산된 총 페이지 수
    const totalPages = computed(() => {
      return Math.ceil(items.value.length / itemsPerPage.value);
    });

    // 계산된 페이지네이션 데이터
    const paginatedData = computed(() => {
      const start = (currentPage.value - 1) * itemsPerPage.value;
      const end = start + itemsPerPage.value;
      return items.value.slice(start, end);
    });

    // 롤링페이퍼 목록 가져오기
    const fetchRollingPapers = () => {
      axios
        .get("http://localhost:8080/rollingPaper/list")
        .then((response) => {
          items.value = response.data;
        })
        .catch((error) => console.error(error));
    };

    // 롤링페이퍼 상세보기로 이동
    const navigateToRollingPaper = (rollingPaperId) => {
      router.push(`/rollingPaper/${rollingPaperId}`);
    };

    // 이전 페이지로 이동
    const prevPage = () => {
      if (currentPage.value > 1) currentPage.value--;
    };

    // 다음 페이지로 이동
    const nextPage = () => {
      if (currentPage.value < totalPages.value) currentPage.value++;
    };

    // 수정 모달 표시
    const showEditModal = (item) => {
      editingItem.value = { ...item };
      showModal.value = true;
    };

    // 롤링페이퍼 수정
    const updateRP = () => {
      console.log(editingItem.value);
      axios
        .put(`http://localhost:8080/rollingPaper/update`, editingItem.value)
        .then(() => {
          const item = items.value.find(
            (i) => i.rollingPaperNo === editingItem.value.rollingPaperNo
          );
          if (item) {
            item.title = editingItem.value.title;
          }
          showModal.value = false;
        })
        .catch((error) =>
          console.error("Error updating rolling paper:", error)
        );
    };

    //목록 삭제
    const deleteRP = (rollingPaperNo) => {
      axios
        .delete(`http://localhost:8080/rollingPaper/delete/${rollingPaperNo}`) // 1초 타임아웃
        .then(() => {
          items.value = items.value.filter(
            (item) => item.rollingPaperNo !== rollingPaperNo
          );
        })
        .catch((error) => {
          console.error("Error deleting rolling paper:", error);
        });
    };

    function date(regDate) {
      return regDate[0] + " / " + regDate[1] + " / " + regDate[2];
    }

    // 컴포넌트 마운트 시 롤링페이퍼 목록 가져오기
    onMounted(fetchRollingPapers);

    return {
      items,
      currentPage,
      totalPages,
      paginatedData,
      showModal,
      editingItem,
      fetchRollingPapers,
      navigateToRollingPaper,
      prevPage,
      nextPage,
      showEditModal,
      updateRP,
      deleteRP,
      date,
      useStore,
    };
  },
};
</script>

<style scoped>
.container {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 20px;
  background-color: #fffaf0; /* 배경색 변경 */
  border-radius: 15px;
  box-shadow: 5px 5px 10px rgba(0, 0, 0, 0.2);
  width: 100%;
  max-width: 700px; /* 컨테이너 최대 너비 */
}

h2 {
  color: #bf94e4; /* 제목 색상 */
  margin-bottom: 20px; /* 여백 증가 */
}

table {
  width: 100%;
  border-collapse: collapse;
  margin-bottom: 20px;
  background-color: white; /* 테이블 배경색 추가 */
}

th,
td {
  border: 1px solid #bf94e4;
  padding: 8px;
  text-align: center;
}

.clickable {
  cursor: pointer;
  text-decoration: underline;
  color: #bf94e4; /* 클릭 가능한 텍스트 색상 */
}
.pagination {
  display: flex;
  justify-content: flex-end; /* 버튼을 우측으로 정렬 */
  align-items: center;
  width: 100%;
}
button {
  background-color: #bf94e4; /* 버튼 배경색 */
  color: white; /* 버튼 텍스트 색상 */
  border: none; /* 테두리 없음 */
  padding: 8px 16px; /* 버튼 패딩 */
  border-radius: 4px; /* 둥근 버튼 모서리 */
  margin: 5px; /* 버튼 간격 */
  cursor: pointer; /* 커서 변경 */
  font-weight: bold; /* 글자 두껍게 */
}

button:disabled {
  background-color: #ccc; /* 비활성화 버튼 색상 */
  cursor: not-allowed; /* 비활성화 커서 변경 */
}

button:hover:not(:disabled) {
  background-color: #a476c1; /* 호버 시 버튼 색상 변경 */
}
.modal {
  display: block; /* Hidden by default */
  position: fixed; /* Stay in place */
  z-index: 1; /* Sit on top */
  left: 0;
  top: 0;
  width: 100%; /* Full width */
  height: 100%; /* Full height */
  overflow: auto; /* Enable scroll if needed */
  background-color: rgb(0, 0, 0); /* Fallback color */
  background-color: rgba(0, 0, 0, 0.4); /* Black w/ opacity */
}

.modal-content {
  background-color: #fefefe;
  margin: 15% auto; /* 15% from the top and centered */
  padding: 20px;
  border: 1px solid #888;
  width: 80%; /* Could be more or less, depending on screen size */
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
</style>
