package com.socialnetwork.dao;

import com.socialnetwork.entity.interfaces.ISocialGroup;

import java.util.Collection;

public interface GroupDao {

    Collection<ISocialGroup> getAllGroup();

    ISocialGroup getGroupById(int id);

    void removeGroupById(int id);

    void createGroup(ISocialGroup socialGroup);

    Collection<ISocialGroup> getGroupByName(String name);

    Collection<ISocialGroup> getGroupByAdmin(String name);

    Collection<ISocialGroup> getAllGroupsForUser(int memberId);

    void sendJoinRequest(int groupOd, int memberId);

    void addMemberToGroup(int userid, int groupId, int memberId);

    void removeMemberFromGroup(int userid, int groupId, int memberId);
}