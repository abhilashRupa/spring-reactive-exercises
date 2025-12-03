package com.nuvyra.exercise.controller;

import com.nuvyra.exercise.configuration.WebClintConfiguration;
import com.nuvyra.exercise.dto.TransactionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class FabShoppeController {

  private WebClintConfiguration webClintConfiguration;

  public FabShoppeController(WebClintConfiguration webClintConfiguration) {
    this.webClintConfiguration = webClintConfiguration;
  }

  @GetMapping(path = "/find-all", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Flux<TransactionDto> findAll() {

    return webClintConfiguration
        .getWebClient()
        .get()
        .uri("/all-transactions")
        .retrieve()
        .bodyToFlux(TransactionDto.class);
  }


  @GetMapping(path = "/find/{transactionId}")
  public Mono<TransactionDto> findById(@PathVariable("transactionId") String transactionId){

    return webClintConfiguration.getWebClient()
            .get()
            .uri("/product-detail/"+transactionId)
            .retrieve()
            .onStatus(HttpStatus.NOT_FOUND::equals, clientResponse -> Mono.empty())
            .bodyToMono(TransactionDto.class);

  }


}
