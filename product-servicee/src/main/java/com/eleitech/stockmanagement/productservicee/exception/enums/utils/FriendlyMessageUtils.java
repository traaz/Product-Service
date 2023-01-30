package com.eleitech.stockmanagement.productservicee.exception.enums.utils;

import com.eleitech.stockmanagement.productservicee.enums.Language;
import com.eleitech.stockmanagement.productservicee.exception.enums.IFriendlyMessageCode;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

@Slf4j
@UtilityClass
public class FriendlyMessageUtils {
    private static final String RESOURCE_BUNDLE_NAME= "FriendlyMessage";
    private static final String SPECIAL_CHARACTER = "__";

    public static String getFriendlyMessage(Language language, IFriendlyMessageCode messageCode){
        String messageKey=null;
        try{
            Locale locale = new Locale(language.name()); //en or tr
            ResourceBundle resourceBundle = ResourceBundle.getBundle(RESOURCE_BUNDLE_NAME, locale); //FriendlyMessage_en ya da _tr
            messageKey = messageCode.getClass().getSimpleName() + SPECIAL_CHARACTER + messageCode; //FriendlyMessageCodes__OK
            return resourceBundle.getString(messageKey); //frienldymessage_en ya da tr dosyasindaki freindlymessagecodes__OK deegrini donduurur
        }catch (MissingResourceException missingResourceException){
            log.error("Friendly message not found for key: {}", messageKey);;
            return null;
        }
    }
}
