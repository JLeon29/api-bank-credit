package com.nttdata.credit.service;

import com.nttdata.credit.dto.Customer;
import com.nttdata.credit.model.Credit;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICreditService extends ICrudService<Credit, String> {
	
	Mono<Credit> findByContractNumber(String contractNumber);
	Mono<Customer> getCustomer(String customerIdentityNumber);
	Flux<Credit> findAllByCustomerIdentityNumber(String customerIdentityNumber);
	Mono<Credit> validateCustomerIdentityNumber(String customerIdentityNumber);
    

}
