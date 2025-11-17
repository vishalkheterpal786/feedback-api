package com.study.feedback_api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ContactType {
    GENERAL,
    MARKETING,
    SUPPORT;

    @JsonValue
    public String toValue(){
        return this.name();
    }

    @JsonCreator
    public static ContactType fromValue(String value) {
        if (value == null || value.trim().equalsIgnoreCase("null")) {
            return null;
        }
        return ContactType.valueOf(value.trim().toUpperCase());
    }
}