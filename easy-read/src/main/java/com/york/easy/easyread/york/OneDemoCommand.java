package com.york.easy.easyread.york;

public class OneDemoCommand extends AbstractDemoCommand {

    @Override
    void say() {

        System.out.println("OneDemoCommand.say");

    }

    @Override
    void hello() {
        System.out.println("OneDemoCommand.hello");

    }
}
