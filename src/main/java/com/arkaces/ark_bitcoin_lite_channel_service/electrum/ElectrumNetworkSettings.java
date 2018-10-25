package com.arkaces.ark_bitcoin_lite_channel_service.electrum;

import lombok.Data;

import java.util.List;

@Data
public class ElectrumNetworkSettings {

    private String network;
    private List<ElectrumNode> seedPeers;

}
