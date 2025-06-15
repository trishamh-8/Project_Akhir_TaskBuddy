package model;

import structure.TaskTree;
import structure.ActivityLog;

public class User {
    private String username;
    private UserRole role;
    private TaskTree userTaskTree;
    private ActivityLog userActivityLog;

    public User(String username, UserRole role) {
        this.username = username;
        this.role = role;
        this.userTaskTree = new TaskTree();
        this.userActivityLog = new ActivityLog();
    }

    public String getUsername() {
        return username;
    }

    public UserRole getRole() {
        return role;
    }

    public TaskTree getUserTaskTree() {
        return userTaskTree;
    }

    public ActivityLog getUserActivityLog() {
        return userActivityLog;
    }

    @Override
    public String toString() {
        return "User(username=" + username + ", role=" + role + ")";
    }
}