package com.message.publisher.custome.validators;

import com.message.publisher.constant.MessageTypeEnum;
import com.message.publisher.custome.annotations.ValidSenderReceiver;
import com.message.publisher.model.MessageDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

import java.util.regex.Pattern;

public class SenderReceiverValidator  implements ConstraintValidator<ValidSenderReceiver, Object> {

    private String sender,receiver;
    private MessageTypeEnum messageType;

    @Override
    public void initialize(ValidSenderReceiver validSenderReceiver) {
        this.sender = validSenderReceiver.sender();
        this.receiver = validSenderReceiver.receiver();
        this.messageType = validSenderReceiver.messageType();
        System.out.println(this.sender);
        System.out.println(this.receiver);
        System.out.println(this.messageType);
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        System.out.println("Is valid");
        Object sender = new BeanWrapperImpl(value).getPropertyValue(this.sender);
        Object receiver = new BeanWrapperImpl(value).getPropertyValue(this.sender);
        Object messageType = new BeanWrapperImpl(value).getPropertyValue("messageType");

        System.out.println((String)sender);
        System.out.println((String)receiver);
        System.out.println(" sender and receiver done");
        System.out.println(messageType.toString());
        System.out.println(" message type ");
        if(messageType.toString().equalsIgnoreCase(MessageTypeEnum.SMS.toString())){
            return validPhoneNo(String.valueOf(sender)) && validPhoneNo(String.valueOf(receiver));
        }else{
            return validEmail(String.valueOf(sender)) && validEmail(String.valueOf(receiver));
        }
    }


    private boolean validPhoneNo(String phoneNo){
        if(phoneNo == null){
            return false;
        }
        String phonePattern = "^[+]{1}(?:[0-9\\-\\(\\)\\/" +
                "\\.]\\s?){6,15}[0-9]{1}$";
        Pattern pat = Pattern.compile(phonePattern);
        return pat.matcher(phoneNo).matches();
    }

    private boolean validEmail(String email){
        if(email == null){
            return false;
        }
        String emailRegex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
}

