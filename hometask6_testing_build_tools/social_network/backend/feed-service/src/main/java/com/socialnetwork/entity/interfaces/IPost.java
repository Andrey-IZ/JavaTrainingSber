package com.socialnetwork.entity.interfaces;

public interface IPost {
    String getWallName();

    void setWallName(String wallName);

    int getId();

    void setId(int id);

    String getAuthorFirstName();

    String getAuthorLastName();

    void setAuthorFirstName(String authorFirst);

    void setAuthorLastName(String authorLast);

    String getContent();

    void setContent(String content);

    int getLikes();

    void setLikes(int likes);

    int getTime();

    void setTime(int time);

    int getVisibility();

    void setVisibility(int visibility);
}
