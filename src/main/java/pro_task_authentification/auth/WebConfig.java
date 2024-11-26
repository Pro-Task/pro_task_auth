package pro_task_authentification.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // CORS config
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000") // frontend
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // methods
                .allowedHeaders("*") // heads
                .allowCredentials(true); // cookie and auth
    }
}
