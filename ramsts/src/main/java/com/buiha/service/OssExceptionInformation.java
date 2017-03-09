package com.buiha.service;

/**
 * Created by tal on 2017/03/09.
 */
public class OssExceptionInformation {
    private String errorCode;
    private String message;
    private String requestId;
    private String hostId;

    public OssExceptionInformation(String errorCode, String message, String requestId, String hostId) {
        this.errorCode = errorCode;
        this.message = message;
        this.requestId = requestId;
        this.hostId = hostId;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getHostId() {
        return hostId;
    }

    public void setHostId(String hostId) {
        this.hostId = hostId;
    }
}
