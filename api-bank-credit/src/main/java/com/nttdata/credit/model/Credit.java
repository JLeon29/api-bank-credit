package com.nttdata.credit.model;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nttdata.credit.dto.CustomerDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "credit")
@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Credit {
	
	@Id
    private String id;

    @NotNull
    private double capital;

    @Field( name = "date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateOperation = LocalDateTime.now();

    @Indexed(unique = true)
    private String contractNumber;

    @NotNull
    private String customerIdentityNumber;

    private double amountInitial;

    /**
     * @param amount -> capital + capital*interest
     */
    private double amount;

    private String chargeDay;

    private double commission;

    private double interestRate;

    private boolean debtor;

    @NotNull
    private CustomerDTO customer;
	
	

}
