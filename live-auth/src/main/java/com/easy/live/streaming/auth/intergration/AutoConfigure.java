package com.easy.live.streaming.auth.intergration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class AutoConfigure extends WebMvcConfigurerAdapter {

	@Bean
	StartupRunner startupRunner() {
		return new StartupRunner();
	}

	@Bean
	ApplicationDestroy applicationDestroy() {
		return new ApplicationDestroy();
	}
	
}
