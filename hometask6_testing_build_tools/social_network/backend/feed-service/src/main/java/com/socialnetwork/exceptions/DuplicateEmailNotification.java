package com.socialnetwork.exceptions;

public class DuplicateEmailNotification extends RuntimeException {
    public DuplicateEmailNotification(String message) {
        super(message);
    }
}
