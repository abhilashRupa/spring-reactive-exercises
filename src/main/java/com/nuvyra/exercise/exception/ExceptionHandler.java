package com.nuvyra.exercise.exception;

import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
public class ExceptionHandler extends AbstractErrorWebExceptionHandler {

  public ExceptionHandler(
      ErrorAttributes errorAttributes,
      WebProperties.Resources resource,
      ApplicationContext applicationContext,
      ServerCodecConfigurer serverCodecConfigurer) {
    super(errorAttributes, resource, applicationContext);
    this.setMessageReaders(serverCodecConfigurer.getReaders());
    this.setMessageWriters(serverCodecConfigurer.getWriters());
  }

  @Override
  protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {

    return RouterFunctions.route(RequestPredicates.all(), this::handleError);
  }

  private Mono<ServerResponse> handleError(ServerRequest serverRequest) {

    Map<String, Object> error =
        this.getErrorAttributes(serverRequest, ErrorAttributeOptions.defaults());

    Mono<ServerResponse> responseMono =
        ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(BodyInserters.fromValue(error));

    return responseMono;
  }
}
