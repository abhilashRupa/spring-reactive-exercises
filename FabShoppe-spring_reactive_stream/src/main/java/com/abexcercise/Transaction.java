package com.abexcercise;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    private String transactionId;
    private LocalDateTime transactionTime;
    private Integer itemId;
    private Double amount;
}
