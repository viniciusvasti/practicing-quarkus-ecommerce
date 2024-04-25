package org.vas;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;

import jakarta.ws.rs.core.Application;

@OpenAPIDefinition(info = @Info(title = "E-commerce Monolith",
description = "A monolithic e-commerce application supporting product inventory and pricing management, order and payment processing.",
version = "1.0.0", contact = @Contact(name = "Vinícius A. Santos", url = "https://vinisantos.dev", email = "vinicius.vas.ti@gmail.com"), license = @License(name = "MIT")))
public class EcommerceMonolithApp extends Application {

}
