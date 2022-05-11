package com.nttdata.credit.service;

import com.nttdata.credit.dto.CreditDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICreditService {
	
	
	Mono<CreditDto> findByContractNumber(String contractNumber);
    //Mono<ClientDto> getClient(String clientDtoIdNumber);
    //Flux<Credit> findAllByClientIdNumber(String clientIdNumber);
    //Mono<Credit> validateClientIdNumber(String clientIdNumber);
    Flux<CreditDto> findAllCredit();
    Mono<CreditDto> findByIdCredit(String idCreditDto);
    Mono<CreditDto> saveCredit(Mono<CreditDto> creditDto);
    Mono<CreditDto> updateCreditDto(Mono<CreditDto> creditDto, String idCreditDto);
    Mono<Void> deleteCredit (String idCreditDto);
    

}
