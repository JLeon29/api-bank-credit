package com.nttdata.credit.dto;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class CreditDto {

    private String id;
    private double capital;
    private LocalDateTime dateOperation = LocalDateTime.now();
    private String contractNumber;
    private String clientIdNumber;
    private double amountInitial;
    private double amount;
    private String chargeDay;
    private double commission;
    private double interestRate;
    private ClientDto client;
	

}
