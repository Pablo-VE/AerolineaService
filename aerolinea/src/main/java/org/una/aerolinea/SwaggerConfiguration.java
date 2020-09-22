/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerolinea;


import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 * @author Pablo-VE
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(
                        RequestHandlerSelectors
                                .basePackage("org.una.aerolinea.controllers"))
                .paths(PathSelectors.regex("/.*"))
                .build()
                .apiInfo(apiInfo())
                .tags(new Tag("Seguridad", "Metodos de seguridad"),
                         new Tag("Usuarios", "Manejo de usuarios"),
                         new Tag("Empleados", "Manejo de empleados"),
                         new Tag("Roles", "Manejo de roles"),
                         new Tag("Transacciones", "Manejo de transacciones"),
                         new Tag("Trabajos_Empleados", "Manejo de los trabajos de los empleados"),
                         new Tag("Areas_Trabajos", "Manejo de areas de trabajos"),
                         new Tag("Parametros_Aplicacion", "Manejo de parametros de la aplicacion")
                        
                );

    }
    
    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Aerolinea UNA",
                "Rest API sobre la administracion de una aerolinea.",
                "Versión:2.1.0",
                "https://google.com",
                new Contact("UNA Sede Región Brunca", "https://srb.una.ac.cr/index.php/es/", "decanatosrb@una.cr"),
                "Apache-2.0", "http://www.apache.org/licenses/LICENSE-2.0", Collections.emptyList());
    }
    
}
