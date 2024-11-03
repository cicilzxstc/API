<script setup lang="ts">
import { ref,  watchEffect} from 'vue';
import myAxios from "../plugins/myAxios.ts";
import UserCardList from "../components/UserCardList.vue";
import {UserType} from "../models/user";

const isMatchMode = ref<boolean>(false);

const userList = ref([]);

const loading = ref(true);

// 分页控制
const pageNum = ref(1); // 当前页码
const pageSize = ref(8); // 每页显示的数量
const totalPage = ref(1); // 总页数
const totalMatchedNum = ref(20); // 控制匹配的总数（可以通过其他方式动态设置）

const loadData = async () =>{
  loading.value=true;
  // 在每次新请求之前清空 userList，以防数据叠加
  userList.value = [];
  let userListData;
  //如果是匹配模式
  if(isMatchMode.value){
    // const num = 3;
    userListData = await myAxios.get('/user/match',{
      params:{
        // num,
        num: totalMatchedNum.value, // 匹配总个数
        pageSize: pageSize.value, // 每页数量
        pageNum: pageNum.value, // 当前页码
      }
    })
        .then(function (response){
          console.log('/user/match succeed',response);
          totalPage.value = Math.ceil(response?.data?.total / pageSize.value); // 更新总页数
          return response?.data?.records;
        })
        .catch(function (error){
          console.error('/user/match error',error);
        })
    // console.log(userListData);
    if(userListData){
      userListData.forEach((user: UserType)=>{
        if(user.tags){
          user.tags = JSON.parse(user.tags);
        }
      })
      userList.value = userListData;
    }
  }else{//如果不是匹配模式
    userListData = await myAxios.get('/user/recommend',{
      params:{
        // pageSize:8,
        // pageNum:1,
        pageSize: pageSize.value,
        pageNum: pageNum.value, // 使用分页
      },
    })
        .then(function (response){
          console.log('/user/recommend succeed',response);
          totalPage.value = response?.data?.pages || 1; // 更新总页数
          return response?.data?.records;
        })
        .catch(function (error){
          console.error('/user/recommend error',error);
        })
    // console.log(userListData);
    if(userListData){
      userListData.forEach((user:UserType)=>{
        if(user.tags){
          user.tags = JSON.parse(user.tags);
        }
      })
      userList.value = userListData;
    }
  }
  loading.value=false;
}

watchEffect(()=>{
  loadData();
})

// 处理 van-switch 的点击事件
const onSwitchChange = (value: boolean) => {
  isMatchMode.value = value;  // 更新匹配模式
  if (value) {
    loadData();  // 切换到匹配模式时加载数据
  }
};

// 切换下一页
const nextPage = () => {
  if (pageNum.value < totalPage.value) {
    pageNum.value += 1;
    loadData();
  }
}

// 切换上一页
const prevPage = () => {
  if (pageNum.value > 1) {
    pageNum.value -= 1;
    loadData();
  }
}
</script>

<template>
<!--传入参数userList，这里的user-list是vue的格式，用userList也可以-->
<!--  <van-cell center title="匹配队友">-->
<!--    <template #right-icon>-->
<!--      <van-switch v-model="isMatchMode" />-->
<!--      -->
<!--    </template>-->
<!--  </van-cell>-->

  <van-cell center title="匹配队友">
    <!-- 使用 flex 布局将 van-switch 和 输入框 放在同一行 -->
    <div style="display: flex; align-items: center;">
      <!-- van-switch 开关，点击后开始匹配 -->
      <van-switch v-if="isMatchMode" v-model="isMatchMode" @change="onSwitchChange" style="margin-left: 118px;"/>
      <van-switch v-if="!isMatchMode" v-model="isMatchMode" @change="onSwitchChange" size="30" style="width: 62px"/>
      <!-- 匹配总数输入框 -->
      <van-field
          v-if="!isMatchMode"
          v-model="totalMatchedNum"
          label="输入匹配总数"
          type="number"
          placeholder="最多匹配20个"
          :min="1"
          :max="20"
          style="margin-right: 10px; width: 150px;"
      />
    </div>
  </van-cell>

  <user-card-list :user-list="userList" :loading="loading"/>
  <van-empty v-if="!userList || userList.length<1" description="查询不到数据"/>
  <!-- 如果有数据则显示分页按钮 -->
  <div v-if="userList && userList.length > 0" style="display: flex; justify-content: center; margin-top: 20px;">
    <van-button size="small" @click="prevPage" :disabled="pageNum <= 1">上一页</van-button>
    <span style="margin: 0 10px;">{{ pageNum }} / {{ totalPage }}</span>
    <van-button size="small" @click="nextPage" :disabled="pageNum >= totalPage">下一页</van-button>
  </div>

</template>

<style scoped>

</style>