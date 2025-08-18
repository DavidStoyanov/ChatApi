package org.myapp.chat_api.models.dto;

import lombok.Getter;
import lombok.Setter;

public class MyResponse {
    private String message;
    private boolean success;

    public MyResponse(boolean success) {
        this.success = success;
    }

    public MyResponse(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
