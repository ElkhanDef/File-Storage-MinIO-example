package com.fss.service;

import com.fss.model.ApiKey;
import com.fss.repository.ApiKeyRepository;
import com.fss.util.ApiKeyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ApiKeyService {

    private final Logger logger = LoggerFactory.getLogger(ApiKeyService.class);
    private final ApiKeyRepository apiKeyRepository;
    private final PasswordEncoder passwordEncoder;

    public ApiKeyService(ApiKeyRepository apiKeyRepository,
                         PasswordEncoder passwordEncoder) {
        this.apiKeyRepository = apiKeyRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String createApiKey(){
        logger.info("ActionLog.createApiKey.start");
        String apiKey = ApiKeyUtil.generateApiKey();
        apiKeyRepository.save(new ApiKey(passwordEncoder.encode(apiKey)));
        logger.info("ActionLog.createApiKey.end");
        return apiKey;
    }
}
