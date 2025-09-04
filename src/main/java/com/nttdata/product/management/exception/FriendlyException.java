package com.nttdata.product.management.exception;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import reactor.core.publisher.Mono;

/**
 * Clase que representa una excepción personalizada para manejar errores en el sistema.
 * Proporciona información detallada sobre el error, como el código,
 * descripción, mensajes adicionales y encabezados.
 */
@EqualsAndHashCode(callSuper = false)
@ToString
@JsonAutoDetect(
    creatorVisibility = JsonAutoDetect.Visibility.NONE,
    fieldVisibility = JsonAutoDetect.Visibility.NONE,
    getterVisibility = JsonAutoDetect.Visibility.NONE,
    isGetterVisibility = JsonAutoDetect.Visibility.NONE,
    setterVisibility = JsonAutoDetect.Visibility.NONE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuppressWarnings("serial")
@Schema(name = "FriendlyException", description = "Datos del error de sistema.")
@Getter
@Setter
public class FriendlyException extends RuntimeException {
  /**
   * Código del error. Por defecto, es "400".
   */
  @JsonProperty("code")
  private String code = "400";

  /**
   * Descripción del error. Por defecto, es "Bad Request".
   */
  @JsonProperty("description")
  private String description = "Bad Request";

  /**
   * Lista de mensajes adicionales relacionados con el error.
   */
  @JsonProperty("messages")
  private List<String> messages = new ArrayList<>();

  /**
   * Encabezados adicionales relacionados con el error. No se incluye en la serialización JSON.
   */
  @JsonIgnore
  private Map<String, String> headers;

  /**
   * Constructor que inicializa la excepción con una descripción específica.
   *
   * @param description Descripción del error.
   */
  @JsonCreator
  public FriendlyException(String description) {
    this.setCode("409");
    this.description = description;
  }

  /**
   * Método estático para construir una instancia de FriendlyException con una descripción
   * específica.
   *
   * @param description Descripción del error.
   * @return Una nueva instancia de FriendlyException.
   */
  public static FriendlyException build(String description) {
    FriendlyException fe = new FriendlyException(description);
    fe.setCode("409");
    return fe;
  }

  /**
   * Construye y retorna la instancia actual de FriendlyException.
   *
   * @return La instancia actual de FriendlyException.
   */
  public FriendlyException build() {
    return this;
  }

  /**
   * Método estático para construir una instancia de FriendlyException como un Mono.
   *
   * @param description Descripción del error.
   * @return Un Mono que contiene una instancia de FriendlyException.
   */
  static Mono<FriendlyException> buildAsMono(String description) {
    FriendlyException fe = new FriendlyException(description);
    fe.setCode("409");
    return Mono.just(fe);
  }

  /**
   * Construye la excepción como un Mono que emite un error.
   *
   * @param <T> Tipo genérico del Mono.
   * @return Un Mono que emite esta excepción como error.
   */
  public <T> Mono<T> buildAsMono() {
    return Mono.error(this);
  }

  /**
   * Genera una instancia de FriendlyException a partir de una excepción existente.
   *
   * @param ex Excepción original.
   * @return Una nueva instancia de FriendlyException.
   */
  public static FriendlyException generate(Throwable ex) {
    FriendlyException fe = new FriendlyException(ex.getMessage());
    fe.setCode("500");
    return fe;
  }

}