package net.youareatman.enums;

public enum ErrorTypesEnum {

    UnknownError(1,"Unknown error."),
    CRUDError(2,"Error while performing a CRUD operation on the database.")
    ;

    private final int code;
    private final String description;

    ErrorTypesEnum(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "ErrorTypesEnum{" +
                "code=" + code +
                ", description='" + description + '\'' +
                '}';
    }
}
