package com.gunn.jys.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "t_academic")
public class Academic {

    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    private Integer presidentId;

    private String academicName;

    private String academicDescription;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPresidentId() {
        return presidentId;
    }

    public void setPresidentId(Integer presidentId) {
        this.presidentId = presidentId;
    }

    public String getAcademicName() {
        return academicName;
    }

    public void setAcademicName(String academicName) {
        this.academicName = academicName == null ? null : academicName.trim();
    }

    public String getAcademicDescription() {
        return academicDescription;
    }

    public void setAcademicDescription(String academicDescription) {
        this.academicDescription = academicDescription == null ? null : academicDescription.trim();
    }
}