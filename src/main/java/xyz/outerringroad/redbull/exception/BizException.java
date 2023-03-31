package xyz.outerringroad.redbull.exception;

public class BizException extends RuntimeException {

    private final Integer code;

    public BizException(String message) {
        super(message);
        this.code = null;
    }

    public BizException(Integer code, String message) {
        super(String.format("%d-%s", code, message));
        this.code = code;
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
