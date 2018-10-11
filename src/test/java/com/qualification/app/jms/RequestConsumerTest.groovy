package com.qualification.app.jms

import com.qualification.app.app.AppApplication
import com.qualification.app.enums.RequestType
import com.qualification.app.model.SimpleRequest
import com.qualification.app.service.IRequestService

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.jms.core.JmsTemplate
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

@SpringBootTest(classes = AppApplication.class)
@Unroll
class RequestConsumerTest extends Specification {

    @Autowired
    JmsTemplate jmsTemplate

    @Autowired
    RequestProducer requestProducer

    @MockBean(name = "saveToFileService")
    IRequestService saveToFileService

    @MockBean(name = "consoleOutService")
    IRequestService consoleOutService

    @MockBean(name = "rejectedService")
    IRequestService rejectedService

    @MockBean(name = "saveToDBService")
    IRequestService saveToDBService

    @Shared String waitingQueue
    @Shared String processedQueue

    def setup(){
        waitingQueue = "waiting.queue"
        processedQueue = "processed.queue"
    }

    def "should send data to waiting.queue, consume and send to processed.queue"(){
        given:
        def simpleRequest = SimpleRequest.builder(data, type).build()

        when:
        requestProducer.sendRequestToQueue(waitingQueue, simpleRequest)

        and:
        def receivedSimpleRequest = getRequestFromProcessedQueue()

        then:
        simpleRequest.data == receivedSimpleRequest.data
        simpleRequest.requestType == receivedSimpleRequest.requestType

        where:
        data    |   type
        "DATA1" |   RequestType.FIRST
        "DATA3" |   RequestType.THIRD
        "DATA4" |   RequestType.FOURTH
    }




    private SimpleRequest (){
        return SimpleRequest.builder("DATA", RequestType.THIRD).build()
    }
    private getRequestFromProcessedQueue(){
        return (SimpleRequest) jmsTemplate.receiveAndConvert(processedQueue)
    }
}
