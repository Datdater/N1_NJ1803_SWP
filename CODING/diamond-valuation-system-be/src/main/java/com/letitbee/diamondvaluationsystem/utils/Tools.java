package com.letitbee.diamondvaluationsystem.utils;

import java.util.Random;

public class Tools {
    public static String generateId(int length) {
        Random random = new Random();
        StringBuilder id = new StringBuilder();
        for (int i = 0; i < length; i++) {
            id.append(random.nextInt(10));
        }
        return id.toString();
    }
}
