package com.easy.live.streaming.registry.intergration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;

public class StartupRunner implements CommandLineRunner {
	
	Logger logger  = LoggerFactory.getLogger(StartupRunner.class);

	@Override
    public void run(String... args) throws Exception {
		logger.info("注册中心服务启动成功");
    }
}
