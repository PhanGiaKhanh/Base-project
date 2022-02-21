package com.KhanhPG.springboot.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
* @author: KhanhPG
* @since: 21/02/2022	
*/
public class AgeValidation implements ConstraintValidator<ValidAge, Integer>{

	@Override
	public boolean isValid(Integer age, ConstraintValidatorContext context) {
		//	Period p = Period.between(userDto.getBirthday(), LocalDate.now())
		//	return p.getYears() == userDto.getAge()
		return age >= 18;
	}
}
