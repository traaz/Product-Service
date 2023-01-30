package com.eleitech.stockmanagement.productservicee.exception.enums.exceptions;

import com.eleitech.stockmanagement.productservicee.enums.Language;
import com.eleitech.stockmanagement.productservicee.exception.enums.IFriendlyMessageCode;
import com.eleitech.stockmanagement.productservicee.exception.enums.utils.FriendlyMessageUtils;
import com.eleitech.stockmanagement.productservicee.response.FriendlyMessage;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class ProductNotCreteadException extends RuntimeException{
    private final Language language;
    private final IFriendlyMessageCode friendlyMessageCode;


    public ProductNotCreteadException(Language language, IFriendlyMessageCode friendlyMessageCode, String message) {
        super(FriendlyMessageUtils.getFriendlyMessage(language, friendlyMessageCode));
        this.language = language;
        this.friendlyMessageCode = friendlyMessageCode;
        log.error("[ProductNotCreatedException] -> messsage:{} developer message: {}", FriendlyMessageUtils.getFriendlyMessage(language, getFriendlyMessageCode()));
    }



}
