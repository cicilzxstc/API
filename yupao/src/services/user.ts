import myAxios from "../plugins/myAxios.ts";
import {setCurrentUserState} from "../state/user.ts";

//对于一些需要展示个人信息的页面，每次进入都得获取用户信息（向后端发请求），这是一个频繁的操作，所以单独提出来作为一个方法，可以不去记请求地址和请求参数
export const getCurrentUser = async()=>{
    // 使用缓存有个弊端：当修改用户信息后需要更新数据库里的用户信息，否则下次刷新页面还是未更改的用户信息，这里暂且不用缓存
    // const currentUser = getCurrentUserState();
    // if(currentUser){
    //     return currentUser;
    // }
    //没有缓存则向后端发送请求
    const res = await myAxios.get('/user/current');
    if(res.code === 0){
        setCurrentUserState(res.data);
        console.log(res.data);
        return res.data;
    }
    return null;
}