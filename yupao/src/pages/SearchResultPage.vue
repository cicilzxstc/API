<script setup >
import {useRoute} from "vue-router";
import {onMounted, ref} from 'vue';

import qs from "qs";
import myAxios from "../plugins/myAxios.ts";
import UserCardList from "../components/UserCardList.vue";

const route = useRoute();
const { tags, pageNum = 1, pageSize = 10 } = route.query;
// const {tags} = route.query;
const userList = ref([]);
const totalPages = ref(1);

//钩子函数，进入该页面时自动加载，得到请求路径中的参数tagNameList，并用该参数向后端发送请求,将响应结果返回成List对象userListData
// onMounted(async ()=>{
//   const userListData = await myAxios.get('/user/search/tags',{
//     params:{
//       tagNameList:tags,
//     },
//     //直接取出的参数还不适用于后端的参数格式，需要从键值对对象序列化为查询字符串
//     paramsSerializer:params=>{
//       return qs.stringify(params,{indices:false})
//     }
//   })
//   .then(function(response){
//     console.log('/user/search/tags succeed',response);
//     return response?.data;
//   })
//   .catch(function(error){
//     console.error('/user/search/tag error',error);
//   })
//   console.log(userListData)
//   if(userListData){
//     userListData.forEach(user=>{
//       if(user.tags){
//         user.tags = JSON.parse(user.tags);
//       }
//     })
//     userList.value = userListData;
//   }
// })



// const mockUser={
//   id:12345,
//   username:'鱼皮',
//   userAccount:'12314',
//   profile:'一名精神小伙，目前还有头发，谢谢大家，阿巴阿巴阿巴阿巴巴巴巴巴巴',
//   // TODO 替换图片
//   avartarUrl:'http://636f-codenav-8qrj8px727565176-1256524210.tcb.qcloud.la/img/logo.png',
//   gender:0,
//   phone:'13131313131313',
//   email:'123456@qq.com',
//   userRole:0,
//   planetCode:'1234',
//   tags:['java','emo','打工中','学习'],
//   createTime:new Date(),
// }
//
// const userList=ref([mockUser]);


// 获取用户列表
const loadUserList = async () => {
  const userListData = await myAxios.get('/user/search/tags', {
    params: {
      tagNameList: tags,
      pageNum: pageNum,
      pageSize: pageSize,
    },
    paramsSerializer: params => qs.stringify(params, { indices: false })
  })
      .then(function (response) {
        console.log('/user/search/tags succeed', response);
        return response?.data;
      })
      .catch(function (error) {
        console.error('/user/search/tags error', error);
      });

  console.log(userListData);
  if (userListData) {
    userListData.records.forEach(user => {
      if (user.tags) {
        user.tags = JSON.parse(user.tags);
      }
    });
    userList.value = userListData.records;
    totalPages.value = Math.ceil(userListData.total / pageSize);
  }
};

onMounted(() => {
  loadUserList();
});

// 翻页操作
const goToPreviousPage = () => {
  if (pageNum > 1) {
    route.query.pageNum = pageNum - 1;
    loadUserList();
  }
};

const goToNextPage = () => {
  if (pageNum < totalPages.value) {
    route.query.pageNum = Number(pageNum) + 1;
    loadUserList();
  }
};

</script>

<!--<template>-->
<!--  <user-card-list :user-list="userList"/>-->


<!--  <van-empty v-if="!userList || userList.length<1" description="搜索结果为空"/>-->
<!--</template>-->

<template>
  <user-card-list :user-list="userList" :loading="false" />

  <van-empty v-if="!userList || userList.length < 1" description="搜索结果为空" />

  <!-- 分页按钮 -->
<!--  <div style="padding:12px">-->
<!--    <van-button type="default" @click="goToPreviousPage" :disabled="pageNum === 1">上一页</van-button>-->
<!--    <span>第 {{pageNum}} 页 / 共 {{totalPages}} 页</span>-->
<!--    <van-button type="default" @click="goToNextPage" :disabled="pageNum === totalPages">下一页</van-button>-->
<!--  </div>-->

  <!-- 如果有数据则显示分页按钮 -->
  <div v-if="userList && userList.length > 0" style="display: flex; justify-content: center; margin-top: 20px;">
    <van-button size="small" type="default" @click="goToPreviousPage" :disabled="pageNum === 1">上一页</van-button>
    <span style="margin: 0 10px;">{{ pageNum }} / {{ totalPages }}</span>
    <van-button size="small" type="default" @click="goToNextPage" :disabled="pageNum === totalPages">下一页</van-button>
  </div>
</template>

<style scoped>

</style>