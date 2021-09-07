package com.sber.javaschool.hometask4.terminal.exceptions;

public class AccountIsLockedException extends Exception {
    private final long remainingTime;

    public AccountIsLockedException(long remainingTime) {
        this.remainingTime = remainingTime;
    }

    public long getRemainingTime() {
        return remainingTime;
    }
}
