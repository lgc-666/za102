package zhbit.za102.bean;

import java.io.Serializable;

/**
 * 消息响应类
 */
public class Msg implements Serializable {
    private final static int SUCCESS_CODE = 200 ;
    private final static String SUCCESS_MESSAGE = "请求成功";
    private final static String FAILURE_MESSAGE = "请求失败";

    private int code ;
    private Object data;
    private String message;
    private boolean success;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
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

    //请求成功
    public Msg() {
        this.code = SUCCESS_CODE;
        this.message = SUCCESS_MESSAGE;
        this.success = true;
    }
    public Msg(Object data) {
            this.code = SUCCESS_CODE;
            this.message = SUCCESS_MESSAGE;
            this.success = true;
            this.data = data;
    }

    //请求失败
    public Msg(String data, int code) {
            this.code = code;
            this.message = FAILURE_MESSAGE;
            this.success = false;
            this.data=data;
    }
}
