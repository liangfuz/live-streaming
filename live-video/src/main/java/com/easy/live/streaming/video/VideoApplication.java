package com.easy.live.streaming.video;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @Description:视频直播
 * @Author: zhangliangfu
 * @Create on: 2019-06-13 17:56
 */

@ComponentScan(basePackages={"com.easy"})
@EnableJpaRepositories(basePackages = "com.easy")
@EntityScan(basePackages = "com.easy")
@SpringBootApplication
@EnableDiscoveryClient
public class VideoApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(VideoApplication.class).web(true).run(args);
    }
}
