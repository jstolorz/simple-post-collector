package com.example.simplepostcollector;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@EnableScheduling
@Configuration
class PostsConfiguration {


    @Bean
    public Docket docket(){
       return new Docket(DocumentationType.SWAGGER_2)
               .select()
               .paths(PathSelectors.ant("/posts/**"))
               .apis(RequestHandlerSelectors.basePackage("com.example.simplepostcollector"))
               .build().apiInfo(createApiInfo());
    }

    private ApiInfo createApiInfo() {
        return new ApiInfo("Posta API",
                "Post database",
                "1.00",
                "http://bluesoft.com",
                new Contact("Janusz","http://bluesoft.com","jstolorz@gmail.com"),
                "my own licence",
                "http://bluesoft.com",
                Collections.emptyList()
                );
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }


}
