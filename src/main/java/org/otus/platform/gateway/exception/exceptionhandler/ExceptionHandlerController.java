package org.otus.platform.gateway.exception.exceptionhandler;

import feign.FeignException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.otus.platform.gateway.dto.error.ArgumentNotValidDto;
import org.otus.platform.gateway.dto.error.ErrorDto;
import org.otus.platform.gateway.dto.error.MissingParameterDto;
import org.otus.platform.gateway.exception.exceptions.AuthenticationFailedException;
import org.otus.platform.gateway.exception.exceptions.IncorrectPasswordException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ControllerAdvice
@AllArgsConstructor
@Slf4j
public class ExceptionHandlerController {

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<String> feignExceptionHandler(FeignException exception) {
        ResponseEntity.BodyBuilder responseBuilder = getDefaultResponseEntityBuilder(exception.status());
        Optional<ByteBuffer> body = exception.responseBody();
        if (body.isPresent()) {
            String message = getDecodedResponseBody(body.get());
            log.error("Internal server error. " + message);
            return responseBuilder.body(message);
        }

        return responseBuilder.build();
    }

    @ResponseStatus(code = HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorDto> accessDeniedExceptionHandler(AccessDeniedException e) {
        log.error("Access denied. " + e.getMessage());
        return createResponseEntity(
                HttpStatus.FORBIDDEN,
                new ErrorDto(Integer.toString(HttpStatus.FORBIDDEN.value()),
                        e.getMessage()));
    }

    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> serverExceptionHandler(Exception e) {
        log.error("Internal server error. " + e.getMessage());
        return createResponseEntity(
                HttpStatus.INTERNAL_SERVER_ERROR,
                new ErrorDto(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()),
                        e.getMessage()));
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<MissingParameterDto> handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        log.error("Request parameter is missing. " + e.getParameterName());
        MissingParameterDto body = new MissingParameterDto(e.getParameterName(), e.getMessage());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorDto> httpMessageNotReadableExceptionHandler(Exception e) {
        log.error("Http Message Not Readable Exception. " + e.getMessage());
        return createResponseEntity(
                HttpStatus.BAD_REQUEST,
                new ErrorDto(Integer.toString(HttpStatus.BAD_REQUEST.value()),
                        "JSON parse error"));
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ArgumentNotValidDto> handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        log.error("Method argument not valid. " + e.getMessage());
        List<String> errors = new ArrayList<String>();
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        ArgumentNotValidDto body = new ArgumentNotValidDto(HttpStatus.BAD_REQUEST, e.getMessage(), errors);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ErrorDto> maxUploadSizeExceededExceptionHandler(MaxUploadSizeExceededException e) {
        log.error("Max upload size exceeded. " + e.getMessage());
        return createResponseEntity(HttpStatus.BAD_REQUEST,
                new ErrorDto(Integer.toString(HttpStatus.BAD_REQUEST.value()), e.getMessage()));
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IncorrectPasswordException.class)
    public ResponseEntity<ErrorDto> incorrectPasswordException(IncorrectPasswordException e) {
        log.error("Incorrect password. " + e.getMessage());
        return createResponseEntity(HttpStatus.BAD_REQUEST,
                new ErrorDto(Integer.toString(HttpStatus.BAD_REQUEST.value()), e.getMessage()));
    }

    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AuthenticationFailedException.class)
    public ResponseEntity<ErrorDto> authenticationFailedException(AuthenticationFailedException e) {
        log.error("Authentication failed. " + e.getMessage());
        return createResponseEntity(HttpStatus.UNAUTHORIZED,
                new ErrorDto(Integer.toString(HttpStatus.UNAUTHORIZED.value()), e.getMessage()));
    }

    private ResponseEntity<ErrorDto> createResponseEntity(HttpStatus status, ErrorDto errorDto) {
        return ResponseEntity.status(status)
                .header("Content-Type", "application/json")
                .body(errorDto);
    }

    private ResponseEntity.BodyBuilder getDefaultResponseEntityBuilder(int status) {
        return ResponseEntity.status(HttpStatus.valueOf(status)).contentType(MediaType.APPLICATION_JSON);
    }

    private String getDecodedResponseBody(ByteBuffer byteBuffer) {
        return StandardCharsets.UTF_8.decode(byteBuffer).toString();
    }
}
