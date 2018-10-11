package com.qualification.app.jms;

import com.qualification.app.model.SimpleRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RequestProducer {

    @Autowired
    JmsTemplate jmsTemplate;

    public void sendRequestToQueue(String queueName, SimpleRequest simpleRequest){
        log.info("Sendindg simpleRequest to Queue");
        jmsTemplate.convertAndSend(queueName, simpleRequest);
    }
}
