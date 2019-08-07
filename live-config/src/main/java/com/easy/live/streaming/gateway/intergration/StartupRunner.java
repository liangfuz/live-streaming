package com.easy.live.streaming.gateway.intergration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;

public class StartupRunner implements CommandLineRunner {
	
	Logger logger  = LoggerFactory.getLogger(StartupRunner.class);

	@Override
    public void run(String... args) throws Exception {
		logger.warn("配置中心服务启动成功");
    }
}
