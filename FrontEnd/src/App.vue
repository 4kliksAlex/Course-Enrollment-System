<template>
  <user-header></user-header>
  <div class="main-container" 
    v-loading="!userStore.logged"
    element-loading-text="加载中..."
    element-loading-background="rgba(122, 122, 122, 0.1)"
    >
    <template v-if="userStore.logged">
      <student-home v-if="userStore.user.role==='student'"/>
      <teacher-home v-else></teacher-home>
    </template>
  </div>
</template>

<script setup>
import StudentHome from './views/StudentHome.vue'
import TeacherHome from './views/TeacherHome.vue';
import {useUserStore} from './stores/user'
import UserHeader from './components/UserHeader.vue';
import {ElMessageBox} from 'element-plus'

// 登录
const userStore = useUserStore();
userStore.toLogin();
let tiping = false;
setInterval(() => {
  if(!tiping && !userStore.logged && !(userStore.logining || userStore.registering)){
    tiping = true;
    ElMessageBox.alert('你还未登录, 请先登录', '提示', {
      confirmButtonText: '确定',
      callback: () => {
        userStore.toLogin();
        tiping = false;
      },
    })
    
  }
}, 1000);

</script>

<style>
body{
  width: 100vw;
  height: 100vh;
  background-color: white;
}
.center-son{
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  margin: auto;
}
.main-container{
  position: absolute;
  top: 50px;
  left: 0;
  width: 100vw;
  height: 90vh;
}
</style>
