<script setup lang="ts">
import { ref } from 'vue';
import {useRouter} from "vue-router";
const searchText = ref('');
const router = useRouter();

//原始标签
const originTaglist = [
  {
    text:'性别',
    children:
        [
          {text:'男',id:'男'},
          {text:'女',id:'女'},
        ],
  },
  {
    text:'年级',
    children:
        [
          {text:'大一',id:'大一'},
          {text:'大二',id:'大二'},
          {text:'大三',id:'大三'},
          {text:'大四',id:'大四'},
        ],
  },
  {
    text:'方向',
    children:
        [
          {text:'java',id:'java'},
          {text:'python',id:'python'},
          {text:'go',id:'go'},
          {text:'前端',id:'前端'},
        ],
  },
  {
    text:'爱好',
    children:
        [
          {text:'篮球',id:'篮球'},
          {text:'说唱',id:'说唱'},
          {text:'跳舞',id:'跳舞'},
          {text:'足球',id:'足球'},
        ],
  },
];


//已选中的标签
const activeIds = ref([]);
const activeIndex = ref(0);

//标签列表
let tagList = ref(originTaglist);

//移除选中的标签
const doClose = (tag) =>{
  activeIds.value = activeIds.value.filter(item =>{
    return item!==tag;
  })
}

//搜索过滤标签
const onSearch =(val)=>{
  tagList.value = originTaglist.map(parentTag=>{
    const tempChildren = [...parentTag.children];
    const tempParentTag={...parentTag};
    tempParentTag.children = tempChildren.filter(item=> item.text.includes(searchText.value));
    return tempParentTag;
  })
}

const onCancel = () =>{
  searchText.value='';
  tagList.value = originTaglist;
}

// 页码和每页数量
const pageNum = ref(1);
const pageSize = ref(10);


//执行搜索
const doSearchResult = ()=>{
  router.push({
    path:'/user/list',
    query:{
      tags: activeIds.value,
      pageNum: pageNum.value,
      pageSize: pageSize.value
    }
  })
}

</script>

<template>
  <form action="/">
    <van-search
        v-model="searchText"
        show-action
        placeholder="请输入要搜索的标签"
        @search="onSearch"
        @cancel="onCancel"
    />
  </form>
  <van-divider content-position="left">已选标签</van-divider>
  <div v-if="activeIds.length===0">请选择标签</div>


  <van-row gutter = "16" style="padding:0 16px">
    <van-col v-for="tag in activeIds">
      <van-tag closeable size="medium" type="primary" @close="doClose(tag)">
        {{tag}}
      </van-tag>
    </van-col>
  </van-row>


  <van-divider content-position="left">请选择标签</van-divider>
  <van-tree-select
    v-model:active-id="activeIds"
    v-model:main-active-index="activeIndex"
    :items="tagList"
  />

  <div style="padding:12px">
    <van-button block type="primary" @click="doSearchResult">搜索</van-button>
  </div>

</template>

<style scoped>

</style>