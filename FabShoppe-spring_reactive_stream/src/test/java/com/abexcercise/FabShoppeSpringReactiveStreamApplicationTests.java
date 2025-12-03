package com.abexcercise;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class FabShoppeSpringReactiveStreamApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void testFabShoppe(){

		List<Transaction> transactionList = new ArrayList<>();

		transactionList.add(new Transaction("FS000", LocalDateTime.now(), 10001, 1000.00));
		transactionList.add(new Transaction("FS001", LocalDateTime.now(), 10002, 1100.00));
		transactionList.add(new Transaction("FS002", LocalDateTime.now(), 10003, 1300.00));
		transactionList.add(new Transaction("FS003", LocalDateTime.now(), 10004, 1500.00));
		transactionList.add(new Transaction("FS004", LocalDateTime.now(), 10005, 2000.00));

		Flux<Transaction> transactionFlux = Flux.fromIterable(transactionList).delayElements(Duration.ofSeconds(2)).log();


		StepVerifier
				.withVirtualTime(() -> transactionFlux)
				.expectSubscription()
				.expectNoEvent(Duration.ofSeconds(1))
				.expectNext(transactionList.get(0))
				.expectNext(transactionList.get(1), transactionList.get(2), transactionList.get(3), transactionList.get(4))
				.verifyComplete();


	}

}
