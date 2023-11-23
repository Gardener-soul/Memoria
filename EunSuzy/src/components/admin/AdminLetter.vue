<template>
  <div>
    <table border="1">
      <tr>
        <th>편지 번호</th>
        <th>편지 주인</th>
        <th>편지 작성자</th>
        <th>편지 내용</th>
        <th>작성 일자</th>
        <th>보기 / 수정 / 삭제</th>
      </tr>
      <tr v-for="(item, index) in items" :key="index">
        <td>{{ item.letterNo }}</td>
        <td>{{ master(item.title) }}</td>
        <td>{{ item.userName }}</td>
        <td>{{ item.content }}</td>
        <td>{{ item.regDate }}</td>
        <td>
          <button @click="goLetter(item.letterNo)">보기</button>
          <button @click="goModi(item.letterNo)">수정</button>
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
    .get("http://localhost:8080/letter/admin/list")
    .then((response) => {
      items.value = response.data;
    })
    .catch((error) => console.error(error));
};

function master(title) {
  return title.substr(0, 3);
}

const deleteRP = (letterNo) => {
  axios
    .delete(`http://localhost:8080/letter/delete/${letterNo}`)
    .then(() => {
      items.value = items.value.filter((item) => item.letterNo !== letterNo);
    })
    .catch((error) => {
      console.error("Error deleting rolling paper:", error);
    });
};

function goLetter(letterNo) {
  router.push(`/myletter/${letterNo}`);
}

function goModi(letterNo) {
  router.push(`/letterupdate/${letterNo}`);
}
onMounted(fetchUsers);
</script>

<style scoped>
table {
  width: 100%;
  border-collapse: collapse;
  background-color: #282c34;
  color: #ffffff;
  text-align: left;
}

th,
td {
  padding: 15px;
  border-bottom: 1px solid #444;
}

th {
  background-color: #3a3f47;
  font-weight: bold;
}

tr:nth-child(even) {
  background-color: #32363e;
}

tr:hover {
  background-color: #3a3f47;
}

button {
  padding: 8px 12px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-weight: bold;
  transition: background-color 0.3s ease, transform 0.2s ease;
  margin-right: 5px;
  color: black;
}

/* 호버 효과 */
button:hover {
  transform: translateY(-2px);
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
}

/* 보기 버튼 - 초록색 */
.view-button {
  background-color: #28a745;
}

.view-button:hover {
  background-color: #218838;
}

/* 수정 버튼 - 파란색 */
.edit-button {
  background-color: #007bff;
}

.edit-button:hover {
  background-color: #0069d9;
}

/* 삭제 버튼 - 빨간색 */
.delete-button {
  background-color: #dc3545;
}

.delete-button:hover {
  background-color: #c82333;
}
</style>
