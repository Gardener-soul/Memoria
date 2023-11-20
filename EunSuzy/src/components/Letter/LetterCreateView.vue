<template>
    <div>
        <p>이수지 바보 ㅋ</p>
        <label for="image">이미지</label>
        <input type="file" id="image" @change="appendImage">
        <br>
        <button type="button" @click="submit">전송</button>
    </div>
</template>

<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import axios from "@/util/http-common";

const router = useRouter();
const formData = new FormData();

const appendImage = (e) => {
    formData.append("image", e.target.files[0]);
}

const submit = () => {
    // formData.append("name", name.value);

    axios({
        method: 'post',
        url: 'http://localhost:8080/letter/write',
        headers: { 'Content-Type': 'multipart/form-data' },
        data: formData,
    }).then((response) => {
        if (response.data === "OK") {
            alert("등록 완료")
            router.push('/list');
        } else {
            alert("등록 실패")
        }
    });
}
</script>

<style scoped>
</style>