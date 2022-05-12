package com.nttdata.credit.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.nttdata.credit.dto.Customer;
import com.nttdata.credit.model.Credit;
import com.nttdata.credit.repository.CreditRepository;
import com.nttdata.credit.service.ICreditService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CreditServiceImpl implements ICreditService {
	
	 private static final Logger log = LoggerFactory.getLogger(CreditServiceImpl.class);
	
	@Autowired
    WebClient.Builder webClientBuilder;
	
	@Autowired
	private CreditRepository repository;

	@Override
	public Mono<Credit> create(Credit o) {
		 return repository.save(o);
    }

	@Override
	public Flux<Credit> findAll() {
		return repository.findAll();
    }

	@Override
	public Mono<Credit> findById(String id) {
		 return repository.findById(id);
    }

	@Override
	public Mono<Credit> update(Credit o) {
		return repository.save(o);
    }

	@Override
	public Mono<Void> delete(Credit o) {
		return repository.delete(o);
    }

	@Override
	public Mono<Credit> findByContractNumber(String contractNumber) {
		return repository.findByContractNumber(contractNumber);
	}

	@Override
	public Mono<Customer> getCustomer(String customerIdentityNumber) {
		return webClientBuilder.build()
                .get()
                .uri("localhost:9000/api/customer/findCustomerCredit/"+customerIdentityNumber)
                .retrieve()
                .bodyToMono(Customer.class)
                .doOnNext(c -> log.info("Customer response: {}", c.getName()) );
    }

	@Override
	public Flux<Credit> findAllByCustomerIdentityNumber(String customerIdentityNumber) {
		return repository.findAllByCustomerIdentityNumber(customerIdentityNumber);
    }

	@Override
	public Mono<Credit> validateCustomerIdentityNumber(String customerIdentityNumber) {
		return repository.findByCustomerIdentityNumber(customerIdentityNumber)
                .switchIfEmpty(Mono.just(Credit.builder().customerIdentityNumber(null).build()));
    }

	

	

}
