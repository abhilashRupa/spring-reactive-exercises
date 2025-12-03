package com.nuvyra.exercise.controller;

import com.nuvyra.exercise.dto.TransactionDto;
import com.nuvyra.exercise.service.FabShoppeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/fab/api")
public class FabShoppeController {

    private FabShoppeService fabShoppeService;

    public FabShoppeController(FabShoppeService fabShoppeService){
        this.fabShoppeService = fabShoppeService;
    }


    @GetMapping(path = "/all-transactions", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<Flux<TransactionDto>> getAllProductTransactions(){

        Flux<TransactionDto> transactionDtoFlux = fabShoppeService.getAllProductTransactions();

        return new ResponseEntity<>(transactionDtoFlux, HttpStatus.OK);

    }

    @GetMapping(path = "/product-detail/{productId}")
    public ResponseEntity<Mono<TransactionDto>> getProductTransaction(@PathVariable("productId") Integer itemId){
        Mono<TransactionDto> transactionDtoMono = fabShoppeService.getProductTransactionDetails(itemId);

        return new ResponseEntity<>(transactionDtoMono, HttpStatus.OK);
    }


    public Flux<TransactionDto> getProductTransactionsError() {
        Flux<TransactionDto> transactionDtoFlux = null;
        try{
            transactionDtoFlux = fabShoppeService.getProductTransactionError();
        }catch (Exception e){
            e.printStackTrace();
        }
        return transactionDtoFlux;
    }

}
