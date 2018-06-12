package com.cm.mybet.rest.api.v1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Base class for all controllers.
 *
 * @author cmilasan
 * @since 1.0
 */
public abstract class AbstractController {

    private static final Logger LOG = LogManager.getLogger();
    protected static final String API_ENDPOINT_BASE_URI = "/api/v1";

    protected ResponseEntity<?> buildResponseEntity(Object entity, HttpStatus httpStatus) {
        LOG.info("Response: " + entity);
        return new ResponseEntity<>(entity, null, httpStatus);
    }
}

