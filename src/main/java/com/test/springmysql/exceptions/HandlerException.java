package com.test.springmysql.exceptions;

import com.test.springmysql.dtos.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class HandlerException {

    //controlador por si no se encuentra el recurso.
    @ExceptionHandler(RecursoNoEncontrado.class)
    public ResponseEntity<ApiResponse> handleRecursoNoEncontradoException(RecursoNoEncontrado exception, WebRequest webRequest){
        ApiResponse apiResponse = new ApiResponse(exception.getMessage(),webRequest.getDescription(false));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
    }
    //controlador de excepcion para que se valide el @Valid
    //en las clases
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handlderMethodArgumentNotValidException(MethodArgumentNotValidException exception,
                                                                          WebRequest webRequest) {
        Map<String, String> mapErrors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
                    String clave = ((FieldError) error).getField();
                    String valor = error.getDefaultMessage();
                    mapErrors.put(clave, valor);
                }
        );
        ApiResponse apiResponse = new ApiResponse(mapErrors.toString(), webRequest.getDescription(false));
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiResponse> handleBadRequestException(BadRequestException exception, WebRequest webRequest){
        ApiResponse apiResponse = new ApiResponse(exception.getMessage(), webRequest.getDescription(false));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    //controla los errores globales de los path en 404
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ApiResponse> handlerNoHandlerFoundException(NoHandlerFoundException exception,
                                                                      WebRequest webRequest) {
        ApiResponse apiResponse = new ApiResponse(exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

    //controla los errores de varios tipos y globalizrlo con un error 500
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleException(Exception exception, WebRequest webRequest){
        //en el paraemtro del getDescription va false
        //para que no devuelva informacion del cliente y solo la uri
        ApiResponse apiResponse = new ApiResponse(exception.getMessage(), webRequest.getDescription(false));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
    }
}
