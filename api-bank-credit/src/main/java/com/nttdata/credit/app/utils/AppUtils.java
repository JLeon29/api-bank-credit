package com.nttdata.credit.app.utils;

import org.springframework.beans.BeanUtils;

import com.nttdata.credit.model.Credit;
import com.nttdata.credit.model.dto.CreditDto;

public class AppUtils {
	
	public static CreditDto entityToDto(Credit credit){
        CreditDto creditDto=new CreditDto();
        BeanUtils.copyProperties(credit,creditDto);
        return creditDto;
    }

    public static Credit dtoToEntity(CreditDto creditDto){
        Credit credit=new Credit();
        BeanUtils.copyProperties(creditDto,credit);
        return credit;
    }

}