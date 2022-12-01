package com.york.easy.easyread.york;

public abstract class AbstractDemoCommand {

    private void sendBefore(){

        System.out.println("这是前置父类方法");
    }


    abstract void say();

    abstract void hello();

    private void sendAfter(){
        System.out.println("这是后置父类方法");
    }

    public void send(){
        sendBefore();
        say();
        hello();
        sendAfter();
    }




}
