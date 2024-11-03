<script setup lang="ts">
import { ref } from "vue";
import { useRouter } from "vue-router";
import myAxios from "../plugins/myAxios.ts";

const router = useRouter();
const userAccount = ref('');
const userPassword = ref('');
const checkPassword = ref('');
const planetCode = ref('');

const onSubmit = async () => {
  // 表单校验
  if (!userAccount.value || !userPassword.value || !checkPassword.value || !planetCode.value) {
    alert('请完整填写所有字段');
    return;
  }

  if (userPassword.value !== checkPassword.value) {
    alert('两次密码输入不一致');
    return;
  }

  // 调用注册接口
  const res = await myAxios.post('/user/register', {
    userAccount: userAccount.value,
    userPassword: userPassword.value,
    checkPassword: checkPassword.value,
    planetCode: planetCode.value,
  });

  console.log(res, '用户注册');
  if (res.code === 0 && res.data) {
    // alert('注册成功');
    router.push('/user/login'); // 注册成功后跳转回登录页面
  }else {
    alert(res.description)
  }
};
</script>

<template>
  <van-form @submit="onSubmit">
    <van-cell-group inset>
      <van-field
          v-model="userAccount"
          name="userAccount"
          label="账号"
          placeholder="请输入账号"
          :rules="[{ required: true, message: '请填写账号' }]"
      />
      <van-field
          v-model="userPassword"
          type="password"
          name="userPassword"
          label="密码"
          placeholder="请输入密码"
          :rules="[{ required: true, message: '请填写密码' }]"
      />
      <van-field
          v-model="checkPassword"
          type="password"
          name="checkPassword"
          label="确认密码"
          placeholder="请再次输入密码"
          :rules="[{ required: true, message: '请确认密码' }]"
      />
      <van-field
          v-model="planetCode"
          name="planetCode"
          label="星球编号"
          placeholder="请输入星球编号"
          :rules="[{ required: true, message: '请填写星球编号' }]"
      />
    </van-cell-group>
    <div style="margin:16px">
      <van-button roun block type="primary" native-type="submit">
        注册
      </van-button>
    </div>
  </van-form>
</template>

<style scoped>

</style>
