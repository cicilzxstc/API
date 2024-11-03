package com.example.yuapiclientsdk.client;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.example.yuapiclientsdk.model.User;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import static com.example.yuapiclientsdk.utils.SignUtils.genSign;

///**
// * 协助发送请求类
// */
//
//public class YuApiClient {
//
//    private String accessKey;
//
//    private String secretKey;
//
//    public YuApiClient(String accessKey, String secretKey) {
//        this.accessKey = accessKey;
//        this.secretKey = secretKey;
//    }
//
////    public String getNameByGet(String name){
////        //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
////        HashMap<String, Object> paramMap = new HashMap<>();
////        paramMap.put("name", name);
////        String url = "http://localhost:8090/api/name/get?name=" + name;
////        String result= HttpUtil.get(url);
////        System.out.println(result);
////        return result;
////    }
//
//    public String getWeatherByGet(String location) {
//        // 构造请求参数
//        HashMap<String, Object> paramMap = new HashMap<>();
//        paramMap.put("location", location);
//
//        // 拼接 URL
//        String url = "https://devapi.qweather.com/v7/weather/now?location=" + location + "&key=86466c3ffe84462f81175f03ae63f4ca";
//
//        // 发送 GET 请求并获取结果
//        String result = HttpUtil.get(url);
//        System.out.println(result);
//
//        try {
//            // 解析 JSON 数据
//            JsonObject json = JsonParser.parseString(result).getAsJsonObject();
//            JsonObject now = json.getAsJsonObject("now");
//
//            // 提取关键信息
//            String temperature = now.get("temp").getAsString();  // 温度
//            String weatherText = now.get("text").getAsString();  // 天气描述
//            String windDir = now.get("windDir").getAsString();   // 风向
//            String windScale = now.get("windScale").getAsString();  // 风力等级
//            String humidity = now.get("humidity").getAsString();  // 湿度
//
//            // 格式化展示
//            String formattedResult = String.format(
//                    "当前天气：%s\n温度：%s°C\n风向：%s\n风力等级：%s级\n湿度：%s%%",
//                    weatherText, temperature, windDir, windScale, humidity
//            );
//
//            System.out.println(formattedResult);  // 输出格式化结果
//            return formattedResult;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "解析天气信息时出错，请稍后再试。";
//        }
////        return result;
//    }
//
//
//    public String getNameByGet(String name){
//        // 将 name 参数转换为 JSON 字符串作为 body 内容
//        String body = JSONUtil.toJsonStr(Collections.singletonMap("name", name));
//
//        // 生成包含签名和其他信息的请求头
//        Map<String, String> headers = getHeaderMap(body);
//
//        // 构建 URL 请求地址
//        String url = "http://localhost:8090/api/name/get?name=" + name;
//
//        // 使用 HttpRequest 发起 GET 请求并添加签名头
//        HttpResponse httpResponse = HttpRequest.get(url)
//                .addHeaders(headers)  // 添加签名请求头
//                .execute();  // 执行请求
//
//        // 打印响应状态和结果
//        System.out.println(httpResponse.getStatus());
//        String result = httpResponse.body();
//        System.out.println(result);
//
//        return result;
//    }
//
//    public String getNameByPost(String name){
//        //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
//        HashMap<String, Object> paramMap = new HashMap<>();
//        paramMap.put("name", name);
//        String result= HttpUtil.post("http://localhost:8090/api/name/", paramMap);
//        System.out.println(result);
//        return result;
//    }
//
//
//    /**
//     * 发送请求时需要ak、随机数nonce（防止重放）、实际参数body、时间戳timestamp（防止重放） 拼接 sk
//     * 通过签名算法生成签名sign（不可逆，即不可解密）
//     * 服务端收到请求后，把拿到的ak、nonce、body、timestamp与sk再进行一次签名生成，如果签名相同则确定可以请求方
//     *
//     * 这里暂时改为与body拼接，方便操作
//     * @return
//     */
//    private Map<String,String> getHeaderMap(String body){
//        HashMap<String, String> hashMap = new HashMap<>();
//        hashMap.put("accessKey",accessKey);
//        //sk一定不能在网络中传递
////        hashMap.put("secretKey",secretKey);
//        hashMap.put("nonce", RandomUtil.randomNumbers(4));
//        hashMap.put("body",body);
//        hashMap.put("timestamp",String.valueOf(System.currentTimeMillis()/1000));
//        hashMap.put("sign",genSign(body,secretKey));
//        return hashMap;
//    }
//
//
//    //发送Restful请求
//    public String getUsernameByPost(User user){
//        System.out.println("AccessKey: " + accessKey);
//        System.out.println("SecretKey: " + secretKey);
//        String json = JSONUtil.toJsonStr(user);
//        HttpResponse httpResponse = HttpRequest.post("http://localhost:8090/api/name/user")
//                //发送请求时添加请求头
//                .addHeaders(getHeaderMap(json))
//                //发送请求的实际参数
//                .body(json)
//                .execute();
//        System.out.println(httpResponse.getStatus());
//        String result = httpResponse.body();
//        System.out.println(result);
//        return result;
//    }
//}


/**
 * 协助发送请求类
 */

public class YuApiClient {

    private String accessKey;

    private String secretKey;

    public YuApiClient(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

//    public String getNameByGet(String name){
//        //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
//        HashMap<String, Object> paramMap = new HashMap<>();
//        paramMap.put("name", name);
//        String url = "http://localhost:8090/api/name/get?name=" + name;
//        String result= HttpUtil.get(url);
//        System.out.println(result);
//        return result;
//    }

    public String getWeatherByGet(String location) {
        // 构造请求参数
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("location", location);

        // 拼接 URL
        String url = "https://devapi.qweather.com/v7/weather/now?location=" + location + "&key=86466c3ffe84462f81175f03ae63f4ca";

        // 发送 GET 请求并获取结果
        String result = HttpUtil.get(url);
        System.out.println(result);

        try {
            // 解析 JSON 数据
            JsonObject json = JsonParser.parseString(result).getAsJsonObject();
            JsonObject now = json.getAsJsonObject("now");

            // 提取关键信息
            String temperature = now.get("temp").getAsString();  // 温度
            String weatherText = now.get("text").getAsString();  // 天气描述
            String windDir = now.get("windDir").getAsString();   // 风向
            String windScale = now.get("windScale").getAsString();  // 风力等级
            String humidity = now.get("humidity").getAsString();  // 湿度

            // 格式化展示
            String formattedResult = String.format(
                    "当前天气：%s\n温度：%s°C\n风向：%s\n风力等级：%s级\n湿度：%s%%",
                    weatherText, temperature, windDir, windScale, humidity
            );

            System.out.println(formattedResult);  // 输出格式化结果
            return formattedResult;

        } catch (Exception e) {
            e.printStackTrace();
            return "解析天气信息时出错，请稍后再试。";
        }
//        return result;
    }


    public String getNameByGet(String name){
        // 将 name 参数转换为 JSON 字符串作为 body 内容
        String body = JSONUtil.toJsonStr(Collections.singletonMap("name", name));

        // 生成包含签名和其他信息的请求头
        Map<String, String> headers = getHeaderMap(body);

        // 构建 URL 请求地址
        String url = "http://localhost:8090/api/name/get?name=" + name;

        // 使用 HttpRequest 发起 GET 请求并添加签名头
        HttpResponse httpResponse = HttpRequest.get(url)
                .addHeaders(headers)  // 添加签名请求头
                .execute();  // 执行请求

        // 打印响应状态和结果
        System.out.println(httpResponse.getStatus());
        String result = httpResponse.body();
        System.out.println(result);

        return result;
    }

    public String getNameByPost(String name){
        //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        String result= HttpUtil.post("http://localhost:8090/api/name/", paramMap);
        System.out.println(result);
        return result;
    }


    /**
     * 发送请求时需要ak、随机数nonce（防止重放）、实际参数body、时间戳timestamp（防止重放） 拼接 sk
     * 通过签名算法生成签名sign（不可逆，即不可解密）
     * 服务端收到请求后，把拿到的ak、nonce、body、timestamp与sk再进行一次签名生成，如果签名相同则确定可以请求方
     *
     * 这里暂时改为与body拼接，方便操作
     * @return
     */
    private Map<String,String> getHeaderMap(String body){
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("accessKey",accessKey);
        //sk一定不能在网络中传递
//        hashMap.put("secretKey",secretKey);
        hashMap.put("nonce", RandomUtil.randomNumbers(4));
        hashMap.put("body",body);
        hashMap.put("timestamp",String.valueOf(System.currentTimeMillis()/1000));
        hashMap.put("sign",genSign(body,secretKey));
        return hashMap;
    }


    //发送Restful请求
    public String getUsernameByPost(User user){
        System.out.println("AccessKey: " + accessKey);
        System.out.println("SecretKey: " + secretKey);
        String json = JSONUtil.toJsonStr(user);
        HttpResponse httpResponse = HttpRequest.post("http://localhost:8090/api/name/user")
                //发送请求时添加请求头
                .addHeaders(getHeaderMap(json))
                //发送请求的实际参数
                .body(json)
                .execute();
//        System.out.println(httpResponse.getStatus());
        String result = httpResponse.body();
//        System.out.println(result);
        return result;
    }
}