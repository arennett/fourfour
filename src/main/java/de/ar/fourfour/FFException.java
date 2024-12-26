package de.ar.fourfour;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FFException extends Exception{
    private static final Logger logger = LoggerFactory.getLogger(FFException.class);
    public FFException(String message, Throwable rootCause){
        super(message,rootCause);
        logger.error(message,rootCause);
    }
    public FFException(String message){
        this(message,null);
    }

}
