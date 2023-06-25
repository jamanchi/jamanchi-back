package com.jamanchi.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

@Configuration
public class GoogleCloudStorageConfig {

    @Value("${GCS_KEY}") // application.yml에 써둔 bucket 이름
    private String key;
    @Value("${GCS_PROJECT_ID}") // application.yml에 써둔 bucket 이름
    private String projectId;

    @Bean
    public Storage storage() throws IOException {

        ClassPathResource resource = new ClassPathResource(key + ".json");
        GoogleCredentials credentials = GoogleCredentials.fromStream(resource.getInputStream());

        return StorageOptions.newBuilder()
                .setProjectId(projectId)
                .setCredentials(credentials)
                .build()
                .getService();
    }
}
