package xyz.outerringroad.redbull.exception;

import xyz.outerringroad.redbull.constant.CodeEnum;

public class BizException extends RuntimeException {

    private final Integer code;

    public BizException(String message) {
        this(CodeEnum.FAILURE.getCode(), message);
    }

    public static BizException createBizException(String message) {
        return new BizException(message);
    }

    public BizException(Integer code, String message) {
        super(String.format("%d-%s", code, message));
        this.code = code;
    }

    public static BizException createBizException(Integer code, String message) {
        return new BizException(code, message);
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "BizException{" +
                "code=" + code +
                '}';
    }

}
