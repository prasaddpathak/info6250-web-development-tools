package com.info6250.jobportal.users;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(unique = true, nullable = false)
    String id;
    @Column(nullable = false)
    private String first_name;
    @Column(nullable = false)
    private String last_name;
    @Column(unique = true, nullable = false)
    private String email_id;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private Integer age;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserType user_type;

    public User(String id, String first_name, String last_name, String email_id, String password, Integer age, UserType user_type) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email_id = email_id;
        this.password = password;
        this.age = age;
        this.user_type = user_type;
    }

    public User() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public UserType getUser_type() {
        return user_type;
    }

    public void setUser_type(UserType user_type) {
        this.user_type = user_type;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email_id='" + email_id + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", user_type=" + user_type +
                '}';
    }
}
