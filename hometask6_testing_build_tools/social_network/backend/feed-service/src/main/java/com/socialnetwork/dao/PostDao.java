package com.socialnetwork.dao;

import com.socialnetwork.entity.interfaces.IPost;

import java.util.Collection;

public interface PostDao {
    void createPost(IPost post);

    void updatePosts(int id, IPost post);

    void removePostsById(int id1, int id);

    IPost getPostsById(int id);

    Collection<IPost> getAllPosts();

    Collection<IPost> getPostsFromGroup(int groupId);

    Collection<IPost> getPostsFromGroup(String name);

    Collection<IPost> getPostsByUser(int userId);

    Collection<IPost> getPostsByUserFromGroup(int id1, int id2, int groupId);

    Collection<IPost> getPostsByUserFromGroupName(int id1, int id2, String name);

    Collection<IPost> getPostsByContent(String content);

    Collection<IPost> getPostsByAuthor(String authorFirs, String authorLast);

    Collection<IPost> getPostsByLikes(int likes);

    Collection<IPost> getPostsByLikedBy(int likedBy);

    Collection<IPost> getPostsByTime(int time);

    Collection<IPost> getPostUserMainPage(int userid);

    Collection<IPost> getPostsByWall(int wall);
}