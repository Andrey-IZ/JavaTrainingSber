package com.socialnetwork.service;

import com.socialnetwork.dao.GroupDao;
import com.socialnetwork.entity.interfaces.ISocialGroup;

import java.util.Collection;

public class GroupService {
    private final GroupDao groupDao;
    private final Logger logger;


    GroupService(GroupDao groupDao, Logger logger) {
        this.groupDao = groupDao;
        this.logger = logger;
    }

    public Collection<ISocialGroup> getAllGroup() {
        return this.groupDao.getAllGroup();
    }

    public ISocialGroup getGroupById(int id) {
        return this.groupDao.getGroupById(id);
    }

    public void removeGroupById(int id) {
        this.groupDao.removeGroupById(id);
    }

    public void createGroup(ISocialGroup fbGroup) {
        this.groupDao.createGroup(fbGroup);
    }

    public Collection<ISocialGroup> getGroupByName(String name) {
        return this.groupDao.getGroupByName(name);
    }

    public Collection<ISocialGroup> getGroupByAdmin(String admin) {
        return this.groupDao.getGroupByAdmin(admin);
    }

    public Collection<ISocialGroup> getAllGroupsForUser(int memberId) {
        return this.groupDao.getAllGroupsForUser(memberId);
    }

    public void sendJoinRequest(int groupId, int memberId) {
        this.groupDao.sendJoinRequest(groupId, memberId);
    }

    public void addMemberToGroup(int userid, int groupId, int memberId) {
        this.groupDao.addMemberToGroup(userid, groupId, memberId);
    }

    public void removeMemberFromGroup(int userid, int groupId, int memberId) {
        this.groupDao.removeMemberFromGroup(userid, groupId, memberId);
    }

}
