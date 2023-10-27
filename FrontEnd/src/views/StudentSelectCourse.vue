<template>
    <div style="height: 100%;">
        <el-text>课程号:</el-text>
        <el-input v-model="courseId" placeholder="课程号" style="width: 100px; margin-left: 15px; margin-right: 25px;"></el-input>
        <el-text>课程名:</el-text>
        <el-input v-model="name" placeholder="课程名" style="width: 200px; margin-left: 15px; margin-right: 25px;"></el-input>
        <el-text>教师号:</el-text>
        <el-input v-model="uid" placeholder="教师号" style="width: 100px; margin-left: 15px; margin-right: 25px;"></el-input>
        <el-button type="primary" style="margin-left: 30px;" @click="getCourseList">查询</el-button>
        <div><el-divider></el-divider></div>
        <div style="display: flex; height: 65vh;">
            <!-- 查询到的课程 -->
            <div style="width: 55%; border: 1px solid rgba(145, 142, 142, 0.336);">
                <el-scrollbar height="100%">
                    <course-list :courseList="courseList" btnText="选课" @btnOpt="selectCourse"></course-list>
                </el-scrollbar>
            </div>
            <!-- 课表 -->
            <div style="width: 45%; border: 1px solid rgba(145, 142, 142, 0.336);">
                <course-table :courseTable="courseTable"></course-table>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref } from "vue";
import request from "../request";
import { useUserStore } from "../stores/user";
import CourseTable from "../components/CourseTable.vue";
import CourseList from "../components/CourseList.vue";
import { ElMessage } from "element-plus";

const userStore = useUserStore();

const courseId = ref("")
const uid = ref("")
const name = ref("")
const courseList = ref([]);

function getCourseList(){
    request.get('/student/getCourseList', {courseId: courseId.value, uid: uid.value, name: name.value}).then(res => {
        if(res.data){
            courseList.value = res.data;
        }
    })
}

const courseTable = ref([])
function getCourseTable(){
    request.get('/student/getCourseTable', {uid: userStore.user.uid}).then(res => {
        if(res.data){
            courseTable.value = res.data
        }
    })
}
getCourseTable();

function selectCourse(course){
    request.post('/student/selectCourse', {uid: userStore.user.uid, courseId: course.courseId}).then(res => {
        if(res.data){
            ElMessage.success('选课成功')
            course.selectedNum += 1
            getCourseTable();
        }
    })
}

</script>

<style>

</style>