package com.arkaces.ark_bitcoin_lite_channel_service.contract;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractRepository extends JpaRepository<ContractEntity, Long> {

    ContractEntity findOneById(String id);

    ContractEntity findOneByCorrelationId(String correlationId);

}
