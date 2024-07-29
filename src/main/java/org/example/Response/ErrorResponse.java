package org.example.Response;

public class ErrorResponse {

    private String errorCode;

    public ErrorResponse(String errorCode, String description) {
        this.errorCode = errorCode;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    private String description;
}
