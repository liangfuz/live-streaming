package com.easy.live.streaming.registry;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Description:注册中心
 * @Author: zhangliangfu
 * @Create on: 2019-06-10 17:23
 */

@SpringBootApplication
@EnableEurekaServer
public class RegistryApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(RegistryApplication.class).web(true).run(args);
    }
}
