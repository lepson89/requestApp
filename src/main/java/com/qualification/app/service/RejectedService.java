package com.qualification.app.service;

import com.qualification.app.model.SimpleRequest;
import com.qualification.app.service.IRequestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
class RejectedService implements IRequestService {

    @Override
    public void process(SimpleRequest simpleRequest) {
        log.info("Request is type 2, request print on console!");
        System.out.print("Request Rejected!");
    }
}
