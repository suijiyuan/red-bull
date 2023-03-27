package xyz.outerringroad.redbull.constant;

public enum CodeEnum {

    SUCCESS(0, "SUCCESS"),
    FAILURE(1, "FAILURE"),
    ;

    private final int code;
    private final String message;

    CodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

}
