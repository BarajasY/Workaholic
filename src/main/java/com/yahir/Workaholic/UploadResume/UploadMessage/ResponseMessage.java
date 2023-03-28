package com.yahir.Workaholic.UploadResume.UploadMessage;

public class ResponseMessage {
    private String message;

    public ResponseMessage() {
    }

    public ResponseMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResponseMessage message(String message) {
        setMessage(message);
        return this;
    }
}
