<script setup>
import {useRouter} from "vue-router";
import {onMounted} from "vue";
import myAxios from "../plugins/myAxios.ts";
import {showToast} from "vant";
import TeamCardList from "../components/TeamCardList.vue";
import {ref} from "vue";

const active = ref('public');
const router = useRouter();
//切换公开或加密
const onTabChange = (name)=>{
  //查公开
  if(name === 'public'){
    listTeam(searchText.value,0);
  }else{
    //查加密
    listTeam(searchText.value,2);
  }
}

//跳转到加入队伍页
const toAddTeam = () =>{
  router.push({
    path:"/team/add"
  })
}

const teamList = ref([]);

//搜索关键词（名称、描述）
const searchText = ref('');
/**
 * 根据关键词查询队伍，默认查询公开的队伍
 * @param val
 * @returns {Promise<void>}
 */
const listTeam = async (val = '',status=0)=>{
  const teamQuery={
    searchText:val,
    pageNum:1,
    status,
  };
  const res = await myAxios.post("/team/list",teamQuery);
  if(res?.code === 0){
    teamList.value = res.data;
    // console.log(teamList.value);
  }else{
    // showToast("加载队伍失败，请刷新重试");
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
    <van-tabs v-model:active="active" @change="onTabChange">
      <van-tab title="公开" name="public"/>
      <van-tab title="加密" name="private"/>
    </van-tabs>
    <div style="margin-bottom: 16px"/>
    <van-button class="add-button" type="primary" icon="plus" @click="toAddTeam"/>
    <van-empty v-if="teamList?.length<1" description="数据为空"/>
    <team-card-list :teamList="teamList"/>
  </div>
</template>

<style scoped>
#teamPage{

}
</style>