package com.example.shopping.result;

import lombok.Data;

/**
 * @Description:
 * @Author: SilenceOu
 * @Date: 2022/1/22 17:18
 */
@Data
public class Result {
    private int code;
    private String message;
    private Object result;

    Result(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.result = data;
    }
}
