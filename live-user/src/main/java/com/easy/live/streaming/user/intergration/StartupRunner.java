package com.easy.live.streaming.user.intergration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;

public class StartupRunner implements CommandLineRunner {
	
	Logger logger  = LoggerFactory.getLogger(StartupRunner.class);

	@Value("${spring.profiles.active:dev}")
	private String env;

	@Value("${spring.cloud.config.profile:dev}")
	private String configEnv;

	@Override
    public void run(String... args) {
		logger.warn("spring.profiles.active:{}",env);
		logger.warn("spring.cloud.config.profile:{}",configEnv);
		logger.warn("用户服务启动成功");
    }
}
