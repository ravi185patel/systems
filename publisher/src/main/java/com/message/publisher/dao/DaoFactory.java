package com.message.publisher.dao;

import com.message.publisher.constant.MessageTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Component
public class DaoFactory {

    private static final Logger logger = LogManager.getLogger(DaoFactory.class);
    private final SmsDao smsDao;
    private final EmailDao emailDao;

    @Autowired
    public DaoFactory(SmsDao smsDao,EmailDao emailDao) {
        this.smsDao = smsDao;
        this.emailDao = emailDao;
    }

    public PublisherDaoInterface getDaoInstance(MessageTypeEnum messageTypeEnum){
        logger.info("Get dao instance based on message type :"+messageTypeEnum.toString());
        PublisherDaoInterface publisherDaoInterface= null;
        switch (messageTypeEnum){
            case SMS -> publisherDaoInterface = smsDao;
            case EMAIL -> publisherDaoInterface = emailDao;
            default -> publisherDaoInterface=smsDao;
        }
        logger.info("Get dao instance:"+ publisherDaoInterface.hashCode() +" based on message type :"+messageTypeEnum.toString());
        return publisherDaoInterface;
    }
}


