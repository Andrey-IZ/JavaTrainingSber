package com.socialnetwork.dao;

import com.socialnetwork.entity.User;
import com.socialnetwork.exceptions.TransactionException;

import java.util.Collection;

public interface FriendsDao {

    /**
     * Get a list of all the friends of a user
     *
     * @param id the user id
     * @return all friends of the user
     */
    Collection<User> getAllFriends(int id);

    /**
     * Get a list of common friends between 2 users
     *
     * @param id       id of user1
     * @param username id of user2
     * @return a list of common friends between 2 users
     */
    Collection<User> commonFriends(int id, String username);

    /**
     * Get a list of friends, who have same last name or first name, of a user
     *
     * @param username the name of the friend
     * @param id       the id of the user
     * @return a list of friends, who have same last name or first name, of a user
     */
    Collection<User> getFriendsByName(String username, int id);

    /**
     * @param id the id of the user
     * @return a list of invited users,
     */
    Collection<User> getInvitationList(int id);

    /**
     * @param id the id of the user
     * @return blocked users list
     */
    Collection<User> getBlockList(int id);

    /**
     * Remove all friends of a user
     *
     * @param id the id of the user
     */
    void removeAllFriends(int id);

    /**
     * Remove a friend of a user from the friend list
     *
     * @param id       the id of the user
     * @param username the friend
     */
    void unFriend(int id, String username) throws TransactionException;

    /**
     * Count all the friends that a user has
     *
     * @param id the user id
     * @return the number of the friends of a user
     */
    int countFriends(int id);

    /**
     * Sending a friend request to a user2 from user1, put them on the friends table
     *
     * @param id       the id of user1
     * @param username the id of user2
     */
    void sendRequest(int id, String username) throws TransactionException;

    /**
     * Make user2 become the friend of user1
     *
     * @param id       id of user1
     * @param username id of user2
     */
    void becomeFriend(int id, String username);

    /**
     * Blocking user2, who is friend of user1, by user1
     */
    void blockFriend(int id, String username);
}