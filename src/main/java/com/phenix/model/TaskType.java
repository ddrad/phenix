package com.phenix.model;

public enum TaskType {

    DAFAULT("Default");

    private String typeName;

    TaskType(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }
}
