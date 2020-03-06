package com.healthedge.healthchain.common.util;

import java.util.UUID;

public class RandomStringUUID {

    public static String generateRandomUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
