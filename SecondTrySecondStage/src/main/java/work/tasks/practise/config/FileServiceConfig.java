package work.tasks.practise.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import work.tasks.practise.services.FileService;

@Configuration
public class FileServiceConfig {

    @Bean
    public FileService fileService() {
        return new FileService();
    }
}