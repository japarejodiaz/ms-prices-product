package es.com.priceproduct.between.controller;

import es.com.priceproduct.between.constant.ErrorEnum;
import es.com.priceproduct.between.dto.response.ErrorResponse;
import es.com.priceproduct.between.exception.PricesNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.text.MessageFormat;
import java.util.Locale;

@ControllerAdvice
@Slf4j
public class CustomExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(PricesNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePricesNotFoundException(PricesNotFoundException ex) {

        ErrorResponse response = new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value());

        log.debug("[ERROR]: {}",ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponse> handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {

        String message = messageSource.getMessage(ErrorEnum.MISSING_PARAMETER.getMessage(), null, Locale.getDefault());
        String formattedMessage = MessageFormat.format(message, ex.getParameterName());
        ErrorResponse response = new ErrorResponse(formattedMessage, HttpStatus.BAD_REQUEST.value());

        log.debug("[ERROR]: {}",formattedMessage);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {

        String message = messageSource.getMessage(ErrorEnum.PARAMETER_TYPE_MISMATCH.getMessage(), null, Locale.getDefault());
        String formattedMessage = MessageFormat.format(message, ex.getValue(), ex.getName(), ex.getRequiredType());
        ErrorResponse response = new ErrorResponse(formattedMessage, HttpStatus.BAD_REQUEST.value());

        log.debug("[ERROR]: {}",formattedMessage);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {

        String message = messageSource.getMessage(ErrorEnum.GENERIC_ERROR.getMessage(), null, Locale.getDefault());
        ErrorResponse response = new ErrorResponse(message, HttpStatus.INTERNAL_SERVER_ERROR.value());

        log.debug("[ERROR]: {}",ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
