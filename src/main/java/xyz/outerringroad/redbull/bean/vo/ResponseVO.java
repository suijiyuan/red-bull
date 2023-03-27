package xyz.outerringroad.redbull.bean.vo;

import xyz.outerringroad.redbull.constant.CodeEnum;

public class ResponseVO<T> {

    private Integer code;
    private String message;
    private T data;

    public ResponseVO() {
    }

    public ResponseVO(Integer code) {
        this.code = code;
    }

    public ResponseVO(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseVO(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResponseVO(CodeEnum codeEnum) {
        this.code = codeEnum.getCode();
        this.message = codeEnum.getMessage();
    }

    public ResponseVO(CodeEnum codeEnum, T data) {
        this.code = codeEnum.getCode();
        this.message = codeEnum.getMessage();
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
