package com.example.yuapiinterface;

import com.example.yuapiclientsdk.YuApiClientConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(YuApiClientConfig.class)
public class YuapiInterfaceApplication {

    public static void main(String[] args) {
        SpringApplication.run(YuapiInterfaceApplication.class, args);
    }

}
