package com.qualification.app.service;

import com.qualification.app.model.SimpleRequest;
import com.qualification.app.repository.SimpleRequestRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
class SaveToDBService implements IRequestService {

    @Autowired
    SimpleRequestRepository simpleRequestRepository;

    @Transactional
    @Override
    public void process(SimpleRequest simpleRequest) {
        log.info("Request is Type 1, save request in H2 database");
        simpleRequestRepository.save(simpleRequest);
    }
}
