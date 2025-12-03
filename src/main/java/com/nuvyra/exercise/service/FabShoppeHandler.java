package com.nuvyra.exercise.service;

import com.nuvyra.exercise.dto.TransactionDto;
import com.nuvyra.exercise.entity.Transaction;
import com.nuvyra.exercise.repository.FabShoppeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class FabShoppeHandler {

    @Autowired
    private FabShoppeRepository fabShoppeRepository;


    public Mono<ServerResponse> getAllProductTransactions(ServerRequest request) {

        Flux<Transaction> transactionFlux = fabShoppeRepository.findAllTransactions();
        Flux<TransactionDto> transactionDtoFlux = transactionFlux.map(TransactionDto::entityToDto);

        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(transactionDtoFlux, TransactionDto.class);

    }

    public Mono<ServerResponse> getProductTransactionDetails(ServerRequest request) {
        int productId = Integer.parseInt(request.pathVariable("productId"));

        Mono<Transaction> transactionDtoMono = fabShoppeRepository.findByItemId(productId);
        Mono<TransactionDto> transactionDtoMono1 = transactionDtoMono.map(transaction -> TransactionDto.entityToDto(transaction));

        return ServerResponse.ok()
                .body(transactionDtoMono1, TransactionDto.class);


    }

    public Mono<ServerResponse> getAllProductTransactionsError(ServerRequest request) {

        Flux<Transaction> transactionFlux = fabShoppeRepository.findAllTransactions();
        Flux<TransactionDto> transactionDtoFlux = transactionFlux.map(TransactionDto::entityToDto);

        throw  new RuntimeException("Get All transaction failed");

    }
}
