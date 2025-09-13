package com.nttdata.product.management.exception;


import com.mongodb.MongoSocketOpenException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.webjars.NotFoundException;
import reactor.core.publisher.Mono;

/**
 * Clase global para el manejo de excepciones en la aplicación.
 * Utiliza la anotación @ControllerAdvice para proporcionar manejo centralizado de excepciones
 * en los controladores de Spring.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

  /**
   * handleValidationException.
   *
   * @param ex (excepción)
   * @return mono
   */
  @ExceptionHandler(WebExchangeBindException.class)
  public Mono<ResponseEntity<FriendlyException>> handleValidationException(
      WebExchangeBindException ex) {
    FriendlyException friendlyException =
        new FriendlyException("La información proporcionada no es válida");
    friendlyException.setCode("400");
    Map<String, String> errors = new HashMap<>();
    ex.getFieldErrors().forEach(error -> {
      errors.put(error.getField(), error.getDefaultMessage());
      friendlyException.getMessages().add(error.getField() + " - " + error.getDefaultMessage());
    });
    return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(friendlyException));
  }

  /**
   * handleValidationException.
   *
   * @param ex (excepción)
   * @return mono
   */
  @ExceptionHandler(FriendlyException.class)
  public Mono<ResponseEntity<FriendlyException>> handleValidationException(FriendlyException ex) {
    if (ex.getCode() == null) {
      ex.setCode("409");
    }
    if (ex.getCode().equals("409")) {
      return Mono.just(ResponseEntity.status(HttpStatus.CONFLICT).body(ex));
    } else {
      return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex));
    }

  }

  /**
   * handleValidationException.
   *
   * @param ex (excepción)
   * @return mono
   */
  @ExceptionHandler(NotFoundException.class)
  public Mono<ResponseEntity<FriendlyException>> handleValidationException(NotFoundException ex) {
    FriendlyException fe = new FriendlyException(ex.getMessage());
    fe.setCode("409");
    if (fe.getCode().equals("409")) {
      return Mono.just(ResponseEntity.status(HttpStatus.CONFLICT).body(fe));
    } else {
      return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(fe));
    }

  }

  /**
   * handleRuntimeException.
   *
   * @param ex (excepción)
   * @return mono
   */
  @ExceptionHandler(MongoSocketOpenException.class)
  public Mono<ResponseEntity<FriendlyException>> handleRuntimeException(
      MongoSocketOpenException ex) {
    FriendlyException fe = new FriendlyException("Error de conexión a la base de datos");
    fe.setCode("503");
    return Mono.just(ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(fe));
  }

  /**
   * Maneja excepciones de tipo DataAccessResourceFailureException.
   * Esta excepción se lanza cuando hay un error de conexión a la base de datos.
   *
   * @param ex la excepción capturada de tipo DataAccessResourceFailureException
   * @return un Mono
   */
  @ExceptionHandler(DataAccessResourceFailureException.class)
  public Mono<ResponseEntity<FriendlyException>> handleRuntimeException(
      DataAccessResourceFailureException ex) {
    FriendlyException fe = new FriendlyException("Error de conexión a la base de datos. "
        + "Inicia el servicio de MongoDB");
    fe.setCode("503");
    return Mono.just(ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(fe));
  }

  /**
   * handleRuntimeException.
   *
   * @param ex (excepción)
   * @return mono
   */
  @ExceptionHandler(RuntimeException.class)
  public Mono<ResponseEntity<FriendlyException>> handleRuntimeException(RuntimeException ex) {
    FriendlyException fe = new FriendlyException(getExceptionDescription(ex));
    fe.setCode("409");
    if (fe.getCode().equals("409")) {
      return Mono.just(ResponseEntity.status(HttpStatus.CONFLICT).body(fe));
    } else {
      return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(fe));
    }
  }

  private String getExceptionDescription(RuntimeException ex) {
    //log.error("ExceptionHandlerAdvice.RuntimeException = {}", ex, ex);
    return Optional.ofNullable(ex.getCause())
        .map(Throwable::getMessage)
        .orElse(ex.getMessage());
  }
}
