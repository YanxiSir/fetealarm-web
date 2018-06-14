package com.yanxisir.fetealarm;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nullable;

/**
 * @author YanxiSir
 * @since 2018/6/14
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResult {

    private Integer status;
    private String msg;
    private Object data;

    public static ApiResult success(Object data) {
        return ApiResult.builder()
                .status(0)
                .msg("success")
                .data(data)
                .build();
    }

    public static ApiResult success() {
        return success(null);
    }

    public static ApiResult fail(Object data) {
        return ApiResult.builder()
                .status(1)
                .msg("fail")
                .data(data)
                .build();
    }

    public static ApiResult fail() {
        return fail(null);
    }
}
