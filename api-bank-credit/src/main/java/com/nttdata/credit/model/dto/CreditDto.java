package com.nttdata.credit.model.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.nttdata.credit.model.Credit;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreditDto {
	
	@Id
    private String id;
    private double capital;

    @Indexed(unique = true)
    private String contractNumber;

    @NotNull
    private String clientIdNumber;

    private double amountInitial;

    private double amount;

    private String chargeDay;

    private double commission;

    private double interestRate;

    private boolean debitor;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateOperation = LocalDateTime.now();

}
