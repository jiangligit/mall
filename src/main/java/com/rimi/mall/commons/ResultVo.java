package com.rimi.mall.commons;

import java.io.Serializable;

/**
 * 一般的返回类型
 *
 * @author Administrator
 * @date 2019-04-12 16:13
 */
public class ResultVo implements Result,Serializable {
    private int code;
    private String msg;

    public ResultVo() {
    }

    public ResultVo(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public ResultVo(ResultCode resultCode) {
       this.code =resultCode.code;
       this.msg = resultCode.msg;
    }
    /**
     * 成功
     * @return
     */
    public static Result success(){
        return of(ResultCode.SUCCESS);
    }

    /**
     * 失败
     * @return
     */
    public static Result failure(){
        return of(ResultCode.FAILURE);
    }


    public static Result of(ResultCode resultCode){
        return new ResultVo(resultCode);
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
