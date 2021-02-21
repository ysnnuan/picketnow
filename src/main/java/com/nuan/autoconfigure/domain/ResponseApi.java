package com.nuan.autoconfigure.domain;

/**
 * @program: picketnow
 * @description:
 * @author: yeshengnuan
 * @create: 2021-02-20 14:57
 **/
public class ResponseApi<T>
{
    private String code;

    private String msg;

    private T data;

    public ResponseApi()
    {

    }

    public ResponseApi(String code)
    {
        this.code = code;
    }

    public ResponseApi(String code, String msg)
    {
        this.code = code;
        this.msg = msg;
    }

    public ResponseApi(String code, T data)
    {
        this.code = code;
        this.data = data;
    }

    public ResponseApi(String code, String msg, T data)
    {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

    public T getData()
    {
        return data;
    }

    public void setData(T data)
    {
        this.data = data;
    }

    @Override
    public String toString()
    {
        final StringBuffer sb = new StringBuffer("ResponseApi{");
        sb.append("code='").append(code).append('\'');
        sb.append(", msg='").append(msg).append('\'');
        sb.append(", data=").append(data);
        sb.append('}');
        return sb.toString();
    }
}
