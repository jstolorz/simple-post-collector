package com.example.simplepostcollector;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableScheduling
@Configuration
@EnableSwagger2
class PostsConfiguration {

    public Docket docket(){
       return new Docket(DocumentationType.OAS_30)
               .select()
               .apis(RequestHandlerSelectors.basePackage("com.example.simplepostcollector"))
               .build();
    }
}
