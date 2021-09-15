package com.socialnetwork.service;

public interface Logger {
    void info(String message);

    void warning(String message);

    void critical(String message);

    void fatal(String message);
}
