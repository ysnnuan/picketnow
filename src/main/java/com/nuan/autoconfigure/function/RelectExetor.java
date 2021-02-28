package com.nuan.autoconfigure.function;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: learn
 * @description:
 * @author: yeshengnuan
 * @create: 2021-02-18 12:24
 **/

public class RelectExetor
{
    public RelectExetor()
    {
    }

    private  final Logger logger = LoggerFactory.getLogger(RelectExetor.class);

    public final static ObjectMapper mapper = new ObjectMapper();

    public Object executer(String req,String methodStr) throws Exception
    {
        Object methodresq=null;
        String[] reqs=req.split(";");
        String[] strs=methodStr.split("#");
        String className=strs[0];
        String methoddetail=strs[1];
        String methodName=methoddetail.substring(0, methoddetail.indexOf("("));
        String[] RequestParamsTyes =methoddetail.substring(methoddetail.indexOf("(")+1,methoddetail.indexOf(")")).split(",");
        int methodTime=0;
        try
        {
            reqs=req.split(";");
            strs=methodStr.split("#");
            className=strs[0];
            methoddetail=strs[1];
            methodName=methoddetail.substring(0, methoddetail.indexOf("("));
            RequestParamsTyes =methoddetail.substring(methoddetail.indexOf("(")+1,methoddetail.indexOf(")")).split(",");
        }catch (Exception e)
        {
            logger.error("入参错误请重试 Exception e",e);
            throw new Exception();
        }
        Class clazz=Class.forName(className);
        Object handleBean=SpringBeanUtils.getBean(clazz);
        if(handleBean==null)
        {
            handleBean=clazz.newInstance();
        }
        Method[] methods=clazz.getDeclaredMethods();
        for (Method method:methods)
        {
            if(methodName.equals(method.getName()))
            {
                methodTime ++;
            }
        }
        for (Method method:methods)
        {
            if(methodName.equals(method.getName()))
            {
                boolean isContinue=genericsHandler(method,methodTime,RequestParamsTyes);
                if(isContinue)
                {
                    continue;
                }
                boolean isVoid=false;
                if(method.getReturnType()==Void.TYPE)
                {
                    isVoid=true;
                }
                Class<?>[] paramTypes = method.getParameterTypes();
                Type[] type = method.getGenericParameterTypes();
                Object[] objs=new Object[paramTypes.length];
                for ( int i=0;i<paramTypes.length;i++)
                {
                    Object obj=null;
                    if(i+1>reqs.length)
                    {
                        isHandleBasicTypes(type,reqs,objs,i);
                        continue;
                    }
                    if(StringUtils.isEmpty(reqs[i]))
                    {
                        isHandleBasicTypes(type,reqs,objs,i);
                        if(objs[i]!=null&&(int)objs[i]==0)
                        {
                            continue;
                        }
                    }
                    obj=builderRequestParam(i,reqs,paramTypes,type);
                    objs[i]=obj;
                }
                if(isVoid)
                {
                    method.invoke(handleBean,objs);
                    methodresq="void success";
                }
                else
                {
                    methodresq=method.invoke(handleBean,objs);
                }
            }
        }
        return methodresq;
    }

    private boolean genericsHandler(Method method,int methodTime,String[] requestParamsTyes)
    {
        if(methodTime>1)
        {
            if(StringUtils.isEmpty(requestParamsTyes[0])&&method.getGenericParameterTypes().length>0)
            {
                return true;
            }
            if(!StringUtils.isEmpty(requestParamsTyes[0]))
            {
                if(requestParamsTyes.length!=method.getGenericParameterTypes().length)
                {
                    return true;
                }
                Type[] types = method.getGenericParameterTypes();
                for(int i =0;i<types.length;i++ )
                {
                    if (!types[i].getTypeName().toLowerCase().contains(requestParamsTyes[i].toLowerCase()))
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 将参数转换成方法中对应的具体参数并且赋值
     * @param i
     * @param reqs
     * @param paramTypes
     * @param type
     * @return
     * @throws ClassNotFoundException
     * @throws JsonProcessingException
     */
    private Object builderRequestParam(int i, String[] reqs, Class<?>[] paramTypes, Type[] type) throws IOException
    {
        Object obj=null;
        if(type[i].getTypeName().equals("java.lang.String"))
        {
            obj=reqs[i];
        }
        else if (paramTypes[i].isArray())
        {
            String[] szrequest=reqs[i].split(",");
            obj=szrequest;
        }
        else if(Map.class.isAssignableFrom(paramTypes[i]))
        {
            Map mapTypes=strParseObjectMap(reqs[i],type[i]);
            obj=mapTypes;
        }
        else if(List.class.isAssignableFrom(paramTypes[i]))
        {
            List list=strParseObjectList(reqs[i],type[i]);
            obj=list;
        }
        else
        {
            obj=mapper.readValue(reqs[i], paramTypes[i]);
        }
        return obj;
    }

    /**
     * 处理基础的8大数据类型中除char类型之外的在后面无参数传入但是有默认值的问题
     * @param type
     * @param reqs
     * @param objs
     * @param i
     */
    private void isHandleBasicTypes(Type[] type, String[] reqs, Object[] objs, int i)
    {
        if("int".equals(type[i].getTypeName()))
        {
            objs[i]=0;
        }
        else if ("byte".equals(type[i].getTypeName()))
        {
            objs[i]=(byte)0;
        }
        else if ("short".equals(type[i].getTypeName()))
        {
            objs[i]=(short)0;
        }
        else if ("long".equals(type[i].getTypeName()))
        {
            objs[i]=0;
        }
        else if ("float".equals(type[i].getTypeName()))
        {
            objs[i]=0;
        }
        else if ("double".equals(type[i].getTypeName()))
        {
            objs[i]=0;
        }
        else if ("boolean".equals(type[i].getTypeName()))
        {
            objs[i]=false;
        }
        else
        {
            objs[i]=null;
        }
    }

    /**
     * 使用class进行转换对应的map类
     * @param req
     * @param mapKeyClass
     * @param mapValueClass
     * @return
     * @throws JsonProcessingException
     */
    private static Map strParseObjectMapLe1(String req, Class<?> mapKeyClass, Class<?> mapValueClass) throws IOException
    {
        Map map=null;
        JavaType javaType = getCollectionType(HashMap.class,mapKeyClass, mapValueClass);
        map=mapper.readValue(req, javaType);
        return map;
    }

    /**
     * 使用class进行转换对应的map类
     * @param req
     * @param listClass
     * @return
     * @throws JsonProcessingException
     */
    private static List strParseObjectListLe1(String req, Class<?> listClass) throws IOException
    {
        List list=null;
        JavaType javaType = getCollectionType(List.class,listClass);
        list=mapper.readValue(req, javaType);
        return list;
    }

    /**
     * 获取泛型的Collection Type
     * @param collectionClass 泛型的Collection
     * @param elementClasses 元素类
     * @return JavaType Java类型
     * @since 1.0
     */
    public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

    /**
     * 通过Type对象获取javaType
     * @param type
     * @return
     */
    public static JavaType getCollectionType(Type type) {
        return mapper.getTypeFactory().constructType(type);
    }

    /**
     * 将json字符串转成javabean
     * @param req
     * @param type
     * @return
     */
    private static Map strParseObjectMap(String req,Type type) throws IOException
    {
        JavaType javaType = getCollectionType(type);
        return mapper.readValue(req, javaType);
    }

    /**
     * 将json字符串转成javabean
     * @param req
     * @param type
     * @return
     */
    private static List strParseObjectList(String req,Type type) throws IOException
    {
        JavaType javaType = getCollectionType(type);
        return mapper.readValue(req, javaType);
    }

}
