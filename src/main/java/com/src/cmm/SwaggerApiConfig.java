package com.src.cmm;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "스케줄러 API",
        version = "1.0",
        description = "API documentation for 스케줄러"
    )
)
public class SwaggerApiConfig {
}

