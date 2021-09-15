package com.socialnetwork.service;

import com.socialnetwork.entity.User;

import java.util.Collection;

public interface IFriendsService {
    Collection<User> getAllFriends(int id);

    void removeAllFriends(int id);

    void unFriend(int id, String username);

    int countFriends(int id);

    void sendRequest(int id, String username);

    void becomeFriend(int id, String username);

    void blockFriend(int id, String username);

    Collection<User> commonFriends(int id, String username);

    Collection<User> getFriendsByName(String username, int id);

    Collection<User> getInvitationList(int id);

    Collection<User> getBlockList(int id);
}
