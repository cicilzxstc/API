package com.example.yuapiinterface;

import com.example.yuapiclientsdk.YuApiClientConfig;
import com.example.yuapiclientsdk.client.YuApiClient;
import com.example.yuapiclientsdk.model.User;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;


@SpringBootTest
// 这里出现问题，为什么一定要在这里写@EnableConfigurationProperties注解才能加载到YuApiClientConfig这个Bean
@ContextConfiguration(classes = {YuApiClientConfig.class})
@EnableConfigurationProperties(YuApiClientConfig.class)
class YuapiInterfaceApplicationTests {

    @Resource
    private YuApiClient yuApiClient;

    //测试签名
    @Test
    void contextLoads() {
        String result = yuApiClient.getNameByGet("yupi");
        User user = new User();
        user.setUsername("liyupi");
        String usernameByPost = yuApiClient.getUsernameByPost(user);
        System.out.println(result);
        System.out.println(usernameByPost);
    }

}
