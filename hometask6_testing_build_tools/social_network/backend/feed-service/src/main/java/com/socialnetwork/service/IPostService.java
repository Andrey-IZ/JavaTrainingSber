package com.socialnetwork.service;

import com.socialnetwork.entity.interfaces.IPost;

import java.util.Collection;

public interface IPostService {
    Collection<IPost> getAllPosts();

    Collection<IPost> getPostsByUser(int userId);

    Collection<IPost> getPostsFromGroup(int groupId);

    Collection<IPost> getPostsFromGroup(String name);

    Collection<IPost> getPostsByUserFromGroup(int id1, int id2, int groupId);

    IPost getPostsById(int id);

    void removePostsById(int id1, int id);

    void updatePosts(int id, IPost assignment);

    Collection<IPost> getPostUserMainPage(int userid);

    void createPost(IPost assignment);

    Collection<IPost> getPostsByContent(String content);

    Collection<IPost> getPostsByUserFromGroupName(int id1, int id2, String name);
}
