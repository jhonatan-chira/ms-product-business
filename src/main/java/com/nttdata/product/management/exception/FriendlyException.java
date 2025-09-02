package com.nttdata.product.management.exception;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    @JsonProperty("code")
    private String code = "400";
    @JsonProperty("description")
    private String description = "Bad Request";
    //private String message;
    @JsonProperty("messages")
    private List<String> messages = new ArrayList<>();
    @JsonIgnore
    private Map<String, String> headers;

    @JsonCreator
    public FriendlyException(String description) {
        this.setCode("409");
        this.description = description;
    }

    static public FriendlyException build(String description) {
        FriendlyException fe = new FriendlyException(description);
        fe.setCode("409");
        return fe;
    }

    static Mono<FriendlyException> buildAsMono(String description) {
        FriendlyException fe = new FriendlyException(description);
        fe.setCode("409");
        return Mono.just(fe);
    }

    public FriendlyException build() {

        return this;
    }

    public static FriendlyException generate(Throwable ex) {
        FriendlyException fe = new FriendlyException(ex.getMessage());
        fe.setCode("500");
        return fe;
    }


    public <T> Mono<T> buildAsMono() {
        return Mono.error(this);
    }
}
