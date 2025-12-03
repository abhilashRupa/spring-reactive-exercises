package com.nuvyra.exercise.controller;

import com.nuvyra.exercise.service.FabShoppeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class FabShoppeController {


    @Autowired
    private FabShoppeHandler fabShoppeHandler;

    @Bean
    public RouterFunction<ServerResponse> routerFunction(){
        return RouterFunctions.route()
                .GET("/fab/api/all-transactions", request -> fabShoppeHandler.getAllProductTransactions(request))
                .GET("/fab/api/product-detail/{productId}", request -> fabShoppeHandler.getProductTransactionDetails(request))
                .GET("/fab/api/transaction/error", request -> fabShoppeHandler.getAllProductTransactionsError(request))
                .build();
    }

}
