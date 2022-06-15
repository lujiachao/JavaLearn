package org.example;

import lombok.Getter;
import org.example.enums.WebResultCode;

public class WebException extends Exception {
    @Getter
    private int code;

    public WebException(WebResultCode resultCode) {
        this(resultCode.getCode(), resultCode.getMessage());
    }

    public WebException(WebResultCode resultCode, String message) {
        this(resultCode.getCode(), message);
    }

    public WebException(int code, String message) {
        super(message);
        this.code = code;
    }
}
