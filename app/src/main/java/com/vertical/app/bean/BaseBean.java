package com.vertical.app.bean;

import java.io.Serializable;

/**
 * Created by katedshan on 17/9/4.
 */

public class BaseBean implements Serializable {
    private int status;
    private String message;

    public boolean isSuccess(){
        return status == 0;
    }

    public String getMessage() {
        return message;
    }
}
