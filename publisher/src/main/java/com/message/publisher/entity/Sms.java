package com.message.publisher.entity;

import com.message.publisher.constant.MessageStatusEnum;
import com.message.publisher.constant.MessageTypeEnum;
import com.message.publisher.custome.annotations.ValidSenderReceiver;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "sms")
@ValidSenderReceiver(
        sender = "sender",
        receiver = "receiver",
        messageType = MessageTypeEnum.SMS,
        message = "Invalid sender and receiver!"
)
public class Sms {

    @Id
    private String messageId;
    private String sender;
    private String receiver;
    @NotNull(message = "Please provide message content.")
    @Size(min=5, max=100, message="Message content should be between 5 - 100 characters.")
    private String data;
    private MessageStatusEnum status = MessageStatusEnum.INIT;
    @NotNull(message = "Please provide message type.")
    private MessageTypeEnum messageType;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public MessageStatusEnum getStatus() {
        return status;
    }

    public void setStatus(MessageStatusEnum status) {
        this.status = status;
    }

    public MessageTypeEnum getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageTypeEnum messageType) {
        this.messageType = messageType;
    }


    @Override
    public String toString() {
        return "Sms{" +
                "messageId='" + messageId + '\'' +
                ", sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                ", data='" + data + '\'' +
                ", status=" + status +
                ", messageType=" + messageType +
                '}';
    }
}