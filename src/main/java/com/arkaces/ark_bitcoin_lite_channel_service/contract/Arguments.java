package com.arkaces.ark_bitcoin_lite_channel_service.contract;

import lombok.Data;

@Data
public class Arguments {
    private String recipientBtcAddress;
    private String returnArkAddress;
}
