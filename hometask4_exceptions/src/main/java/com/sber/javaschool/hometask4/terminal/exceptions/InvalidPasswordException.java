package com.sber.javaschool.hometask4.terminal.exceptions;

public class InvalidPasswordException extends Exception {
    private final int attempts;

    public InvalidPasswordException(int attempts) {
        super();
        this.attempts = attempts;
    }

    public int getAttempts() {
        return attempts;
    }
}
