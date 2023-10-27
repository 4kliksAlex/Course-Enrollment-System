<template>
  <div class="user-header">
    <div style="float: right;">
        <el-divider direction="vertical" />
        <el-tag v-if="!userStore.logged">未登录</el-tag>
        <template v-else>
            <el-tag v-if="userStore.user.role=='student'">学生端</el-tag>
            <el-tag v-else>教师端</el-tag>
        </template>
        <el-divider direction="vertical" />
        <el-button v-if="!userStore.logged" @click="userStore.toLogin()" type="primary" round>登录</el-button>
        <el-popconfirm v-else title="是否注销?" confirm-button-text="确定" cancel-button-text="取消" @confirm="userStore.user = null">
            <template #reference>
                <el-button type="danger" round>注销</el-button>
            </template>
        </el-popconfirm>
        <el-divider direction="vertical" />
        
        <el-text v-if="userStore.logged" style="float: right;">
            {{ userStore.user.name }}
        </el-text>
        <el-divider direction="vertical"  style="float: right;"/>
        <span style="float: right; margin-top: 5px;">
            <el-avatar :size="40"
                src="/src/assets/default.jpg"
            />
        </span>
        
    </div>
    <!-- <el-text style="float: left;">
        <h1 style="margin-left: 50px; letter-spacing: 7px;">
           学生选课管理系统
        </h1>
    </el-text> -->
    
    <!-- 登录 -->
    <el-dialog
        v-model="userStore.logining"
        append-to-body
        :close-on-click-modal="false"
        :close-on-press-escape="false"
        align-center
        destroy-on-close
        @close="resetLoginDialogState"
        draggable
        width="350px"
        style="padding: 0 40px; border-radius: 10px; opacity: 0.9;"
    >
        <div style="height: 150px; text-align: center;">
            <el-avatar :size="100"
                src="/src/assets/default.jpg"
            />
        </div>
        <!-- 学号 -->
        <el-input 
            v-model="name" 
            placeholder="姓名"
            prefix-icon="User"
            style="height: 40px;"
        />
    
        <div style="height: 7px;"></div>
        <!-- 密码 -->
        <el-input 
            v-model="password" 
            placeholder="密码"
            prefix-icon="Lock"
            style="height: 40px;"
            show-password
        />
        <div style="display: flex; margin-top: 17px;">
            <el-button style="width: 50%; height: 40px;" @click="userStore.toRegister">注册</el-button>
            <el-button style="width: 50%; height: 40px;" type="primary" @click="userLogin">登录</el-button>
        </div>
    </el-dialog>
    <!-- 注册 -->
    <el-dialog
        v-model="userStore.registering"
        append-to-body
        :close-on-click-modal="false"
        :close-on-press-escape="false"
        align-center
        destroy-on-close
        @close="resetRigisterDialogState"
        @open="openRegisterDialog"
        draggable
        width="350px"
        style="padding: 0 40px; border-radius: 10px; opacity: 0.9;"
    >
        <div style="height: 150px; text-align: center;">
            <el-avatar :size="100"
                src="/src/assets/default.jpg"
            />
        </div>
        <!-- 姓名 -->
        <el-input 
            v-model="name" 
            placeholder="姓名"
            prefix-icon="User"
            style="height: 40px;"
        />
        <div style="height: 7px;"></div>
        <!-- 密码 -->
        <el-input 
            v-model="password" 
            placeholder="密码"
            prefix-icon="Lock"
            style="height: 40px;"
            show-password
        />
        <div style="height: 7px;"></div>
        <!-- 验证码 -->
        <div style="display: flex;">
            <el-image fit="fill" :src="`data:image/png;base64,${checkCodeImg}`" style="height: 40px; width: 47%;" @click="generateCheckCode"></el-image>
            <div style="width: 5%;"></div>
            <el-input 
                v-model="checkCode" 
                placeholder="验证码"
                style="height: 40px; width: 47%;"
            />
        </div>
        <div style="height: 7px;"></div>
        <!-- 角色 -->
        <el-radio-group v-model="role">
            <el-text style="margin-right: 30px">身份: </el-text>
            <el-radio label="student" size="large">学生</el-radio>
            <el-radio label="teacher" size="large">教师</el-radio>
        </el-radio-group>
        <div style="height: 7px;"></div>
        <div style="display: flex; margin-top: 17px;">
            <el-button style="width: 50%; height: 40px;" @click="userStore.toLogin">登录</el-button>
            <el-button style="width: 50%; height: 40px;" type="primary" @click="userRegister">注册</el-button>
        </div>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { useUserStore } from '../stores/user';
import request from '../request';
import {ElMessage} from 'element-plus'
import router from '/src/router'
const userStore = useUserStore();

const name = ref("")
const password = ref("");
function resetLoginDialogState(){
    name.value = "";
    password.value = "";
    userStore.logining = false;
}
// 登录
function userLogin(){
    // 检查参数
    if(!name.value){
        ElMessage.warning("请输入姓名")
        return;
    } 
    if(!password.value){
        ElMessage.warning("请输入密码")
        return;
    }
    request.post('/login', {name: name.value, password: password.value}).then(res => {
        if(!res.data){
            ElMessage.warning('账号或密码错误')
        } else{
            userStore.user = res.data
            ElMessage.success('登录成功')
            resetLoginDialogState();
            // 判断身份
            if(res.data.role === 'student'){
                router.push('/student')
            } else{
                router.push('/teacher')
            }
        }
    })
}

// 注册
const role = ref("student")
const checkCode = ref("")
const checkCodeId = ref("")
const checkCodeImg = ref("")
function resetRigisterDialogState(){
    name.value = "";
    password.value = "";
    role.value = "";
    checkCode.value = "";
    checkCodeId.value = "";
    checkCodeImg.value = "";
    userStore.registering = false;
}

function generateCheckCode(){
    // 生成验证码
    request.get('/checkCode').then(res => {
        if(res.data){
            checkCodeId.value = res.data.checkCodeId;
            checkCodeImg.value = res.data.image
        }
    })
}

function openRegisterDialog(){
    generateCheckCode()
}

function userRegister(){
    // 检查参数
    if(!name.value){
        ElMessage.warning('请输入姓名')
        return;
    }
    if(!password.value){
        ElMessage.warning('请输入密码')
        return;
    }
    if(!checkCode.value){
        ElMessage.warning('请输入验证码')
        return;
    }
    request.post('/register', {name: name.value, password: password.value, role: role.value, checkCodeId: checkCodeId.value, checkCode: checkCode.value}).then(res => {
        if(res.data){
            ElMessage.success('注册成功');
            resetRigisterDialogState();
            userStore.toLogin();
        } else{
            // generateCheckCode();
        }
    })
}

</script>

<style scoped>
.user-header{
    position: absolute;
    width: 100vw;
    left: 0;
    top: 0;
    background-color: rgba(205, 215, 223, 0.2);
    opacity: 0.8;
    height: 50px;
    line-height: 50px;
    padding: 0 20px;
}
</style>