# picketnow
一个集成了springboot自动配置的的java测试工具，可以测试业务中的每个方法.当做接口测试！

## **注意事项**
1.不支持带有object的入参：包括map中和list已经自定义bean中带有的Object的对象入参

2.不支持Class<?>类型入参
##使用说明
###请求

路径：/picketnow/mtest   
场景1 方法不纯在多态，即这个方法在类中仅有一个  
请求： 
```json
{
    "classMethod":"class全路径 方法（）",
    "data":
    {
      "参数位置":{"json数据"}
    }

}
```
响应:
```json
{
    "code": "响应码",
    "msg": "信息",
    "data": "返回的数据" //返回为方法本身的return结果的object，结果由springmvc序列化返回
}
```


请求：样例  
```json
{

    "classMethod": "com.github.ysnnuan.javaBean getBean()",
    "data": {
        "1": {
            "name": "nuan",
            "timestamp": "2010-08-20 14:06:27.186",
            "sqlDate": "2010-08-20"
        },
        "2": ["dd","dd"]
    }
}
```

响应：样例

```json
{
    "code": "00000",
    "msg": "success",
    "data": "helloword" 
}
```
---
场景2 方法纯在多态  
请求： 
```json
{
    "classMethod":"class全路径 方法（类型,类型）",
    "data":
    {
      "参数位置":{"json数据"}
    }

}
```

请求：样例  
```json
{

    "classMethod": "com.github.ysnnuan.javaBean getBean(student,string[],map,list,string,int)",
    "data": {
        "1": {
            "name": "nuan",
            "timestamp": "2010-08-20 14:06:27.186",
            "sqlDate": "2010-08-20"
        },
        "2": ["dd","dd"]
    }
}
```
