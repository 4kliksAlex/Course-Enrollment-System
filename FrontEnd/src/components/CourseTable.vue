<template>
    <div>
        <el-menu
            default-active="0"
            mode="horizontal"
            @select="changeCurWeek"
        >
            <el-sub-menu index="0">
                <template #title>第{{ Number(curWeek) + 1 }}周</template>
                <el-menu-item index="0">第一周</el-menu-item>
                <el-menu-item index="1">第二周</el-menu-item>
                <el-menu-item index="2">第三周</el-menu-item>
                <el-menu-item index="3">第四周</el-menu-item>
                <el-menu-item index="4">第五周</el-menu-item>
                <el-menu-item index="5">第六周</el-menu-item>
                <el-menu-item index="6">第七周</el-menu-item>
                <el-menu-item index="7">第八周</el-menu-item>
                <el-menu-item index="8">第九周</el-menu-item>
                <el-menu-item index="9">第十周</el-menu-item>
            </el-sub-menu>
        </el-menu>
        <el-table :data="curCourseTable" border height="100%" style="width: 100%; font-size: small;">
            <el-table-column prop="orderNumber" label="序号"/>
            <el-table-column prop="0" label="星期一" show-overflow-tooltip/>
            <el-table-column prop="1" label="星期二" show-overflow-tooltip/>
            <el-table-column prop="2" label="星期三" show-overflow-tooltip/>
            <el-table-column prop="3" label="星期四" show-overflow-tooltip/>
            <el-table-column prop="4" label="星期五" show-overflow-tooltip/>
            <el-table-column prop="5" label="星期六" show-overflow-tooltip/>
            <el-table-column prop="5" label="星期日" show-overflow-tooltip/>
            <template #empty>
                暂无课程
            </template>
        </el-table>
    </div>
</template>

<script setup>
import {computed, defineProps, ref} from 'vue'

const props = defineProps({
    courseTable: {
        type: Array,
        required: true
    }
})

let curWeek = ref(0); // 第几周


function changeCurWeek(key){
    curWeek.value = key;
}

let curCourseTable = computed(() => {
    let weekCourseTable = props.courseTable[curWeek.value];
    let colCourseTable = [];
    if(weekCourseTable)
    // 12个对象
    for(let i = 0; i < 12; ++i){
        colCourseTable[i] = {};
        colCourseTable[i]['orderNumber'] = `第${i + 1}节`
        // 星期1 - 星期7
        for(let j = 0; j < 7; ++j){
            colCourseTable[i][j] = weekCourseTable[j][i];
        }
    }
    return colCourseTable;
})



</script>

<style>

</style>