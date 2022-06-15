package org.example.enums;

import lombok.Getter;

public enum WebResultCode {
    SUCCESS(0, "OK"),
    INVALID_FAIL(100, "UNKNOW FAIL"),
    INVALID_ARGUS(101, "INVALID ARGUS"),
    SIGN_TYPE_NOT_SUPPORT(102, "SIGN TYPE NOT SUPPORT"),
    NONCE_ERROR(105, "NONCE HEADER ERROR"),
    TIMESTAMP_ERROR(106, "TIMESTAMP HEADER ERROR"),
    SIGN_ERROR(106, "SIGN HEADER ERROR"),
    SIGN_VERIFY_ERROR(107, "SIGN VERIFY ERROR"),
    FILE_NOT_EXISTS(108, "FILE NOT EXISTS"),
    FILE_READ_ERROR(109, "FILE READ ERROR"),
    EXEC_SQL_ERROR(110, "EXEC SQL ERROR"),
    EXEC_DAPP_SDK_API_ERROR(120, "EXEC DAPP SDK API ERROR"),
    EXEC_DAPP_SDK_API_BUS_ERROR(121, "EXEC DAPP SDK API BUS ERROR");

    @Getter
    private final Integer code;
    @Getter
    private final String message;

    WebResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
