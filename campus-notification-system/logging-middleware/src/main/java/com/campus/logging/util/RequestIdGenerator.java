package com.campus.logging.util;

import java.util.UUID;

public  final class RequestIdGenerator {


    private    RequestIdGenerator()
    {
        throw new IllegalStateException("Utility class");
    }

    public   static  String generateRequestId(){
        return UUID.randomUUID().toString();
    }
}
