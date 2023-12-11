package az.ingress.etaksifyms.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    private final String key = "bearer-key";

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("ETASKIFY API"))
                .addSecurityItem(new SecurityRequirement().addList(key))
                .components(new Components()
                        .addSecuritySchemes(key,
                                new SecurityScheme()
                                        .name(key).type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer").bearerFormat("JWT")));
    }
}