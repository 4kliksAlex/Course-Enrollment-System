import { createRouter, createWebHistory } from 'vue-router'
import StudentCourseTable from '/src/views/StudentCourseTable.vue'
import StudentDropCourse from '/src/views/StudentDropCourse.vue'
import StudentSelectCourse from '/src/views/StudentSelectCourse.vue'
import TeacherCourseTable from '/src/views/TeacherCourseTable.vue'
import TeacherExpandCourse from '/src/views/TeacherExpandCourse.vue'
import TeacherPublishCourse from '/src/views/TeacherPublishCourse.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      component: StudentSelectCourse
    },
    {
      path: '/student',
      component: StudentSelectCourse
    },
    {
      path: '/student/selectCourse',
      component: StudentSelectCourse
    },
    {
      path: '/student/dropCourse',
      component: StudentDropCourse
    },
    {
      path: '/student/courseTable',
      component: StudentCourseTable
    },
    {
      path: '/teacher',
      component: TeacherPublishCourse
    },
    {
      path: '/teacher/publishCourse',
      component: TeacherPublishCourse
    },
    {
      path: '/teacher/expandCourse',
      component: TeacherExpandCourse
    },
    {
      path: '/teacher/courseTable',
      component: TeacherCourseTable
    },
  ]
})

export default router
