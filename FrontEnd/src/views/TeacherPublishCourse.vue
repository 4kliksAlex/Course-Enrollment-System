<template>
    <div style="display: flex;">
        <div style="width: 55%; border: 1px solid rgba(145, 142, 142, 0.336); padding-left: 20px; padding-top: 20px;">
            <!-- 课程名 -->
            <el-text>课程名: </el-text>
            <el-input v-model="name" placeholder="课程名" style="width: 200px; margin-left: 20px;"></el-input>
            <div style="height: 20px;"></div>
            <!-- 容量 -->
            <el-text>容量:</el-text>
            <el-input v-model="capacity" type="number" placeholder="容量" style="width: 200px; margin-left: 40px;"></el-input>
            <div style="height: 20px;"></div>
            <!-- 上课周 -->
            <el-text>上课时间-周:</el-text>
            <el-select
                v-model="timeWeek"
                multiple
                placeholder="上课时间-周"
                style="width: 500px; margin-left: 20px;"
            >
                <el-option label="第一周" :value="0"/>
                <el-option label="第二周" :value="1"/>
                <el-option label="第三周" :value="2"/>
                <el-option label="第四周" :value="3"/>
                <el-option label="第五周" :value="4"/>
                <el-option label="第六周" :value="5"/>
                <el-option label="第七周" :value="6"/>
                <el-option label="第八周" :value="7"/>
                <el-option label="第九周" :value="8"/>
                <el-option label="第十周" :value="9"/>
            </el-select>
            <div style="height: 20px;"></div>
            <!-- 上课星期几 -->
            <el-text>上课时间-星期几:</el-text>
            <el-select
                v-model="timeWeekday"
                multiple
                placeholder="上课时间-星期几"
                style="width: 400px; margin-left: 20px;"
            >
                <el-option label="周一" :value="0"/>
                <el-option label="周二" :value="1"/>
                <el-option label="周三" :value="2"/>
                <el-option label="周四" :value="3"/>
                <el-option label="周五" :value="4"/>
                <el-option label="周六" :value="5"/>
                <el-option label="周日" :value="6"/>
            </el-select>
            <div style="height: 20px;"></div>
            <!-- 第几节 -->
            <el-text>上课时间-第几节:</el-text>
            <el-select
                v-model="timeSection"
                multiple
                placeholder="上课时间-第几节"
                style="width: 400px; margin-left: 20px;"
            >
                <el-option label="1" :value="0"/>
                <el-option label="2" :value="1"/>
                <el-option label="3" :value="2"/>
                <el-option label="4" :value="3"/>
                <el-option label="5" :value="4"/>
                <el-option label="6" :value="5"/>
                <el-option label="7" :value="6"/>
                <el-option label="8" :value="7"/>
                <el-option label="9" :value="8"/>
                <el-option label="10" :value="9"/>
                <el-option label="11" :value="10"/>
                <el-option label="12" :value="11"/>
            </el-select>
            <div style="height: 20px;"></div>
            <!-- 生成备注(时间) -->
            <el-text>备注: </el-text>
            <el-input v-model="remarks" placeholder="备注" style="width: 500px; margin-left: 20px;" disabled></el-input>
            <div style="height: 20px;"></div>
            <el-button @click="resetState" type="warning" plain>重置</el-button>
            <el-button @click="publishCourse" type="success" style="margin-left: 20px;" plain>提交</el-button>
        </div>

        <!-- 课表 -->
        <div style="width: 45%; border: 1px solid rgba(145, 142, 142, 0.336);">
            <course-table :courseTable="courseTable"></course-table>
        </div>
    </div>
</template>

<script setup>
import { ElMessage } from "element-plus";
import { computed, ref } from "vue";
import request from "../request";
import { useUserStore } from "../stores/user";
import CourseTable from '/src/components/CourseTable.vue'

const uid = useUserStore().user.uid

const name = ref("")
const capacity=ref(30)
const timeWeek = ref([0, 1, 2, 3, 4, 5, 6, 7, 8, 9])
const timeWeekday = ref([0])
const timeSection = ref([0, 1])
const remarks = computed(() => {
    let rst = '';
    if(timeWeek.value.length != 10 && timeWeek.value.length != 0){
        rst = `${timeWeek.value[0] + 1}`;
        for(let i = 1; i < timeWeek.value.length; ++i){
            rst = rst + `,${timeWeek.value[i] + 1}`
        }
        rst += '周 ';
    }
    if(timeWeekday.value.length != 0){
        rst += `周${timeWeekday.value[0] + 1}`
        for(let i = 1; i < timeWeekday.value.length; ++i){
            rst += `,${timeWeekday.value[i] + 1}`
        }
        rst +='  ';
    }
    if(timeSection.value.length != 0){
        rst += `${timeSection.value[0] + 1}`
        for(let i = 1; i < timeSection.value.length; ++i){
            rst += `,${timeSection.value[i] + 1}`
        }
        rst += '节';
    }
    
    return rst;
})

function resetState(){
    name.value = "";
    capacity.value = 30;
    timeWeek.value = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9];
    timeWeekday.value = [0];
    timeSection.value = [0, 1];
}

function getBitStr(length, arr){
    let s = [];
    for(let i = 0; i < length; ++i){
        s.push('0');
    }
    for(let item of arr){
        s[item] = '1';
    }
    return s.join('');
}

function publishCourse(){
    // 检查参数
    if(!name.value){
        ElMessage.warning('请输入课程名');
        return;
    }
    if(!capacity.value){
        ElMessage.warning('请输入容量');
        return;
    }
    if(!timeWeek.value.length){
        ElMessage.warning('请输入上课时间-周');
        return;
    }
    if(!timeWeekday.value.length){
        ElMessage.warning('请输入上课时间-星期几');
        return;
    }
    if(!timeSection.value.length){
        ElMessage.warning('请输入上课时间-第几节');
        return;
    }
    request.post('/teacher/publishCourse', {
        uid, 
        name: name.value, 
        capacity: capacity.value, 
        remark: remarks.value,
        week: getBitStr(10, timeWeek.value),
        weekday: getBitStr(7, timeWeekday.value),
        section: getBitStr(12, timeSection.value)
    }).then(res => {
        if(res.data){
            ElMessage.success('添加成功');
            getCourseTable();
            resetState();
        }
    })
}

const courseTable = ref([])
function getCourseTable(){
    request.get('/teacher/getCourseTable', {uid}).then(res => {
        if(res.data){
            courseTable.value = res.data
        }
    })
}
getCourseTable()

</script>

<style>

</style>