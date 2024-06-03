package com.message.publisher.custome.annotations;

import com.message.publisher.constant.MessageTypeEnum;
import com.message.publisher.custome.validators.MessageTypeEnumValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = MessageTypeEnumValidator.class)
public @interface ValidMessageType {
    MessageTypeEnum[] anyOf();
    String message() default "must be any of {anyOf}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
