package com.qualification.app.service;

import com.qualification.app.enums.RequestType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
@ComponentScan("com.qualification.app.service")
public class ServiceMapCreator {

    @Autowired
    @Qualifier("consoleOutService")
    private IRequestService consoleOutService;

    @Autowired
    @Qualifier("rejectedService")
    private IRequestService rejectedService;

    @Autowired
    @Qualifier("saveToDBService")
    private IRequestService saveToDBService;

    @Autowired
    @Qualifier("saveToFileService")
    private IRequestService saveToFileService;

    @Bean("serviceMap")
    public Map<RequestType, IRequestService> createServiceMap(){
        Map<RequestType, IRequestService> mpa = new LinkedHashMap<>();
            mpa.put(RequestType.FIRST, saveToDBService);
            mpa.put(RequestType.SECOND, rejectedService);
            mpa.put(RequestType.THIRD, saveToFileService);
            mpa.put(RequestType.FOURTH, consoleOutService);
        return mpa;
    }
}
