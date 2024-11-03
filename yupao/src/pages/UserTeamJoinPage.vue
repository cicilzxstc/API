TeamPage.vue<script setup>
import {useRouter} from "vue-router";
import {onMounted} from "vue";
import myAxios from "../plugins/myAxios.ts";
import {showToast} from "vant";
import TeamCardList from "../components/TeamCardList.vue";
import {ref} from "vue";

const router = useRouter();

const teamList = ref([]);
//搜索关键词（名称、描述）
const searchText = ref('');

const listTeam = async (val = '')=>{
  const teamQuery={
    searchText:val,
    pageNum:1,
  };
  const res = await myAxios.post("/team/list/my/join",teamQuery);
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
    <team-card-list :teamList="teamList"/>
    <van-empty v-if="teamList?.length<1" description="数据为空"/>
  </div>
</template>

<style scoped>
#teamPage{

}
</style>