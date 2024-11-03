import {UserType} from "../models/user";

//用户信息缓存，避免多次向后端发送请求
let currentUser : UserType;
const setCurrentUserState = (user:UserType)=>{
    currentUser = user;
}
const getCurrentUserState = ():UserType=>{
    return currentUser;
}

export {
    setCurrentUserState,
    getCurrentUserState,
}