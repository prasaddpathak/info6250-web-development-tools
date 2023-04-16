package com.info6250.jobportal.applications;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "applications")
@IdClass(ApplicationId.class)
public class Application {

    @Id
    @Column(nullable = false)
    String opening_id;
    @Id
    @Column(nullable = false)
    String user_id;
    String status;
    Date applied_on;

    public Application(String opening_id,
                       String user_id,
                       String status,
                       Date applied_on) {
        this.opening_id = opening_id;
        this.user_id = user_id;
        this.status = status;
        this.applied_on = applied_on;
    }

    public Application() {

    }

    public String getOpening_id() {
        return opening_id;
    }

    public void setOpening_id(String opening_id) {
        this.opening_id = opening_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getApplied_on() {
        return applied_on;
    }

    public void setApplied_on(Date applied_on) {
        this.applied_on = applied_on;
    }

    @Override
    public String toString() {
        return "Application{" +
                "opening_id='" + opening_id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", status='" + status + '\'' +
                ", applied_on=" + applied_on +
                '}';
    }
}
