package com.socialnetwork.controller;

import com.socialnetwork.entity.User;

import java.util.Collection;

public interface UserController {

    User getUserById(int userid2, int userid, String token);

    void deleteUserById(int userid2, int userid, String token);

    void updateUserById(User user, int userid, String token);

    Collection<User> getUsersByFirstName(String name, int userid, String token);

    Collection<User> getUsersByLastName(String name, int userid, String token);

    User getUsersByFullName(String name, int userid, String token);

    User personalPage(String firstName, String lastName, int userid, String token);

    User getUserByEmail(String email, int userid, String token);

    Collection<User> getUserByAge(int age, int userid, String token);

    Collection<User> getUsersByGender(String gender, int userid, String token);

    Collection<User> getUsersByCountry(String country, int userid, String token);

    Collection<User> getUsersByCity(String city, int userid, String token);

    void setFirst(String first, int userid, String token);

    void setLast(String last, int userid, String token);

    void setEmail(String email, int userid, String token);

    void setAge(int age, int userid, String token);

    void setGender(String gender, int userid, String token);

    void setCountry(String country, int userid, String token);

    void setCity(String city, int userid, String token);

    void setPassword(String password, int userid, String token);
}
