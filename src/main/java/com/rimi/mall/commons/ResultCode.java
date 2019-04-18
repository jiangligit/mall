package com.rimi.mall.commons;

/**
 * 统一返回码
 *
 * @author Administrator
 * @date 2019-04-12 16:28
 */
public enum  ResultCode {
    /**
     * 成功
     */
    SUCCESS(200,"成功"),
    /**
     * 失败
     */
    FAILURE(201,"失败");
    int code;
    String msg;
    ResultCode(int code,String msg) {
        this.code=code;
        this.msg=msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
