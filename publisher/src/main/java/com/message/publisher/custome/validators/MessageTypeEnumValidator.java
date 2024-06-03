package com.message.publisher.custome.validators;

import com.message.publisher.constant.MessageTypeEnum;
import com.message.publisher.custome.annotations.ValidMessageType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class MessageTypeEnumValidator implements ConstraintValidator<ValidMessageType, MessageTypeEnum> {
    private MessageTypeEnum[] subset;

    @Override
    public void initialize(ValidMessageType validMessageType) {
        this.subset = validMessageType.anyOf();
    }

    @Override
    public boolean isValid(MessageTypeEnum messageTypeEnum, ConstraintValidatorContext context) {
        return messageTypeEnum == null || Arrays.asList(subset).contains(messageTypeEnum);
    }
}