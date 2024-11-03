<script setup lang="ts">
import {useRouter} from "vue-router";
import {onMounted, ref} from "vue";
import {getCurrentUser} from "../services/user.ts";
import myAxios from "../plugins/myAxios.ts";

const user = ref();
onMounted(async()=>{
  user.value = await getCurrentUser();
})

const router = useRouter();

const toEdit = (editKey:string,editName:string,currentValue:string) =>{
  router.push({
    path:'/user/edit',
    query: {
      editKey,
      editName,
      currentValue,
    }
  })
}

// 退出登录的函数
const logout = async () => {
  const res = await myAxios.post('/user/logout');
  if (res && res.code === 0) {
    // alert('退出成功');
    router.push('/user/login'); // 退出成功后重定向到登录页面
  } else {
    // alert('退出失败');
  }
};
</script>

<template>
  <template v-if="user">
    <van-cell title="当前用户" :value="user?.username"/>
    <van-cell title="修改信息" is-link to="/user/update"/>
    <van-cell title="我创建的队伍" is-link to="/user/team/create"/>
    <van-cell title="我加入的队伍" is-link to="/user/team/join"/>
    <div style="display: flex;justify-content: center;" >
      <van-button type="danger" @click="logout" >退出登录</van-button>
    </div>

  </template>

</template>

<style scoped>

</style>