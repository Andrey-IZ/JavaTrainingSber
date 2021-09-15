package com.socialnetwork.service;

import com.socialnetwork.dao.UserDao;
import com.socialnetwork.entity.interfaces.IUser;

import java.util.Collection;

public class UserService implements IUserService {
    private final Logger logger;
    private final UserDao userDao;

    UserService(Logger logger, UserDao userDao) {
        this.logger = logger;
        this.userDao = userDao;
    }

    @Override
    public Collection<IUser> getAllUsers() {
        return this.userDao.getAllUser();
    }

    @Override
    public IUser getUserById(int userid) {
        return this.userDao.getUserById(userid);
    }

    @Override
    public void removeUserById(int id) {
        this.userDao.removeUserById(id);
    }

    @Override
    public void updateUser(IUser user) {
        this.userDao.updateUser(user);
    }

    @Override
    public Collection<IUser> getUsersByFirstName(String name) {
        return userDao.getUsersByFirstName(name);
    }

    @Override
    public Collection<IUser> getUsersByLastName(String name) {
        return this.userDao.getUsersByLastName(name);
    }

    @Override
    public IUser getUsersByFullName(String name) {
        return this.userDao.getUsersByFullName(name);
    }

    @Override
    public IUser getUserByUserName(String name) {
        return this.userDao.getUserByUserName(name);
    }

    @Override
    public IUser getUserByEmail(String email) {
        return this.userDao.getUserByEmail(email);
    }

    @Override
    public Collection<IUser> getUsersByAge(int age) {
        return this.userDao.getUsersByAge(age);
    }

    @Override
    public Collection<IUser> getUsersByGender(String gender) {
        return this.userDao.getUsersByGender(gender);
    }

    @Override
    public Collection<IUser> getUsersByCountry(String country) {
        return this.userDao.getUsersByCountry(country);
    }

    @Override
    public Collection<IUser> getUsersByCity(String city) {
        return this.userDao.getUserByCity(city);
    }

    @Override
    public void register(IUser user) {
        this.userDao.insertUserToDb(user);
        logger.info("registered new user");
    }

    @Override
    public void setFirst(int userid, String first) {
        this.userDao.setFirstName(userid, first);
    }

    @Override
    public void setLast(int userid, String last) {
        this.userDao.setLastName(userid, last);
    }

    @Override
    public void setEmail(int userid, String email) {
        this.userDao.setEmail(userid, email);
    }

    @Override
    public void setAge(int userid, int age) {
        this.userDao.setAge(userid, age);
    }

    @Override
    public void setGender(int userid, String gender) {
        this.userDao.setGender(userid, gender);
    }

    @Override
    public void setCountry(int userid, String country) {
        this.userDao.setCountry(userid, country);
    }

    @Override
    public void setCity(int userid, String city) {
        this.userDao.setCity(userid, city);
    }

    @Override
    public void setPassword(int userid, String password) {
        this.userDao.setPassword(userid, password);
    }
}