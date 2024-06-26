package com.sms.worker.entity;

import com.sms.worker.constant.MessageStatusEnum;
import com.sms.worker.constant.MessageTypeEnum;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "sms")
public class Sms {

    @Id
    private String messageId;
    private String sender;
    private String receiver;
    private String data;
    private MessageStatusEnum status = MessageStatusEnum.INIT;
    private MessageTypeEnum messageType = MessageTypeEnum.SMS;

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