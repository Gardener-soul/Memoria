<template>
  <div>
    <table border="1">
      <tr>
        <th>User No</th>
        <th>User Id</th>
        <th>User Password</th>
        <th>User Name</th>
        <th>User email</th>
      </tr>
      <tr v-for="(item, index) in items" :key="index">
        <td>{{ item.userNo }}</td>
        <td>{{ item.userId }}</td>
        <td>{{ item.userPwd }}</td>
        <td>{{ item.userName }}</td>
        <td>{{ item.email }}</td>
      </tr>
    </table>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import axios from "axios";

const items = ref([]);

const fetchUsers = () => {
  axios
    .get("http://localhost:8080/user/users")
    .then((response) => {
      items.value = response.data;
      console.log(items.value);
    })
    .catch((error) => console.error(error));
};

onMounted(fetchUsers);
</script>

<style scoped></style>
