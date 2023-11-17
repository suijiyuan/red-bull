package xyz.outerringroad.redbull.bean.vo;

import xyz.outerringroad.redbull.constant.CodeEnum;
import xyz.outerringroad.redbull.exception.BizException;

public class ResponseVO<T> {

    private Integer code;
    private String message;
    private T data;
    private String traceId;

    private ResponseVO() {
    }

    public ResponseVO(Integer code) {
        this(code, null);
    }

    public ResponseVO(Integer code, String message) {
        this(code, message, null);
    }

    public ResponseVO(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResponseVO(CodeEnum codeEnum) {
        this(codeEnum, null);
    }

    public ResponseVO(T data) {
        this(CodeEnum.SUCCESS, data);
    }

    public ResponseVO(CodeEnum codeEnum, T data) {
        this.code = codeEnum.getCode();
        this.message = codeEnum.getMessage();
        this.data = data;
    }

    public ResponseVO(BizException bizException) {
        this.code = bizException.getCode();
        this.message = bizException.getMessage();
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
