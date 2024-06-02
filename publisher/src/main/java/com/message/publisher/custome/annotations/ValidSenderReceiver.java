package com.message.publisher.custome.annotations;


import com.message.publisher.constant.MessageTypeEnum;
import com.message.publisher.custome.validators.SenderReceiverValidator;
import java.lang.annotation.*;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = SenderReceiverValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidSenderReceiver {
    String sender();

    String receiver();

    MessageTypeEnum messageType();

    String message() default "Invalid Sender/Receiver: Please provide valid sender/receiver.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

//    @Target({ ElementType.TYPE })
//    @Retention(RetentionPolicy.RUNTIME)
//    @interface List {
//        PasswordMatching[] value();
//    }
}
//public interface ValidSenderReceiver {
//}
