package com.sber.javaschool.hometask4.terminal;

public class TerminalMessage implements IMessage {
    @Override
    public void show(String textMessage) {
        System.out.println(textMessage);
    }
}
