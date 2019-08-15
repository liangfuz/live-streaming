package com.easy.live.streaming.video.inteceptor;

import com.easy.live.streaming.data.filter.AcquireUserInfoInterceptor;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Description: WEB拦截器
 * Author: zhangliangfu
 * Create on: 2019-07-26 14:57
 */

@Configuration
@AutoConfigureAfter(WebMvcConfigurerAdapter.class)
public class WebInterceptor extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AcquireUserInfoInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/swagger-resources/**");
    }
}
