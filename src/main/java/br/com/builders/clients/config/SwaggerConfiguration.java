package br.com.builders.clients.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(details())
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.builders.clients.rest.v1"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo details() {
        return new ApiInfo(
                "API para controle de cadastro de clientes.",
                "Este serviço oferece gerenciamento do cadastro de clientes.",
                "1.0",
                "Apenas para efeito de estudo.",
                new Contact("Felipe Sampaio", "", "felipe@sampaio.us"),
                "License Version",
                "License Version", new ArrayList<>()
        );
    }
}
