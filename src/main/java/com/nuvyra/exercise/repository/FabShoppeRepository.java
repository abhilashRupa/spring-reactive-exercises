package com.nuvyra.exercise.repository;

import com.nuvyra.exercise.entity.Transaction;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Random;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Repository
public class FabShoppeRepository {

  private String[] transactionIds = {
    "FS000", "FS001", "FS002", "FS003", "FS004", "FS005", "FS006", "FS007", "FS008", "FS009"
  };
  private Integer[] itemIds = {
    10001, 10002, 10003, 10004, 10005, 10006, 10007, 10008, 10009, 10010
  };

  private final Random random = new Random();

  public Flux<Transaction> findAllTransactions() {

    return Flux.range(0, 10)
        .delayElements(Duration.ofSeconds(2))
        .doOnNext(i -> log.info("Transaction count: " + (i + 1)))
        .map(
            i ->
                new Transaction(
                    transactionIds[i],
                    LocalDateTime.now(),
                    itemIds[i],
                    this.random.nextDouble(1000.00, 15000.00)));
  }

  public Mono<Transaction> findByItemId(int itemId) {

    return Flux.range(0, 10)
        .delayElements(Duration.ofSeconds(2))
        .doOnNext(i -> log.info("Transaction count: " + (i + 1)))
        .map(
            i ->
                new Transaction(
                    transactionIds[i],
                    LocalDateTime.now(),
                    itemIds[i],
                    this.random.nextDouble(1000.00, 15000.00)))
        .filter(i -> i.getItemId().equals(itemId))
        .next();
  }
}
