package com.message.publisher.dao;

import com.message.publisher.constant.MessageTypeEnum;
import com.message.publisher.entity.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DaoFactory {

    private final SmsDao smsDao;
    private final EmailDao emailDao;

    @Autowired
    public DaoFactory(SmsDao smsDao,EmailDao emailDao) {
        this.smsDao = smsDao;
        this.emailDao = emailDao;
    }

    public PublisherDaoInterface getDaoInstance(MessageTypeEnum messageTypeEnum){
        PublisherDaoInterface publisherDaoInterface= null;
        switch (messageTypeEnum){
            case SMS -> publisherDaoInterface = smsDao;
            case EMAIL -> publisherDaoInterface = emailDao;
            default -> publisherDaoInterface=smsDao;
        }
        return publisherDaoInterface;
    }
}


