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
      </tr>
      <tr v-for="(item, index) in paginatedData" :key="index">
        <td>{{ item.rollingPaperNo }}</td>
        <td>{{ item.owner }}</td>
        <td @click="navigateToRollingPaper(item.rollingPaperNo)" class="clickable">
          {{ item.title }}
        </td>
        <td>{{ item.writerCount }}</td>
        <td>{{ item.regDate }}</td>
      </tr>
    </table>
    <div class="pagination">
      <button @click="prevPage" :disabled="currentPage === 1">이전</button>
      <span>페이지 {{ currentPage }} / {{ totalPages }}</span>
      <button @click="nextPage" :disabled="currentPage === totalPages">다음</button>
    </div>
  </div>
</template>


<script>
import axios from 'axios';

export default {
  data() {
    return {
      items: [],
      currentPage: 1,
      itemsPerPage: 10,
    };
  },
  computed: {
    totalPages() {
      return Math.ceil(this.items.length / this.itemsPerPage);
    },
    paginatedData() {
      let start = (this.currentPage - 1) * this.itemsPerPage;
      let end = start + this.itemsPerPage;
      return this.items.slice(start, end);
    },
  },
  methods: {
    navigateToRollingPaper(rollingPaperId) {
    this.$router.push(`/rollingPaper/${rollingPaperId}`);
  },
    fetchRollingPapers() {
      axios
        .get("http://localhost:8080/rollingPaper/list")
        .then(response => {
          this.items = response.data;
        })
        .catch(error => {
          console.error(error);
        });
    },
    prevPage() {
      if (this.currentPage > 1) this.currentPage--;
    },
    nextPage() {
      if (this.currentPage < this.totalPages) this.currentPage++;
    },
  },
  mounted() {
    this.fetchRollingPapers();
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

th, td {
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
</style>

