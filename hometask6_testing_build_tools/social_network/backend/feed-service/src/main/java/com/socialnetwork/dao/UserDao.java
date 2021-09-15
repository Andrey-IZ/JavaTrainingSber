package com.socialnetwork.dao;

import com.socialnetwork.entity.interfaces.IUser;

import java.util.Collection;

public interface UserDao {
    Collection<IUser> getAllUser();

    IUser getUserById(int id);

    void removeUserById(int id);

    void updateUser(IUser user);

    void insertUserToDb(IUser user);

    Collection<IUser> getUsersByFirstName(String name);

    Collection<IUser> getUsersByLastName(String name);

    IUser getUsersByFullName(String name);

    IUser getUserByUserName(String name);

    IUser getUserByEmail(String email);

    Collection<IUser> getUsersByAge(int age);

    Collection<IUser> getUsersByGender(String gender);

    Collection<IUser> getUsersByCountry(String country);

    Collection<IUser> getUserByCity(String city);

    void setFirstName(int userId, String first);

    void setLastName(int userID, String last);

    void setEmail(int userID, String email);

    void setAge(int userID, int age);

    void setGender(int userID, String gender);

    void setCountry(int userID, String country);

    void setCity(int userID, String city);

    void setPassword(int userID, String password);
}