package com.eleitech.stockmanagement.productservicee.exception.enums;

public enum FriendlyMessageCodes implements IFriendlyMessageCode{
    OK(1000),
    ERROR(1001),
    SUCCESS(1002),
    PRODUCT_NOT_CREATED_EXCEPTION(1500),
    PRODUCT_SUCCESFULLY_CREATED(1501);

    private final int value;

    FriendlyMessageCodes(int value) {
        this.value = value;
    }

    @Override
    public int getFriendlyMessageCode() {
        return value;
    }
}
