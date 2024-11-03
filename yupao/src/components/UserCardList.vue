<script setup lang="ts">
import {UserType} from "../models/user";
import {showDialog} from "vant";


interface UserCardListProps{
  // loading:boolean;
  loading?: boolean;  // 改为可选属性
  userList:UserType[];
}

const props = withDefaults(defineProps<UserCardListProps>(),{
  loading:true,
  // @ts-ignore
  userList:[] as UserType[],
});

const Contact = (phone:string,email:string)=>{
  showDialog({
    title: '请联系我咨询信息',
    message:`手机号码：${phone}\n电子邮箱：${email}`,
  }).then(() => {
    // on close
  });
}
</script>

<template>
  <van-skeleton title avatar :row="3" :loading="props.loading" v-for="user in props.userList" :key="user.id">
    <van-card
        :desc="user.profile"
        :title="`${user.username} (${user.planetCode})`"
        :thumb="user.avatarUrl">
      <template #tags>
        <van-tag plain type="danger" v-for="tag in user.tags" style="margin-left: 8px;margin-top: 8px">
          {{tag}}
        </van-tag>
      </template>
      <template #footer>
        <van-button size="mini" @click="Contact(user.phone,user.email)">联系我</van-button>
      </template>
    </van-card>
  </van-skeleton>

</template>
<style scoped>

</style>