package com.code.dbcourse1.pojo;

/**
 * 状态码
 */
public enum ReturnCode {

    SUCCESS(200, "操作成功"), // 成功
    FAIL(999, "操作失败"), // 失败
    INVALID_TOKEN(2001, "访问 token 不合法"),
    LOGIN_EXPIRED(2002, "登录过期"),
    ACCESS_DENIED(2003, "您没有权限访问该资源"),
    NOT_LOGIN(2004, "用户认证异常请重新登录");

    private final int code; // 状态码
    private final String message; // 描述符

    ReturnCode(int code, String message){
        this.code = code;
        this.message = message;
    }

    public int getCode(){
        return code;
    }

    public String getMessage(){
        return message;
    }
}
