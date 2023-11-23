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
  padding: 10px 15px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-weight: bold;
}

button:hover {
  opacity: 0.8;
}

.view-button {
  background-color: #4caf50;
  color: white;
}

.edit-button {
  background-color: #ffc107;
  color: white;
}

.delete-button {
  background-color: #f44336;
  color: white;
}
</style>
