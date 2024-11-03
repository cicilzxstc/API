<script setup lang="ts">
import {TeamType} from "../models/team";
import {teamStatusEnum} from "../constant/team.ts";
import teamUrl from '../assets/teamUrl.webp';
import myAxios from "../plugins/myAxios.ts";
import {showToast} from "vant";
import {onMounted, ref} from "vue";
import {getCurrentUser} from "../services/user.ts";
import {useRouter} from "vue-router";

const router = new useRouter();
const showPasswordDialog= ref(false);
const password = ref('');
const joinTeamId = ref();

interface TeamCardListProps{
  teamList:TeamType[];
}
//通过全局状态方法直接获取当前用户信息
const currentUser = ref();
onMounted(async ()=>{
  currentUser.value = await getCurrentUser();
})


const props = withDefaults(defineProps<TeamCardListProps>(),{
  // @ts-ignore
  teamList:[] as TeamType[],
});

//加入队伍前先进行判断，公开可直接加入，加密需要弹出输入框
const preJoinTeam = (team:TeamType)=>{
  // 给全局引用赋值
  joinTeamId.value = team.id;
  if(team.status === 0){
    doJoinTeam()
  }else{
    showPasswordDialog.value = true;
  }
}

/**
 * 加入队伍
 * 队伍id 和 密码 通过一个全局引用传递
 */
const doJoinTeam = async ()=>{
  if(!joinTeamId.value){
    return;
  }
  const res = await myAxios.post("/team/join",{
    teamId:joinTeamId.value,
    password:password.value
  });
  if(res?.code ===0){
    showToast('加入成功');
    doJoinCancel();
  }else{
    showToast('加入失败,'+(res.description ? `${res.description}`:''))
  }
}

//取消输入密码框或加入成功后 需要将joinTeamId、password全局引用的值清空
const doJoinCancel = ()=>{
  joinTeamId.value = 0;
  password.value = '';
}

/**
 * 更新队伍，跳转到更新队伍页
 * @param id
 */
const doUpdateTeam = (id:number)=>{
  router.push({
    path:"/team/update",
    query:{
      id,
    }
  })
}

/**
 * 退出队伍
 * @param id
 */
const doQuitTeam = async (id:number)=>{
  const res = await myAxios.post("/team/quit",{
    teamId:id
  });
  if(res?.code ===0){
    showToast('退出成功');
  }else{
    showToast('退出失败,'+(res.description ? `${res.description}`:''))
  }
}

/**
 * 解散队伍
 * @param id
 */
const doDeleteTeam = async (id:number)=>{
  const res = await myAxios.post("/team/delete",{
    id,
  });
  if(res?.code ===0){
    showToast('解散成功');
  }else{
    showToast('解散失败,'+(res.description ? `${res.description}`:''))
  }
}
</script>

<template>
  <div
    id="teamCardList"
  >
    <van-card
        v-for="team in props.teamList"
        :thumb="teamUrl"
        :desc="team.description"
        :title="`${team.name}`"
    >
      <template #tags>
        <van-tag plain type="danger" style="margin-left: 8px;margin-top: 8px">
          {{
            teamStatusEnum[team.status]
          }}
        </van-tag>
      </template>
      <template #bottom>
        <div>
          {{`队伍人数：${team.hasJoinNum}/${team.maxNum}`}}
        </div>
        <div v-if="team.expireTime">
          {{'过期时间：'+team.expireTime}}
        </div>
      </template>
      <template #footer>
        <van-button size="small" type="primary" v-if="team.userId !== currentUser?.id  && !team.hasJoin" plain
                    @click="preJoinTeam(team)">
          加入队伍
        </van-button>
        <van-button v-if="team.userId === currentUser?.id" size="small" plain @click="doUpdateTeam(team.id)">
          更新队伍
        </van-button>
        <van-button v-if="team.userId !== currentUser?.id &&team.hasJoin" size="small" plain @click="doQuitTeam(team.id)">
          退出队伍
        </van-button>
        <van-button v-if="team.userId === currentUser?.id" size="small" type="danger" plain @click="doDeleteTeam(team.id)">
          解散队伍
        </van-button>
      </template>
    </van-card>
    <van-dialog v-model:show="showPasswordDialog" title="请输入密码" show-cancel-button @confirm="doJoinTeam" @cancel="doJoinCancel">
      <van-field v-model="password" placeholder="请输入密码"/>
    </van-dialog>
  </div>

</template>
<style scoped>
#teamCardList :deep(.van-image__img){
  height: 128px;
  object-fit: unset;
}
</style>