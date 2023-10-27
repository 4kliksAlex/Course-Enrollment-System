package com.code.dbcourse1.pojo;

/**
 * 自定义响应异常类
 */
public class ResponseException extends RuntimeException{
    private final ResultData<Object> resultData;

    public ResponseException(ResultData<Object> resultData){
        this.resultData = resultData;
    }

    public ResponseException(String message){
        this(ReturnCode.FAIL.getCode(), message, null);
    }

    public ResponseException(Exception e){
        this(ReturnCode.FAIL.getCode(),e.getMessage());
    }

    public ResponseException(int status, String message){
        this(status, message, null);
    }

    public ResponseException(String message, Object data){
        this(ReturnCode.FAIL.getCode(), message, data);
    }

    public ResponseException(int status, String message, Object data){
        this.resultData = new ResultData<>();
        this.resultData.setStatus(status);
        this.resultData.setMessage(message);
        this.resultData.setData(data);
    }

    public ResponseException error(){
        this.resultData.setStatus(ReturnCode.FAIL.getCode());
        return this;
    }

    public ResponseException error(int status){
        this.resultData.setStatus(status);
        return this;
    }

    public ResultData<Object> getResultData() {
        return resultData;
    }

    @Override
    public String getMessage() {
        return this.resultData.getMessage();
    }
}
