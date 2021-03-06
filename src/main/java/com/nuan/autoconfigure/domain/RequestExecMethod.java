package com.nuan.autoconfigure.domain;

import java.io.Serializable;
import java.util.Map;

/**
 * @program: picketnow
 * @description:
 * @author: yeshengnuan
 * @create: 2021-02-20 14:13
 **/
public class RequestExecMethod implements Serializable
{
    private String classMethod;

    private Map<String,Object> data;

    public String getClassMethod()
    {
        return classMethod;
    }

    public void setClassMethod(String classMethod)
    {
        this.classMethod = classMethod;
    }

    public Map<String, Object> getData()
    {
        return data;
    }

    public void setData(Map<String, Object> data)
    {
        this.data = data;
    }

    @Override
    public String toString()
    {
        return "RequestExecMethod{" +
                "classMethod='" + classMethod + '\'' +
                ", data=" + data +
                '}';
    }
}
