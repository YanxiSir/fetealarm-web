package com.yanxisir.fetealarm.config;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

/**
 * @author YanxiSir
 * @since 2018/4/24
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket frontedApi() {

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("feteAlarmAPI")
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.yanxisir.fetealarm.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(frontedApiInfo());
    }


    private ApiInfo frontedApiInfo() {
        return new ApiInfoBuilder()
                .title("fete-alarm Api")
                .description("fete-alarm Api")
                .version("1.0")
                .build();
    }
}
