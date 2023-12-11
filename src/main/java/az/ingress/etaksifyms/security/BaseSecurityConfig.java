package az.ingress.etaksifyms.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity
@RequiredArgsConstructor
@Configuration
@EnableMethodSecurity
public class BaseSecurityConfig {
}
