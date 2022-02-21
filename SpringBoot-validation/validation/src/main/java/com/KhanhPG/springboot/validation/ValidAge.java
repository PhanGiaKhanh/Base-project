package com.KhanhPG.springboot.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target(ElementType.FIELD) // Định nghĩa phạm vi annotation for Age / TYPE: class, interface, enum,
							// annotation
@Retention(RetentionPolicy.RUNTIME) // Mức độ tồn tại / RUNTIME: compiler
@Constraint(validatedBy = AgeValidation.class) // Xác định valid and invalid
@Documented
public @interface ValidAge {
	//	String message() default "You don't enough age";
	String message() default "{age.error.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
