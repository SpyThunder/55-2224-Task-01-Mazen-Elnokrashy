package s55_2224.t_27.Mazen_Elnokrashy.models;

import java.time.LocalDateTime;
import java.util.UUID;

public class Note {
    private String id;
    private String title;
    private String content;
    private String userId;
    private LocalDateTime createdAt;

    public Note() {
        this.createdAt = LocalDateTime.now();
        this.id = UUID.randomUUID().toString();
    }

    public Note(String title, String content, String userId) {
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.createdAt = LocalDateTime.now();
        this.id = UUID.randomUUID().toString();
    }

    public Note(String id, String title, String content, String userId, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.createdAt = createdAt;
    }

    public Note(String id, String title, String content, String userId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.createdAt = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getUserId() {
        return userId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }




}
