package com.socialnetwork.service;

import com.socialnetwork.exceptions.AuthenticationException;

public interface IAuthenticationService {
    String getEmail();

    String getToken();

    void login() throws AuthenticationException;
}