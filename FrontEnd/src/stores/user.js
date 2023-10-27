import { ref, computed } from 'vue'
import { defineStore } from 'pinia'

export const useUserStore = defineStore('user', () => {
    const user = ref(null);
    const logged = computed(() => user.value && user.value.uid)
    const logining = ref(false)
    const registering = ref(false)

    function toLogin(){
        logining.value = true;
        registering.value = false;
    }

    function toRegister(){
        registering.value = true;
        logining.value = false;
    }

    function isSelf(uid){
        return computed(() => user.value && user.value.uid === uid);
    }

  return { user, logged, logining, registering, toLogin, toRegister, isSelf }
})
