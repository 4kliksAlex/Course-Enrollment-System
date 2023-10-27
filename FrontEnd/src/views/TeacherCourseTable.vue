<template>
    <course-table :courseTable="courseTable"/>
</template>

<script setup>
import { ref } from "vue";
import request from "../request";
import CourseTable from "../components/CourseTable.vue";
import { useUserStore } from '../stores/user';

const userStore = useUserStore();

const courseTable = ref([])
function getCourseTable(){
    request.get('/teacher/getCourseTable', {uid: userStore.user.uid}).then(res => {
        if(res.data){
            courseTable.value = res.data
        }
    })
}
getCourseTable();
</script>

<style>

</style>