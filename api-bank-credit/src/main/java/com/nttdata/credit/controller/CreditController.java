package com.nttdata.credit.controller;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.WebExchangeBindException;

import com.nttdata.credit.dto.CreditDto;
import com.nttdata.credit.service.ICreditService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/credit")
public class CreditController {
	
	@Autowired
	private ICreditService service;
	
	@GetMapping
	public Mono<ResponseEntity<Flux<CreditDto>>> getClients(){
		return Mono.just(
				ResponseEntity.ok() 
				.contentType(MediaType.APPLICATION_JSON_UTF8) 
				.body(service.findAllCredit())
				);
	}
	
	
	@GetMapping("/{id}")
	public Mono<ResponseEntity<CreditDto>> getClient(@PathVariable String id){
		return service.findByIdCredit(id).map(p -> ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.body(p))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

	@PostMapping
	public Mono<ResponseEntity<Map<String, Object>>> crear(@Valid @RequestBody Mono<CreditDto> accountDto){
		
		Map<String, Object> respuesta = new HashMap<String, Object>();
		
		return service.saveCredit(accountDto).map(c-> {
			respuesta.put("account", c);
			respuesta.put("mensaje", "Credit Created successfully");
			
			return ResponseEntity
				.created(URI.create("/api/credit/".concat(c.getId())))
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.body(respuesta);
			
			
			}).onErrorResume(t -> {
				return Mono.just(t).cast(WebExchangeBindException.class)
						.flatMap(e -> Mono.just(e.getFieldErrors()))
						.flatMapMany(Flux::fromIterable)
						.map(fieldError -> "El campo "+fieldError.getField() + " " + fieldError.getDefaultMessage())
						.collectList()
						.flatMap(list -> {
							respuesta.put("errors", list);
							respuesta.put("status", HttpStatus.BAD_REQUEST.value());
							return Mono.just(ResponseEntity.badRequest().body(respuesta));
						});
			
			
			});
	
		}

	@PutMapping("/{id}")
	public Mono<CreditDto> updateClient(@RequestBody Mono<CreditDto> creditDto, @PathVariable String id) {
		return service.updateCreditDto(creditDto, id);
	}


	@DeleteMapping("/{id}")
	public Mono<Void> deleteClient(@PathVariable String id) {
		return service.deleteCredit(id);
	}



}
