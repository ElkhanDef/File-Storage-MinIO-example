package com.fss.util;

import java.security.SecureRandom;
import java.util.Base64;

public class ApiKeyUtil {

    public static String generateApiKey() {
        byte[] bytes = new byte[32];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }
}
