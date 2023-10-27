<template>
    <el-scrollbar height="100%">
        <course-list :courseList="courseList" btnText="扩课" @btnOpt="expandCourse"></course-list>
    </el-scrollbar>
</template>

<script setup>
import { ElMessage, ElMessageBox } from 'element-plus';
import { ref } from 'vue';
import CourseList from '../components/CourseList.vue';
import request from '../request';
import { useUserStore } from '../stores/user';

const userStore = useUserStore();

const courseList = ref([]);

function getCourseListOfStudent(){
    request.get('/teacher/getCourseList', {uid: userStore.user.uid}).then(res => {
        if(res.data){
            courseList.value = res.data;
        }
    })
}
getCourseListOfStudent()

function expandCourse(course){
    ElMessageBox.prompt('请输入新容量', '扩课', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        inputPattern: /^\d+$/,
        inputErrorMessage: '请输入一个合法的数字',
    }).then(({ value }) => {
        request.post('/teacher/expandCourse', {
            uid: userStore.user.uid, 
            courseId: course.courseId,
            newCapacity: value
        }).then(res => {
            if(res.data){
                ElMessage.success('扩课成功');
                getCourseListOfStudent();
            }
        })
    }).catch(() => {
        
    })
    
}
</script>

<style>

</style>