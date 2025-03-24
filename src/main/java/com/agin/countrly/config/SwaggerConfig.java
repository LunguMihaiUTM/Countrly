package com.agin.countrly.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
/*@OpenAPIDefinition(
        servers = {
                @Server(
                        url = "https://exciting-wonder-production.up.railway.app",
                        description = "Server de producție"
                )
        }
)*/
public class SwaggerConfig {
//    @Value("${application.name}")
//    private String applicationName;
//
//    @Value("${application.version}")
//    private String buildVersion;


    @Bean
    GroupedOpenApi openApi() {
        return GroupedOpenApi.builder()
                .group("public-apis")
                .pathsToMatch("/**")
                .build();
    }


//    @Bean
//    OpenAPI customOpenAPI() {
//        return new OpenAPI()
//                .info(
//                        new Info()
//                                .title("PreorderBack API")
//                                .version(applicationName.concat("_".concat(buildVersion)))
//                ).components(new Components());
//    }
}
