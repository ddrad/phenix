package com.phenix.model;

public enum TaskStatus {

    NEW("New"),
    ACTIVATED("Activated"),
    NOT_ACTIVATED("Not_activated"),
    ASSIGNED("Assigned"),
    BLOCKED("Blocked"),
    RESOLVED("Resolved");

    private String statusName;

    TaskStatus(String statusName) {
        this.statusName = statusName;
    }

    public String statusName() {
        return statusName;
    }
}
