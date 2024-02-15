package GDSCKNU.VitaBelly.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class swaggerConfig {
    @Bean
    public OpenAPI api(){
        Info info = new Info()
                .title("VitaBelly-BE-Server API Document")
                .version("v0.0.1")
                .description("VitaBelly BE Server api 명세서입니다.");

        return new OpenAPI()
                .components(new Components())
                .info(info);
    }
}
