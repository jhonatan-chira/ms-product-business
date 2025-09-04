package com.nttdata.product.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal de la aplicación Spring Boot.
 * Marca el punto de entrada de la aplicación.
 */
@SpringBootApplication
public class Application {

  /**
   * Método principal que inicia la aplicación Spring Boot.
   *
   * @param args Argumentos de línea de comandos.
   */
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

}