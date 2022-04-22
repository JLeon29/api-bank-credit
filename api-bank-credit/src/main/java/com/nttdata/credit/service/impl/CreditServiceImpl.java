package com.nttdata.credit.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.nttdata.credit.app.utils.AppUtils;
import com.nttdata.credit.model.Credit;
import com.nttdata.credit.model.dto.CreditDto;
import com.nttdata.credit.repository.CreditRepository;
import com.nttdata.credit.service.ICreditService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class CreditServiceImpl implements ICreditService{
	
	@Autowired
	private CreditRepository creditRepository;

	@Override
	public Flux<Credit> findAllByClientIdNumber(String clientIdNumber) {
		return creditRepository.findAllByClientIdNumber(clientIdNumber);
	}

	@Override
	public Mono<Credit> findByContractNumber(String contractNumber) {
		return creditRepository.findByContractNumber(contractNumber);
	}

	@Override
	public Mono<Credit> validateClientIdNumber(String clientIdNumber) {
		return creditRepository.findByClientIdNumber(clientIdNumber);
	}

	@Override
	public Flux<CreditDto> listCredit() {
		return creditRepository.findAll().map(AppUtils::entityToDto);
	}

	@Override
	public Mono<CreditDto> findCreditById(String id) {
		return creditRepository.findById(id).map(AppUtils::entityToDto);
	}

	@Override
	public Mono<CreditDto> saveCredit(Mono<CreditDto> creditDto) {
		return creditDto.map(AppUtils::dtoToEntity)
                .flatMap(creditRepository::insert)
                .map(AppUtils::entityToDto);
	}

	@Override
	public Mono<CreditDto> updateCredit(Mono<CreditDto> creditDto, String id) {
		return creditRepository.findById(id)
                .flatMap(p->creditDto.map(AppUtils::dtoToEntity)
                .doOnNext(e->e.setId(id)))
                .flatMap(creditRepository::save)
                .map(AppUtils::entityToDto);
	}

	@Override
	public Mono<Void> deleteClient(String id) {
		return creditRepository.deleteById(id);
	}

}
