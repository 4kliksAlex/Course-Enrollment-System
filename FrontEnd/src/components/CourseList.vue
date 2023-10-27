<template>
    <el-table style="width: 100%;" :data=courseList>
        <el-table-column prop="courseId" label="课程号" show-overflow-tooltip/>
        <el-table-column prop="name" label="课程名" show-overflow-tooltip/>
        <el-table-column prop="uid" label="教师号" show-overflow-tooltip/>
        <el-table-column prop="remarks" label="备注" show-overflow-tooltip/>
        <el-table-column prop="selectedNum" label="已选人数" show-overflow-tooltip/>
        <el-table-column prop="capacity" label="容量" show-overflow-tooltip/>
        <template v-if="btnText">
            <el-table-column label="操作" show-overflow-tooltip>
                <template #default="scope">
                    <el-button @click="emits('btnOpt', scope.row, scope.$index)" type="primary">{{ btnText }}</el-button>
                </template>
            </el-table-column>
        </template>
        <template #empty>
            空
        </template>
    </el-table>
</template>

<script setup>
import {defineProps} from 'vue'
const props = defineProps({
    courseList: {
        type: Array,
        required: true
    },
    btnText: ''
})
const emits = defineEmits(['btnOpt'])
</script>

<style>

</style>