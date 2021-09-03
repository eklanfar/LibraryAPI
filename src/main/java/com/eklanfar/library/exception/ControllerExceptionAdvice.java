package com.eklanfar.library.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.core.annotation.AnnotatedElementUtils.findMergedAnnotation;

@ControllerAdvice
public class ControllerExceptionAdvice {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(HandledException.class)
    public ResponseEntity<ExceptionInformation> handleAllCustomExceptions(Exception ex) {
        StackTraceElement ste = ex.getStackTrace()[1];
        String methodName = ste.getMethodName();
        log.info("FAILED - {}", methodName, ex);
        log.error("EXCEPTION - {}", methodName, ex);
        ExceptionInformation info = new ExceptionInformation();
        info.setMessage(ex.getMessage());
        HttpStatus responseStatus = resolveAnnotatedResponseStatus(ex);
        return new ResponseEntity<>(info, responseStatus);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionInformation> handleRuntimeExceptions(Exception ex) {
        StackTraceElement ste = ex.getStackTrace()[1];
        String methodName = ste.getMethodName();
        log.info("FAILED - {}", methodName, ex);
        log.error("EXCEPTION - {}", methodName, ex);
        ExceptionInformation info = new ExceptionInformation();
        info.setMessage("InternalServerError");
        return new ResponseEntity<>(info, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    HttpStatus resolveAnnotatedResponseStatus(Exception exception) {
        ResponseStatus annotation = findMergedAnnotation(exception.getClass(), ResponseStatus.class);
        if (annotation != null) {
            return annotation.value();
        }
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
