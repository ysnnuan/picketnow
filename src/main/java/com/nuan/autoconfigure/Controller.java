package com.nuan.autoconfigure;

import com.nuan.autoconfigure.domain.RequestExecMethod;
import com.nuan.autoconfigure.domain.ResponseApi;
import com.nuan.autoconfigure.function.RelectExetor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: picketnow
 * @description:
 * @author: yeshengnuan
 * @create: 2021-02-20 11:28
 **/

@org.springframework.stereotype.Controller()
@RequestMapping("/picketnow")
public class Controller
{

    private final Logger logger= LoggerFactory.getLogger(Controller.class);

    @Autowired
    private RelectExetor relectExetor;

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    @ResponseBody
    public String hello() {
        return "Hello World!";
    }

    @RequestMapping(value = "/mtest",method = RequestMethod.POST)
    @ResponseBody
    public ResponseApi execMethod(@RequestBody RequestExecMethod request) {

        ResponseApi<Object> responseApi=null;
        if(validRequst(request))
        {
            return new ResponseApi("20001","参数错误");
        }
        try
        {
            Object object=relectExetor.executer(request.getData(),request.getClassMethod());
            responseApi=new ResponseApi<>("00000","success",object);
        }
        catch (Exception e)
        {
            logger.error("executer invoke e="+e);
            responseApi=new ResponseApi<>("10001","oh! no no no , you fail",e);
        }
        return responseApi;
    }

    private Boolean validRequst(RequestExecMethod request)
    {
        if(StringUtils.isEmpty(request.getClassMethod()))
        {
            return true;
        }
        String[] strs=request.getClassMethod().split("#");
        if (strs.length<2)
        {
            return true;
        }
        return false;
    }
}
