package com.nuvyra.exercise.dto;

import java.time.LocalDateTime;
import lombok.*;

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


}
