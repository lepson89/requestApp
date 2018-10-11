package com.qualification.app.jms

import com.qualification.app.app.AppApplication
import com.qualification.app.model.SimpleRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.jms.core.JmsTemplate
import spock.lang.Specification
import spock.lang.Unroll

@SpringBootTest(classes = AppApplication.class)
@Unroll
class RequestProducerTest extends Specification {

    @Autowired
    JmsTemplate jmsTemplate

    @Autowired
    RequestProducer requestProducer

    def "should send simple request data to test.queue"(){

        given:
        def queue = "test.queue"
        def simpleRequest = Mock(SimpleRequest)

        when:
        requestProducer.sendRequestToQueue(queue, simpleRequest)

        and:
        def receivedSimpleRequest = (SimpleRequest)jmsTemplate.receiveAndConvert(queue)

        then:
        simpleRequest.data == receivedSimpleRequest.data
        simpleRequest.requestType == receivedSimpleRequest.requestType

    }

}
