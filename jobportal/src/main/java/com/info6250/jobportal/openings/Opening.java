package com.info6250.jobportal.openings;

import javax.persistence.*;

@Entity
@Table(name = "openings")
public class Opening {

    @Id
    @Column(unique = true, nullable = false)
    private String id;
    private String employer_id;
    private String company;
    private String position;
    private String location;
    private String description;
    private String requirements;
    private Integer salary;
    private Boolean open;

    public Opening(String id, String employer_id, String company, String position, String location, String description, String requirements, Integer salary, Boolean open) {
        this.id = id;
        this.employer_id = employer_id;
        this.company = company;
        this.position = position;
        this.location = location;
        this.description = description;
        this.requirements = requirements;
        this.salary = salary;
        this.open = open;
    }

    public Opening() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmployer_id() {
        return employer_id;
    }

    public void setEmployer_id(String employer_id) {
        this.employer_id = employer_id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }
}
