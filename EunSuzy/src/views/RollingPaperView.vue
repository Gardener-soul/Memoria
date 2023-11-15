<template>
  <div>
    <h2>Memoria RollingPaper</h2>
    <table border="1">
      <tr>
        <td>롤페 번호</td>
        <td>롤페 주인</td>
        <td>롤페 제목</td>
        <td>작성한 사람 수</td>
        <td>작성 일자</td>
      </tr>
    </table>
    <button @click="prevPage">이전</button>
    <span>페이지 {{ currentPage }} / {{ totalPages }}</span>
    <button @click="nextPage">다음</button>
  </div>
</template>

<script>
export default {
  data() {
    return {
      items: [], // 전체 데이터, 나중에 연동
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
    prevPage() {
      if (this.currentPage > 1) this.currentPage--;
    },
    nextPage() {
      if (this.currentPage < this.totalPages) this.currentPage++;
    },
  },
};
</script>

<style scoped></style>
