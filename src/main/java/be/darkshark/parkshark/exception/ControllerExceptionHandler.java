package be.darkshark.parkshark.exception;

import be.darkshark.parkshark.service.ParkingLotService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {
    private final Logger myLogger = LoggerFactory.getLogger(ParkingLotService.class);

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = "Malformed JSON request";
        myLogger.warn("Request for the following url: '{}' gives error '{}' with exception '{}'.", ((ServletWebRequest)request).getRequest().getRequestURI(), error, ex.getMessage());
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error, ex));
    }
    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(Exception.class)
    protected void exceptionHandler(Exception exception, HttpServletResponse response) throws IOException {
        myLogger.error(exception.getMessage());
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(
            EntityNotFoundException ex) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND);
        apiError.setMessage(ex.getMessage());
        myLogger.warn(ex.getMessage());
        return buildResponseEntity(apiError);
    }
}

