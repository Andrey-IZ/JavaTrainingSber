package com.sber.javaschool.hometask4.terminal.exceptions;

public class IncorrectAmountOfMoney extends Exception {
    private int multiply;

    public IncorrectAmountOfMoney(int multiply) {
        this.multiply = multiply;
    }

    public int getMultiply() {
        return multiply;
    }
}
