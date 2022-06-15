package org.example.rest.base.config.swagger.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.util.Strings;
import org.example.WebException;
import org.example.enums.WebResultCode;

@Getter
@Setter
public class WebResponse<T> {
    @JSONField(ordinal = 10)
    private int code;

    @JSONField(ordinal = 20)
    private String message;

    @JSONField(ordinal = 30)
    private T data;

    public static<T> WebResponse<T> success(T data) {
        WebResponse<T> response = new WebResponse<>();
        response.setCode(WebResultCode.SUCCESS.getCode());
        response.setData(data);
        response.setMessage("SUCCESS");
        return response;
    }

    public static <T> WebResponse<T> error(WebResultCode resultCode) {
        WebResponse<T> response = new WebResponse<>();
        response.setCode(resultCode.getCode());
        response.setMessage(resultCode.getMessage());
        return response;
    }

    public static <T> WebResponse<T> error(WebException ex) {
        WebResponse<T> response = new WebResponse<>();
        response.setCode(ex.getCode());
        response.setMessage(ex.getMessage());
        return response;
    }

    public static <T> WebResponse<T> error(Exception ex) {
        WebResponse<T> response = new WebResponse<>();
        response.setCode(WebResultCode.INVALID_FAIL.getCode());
        if (ex.getMessage() == null || Strings.isEmpty(ex.getMessage())) {
            response.setMessage(WebResultCode.INVALID_FAIL.getMessage());
        } else {
            response.setMessage(ex.getMessage());
        }
        return response;
    }
}
