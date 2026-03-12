package com.fss.config;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinioConfig {

    @Value("${minio.access-key}")
    private String minioAccessKey;

    @Value("${minio.secret-key}")
    private String minioSecretKey;

    @Value("${minio.url}")
    private String url;

    @Bean
    public MinioClient minioClient() {

        return MinioClient.builder().credentials(minioAccessKey, minioSecretKey).endpoint(url).build();
    }

}
