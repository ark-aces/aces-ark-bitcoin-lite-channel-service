package com.arkaces.ark_bitcoin_lite_channel_service.exchange_rate;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ExchangeRate {
    private BigDecimal rate;
    private String from;
    private String to;
}
