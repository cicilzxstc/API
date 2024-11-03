import axios, {AxiosInstance} from "axios";

const isDev = process.env.NODE_ENV === 'development';

//前端请求后端（跨域，后端需要设置跨域权限）
const myAxios:AxiosInstance = axios.create({
    baseURL:isDev ? 'http://localhost:8080/api' : 'http://8.134.253.154:8080/api'
});

//向后端发送请求时携带上请求凭证（cookie），让后端知道现在是谁
myAxios.defaults.withCredentials = true;

myAxios.interceptors.request.use(function(config){
    // console.log('我要发请求啦',config)
    return config;
},function(error){
    return Promise.reject(error);
})

//这里用的是response而不是request，是响应而不是请求
myAxios.interceptors.response.use(function(response){
    // console.log('我收到你的响应啦',response)
    //未登录则跳转到登录页
    if(response?.data?.code === 40100){
        // console.log("11111111111111111111111111111111111111111111111111111")
        const redirectUrl = window.location.href;
        // const redirectUrl = "http://8.134.253.154:83/user";
        // console.log(redirectUrl)
        window.location.href = `/user/login?redirect=${redirectUrl}`;
        // console.log(window.location.href)

    }
    // 这里是直接返回后端业务的返回数据格式（json），直接返回response的是ajax的格式，加上.data后才是后端业务的返回值
    return response.data;
},function(error){
    return Promise.reject(error)
});

export default myAxios;
