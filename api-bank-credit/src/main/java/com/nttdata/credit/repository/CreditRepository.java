package com.nttdata.credit.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.nttdata.credit.model.Credit;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface CreditRepository extends ReactiveMongoRepository<Credit, String> {

	Flux<Credit> findAllByCustomerIdentityNumber(String customerIdentityNumber);
    Mono<Credit> findByCustomerIdentityNumber(String customerIdentityNumber);
    Mono<Credit> findByContractNumber(String contractNumber);
}
