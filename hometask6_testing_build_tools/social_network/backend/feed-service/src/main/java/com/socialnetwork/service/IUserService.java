package com.socialnetwork.service;

import com.socialnetwork.entity.interfaces.IUser;

import java.util.Collection;

public interface IUserService {
    Collection<IUser> getAllUsers();

    IUser getUserById(int userid);

    void removeUserById(int id);

    void updateUser(IUser user);

    Collection<IUser> getUsersByFirstName(String name);

    Collection<IUser> getUsersByLastName(String name);

    IUser getUsersByFullName(String name);

    IUser getUserByUserName(String name);

    IUser getUserByEmail(String email);

    Collection<IUser> getUsersByAge(int age);

    Collection<IUser> getUsersByGender(String gender);

    Collection<IUser> getUsersByCountry(String country);

    Collection<IUser> getUsersByCity(String city);

    void register(IUser user);

    void setFirst(int userid, String first);

    void setLast(int userid, String last);

    void setEmail(int userid, String email);

    void setAge(int userid, int age);

    void setGender(int userid, String gender);

    void setCountry(int userid, String country);

    void setCity(int userid, String city);

    void setPassword(int userid, String password);
}
