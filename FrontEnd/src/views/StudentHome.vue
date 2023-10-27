<template>
    <el-container style="height: 100%;">
        <el-aside>
            <el-menu
                default-active="/student/selectCourse"
                class="el-menu-vertical-demo"
                style="height: 100%;"
                router
            >
                <el-menu-item index="/student/selectCourse">
                    <el-icon><Expand /></el-icon>
                    <span>选课</span>
                </el-menu-item>
                <el-menu-item index="/student/dropCourse">
                    <el-icon><Delete /></el-icon>
                    <span>退课</span>
                </el-menu-item>
                <el-menu-item index="/student/courseTable">
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