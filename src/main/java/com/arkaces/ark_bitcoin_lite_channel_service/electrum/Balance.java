package com.arkaces.ark_bitcoin_lite_channel_service.electrum;

import lombok.Data;

import java.math.BigDecimal;

/*
Example response (note: balances in satoshis, not btc):

{
  "confirmed": "1.03873966",
  "unconfirmed": "0.236844"
}
 */
@Data
public class Balance {
    private BigDecimal confirmed;
    private BigDecimal unconfirmed;
}
