package com.joel.task_master.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "TaskMaster API",
                description = "Performing CRUD operations on Employee and Task objects",
                summary = "This TaskMaster api will add, update, read and delete employees and tasks",
                termsOfService = "T&C",
                contact = @Contact(
                        name = "Joel Oliveira",
                        url = "https://joel-oliveira.netlify.app",
                        email = "joel80851@gmail.com"
                ),
                license = @License(
                        name = "Your license no.",
                        url = "https://joel-oliveira.netlify.app/"
                ),
                version = "v1"
        ),
        servers = {
                @Server(
                        description = "Dev",
                        url = "https://task-master-backend-x8cz.onrender.com/"
                ),
                @Server(
                        description = "Test",
                        url = "http://localhost:8080"
                )
        },
        security=@SecurityRequirement(
                name = "auth"
        )
)
public class OpenApiConfig {
}
