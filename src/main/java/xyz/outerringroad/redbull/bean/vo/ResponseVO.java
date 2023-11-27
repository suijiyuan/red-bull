package xyz.outerringroad.redbull.bean.vo;

import xyz.outerringroad.redbull.constant.CodeEnum;
import xyz.outerringroad.redbull.exception.BizException;

public class ResponseVO<T> {

    private Integer code;
    private String message;
    private T data;
    private String traceId;

    private ResponseVO(Integer code) {
        this(code, null);
    }

    public static <T> ResponseVO<T> createResponseVO(Integer code) {
        return new ResponseVO<>(code);
    }

    private ResponseVO(Integer code, String message) {
        this(code, message, null);
    }

    public static <T> ResponseVO<T> createResponseVO(Integer code, String message) {
        return new ResponseVO<>(code, message);
    }

    private ResponseVO(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> ResponseVO<T> createResponseVO(Integer code, String message, T data) {
        return new ResponseVO<>(code, message, data);
    }

    private ResponseVO(T data) {
        this.code = CodeEnum.SUCCESS.getCode();
        this.message = CodeEnum.SUCCESS.getMessage();
        this.data = data;
    }

    public static <T> ResponseVO<T> createResponseVO(T data) {
        return new ResponseVO<>(data);
    }

    private ResponseVO(BizException bizException) {
        this.code = bizException.getCode();
        this.message = bizException.getMessage();
    }

    public static <T> ResponseVO<T> createResponseVO(BizException bizException) {
        return new ResponseVO<>(bizException);
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

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    @Override
    public String toString() {
        return "ResponseVO{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", traceId='" + traceId + '\'' +
                '}';
    }

}
