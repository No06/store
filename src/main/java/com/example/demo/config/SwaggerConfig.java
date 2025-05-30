package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;

@Configuration
@OpenAPIDefinition(
    info = @io.swagger.v3.oas.annotations.info.Info(
        title = "Store API",
        version = "1.0",
        description = "接口文档描述",
        contact = @Contact(name = "Support Team")
    )
)
public class SwaggerConfig {
}