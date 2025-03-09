package com.marketcar.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ApplicationConfig {

    @Bean
    fun customOpenApi(
        @Value("\${spring.application.name}") appName: String?,
        @Value("\${spring.main.web-application-name}") appVersion: String?
    ): OpenAPI {
        val contact = Contact()
        contact.email = "contactdevhanspeter@gmail.com"
        contact.name = "Hanspeter Dietiker"
        contact.url = "https://www.linkedin.com/in/hanspeterdietiker/"
        return OpenAPI()
            .info(
                Info()
                    .title(appName)
                    .version(appVersion)
                    .description("My API")
                    .contact(contact)


            )
    }
}