package com.nttdata.product.management.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Configuración para el cliente WebClient en la aplicación.
 * Proporciona un bean de WebClient personalizado para realizar solicitudes HTTP reactivas.
 */
@Configuration
public class WebClientConfig {

  /**
   * Crea y configura un bean de WebClient.
   * Este cliente puede ser utilizado para realizar solicitudes HTTP reactivas.
   *
   * @return Una instancia de WebClient configurada.
   */
  @Bean
  public WebClient customerWebClient() {
    return WebClient.builder()
        //.baseUrl("http://localhost:8086/api/v1/customer") //
        // URL base comentada para personalización futura.
        .build();
  }
}
