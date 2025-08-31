package com.example.haveIt.utils;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class Logging {

    private static final Logger log = LoggerFactory.getLogger(Logging.class);

    private static Logging logging;

    private Logging(){

    }

    public static Logging getLogger(){

        if(logging == null){
            logging = new Logging();
        }
        return logging;
    }

    public void info(String message){
        log.info(message);
    }

    public void error(String message){
        log.error(message);
    }

    public void debug(String message){
        log.debug(message);
    }

    public void warn(String message){
        log.warn(message);
    }


}
