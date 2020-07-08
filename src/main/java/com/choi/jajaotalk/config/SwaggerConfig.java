package com.choi.jajaotalk.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    private String version;
    private String title;

    @Bean
    public Docket apiV1(){
        version = "V1";
        title = "jajao_talk API" + version;

        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .groupName(version)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.choi.jajaotalk.api"))
                .build()
                .apiInfo(apiInfo(title,version));
    }

    private ApiInfo apiInfo(String title, String version) {
        return new ApiInfo(
                title,
                "Swagger로 생성한 API Docs",
                version,
                "localhost:8080",
                new Contact("Contact Me", "localhost:8080", "ghtjd753753@gmail.com"),
                "Licenses",

                "localhost:8080",

                new ArrayList<>());
    }
}
