package com.pixelogic.EmpApp;

public class CustomException extends Throwable{

    private String error;

    public CustomException(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }



}
