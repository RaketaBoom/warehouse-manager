package org.example.com.warehousemanager.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "RaketaBoom",
                        email = "rasul.gumarov.2004@gmail.com"
                ),
                description = "OpenApi documentation for Warehouse Manager",
                title = "Warehouse Manager",
                version = "1.0"
        ),
        servers = {
                @Server(
                        description = "Local environment",
                        url = "http://localhost:8080"
                )
        }
)

public class OpenApiConfig {
}
