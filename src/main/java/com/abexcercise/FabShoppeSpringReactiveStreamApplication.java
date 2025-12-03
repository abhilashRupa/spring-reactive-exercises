package com.abexcercise;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class FabShoppeSpringReactiveStreamApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(FabShoppeSpringReactiveStreamApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		List<Transaction> transactionList = new ArrayList<>();

		transactionList.add(new Transaction("FS000", LocalDateTime.now(), 10001, 1000.00));
		transactionList.add(new Transaction("FS001", LocalDateTime.now(), 10002, 1100.00));
		transactionList.add(new Transaction("FS002", LocalDateTime.now(), 10003, 1300.00));
		transactionList.add(new Transaction("FS003", LocalDateTime.now(), 10004, 1500.00));
		transactionList.add(new Transaction("FS004", LocalDateTime.now(), 10005, 2000.00));

		Flux<Transaction> transactionFlux = Flux.fromIterable(transactionList);

		transactionFlux.delayElements(Duration.ofSeconds(2)).log().subscribe(v -> System.out.println(v));


	}
}
