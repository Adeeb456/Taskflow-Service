package com.adeeb.taskflow.taskflow_service.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TaskStatus {

    PENDING("pending"),
    IN_PROGRESS("in_progress"),
    COMPLETED("completed");

    private final String value;

    TaskStatus(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}