package com.nttdata.credit.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.credit.app.utils.AppUtils;
import com.nttdata.credit.dto.CreditDto;
import com.nttdata.credit.repository.CreditRepository;
import com.nttdata.credit.service.ICreditService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CreditServiceImpl implements ICreditService {
	
	@Autowired
	private CreditRepository repository;

	@Override
	public Mono<CreditDto> findByContractNumber(String contractNumber) {
		return repository.findByContractNumber(contractNumber).map(AppUtils::entityToDto);
	}

	@Override
	public Flux<CreditDto> findAllCredit() {
		return repository.findAll().map(AppUtils::entityToDto);
	}

	@Override
	public Mono<CreditDto> findByIdCredit(String idCreditDto) {
		return repository.findById(idCreditDto).map(AppUtils::entityToDto);
	}

	@Override
	public Mono<CreditDto> saveCredit(Mono<CreditDto> creditDto) {
		return creditDto.map(AppUtils::DtoToEntity)
                .flatMap(repository::insert)
                .map(AppUtils::entityToDto);
	}

	@Override
	public Mono<CreditDto> updateCreditDto(Mono<CreditDto> creditDto,String idCreditDto) {
		return repository.findById(idCreditDto)
				.flatMap(p -> creditDto.map(AppUtils::DtoToEntity)
						.doOnNext(c -> c.setId(idCreditDto)))
				.flatMap(repository::save)
				.map(AppUtils::entityToDto);
	}

	@Override
	public Mono<Void> deleteCredit(String idCreditDto) {
		return repository.deleteById(idCreditDto);
	}

	

}
