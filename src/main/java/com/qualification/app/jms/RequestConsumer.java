package com.qualification.app.jms;

import com.qualification.app.enums.RequestType;
import com.qualification.app.model.SimpleRequest;
import com.qualification.app.service.IRequestService;
import com.qualification.app.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
public class RequestConsumer {

    @Autowired
    Map<RequestType, IRequestService> serviceMap;

    @Autowired
    JmsTemplate jmsTemplate;

    @JmsListener(destination = Constants.WAITING_QUEUE, containerFactory = Constants.JMS_CONTAINER_FACTORY)
    public void consumeRequest(SimpleRequest simpleRequest){
        log.info("Starting consume request....");
        IRequestService requestService = getServiceForRequestType(simpleRequest);
        requestService.process(simpleRequest);
        if(checkIfRequestRejected(simpleRequest)) jmsTemplate.convertAndSend(Constants.PROCESSED_QUEUE, simpleRequest);
        log.info("...ending consume request");
    }


    private IRequestService getServiceForRequestType(SimpleRequest simpleRequest) {
        log.info("Getting proper Service for request type");
        return serviceMap.get(simpleRequest.getRequestType());
    }

    private boolean checkIfRequestRejected(SimpleRequest simpleRequest){
        log.info("Checking requestType...");
        return simpleRequest.getRequestType() != RequestType.SECOND;
    }
}
