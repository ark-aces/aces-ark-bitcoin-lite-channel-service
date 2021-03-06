package com.arkaces.ark_bitcoin_lite_channel_service.transfer;

import com.arkaces.ark_bitcoin_lite_channel_service.contract.ContractEntity;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "transfers")
public class TransferEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pid;

    private String id;
    private LocalDateTime createdAt;
    private String status;
    private String arkTransactionId;

    @Column(precision = 20, scale = 8)
    private BigDecimal arkAmount;

    @Column(precision = 20, scale = 8)
    private BigDecimal arkToBtcRate;

    @Column(precision = 20, scale = 8)
    private BigDecimal arkFlatFee;

    @Column(precision = 20, scale = 8)
    private BigDecimal arkPercentFee;

    @Column(precision = 20, scale = 8)
    private BigDecimal arkTotalFee;

    @Column(precision = 20, scale = 8)
    private BigDecimal btcSendAmount;

    private String btcTransactionId;

    private Boolean needsBtcConfirmation;

    private String btcConfirmationSubscriptionId;

    private Boolean needsArkReturn;

    private String returnArkTransactionId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contract_pid")
    private ContractEntity contractEntity;
}
