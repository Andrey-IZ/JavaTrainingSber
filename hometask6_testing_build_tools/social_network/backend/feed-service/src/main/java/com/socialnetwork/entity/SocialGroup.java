package com.socialnetwork.entity;

import com.socialnetwork.entity.interfaces.ISocialGroup;

import java.util.Objects;

public class SocialGroup implements ISocialGroup {
    private int groupId;
    private String groupAdmin;
    private String groupName;

    public SocialGroup(String groupAdmin, String name) {
        this.groupAdmin = groupAdmin;
        this.groupName = name;
    }

    public SocialGroup() {
    }

    @Override
    public int getGroupID() {
        return groupId;
    }

    @Override
    public void setGroupID(int groupId) {
        this.groupId = groupId;
    }

    @Override
    public String getAdmin() {
        return groupAdmin;
    }

    @Override
    public void setAdmin(String groupadmin) {
        this.groupAdmin = groupadmin;
    }

    @Override
    public String getName() {
        return groupName;
    }

    @Override
    public void setName(String name) {
        this.groupName = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SocialGroup socialGroup = (SocialGroup) o;

        if (groupId != socialGroup.groupId) return false;
        if (getAdmin() != null ? !getAdmin().equals(socialGroup.getAdmin()) : socialGroup.getAdmin() != null)
            return false;
        return Objects.equals(groupName, socialGroup.groupName);
    }

    @Override
    public int hashCode() {
        int result = groupId;
        result = 31 * result + (getAdmin() != null ? getAdmin().hashCode() : 0);
        result = 31 * result + (groupName != null ? groupName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "FbGroup{" +
                "groupid=" + groupId +
                ", groupadmin=" + groupAdmin +
                ", groupname='" + groupName + '\'' +
                '}';
    }
}