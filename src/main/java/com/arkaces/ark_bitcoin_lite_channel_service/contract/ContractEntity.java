package com.arkaces.ark_bitcoin_lite_channel_service.contract;

import com.arkaces.ark_bitcoin_lite_channel_service.transfer.TransferEntity;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "contracts")
public class ContractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pid;

    private String id;
    private String correlationId;
    private String status;
    private LocalDateTime createdAt;
    private String recipientBtcAddress;
    private String returnArkAddress;
    private String depositArkAddress;
    private String depositArkAddressPassphrase;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contractEntity")
    private List<TransferEntity> transferEntities = new ArrayList<>();
    
}
