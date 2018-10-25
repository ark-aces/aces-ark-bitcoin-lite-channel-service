package com.arkaces.ark_bitcoin_lite_channel_service.transfer;

import ark_java_client.Transaction;
import lombok.Data;

@Data
public class NewArkTransactionEvent {
    private Long contractPid;
    private String transactionId;
    private Transaction transaction;
}

