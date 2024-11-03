<script setup>
import {ref} from "vue";
import myAxios from "../plugins/myAxios.ts";
import {showToast, Toast} from "vant";
import {useRouter} from "vue-router";
const initFormData = {
  "name":"",
  "description":"",
  "expireTime":null,
  "maxNum":3,
  "password":"",
  "status":0,
}
// {...}扩展对象运算符，保证初始数据不被修改
const addTeamData = ref({...initFormData})

//展示日期选择器
const showPicker = ref(false);
const onConfirm = ({ selectedValues }) => {
  // 构造Date对象
  const selectedDate = new Date(`${selectedValues[0]}-${selectedValues[1]}-${selectedValues[2]}T00:00:00.000Z`);

  // 保存为Date对象并传递给后端
  addTeamData.value.expireTime = selectedDate.toISOString();

  showPicker.value = false;
};

//过期日期最小时间
const minDate = new Date()

const router = new useRouter();

//需要用户填写的表单数据
const doSubmit = async ()=>{
  const postData = {
    ...addTeamData.value,
    //将字符串转换为对应数值
    status:Number(addTeamData.value.status)
  }
  //todo 前端参数校验
  const res = await myAxios.post("/team/add",postData);
  if(res?.code === 0){
    showToast("添加成功");
    router.push({
      path:"/team",
      replace:true,
    });
  }else{
    showToast("添加失败");
  }
}
</script>

<template>
  <div id="teamAddPage">
    <van-form @submit="doSubmit">
      <van-cell-group inset>
        <van-field
            v-model="addTeamData.name"
            name="name"
            label="队伍名"
            placeholder="请输入队伍名"
            :rules="[{required:true,message:'请输入队伍名'}]"
        />

        <van-field
            v-model="addTeamData.description"
            name="userPassword"
            label="队伍描述"
            placeholder="请输入队伍描述"
            rows="4"
            autosize
            type="textarea"
            :rules="[{required:true,message:'请输入队伍描述'}]"
        />

        <van-field
            v-model="addTeamData.expireTime"
            is-link
            readonly
            name="datePicker"
            label="过期时间"
            placeholder="点击选择过期时间"
            @click="showPicker = true"
        />
        <van-popup v-model:show="showPicker" position="bottom">
          <van-date-picker
              title="请选择过期时间"
              :min-date="minDate"
              @confirm="onConfirm"
              @cancel="showPicker = false"
          />
        </van-popup>


        <van-field name="stepper" label="最大人数">
          <template #input>
            <van-stepper v-model="addTeamData.maxNum" max="10"/>
          </template>
        </van-field>

        <van-field name="radio" label="队伍状态">
          <template #input>
            <van-radio-group v-model="addTeamData.status" direction="horizontal">
              <van-radio name="0">公开</van-radio>
              <van-radio name="1">私有</van-radio>
              <van-radio name="2">加密</van-radio>
            </van-radio-group>
          </template>
        </van-field>

        <van-field
            v-if="Number(addTeamData.status) === 2"
            v-model="addTeamData.password"
            type="password"
            name="password"
            label="密码"
            placeholder="请输入队伍密码"
            :rules="[{required:true,message:'请输入队伍密码'}]"
        />

      </van-cell-group>
      <div style="margin:16px">
        <van-button roun block type="primary" native-type="submit">
          创建队伍
        </van-button>
      </div>
    </van-form>
  </div>
</template>

<style scoped>
#teamAddPage{

}
</style>