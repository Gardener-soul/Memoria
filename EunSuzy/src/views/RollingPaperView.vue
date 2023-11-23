<template>
  <div>
    <h2>Memoria RollingPaper</h2>
    <table border="1">
      <tr>
        <th id="one">번호</th>
        <th id="two">편지함 주인</th>
        <th id="three">제목</th>
        <th id="four">받은 편지</th>
        <th id="five">작성 일자</th>
        <th id="six">수정 / 삭제</th>
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
          <div v-else id="notMine">내 계정 아니면 안 보이지롱</div>
        </td>
      </tr>
    </table>
    <div class="pagination">
      <button @click="prevPage" :disabled="currentPage === 1">이전</button>
      <span id="page">페이지 {{ currentPage }} / {{ totalPages }}</span>
      <button @click="nextPage" :disabled="currentPage === totalPages">다음</button>
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
  font-family: 'Gaegu', sans-serif;
  font-size: 30px;
  color: #bf94e4;
  margin: 20px 10px;
  margin-left: 300px;
}

table {
  width: 70%;
  margin-left: auto;
  margin-right: auto;
  border: none;
  border-collapse: collapse; /* 테이블 테두리가 겹치지 않도록 설정 */
  margin-bottom: 20px;
  background-color: white;
}

tr:first-child {
  /* border: none; */
  background-color: #ffc0cb;
  color: #fefefe;
  transition: background-color 0.3s;
}

th,
td {
  padding: 8px;
  border: none;
  border-bottom: 1px solid #ffdbe6;
  text-align: center;
}

#one {
  width: 5%;
}
#two {
  width: 15%;
}
#three {
  width: 30%;
}
#four {
  width: 15%;
}
#five {
  width: 15%;
}
#six {
  width: 20%;
}

.clickable {
  cursor: pointer;
  text-decoration: underline;
  color: #bf94e4;
}

.pagination {
  display: flex;
  justify-content: center;
  align-items: center;
}

button {
  margin: 5px;
  padding: 8px 16px;
  font-weight: bold;
  background-color: #d8b6e2;
  color: white;
  border: none;
  border-radius: 10px;
  cursor: pointer;
  box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.2);
  transition: all 0.3s;
}

button:hover:not(:disabled) {
  background-color: #bf94e4;
  box-shadow: 4px 4px 10px rgba(0, 0, 0, 0.3);
}

/* 비활성화 버튼 */
button:disabled {
  background-color: #d8b6e2;
  cursor: not-allowed;
}

#page {
  margin: 0px 10px;
}

#notMine {
  color: #909090;
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
