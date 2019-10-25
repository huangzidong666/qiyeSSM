package com.huangjindong.controller;

import com.huangjindong.domian.SysLog;
import com.huangjindong.service.ISysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAop {

    private Date visitTime;
    private Class clazz;
    private Method method;

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private ISysLogService iSysLogService;

    @Before("execution(* com.huangjindong.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        visitTime = new Date();
        clazz= jp.getTarget().getClass();
        String name = jp.getSignature().getName();

        Object[] args = jp.getArgs();
        if(args==null||args.length==0){
            method = clazz.getMethod(name);
        }else {
            Class[] classes = new Class[args.length];
            for (int i=0;i<args.length;i++){
                classes[i]=args[i].getClass();
            }
            method=clazz.getMethod(name,classes);
        }
    }
    @After("execution(* com.huangjindong.controller.*.*(..))")
    public void doAfter(JoinPoint jp){
        if(clazz!=LogAop.class){
            RequestMapping requestMapping = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if(requestMapping!=null){
                RequestMapping methodRequestMapping = method.getAnnotation(RequestMapping.class);
                if(methodRequestMapping!=null){
                    String url="";
                    url=requestMapping.value()[0]+methodRequestMapping.value()[0];
                    SysLog sysLog = new SysLog();
                    Long executionTime = new Date().getTime()-visitTime.getTime();
                    sysLog.setUrl(url);
                    sysLog.setExecutionTime(executionTime);
                    String ip = request.getRemoteAddr();
                    sysLog.setIp(ip);
                    SecurityContext context = SecurityContextHolder.getContext();
                    User user = (User) context.getAuthentication().getPrincipal();
                    String username = user.getUsername();
                    sysLog.setUsername(username);

                    sysLog.setMethod("类名："+clazz.getName()+"方法名："+method.getName());
                    sysLog.setVisitTime(visitTime);
                    iSysLogService.addSysLog(sysLog);
                }
            }
        }
    }
}
