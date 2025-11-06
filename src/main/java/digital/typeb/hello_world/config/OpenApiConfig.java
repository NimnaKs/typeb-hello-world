package digital.typeb.hello_world.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI helloWorldOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("TypeB Hello World API")
                        .description("Simple /hello-world API for the TypeB Digital assignment")
                        .version("v1")
                        .contact(new Contact()
                                .name("TypeB Digital")
                                .email("support@typeb.digital")));
    }
}