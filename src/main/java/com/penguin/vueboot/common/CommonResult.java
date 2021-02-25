package com.penguin.vueboot.common;

import java.io.Serializable;

public class CommonResult implements Serializable {
    private int code; //操作码
    private String msg; //返回信息
    private Object data; //返回数据


    public static CommonResult success(Object data) {
        CommonResult res = new CommonResult();
        res.setCode(200);
        res.setMsg("操作成功~");
        res.setData(data);
        return res;
    }
    public static CommonResult fail(int code,String msg) {
        return commonResult(code, msg, null);
    }


    public static CommonResult commonResult(int code, String msg, Object data) {
        CommonResult res = new CommonResult();
        res.setCode(code);
        res.setMsg(msg);
        res.setData(data);
        return res;
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}


