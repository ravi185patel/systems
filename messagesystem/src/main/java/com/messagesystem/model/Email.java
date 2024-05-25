package com.messagesystem.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "email")
public class Email extends Message{

}
