package com.easy.live.streaming.gateway.intergration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;

public class StartupRunner implements CommandLineRunner {
	
	Logger logger  = LoggerFactory.getLogger(StartupRunner.class);

	@Value("${spring.profiles.active:default}")
	private String env;

	@Value("${spring.cloud.config.profile:default}")
	private String configEnv;

	@Override
    public void run(String... args) throws Exception {
		logger.info("spring.profiles.active:{}",env);
		logger.info("spring.cloud.config.profile:{}",configEnv);
		logger.info("网关服务启动成功");
    }
}
