package com.nttdata.credit.app.utils;

import org.springframework.beans.BeanUtils;

import com.nttdata.credit.dto.CreditDto;
import com.nttdata.credit.model.Credit;

public class AppUtils {
	
	public static CreditDto entityToDto(Credit credit){
		CreditDto creditDTO= new CreditDto();
        BeanUtils.copyProperties(credit,creditDTO);
        return creditDTO;
    }
    public static Credit DtoToEntity(CreditDto creditDTO){
        Credit credit = new Credit();
        BeanUtils.copyProperties(creditDTO, credit);
        return credit;
    }

}
