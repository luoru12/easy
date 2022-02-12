package com.york.easywrite.aop;

import com.york.easywrite.config.DBContextHolder;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DataSourceAop {

    @Pointcut("(execution(* com.york.easywrite.service..*.select*(..))) " +
            "|| (execution(* com.york.easywrite.service..*.get*(..)))")
    public void readPointcut(){}

    @Pointcut("(execution(* com.york.easywrite.service..*.insert*(..))) " +
            "|| (execution(* com.york.easywrite.service..*.add*(..))) " +
            "|| (execution(* com.york.easywrite.service..*.delete*(..))) " +
            "|| (execution(* com.york.easywrite.service..*.remove*(..))) " +
            "|| (execution(* com.york.easywrite.service..*.save*(..))) " +
            "|| (execution(* com.york.easywrite.service..*.update*(..)))")
    public void writePointcut(){}

    @Before("readPointcut()")
    public void read(){
        DBContextHolder.slave();
    }

    @Before("writePointcut()")
    public void write(){
        DBContextHolder.master();
    }

}
