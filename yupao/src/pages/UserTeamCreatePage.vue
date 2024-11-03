<script setup>
import {useRouter} from "vue-router";
import {onMounted} from "vue";
import myAxios from "../plugins/myAxios.ts";
import {showToast} from "vant";
import TeamCardList from "../components/TeamCardList.vue";
import {ref} from "vue";

const router = useRouter();

//跳转到加入队伍页
const toAddTeam = () =>{
  router.push({
    path:"/team/add"
  })
}

const teamList = ref([]);
//搜索关键词（名称、描述）
const searchText = ref('');

const listTeam = async (val = '')=>{
  const teamQuery={
    searchText:val,
    pageNum:1,
  };
  const res = await myAxios.post("/team/list/my/create",teamQuery);
  if(res?.code === 0){
    teamList.value = res.data;
    // console.log(teamList.value);
  }else{
    showToast("加载队伍失败，请刷新重试");
  }
}

onMounted(async ()=>{
  listTeam();
})

const onSearch = async (val)=>{
  listTeam(val);
}


</script>

<template>
  <div id="teamPage">
    <van-search v-model="searchText" placeholder="搜索队伍" @search="onSearch"/>
<!--    <van-button type="primary" @click="toAddTeam">创建队伍</van-button>-->
    <van-empty v-if="teamList?.length<1" description="数据为空"/>
    <team-card-list :teamList="teamList"/>
    <div style="margin-bottom: 16px"/>
    <van-button class="add-button" type="primary" icon="plus" @click="toAddTeam"/>
  </div>
</template>

<style scoped>
#teamPage{

}
</style>