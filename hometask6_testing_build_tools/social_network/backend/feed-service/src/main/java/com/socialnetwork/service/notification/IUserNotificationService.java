package com.socialnetwork.service.notification;

public interface IUserNotificationService {
    void notifyLikedPost(int postId);

    void notifyViewedPost(int postId);
}
