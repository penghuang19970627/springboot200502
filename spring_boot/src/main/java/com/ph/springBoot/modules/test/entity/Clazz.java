package com.ph.springBoot.modules.test.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "p_clazz")
public class Clazz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int clazzId;
    private String schoolName;

    /*一对多*/
    @ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id",insertable = false,updatable = false)
    @JsonIgnore
    private School school;


    /*多对多*/
    @ManyToMany(cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
    @JoinTable(name = "p_clazz_student",
            joinColumns = @JoinColumn(name = "clazz_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<Student> students;

    public int getClazzId() {
        return clazzId;
    }

    public void setClazzId(int clazzId) {
        this.clazzId = clazzId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
