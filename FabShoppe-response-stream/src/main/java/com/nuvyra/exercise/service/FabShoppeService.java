package com.nuvyra.exercise.service;

import com.nuvyra.exercise.dto.TransactionDto;
import com.nuvyra.exercise.repository.FabShoppeRepository;
import com.nuvyra.exercise.entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class FabShoppeService {

    @Autowired
    private FabShoppeRepository fabShoppeRepository;


    public Flux<TransactionDto> getAllProductTransactions() {
       Flux<Transaction> transactionFlux = fabShoppeRepository.findAllTransactions();

       return transactionFlux.map(TransactionDto::entityToDto);


    }

    public Mono<TransactionDto> getProductTransactionDetails(Integer itemId) {
      Mono<Transaction> transactionMono =  fabShoppeRepository.findByItemId(itemId);

      return transactionMono.map(TransactionDto::entityToDto);


    }

    public Flux<TransactionDto> getProductTransactionError() throws Exception {

        Flux<Transaction> transactionFlux = fabShoppeRepository.findAllTransactions();
        Flux<TransactionDto> transactionDtoFlux = transactionFlux.map(TransactionDto::entityToDto);
        Exception exception = new RuntimeException();

        throw exception;
    }
}
