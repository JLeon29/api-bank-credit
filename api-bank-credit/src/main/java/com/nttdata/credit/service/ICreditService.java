package com.nttdata.credit.service;

import com.nttdata.credit.model.Credit;
import com.nttdata.credit.model.dto.CreditDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICreditService {
	
	
    Flux<Credit> findAllByClientIdNumber(String clientIdNumber);
    Mono<Credit> findByContractNumber(String contractNumber);
    //Mono<Client> getClient(String clientIdNumber);
    Mono<Credit> validateClientIdNumber(String clientIdNumber);
    
    Flux<CreditDto> listCredit();
    Mono<CreditDto> findCreditById(String id);
    Mono<CreditDto> saveCredit(Mono<CreditDto> creditDto);
    Mono<CreditDto> updateCredit(Mono<CreditDto> creditDto, String id);
    Mono<Void> deleteClient(String id);
    
	
	

}
