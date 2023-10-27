package com.code.dbcourse1.handler;

import com.code.dbcourse1.pojo.ResultData;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 处理json返回数据统一格式
 * {
 *   "status": xxx,
 *   "message": "xxx",
 *   "data": xxx,
 *   "timestamp": xxx,
 * }
 */
@RestControllerAdvice
@AllArgsConstructor
@Slf4j
public class JsonDataHandler implements ResponseBodyAdvice {

    private ObjectMapper objectMapper;

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @SneakyThrows
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if(body instanceof String){
            // 如果不加这一步, 处理过后的body已经从String类型转为Result类型，然后在调用实现类即StringHttpMessageConverter#getContentLength(String str, @Nullable MediaType contentType)方法时，第一个参数str发生了类型转换错误的异常。
            // 直接字符串 需要返回
            return objectMapper.writeValueAsString(ResultData.success(body));
        } else if(body instanceof byte[]) {
          return objectMapper.writeValueAsBytes(ResultData.success(body));
        } else if (body instanceof ResultData){
            // 已经处理过
            return body;
        }
        return ResultData.success(body);
    }
}
