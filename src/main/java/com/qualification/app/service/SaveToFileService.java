package com.qualification.app.service;

import com.qualification.app.model.SimpleRequest;
import com.qualification.app.utils.Constants;
import com.qualification.app.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
@Slf4j
class SaveToFileService implements IRequestService {

    public void process(SimpleRequest simpleRequest){
        log.info("Request is Type 3, save Request in File");
        File file = null;
        try {
            file = new File(Constants.FILE_FOLDER_PATH + FileUtils.prepareFileName());
            org.apache.commons.io.FileUtils.writeStringToFile(file, simpleRequest.getData());
        } catch (IOException e) {
            log.error("Error when saving request data in file.txt!", e);
        }
        log.info("File save in" + file.getAbsolutePath());
    }
}
