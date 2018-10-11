package com.qualification.app.config

import com.qualification.app.app.AppApplication
import com.qualification.app.enums.RequestType
import com.qualification.app.service.RejectedService
import com.qualification.app.service.SaveToDBService
import com.qualification.app.service.SaveToFileService
import com.qualification.app.service.ServiceMapCreator
import com.qualification.app.service.ConsoleOutService
import com.qualification.app.service.IRequestService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import spock.lang.Specification
import spock.lang.Unroll

@SpringBootTest(classes = [AppApplication.class, ServiceMapCreator.class])
@Unroll
class ServiceMapCreatorTest extends Specification {

    @Autowired
    @Qualifier("serviceMap")
    Map<RequestType, IRequestService> serviceMap

    def "should return Service: #expectedClass for Request Type: #type"() {

        when:
        def result = serviceMap.get(type)

        then:
        result in expectedClass

        where:
        type                    || expectedClass
        RequestType.FIRST       || SaveToDBService
        RequestType.SECOND      || RejectedService
        RequestType.THIRD       || SaveToFileService
        RequestType.FOURTH      || ConsoleOutService
    }
}
