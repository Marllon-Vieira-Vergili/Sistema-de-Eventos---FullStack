package com.marllon.vieira.vergili.eventos.gerenciamento_eventos.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*Configuração de CORS
*
* CORS- Permitir React de se comunicar com este Backend e fazer Requisições
* */

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry){
        //Permite que o frontend(Rodando em localhost:5173) faça requisições
        registry.addMapping("/api/**") //Aplica para todas as rotas que começam com /api
                .allowedOrigins("http://localhost:5173",
                        "http://localhost:5174") //Endereço do React
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*") //Permitir todos os cabeçalhos
                .allowCredentials(true); //Permite envio de cookies/credenciais
    }
}
