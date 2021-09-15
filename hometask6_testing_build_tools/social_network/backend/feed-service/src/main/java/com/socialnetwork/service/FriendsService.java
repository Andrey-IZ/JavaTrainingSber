package com.socialnetwork.service;

import com.socialnetwork.dao.FriendsDao;
import com.socialnetwork.entity.User;
import com.socialnetwork.exceptions.TransactionException;

import java.util.Collection;

public class FriendsService implements IFriendsService {
    private final Logger logger;
    private final FriendsDao dao;

    FriendsService(FriendsDao friendsDao, Logger logger) {
        dao = friendsDao;
        this.logger = logger;
    }

    @Override
    public Collection<User> getAllFriends(int id) {
        return this.dao.getAllFriends(id);
    }

    @Override
    public void removeAllFriends(int id) {
        this.dao.removeAllFriends(id);
    }

    @Override
    public void unFriend(int id, String username) {
        try {
            this.dao.unFriend(id, username);
        } catch (TransactionException e) {
            logger.critical(e.getMessage());
        }
    }

    @Override
    public int countFriends(int id) {
        return this.dao.countFriends(id);
    }

    @Override
    public void sendRequest(int id, String username) {
        try {
            this.dao.sendRequest(id, username);
        } catch (TransactionException e) {
            logger.critical(e.getMessage());
        }
    }

    @Override
    public void becomeFriend(int id, String username) {
        this.dao.becomeFriend(id, username);
    }

    @Override
    public void blockFriend(int id, String username) {
        this.dao.blockFriend(id, username);
    }

    @Override
    public Collection<User> commonFriends(int id, String username) {
        return this.dao.commonFriends(id, username);
    }

    @Override
    public Collection<User> getFriendsByName(String username, int id) {
        return this.dao.getFriendsByName(username, id);
    }

    @Override
    public Collection<User> getInvitationList(int id) {
        return this.dao.getInvitationList(id);
    }

    @Override
    public Collection<User> getBlockList(int id) {
        return this.dao.getBlockList(id);
    }
}