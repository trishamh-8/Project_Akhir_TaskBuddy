package structure;

import java.time.LocalDateTime;

public class LogNode {
    private String activity;
    private LocalDateTime timestamp;
    private String username;
    private LogNode prev;
    private LogNode next;

    public LogNode(String username, String activity) {
        this.username = username;
        this.activity = activity;
        this.timestamp = LocalDateTime.now();
        this.prev = null;
        this.next = null;
    }

    public String getActivity() {
        return activity;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getUsername() {
        return username;
    }

    public LogNode getPrev() {
        return prev;
    }

    public void setPrev(LogNode prev) {
        this.prev = prev;
    }

    public LogNode getNext() {
        return next;
    }

    public void setNext(LogNode next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return String.format("[%s] [%s] %s", timestamp.toString().replace("T", " "), username, activity);
    }
}