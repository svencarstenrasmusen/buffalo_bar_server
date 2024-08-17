package com.sventheeagle.buffalo_bar_server.model;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;

@Entity
@Table(name = "Buffalo_User_Group")
public class UserGroup {
    @Id
    @UuidGenerator
    private String id;

    @ManyToOne
    @JoinColumn(name = "buffalo_user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "buffalo_group_id", nullable = false)
    private Group group;

    @Column(updatable = false)
    private LocalDateTime joinedAt;

    private boolean isAdmin;

    @PrePersist
    protected void onCreate() {
        this.joinedAt = LocalDateTime.now();
    }

    public UserGroup() {}

    public UserGroup(User user, Group group, LocalDateTime joinedAt, boolean isAdmin) {
        this.user = user;
        this.group = group;
        this.joinedAt = joinedAt;
        this.isAdmin = isAdmin;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public LocalDateTime getJoinedAt() {
        return joinedAt;
    }

    public void setJoinedAt(LocalDateTime joinedAt) {
        this.joinedAt = joinedAt;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
