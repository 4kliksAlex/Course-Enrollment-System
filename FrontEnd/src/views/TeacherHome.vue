<template>
    <el-container style="height: 100%;">
        <el-aside>
            <el-menu
                default-active="/teacher/publishCourse"
                class="el-menu-vertical-demo"
                style="height: 100%;"
                router
            >
                <el-menu-item index="/teacher/publishCourse">
                    <el-icon><Plus /></el-icon>
                    <span>发表课程</span>
                </el-menu-item>
                <el-menu-item index="/teacher/expandCourse">
                    <el-icon><Expand /></el-icon>
                    <span>扩课</span>
                </el-menu-item>
                <el-menu-item index="/teacher/courseTable">
                    <el-icon><Grid /></el-icon>
                    <span>课表</span>
                </el-menu-item>
            </el-menu>
        </el-aside>
        <el-main>
            <router-view></router-view>
        </el-main>
    </el-container>
</template>

<script setup>

</script>

<style>

</style>