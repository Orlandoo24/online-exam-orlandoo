package com.exam.util;

import com.exam.entity.ApiResult;
import lombok.Data;

@Data
public class ApiResultHandler {
    /**
     * 有返回值且响应成功的情况
     * @param object
     * @return
     */
    public static ApiResult success(Object object) {
        ApiResult apiResult = new ApiResult();
        apiResult.setData(object);
        apiResult.setCode(200);
        apiResult.setMessage("请求成功");
        return apiResult;
    }
    /**
     * 无返回值且响应成功的情况
     */
    public static ApiResult success() {
        return success(null);
    }
    /**
     *
     * @param code 响应状态码
     * @param message 响应信息
     * @param data 响应体
     * @return ApiResult<T>
     * @param <T>
     */
    public static <T> ApiResult buildApiResult(Integer code, String message, T data) {
        ApiResult apiResult = new ApiResult();
        apiResult.setCode(code);
        apiResult.setMessage(message);
        apiResult.setData(data);
        return apiResult;
    }
}
