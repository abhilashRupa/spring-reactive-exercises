package com.nuvyra.exercise.dto;

import com.nuvyra.exercise.entity.Transaction;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionDto {
  private String transactionId;
  private LocalDateTime transactionTime;
  private Integer itemId;
  private Double amount;

  public static TransactionDto entityToDto(Transaction transaction) {

    return TransactionDto.builder()
        .transactionId(transaction.getTransactionId())
        .transactionTime(transaction.getTransactionTime())
        .itemId(transaction.getItemId())
        .amount(transaction.getAmount())
        .build();
  }

  public Transaction DtoToEntity() {

    Transaction transaction = new Transaction();
    transaction.setTransactionId(this.getTransactionId());
    transaction.setTransactionTime(this.getTransactionTime());
    transaction.setItemId(this.getItemId());
    transaction.setAmount(this.getAmount());

    return transaction;
  }
}
