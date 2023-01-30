package com.eleitech.stockmanagement.productservicee.exception.enums.handler;

import com.eleitech.stockmanagement.productservicee.exception.enums.FriendlyMessageCodes;
import com.eleitech.stockmanagement.productservicee.exception.enums.exceptions.ProductNotCreteadException;
import com.eleitech.stockmanagement.productservicee.exception.enums.utils.FriendlyMessageUtils;
import com.eleitech.stockmanagement.productservicee.response.FriendlyMessage;
import com.eleitech.stockmanagement.productservicee.response.InternalApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ProductNotCreteadException.class)
    public InternalApiResponse<String> handleProductNotCreatedException(ProductNotCreteadException productNotCreteadException){

        return InternalApiResponse.<String>builder()
                .friendlyMessage(FriendlyMessage.builder()
                                .title(FriendlyMessageUtils.getFriendlyMessage(productNotCreteadException.getLanguage(), FriendlyMessageCodes.ERROR))
                        .description(FriendlyMessageUtils.getFriendlyMessage(productNotCreteadException.getLanguage(), productNotCreteadException.getFriendlyMessageCode()))
                        .build())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .hasError(true)
                .errorMessage(Collections.singletonList(productNotCreteadException.getMessage()))
                .build();

    }
}
