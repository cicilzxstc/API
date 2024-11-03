package com.example.yuapiclientsdk;

import com.example.yuapiclientsdk.client.YuApiClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "yuapi.client")
@Data
// 注意这个注解！！！！
@ComponentScan
public class YuApiClientConfig {
    private String accessKey;
    private String secretKey;

    @Bean
    public YuApiClient yuApiClient(){
        return new YuApiClient(accessKey,secretKey);
    }
}
