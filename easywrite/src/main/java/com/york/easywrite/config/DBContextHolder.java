package com.york.easywrite.config;

import com.york.easywrite.contant.DBTypeEnum;

import java.util.concurrent.atomic.AtomicInteger;

public class DBContextHolder {

    private static final ThreadLocal<DBTypeEnum> context = new ThreadLocal<>();

    private static final AtomicInteger counter = new AtomicInteger(0);

    public static void set(DBTypeEnum typeEnum){
        context.set(typeEnum);
    }

    public static DBTypeEnum get(){
        return context.get();
    }

    public static void remove(){
        context.remove();
    }

    public static void master(){
        set(DBTypeEnum.MASTER);
        System.out.println("写入 "+DBTypeEnum.MASTER);
    }

    public static void slave(){
        int index=counter.getAndIncrement()%(DBTypeEnum.values().length-1);
        System.out.println("下标"+index);
        if(counter.get()>999){
            counter.set(0);
        }
        set(DBTypeEnum.values()[index+1]);
        System.out.println("切换到 "+DBTypeEnum.values()[index+1]);
    }


}
