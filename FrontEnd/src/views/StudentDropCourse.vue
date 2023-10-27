<template>
    <el-scrollbar height="100%">
        <course-list :courseList="courseList" btnText="退课" @btnOpt="dropCourse"></course-list>
    </el-scrollbar>
</template>

<script setup>
import { ElMessage } from 'element-plus';
import { ref } from 'vue';
import CourseList from '../components/CourseList.vue';
import request from '../request';
import { useUserStore } from '../stores/user';

const userStore = useUserStore();

const courseList = ref([]);

function getCourseListOfStudent(){
    request.get('/student/getCourseListOfStudent', {uid: userStore.user.uid}).then(res => {
        if(res.data){
            courseList.value = res.data;
        }
    })
}
getCourseListOfStudent()

function dropCourse(course){
    request.post('/student/dropCourse', {uid: userStore.user.uid, courseId: course.courseId}).then(res => {
        if(res.data){
            ElMessage.success('退课成功');
            getCourseListOfStudent();
        }
    })
}
</script>

<style>

</style>