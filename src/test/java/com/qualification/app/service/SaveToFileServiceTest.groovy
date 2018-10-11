package com.qualification.app.service

import com.qualification.app.app.AppApplication
import com.qualification.app.enums.RequestType
import com.qualification.app.model.SimpleRequest
import com.qualification.app.utils.Constants
import com.qualification.app.utils.FileUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest(classes = AppApplication.class)
class SaveToFileServiceTest extends Specification {

    @Autowired
    @Qualifier("saveToFileService")
    IRequestService saveToFileService

    def "should save file with request data"() {

        given:
        def data = "Request Data"
        def fileName = FileUtils.prepareFileName()
        SimpleRequest simpleRequest = SimpleRequest.builder(data, RequestType.THIRD).build()

        when:
        saveToFileService.process(simpleRequest)
        String requestDataStoreInFile = new File(Constants.FILE_FOLDER_PATH + fileName).text

        then:
        requestDataStoreInFile == data
    }

    //Clear file folder after test
    def cleanupSpec() {
        org.apache.commons.io.FileUtils.cleanDirectory(new File(Constants.FILE_FOLDER_PATH))
    }
}
