package com.nuan.autoconfigure.domain;

import java.io.Serializable;

/**
 * @program: picketnow
 * @description:
 * @author: yeshengnuan
 * @create: 2021-02-20 14:13
 **/
public class RequestExecMethod implements Serializable
{
    private String classMethod;

    private String data;

    public String getClassMethod()
    {
        return classMethod;
    }

    public void setClassMethod(String classMethod)
    {
        this.classMethod = classMethod;
    }

    public String getData()
    {
        return data;
    }

    public void setData(String data)
    {
        this.data = data;
    }

    @Override
    public String toString()
    {
        final StringBuffer sb = new StringBuffer("RequestExecMethod{");
        sb.append("classMethod='").append(classMethod).append('\'');
        sb.append(", data='").append(data).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
