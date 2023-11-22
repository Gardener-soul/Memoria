<template>
  <div>
    <table border="1">
      <tr>
        <th>편지 번호</th>
        <th>편지 주인</th>
        <th>편지 제목</th>
        <th>작성 일자</th>
        <th>보기 / 수정 / 삭제</th>
      </tr>
      <tr v-for="(item, index) in items" :key="index">
        <td>{{ item.letterNo }}</td>
        <td>{{ item.writerNo }}</td>
        <td @click="navigateToRollingPaper(item.letterNo)" class="clickable">
          {{ item.title }}
        </td>
        <td>{{ date(item.regDate) }}</td>
        <td>
          <button>수정</button>
          <button @click="deleteRP(item.letterNo)">삭제</button>
        </td>
      </tr>
    </table>
  </div>
</template>

<script setup>
import { ref, computed, onMounted } from "vue";
import axios from "axios";
import { useUserStore } from "@/stores/user.js";
import { useRouter } from "vue-router";

const router = useRouter();
const useStore = useUserStore();
const items = ref([]);

const fetchUsers = () => {
  axios
    .get("http://localhost:8080/letter/list")
    .then((response) => {
      items.value = response.data;
      console.log(items.value);
    })
    .catch((error) => console.error(error));
};

const deleteRP = (letterNo) => {
  axios
    .delete(`http://localhost:8080/letter/delete/${letterNo}`) // 1초 타임아웃
    .then(() => {
      items.value = items.value.filter(
        (item) => item.rollingPaperNo !== rollingPaperNo
      );
    })
    .catch((error) => {
      console.error("Error deleting rolling paper:", error);
    });
};

onMounted(fetchUsers);
</script>

<style scoped></style>
