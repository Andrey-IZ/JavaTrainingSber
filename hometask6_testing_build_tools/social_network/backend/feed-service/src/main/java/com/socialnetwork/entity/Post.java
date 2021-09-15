package com.socialnetwork.entity;

import com.socialnetwork.entity.interfaces.IPost;

import java.util.Objects;

public class Post implements IPost {
    private int id;
    private String authorFirst;
    private String authorLast;
    private String content;
    private int likes;
    private int time;
    private int visibility;
    private String wallName;

    public Post(String authorFirst, String authorLast, String content,
                int likes, int time, int visibility, String wallName) {
        this.authorFirst = authorFirst;
        this.authorLast = authorLast;
        this.content = content;
        this.likes = likes;
        this.time = time;
        this.visibility = visibility;
        this.wallName = wallName;
    }

    @Override
    public String getWallName() {
        return wallName;
    }

    @Override
    public void setWallName(String wallName) {
        this.wallName = wallName;
    }

    public Post() {
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getAuthorFirstName() {
        return this.authorFirst;
    }

    @Override
    public String getAuthorLastName() {
        return this.authorLast;
    }

    @Override
    public void setAuthorFirstName(String authorFirst) {
        this.authorFirst = authorFirst;
    }

    @Override
    public void setAuthorLastName(String authorLast) {
        this.authorLast = authorLast;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int getLikes() {
        return likes;
    }

    @Override
    public void setLikes(int likes) {
        this.likes = likes;
    }

    @Override
    public int getTime() {
        return time;
    }

    @Override
    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public int getVisibility() {
        return visibility;
    }

    @Override
    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Post post = (Post) o;

        if (id != post.id) return false;
        if (!authorFirst.equals(post.authorFirst)) return false;
        if (!authorLast.equals(post.authorLast)) return false;

        if (likes != post.likes) return false;
        if (time != post.time) return false;
        if (visibility != post.visibility) return false;
        if (!wallName.equals(post.wallName)) return false;
        return Objects.equals(content, post.content);
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (authorFirst != null ? authorFirst.hashCode() : 0);
        result = 31 * result + (authorLast != null ? authorLast.hashCode() : 0);
        result = 31 * result + (getContent() != null ? getContent().hashCode() : 0);
        result = 31 * result + getLikes();
        result = 31 * result + getTime();
        result = 31 * result + getVisibility();
        result = 31 * result + getVisibility();
        result = 31 * result + (wallName != null ? wallName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", authorFirst='" + authorFirst + '\'' +
                ", authorLast='" + authorLast + '\'' +
                ", content='" + content + '\'' +
                ", likes=" + likes +
                ", time=" + time +
                ", visibility=" + visibility +
                ", wallName='" + wallName + '\'' +
                '}';
    }
}
