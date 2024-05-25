Demo project - Messaging system
1] publisher
 -> publisher will send sms, email and whatsup message to other users.
 -> message store into mongodb and push messageId into kafka.
 
2] consumer
 -> consumer will read messageId from kafka.
 -> Retrived message fromo mongod based on messageId from kafka.
 -> update message status to received. 


 Tech stack:
 Spring Boot
 Java
 Kafka
 MongoDb


 ---- remain ---
 1] integration of sms , gmail and whatsup api.
 2] Proper Logging.
 3] refactor method, variable and class name.
 4] refactor api name.
 
 
