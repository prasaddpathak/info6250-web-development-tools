package com.info6250.jobportal.applications;

import java.io.Serializable;

public class ApplicationId implements Serializable {

    private String user_id;
    private String opening_id;

    public ApplicationId(String user_id, String opening_id) {
        this.user_id = user_id;
        this.opening_id = opening_id;
    }

    public ApplicationId() {
    }

    @Override
    public String toString() {
        return "ApplicationId{" +
                "user_id='" + user_id + '\'' +
                ", opening_id='" + opening_id + '\'' +
                '}';
    }
}
