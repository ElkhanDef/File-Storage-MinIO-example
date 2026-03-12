package com.fss.service;

import com.fss.model.File;
import com.fss.repository.FileRepository;
import io.minio.MinioClient;
import io.minio.ObjectWriteResponse;
import io.minio.PutObjectArgs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

    Logger logger = LoggerFactory.getLogger(FileService.class);

    private final FileRepository fileRepository;
    private final MinioClient minioClient;

    public FileService(FileRepository fileRepository, MinioClient minioClient) {
        this.fileRepository = fileRepository;
        this.minioClient = minioClient;
    }

    @Transactional
    public void createFile(MultipartFile file) throws Exception{
        logger.info("ActionLog.createFile.start");
        PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                .stream(file.getInputStream(), file.getSize(), -1)
                .contentType(file.getContentType())
                .object(file.getOriginalFilename())
                .bucket("pictures")
                .build();

        ObjectWriteResponse response = minioClient.putObject(putObjectArgs);
        File file1 = new File();
        file1.setFileName(file.getOriginalFilename());
        file1.setSize(file.getSize());
        file1.setContentType(file.getContentType());
        fileRepository.save(file1);
        logger.info("ActionLog.createFile.end");
    }
}
