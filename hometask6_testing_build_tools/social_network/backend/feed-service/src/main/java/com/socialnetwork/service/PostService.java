package com.socialnetwork.service;

import com.socialnetwork.dao.PostDao;
import com.socialnetwork.entity.interfaces.IPost;

import java.util.Collection;

public class PostService implements IPostService {
    private final Logger logger;
    private final PostDao postDao;

    PostService(PostDao postDao, Logger logger) {
        this.postDao = postDao;
        this.logger = logger;
    }

    @Override
    public Collection<IPost> getAllPosts() {
        return this.postDao.getAllPosts();
    }

    @Override
    public Collection<IPost> getPostsByUser(int userId) {
        return this.postDao.getPostsByUser(userId);
    }

    @Override
    public Collection<IPost> getPostsFromGroup(int groupId) {
        return this.postDao.getPostsFromGroup(groupId);
    }

    @Override
    public Collection<IPost> getPostsFromGroup(String name) {
        return this.postDao.getPostsFromGroup(name);
    }

    @Override
    public Collection<IPost> getPostsByUserFromGroup(int id1, int id2, int groupId) {
        return this.postDao.getPostsByUserFromGroup(id1, id2, groupId);
    }

    @Override
    public IPost getPostsById(int id) {
        return this.postDao.getPostsById(id);
    }

    @Override
    public void removePostsById(int id1, int id) {
        this.postDao.removePostsById(id1, id);
    }

    @Override
    public void updatePosts(int id, IPost assignment) {
        this.postDao.updatePosts(id, assignment);
    }

    @Override
    public Collection<IPost> getPostUserMainPage(int userid) {
        return this.postDao.getPostUserMainPage(userid);
    }

    @Override
    public void createPost(IPost assignment) {
        this.postDao.createPost(assignment);
    }

    @Override
    public Collection<IPost> getPostsByContent(String content) {
        return this.postDao.getPostsByContent(content);
    }

    @Override
    public Collection<IPost> getPostsByUserFromGroupName(int id1, int id2, String name) {
        return this.postDao.getPostsByUserFromGroupName(id1, id2, name);
    }
}