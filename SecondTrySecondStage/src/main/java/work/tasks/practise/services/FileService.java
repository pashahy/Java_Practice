package work.tasks.practise.services;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class FileService {

    @Value("C:\\Users\\Admin\\Desktop\\Java practis\\fromOnetoFiveStages\\SecondTrySecondStage\\src\\main\\resources\\FileForInfo.txt")
    private String filePath;

    public void writeToFile(String content) throws IOException {
        File file = new File(filePath);
        FileUtils.writeStringToFile(file, content, "UTF-8", true);
    }
}