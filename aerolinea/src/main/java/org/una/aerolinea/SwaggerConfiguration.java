/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aerolinea;


import static io.swagger.annotations.ApiKeyAuthDefinition.ApiKeyLocation.HEADER;
import java.util.Collections;
import static java.util.Collections.singletonList;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 * @author Pablo-VE
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    
    /*@Bean
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
                        new Tag("Parametros_Aplicacion", "Manejo de parametros de la aplicacion"),
                        new Tag("Horarios", "Manejo de horarios de los empleados"),
                        new Tag("Horas_Marcajes", "Manejo de las horas de marcaje de los empleados"),
                        new Tag("Servicios_Aeropuerto", "Manejo de los servicios que ofrece el aeropuerto"),
                        new Tag("Aviones", "Manejo de los aviones"),
                        new Tag("Aerolineas", "Manejo de las aerolineas del aeropuerto"),
                        new Tag("Tipos_Aviones", "Manejo de los tipos de aviones"),
                        new Tag("Vuelos", "Manejo de los vuelos que ofrece el aeropuerto"),
                        new Tag("Alertas", "Manejo de las alertas que pueden surgir en una solicitud de vuelo"),
                        new Tag("Rutas", "Manejo de las rutas que se ofrecen para los vuelos")
                        
                );

    }*/
    
    
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .securitySchemes(singletonList(new ApiKey("JWT", AUTHORIZATION, HEADER.name())))
                .securityContexts(singletonList(
                        SecurityContext.builder()
                                .securityReferences(
                                        singletonList(SecurityReference.builder()
                                                .reference("JWT")
                                                .scopes(new AuthorizationScope[0])
                                                .build()
                                        )
                                )
                                .build())
                )
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
                        new Tag("Parametros_Aplicacion", "Manejo de parametros de la aplicacion"),
                        new Tag("Horarios", "Manejo de horarios de los empleados"),
                        new Tag("Horas_Marcajes", "Manejo de las horas de marcaje de los empleados"),
                        new Tag("Servicios_Aeropuerto", "Manejo de los servicios que ofrece el aeropuerto"),
                        new Tag("Aviones", "Manejo de los aviones"),
                        new Tag("Aerolineas", "Manejo de las aerolineas del aeropuerto"),
                        new Tag("Tipos_Aviones", "Manejo de los tipos de aviones"),
                        new Tag("Vuelos", "Manejo de los vuelos que ofrece el aeropuerto"),
                        new Tag("Alertas", "Manejo de las alertas que pueden surgir en una solicitud de vuelo"),
                        new Tag("Rutas", "Manejo de las rutas que se ofrecen para los vuelos")
                );

    }
    
    
    
    
    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Aeropuerto UNA",
                "Rest API sobre la administracion de un aeropuerto.",
                "Versión:2.1.0",
                "https://google.com",
                new Contact("UNA Sede Región Brunca", "https://srb.una.ac.cr/index.php/es/", "decanatosrb@una.cr"),
                "Apache-2.0", "http://www.apache.org/licenses/LICENSE-2.0", Collections.emptyList());
    }
    
}
