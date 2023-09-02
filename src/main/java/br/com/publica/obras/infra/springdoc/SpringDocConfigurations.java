package br.com.publica.obras.infra.springdoc;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfigurations {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Desafio: Gerenciamento de Obras API ")
                        .description("O desafio requer a implementação de uma " +
                                     "API de gerenciamento de obras em Java, com recursos de cadastro, " +
                                     "consulta e listagem, atendendo aos requisitos técnicos especificados e " +
                                     "seguindo boas práticas de programação. ")
                        .contact(new Contact()
                                .name("Email: Rodrigo Andare Filho")
                                .email("rodrigoandare.f@gmail.com"))
                        .version("1.0.0"))
                .externalDocs(new ExternalDocumentation()
                        .description("Repositório do GitHub")
                        .url("https://github.com/rodrigoandarefilho/GerenciamentoObras"))
                .externalDocs(new ExternalDocumentation()
                        .description("Linkedin: Rodrigo Andare Filho")
                        .url("https://www.linkedin.com/in/rodrigoandarefilho/"));
    }
}


