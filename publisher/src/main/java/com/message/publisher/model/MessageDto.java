package com.message.publisher.model;

import com.message.publisher.constant.MessageStatusEnum;
import com.message.publisher.constant.MessageTypeEnum;
import com.message.publisher.custome.annotations.ValidSenderReceiver;

@ValidSenderReceiver(
        sender = "sender",
        receiver = "receiver",
        messageType = MessageTypeEnum.SMS,
        message = "Invalid sender and receiver!"
)
public class MessageDto {

    private String messageId;
    private String sender;
    private String receiver;
    private String data;
    private MessageStatusEnum status = MessageStatusEnum.INIT;
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
}