package com.nttdata.credit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.credit.model.dto.CreditDto;
import com.nttdata.credit.service.ICreditService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/credit")
public class CreditController {
	
	@Autowired
	private ICreditService iCreditService;
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Mono<Void>> deleteCredit(@PathVariable String id){
		
		return new ResponseEntity<Mono<Void>>(iCreditService.deleteClient(id), HttpStatus.OK);
	}
	
	@GetMapping("/list")
	public ResponseEntity<Flux<CreditDto>> listCredit(){
		
		return new ResponseEntity<Flux<CreditDto>>(iCreditService.listCredit(),HttpStatus.OK);
	}
	
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Mono<CreditDto>> updateCredit(@RequestBody Mono<CreditDto> creditDto, @PathVariable String id){
		return new ResponseEntity<Mono<CreditDto>>(iCreditService.updateCredit(creditDto,id),HttpStatus.OK);
	}
	
	
	@PostMapping("/save")
	public ResponseEntity<Mono<CreditDto>> saveCustomer(@RequestBody Mono<CreditDto> creditDto){
		return new ResponseEntity<Mono<CreditDto>>(iCreditService.saveCredit(creditDto), HttpStatus.CREATED);
				
	}

}