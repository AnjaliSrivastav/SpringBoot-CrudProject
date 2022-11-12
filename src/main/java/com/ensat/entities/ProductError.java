package com.ensat.entities;

public class ProductError {
    private int errorCode;

    public ProductError(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    private String errorMsg;

    public ProductError() {
    }
}
