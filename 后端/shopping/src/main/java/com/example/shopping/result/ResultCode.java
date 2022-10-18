package com.example.shopping.result;

/**
 * @Description:
 * @Author: SilenceOu
 * @Date: 2022/1/24 17:48
 */
public enum ResultCode {
    SUCCESS(200),
    FAIL(400),
    UNAUTHORIZED(401),
    NOT_FOUND(404),
    INTERNAL_SERVER_ERROR(500);

    public int code;

    ResultCode(int code) {
        this.code = code;
    }
}
