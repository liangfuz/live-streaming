package com.easy.live.streaming.data.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Description:swagger配置
 * @Author: zhangliangfu
 * @Create on: 2019-08-15 15:39
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    public static final String SWAGGER_SCAN_BASE_PACKAGE = "com.easy";
    public static final String VERSION = "1.0.0";

    ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("SpringCloud搭建直播API")
                .description("视频直播对接文档\n系统搭建完毕统一走网关（8004）服务，对应的api需要加服务名前缀，如\"/video-server/、/auth-server/、/user" +
                        "-server/\"等")
                .license("EASY-LIFE")
                .licenseUrl("http://live.registry.com:80001")
                .termsOfServiceUrl("")
                .version(VERSION)
                .build();
    }

    @Bean
    public Docket customImplementation(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(SWAGGER_SCAN_BASE_PACKAGE))
                .paths(PathSelectors.any())
                .build();
    }
}