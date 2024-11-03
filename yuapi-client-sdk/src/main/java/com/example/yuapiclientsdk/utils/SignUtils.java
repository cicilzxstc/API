package com.example.yuapiclientsdk.utils;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;


/**
 * 签名工具类
 */
public class SignUtils {
    /**
     * 生成签名
     * 这里改为用body  方便操作而已
     * @param body
     * @param secretKey
     * @return
     */
    public static String genSign(String body, String secretKey){
        //使用SHA256算法生成签名（先拼接 再加密）
        Digester md5 = new Digester(DigestAlgorithm.SHA256);
        String content = body + "." + secretKey;
        return md5.digestHex(content);
    }
}
