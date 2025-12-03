package com.nuvyra.exercise.entity;

import java.time.LocalDateTime;

public class Transaction {

    private String transactionId;
    private LocalDateTime transactionTime;
    private Integer itemId;
    private Double amount;

    public Transaction(String transactionId, LocalDateTime transactionTime, Integer itemId, Double amount) {
        this.transactionId = transactionId;
        this.transactionTime = transactionTime;
        this.itemId = itemId;
        this.amount = amount;
    }

    public Transaction() {
        super();
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public LocalDateTime getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(LocalDateTime transactionTime) {
        this.transactionTime = transactionTime;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId='" + transactionId + '\'' +
                ", transactionTime=" + transactionTime +
                ", itemId=" + itemId +
                ", amount=" + amount +
                '}';
    }
}
