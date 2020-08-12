package com.ph.springBoot.modules.test.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "p_school")
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int schoolId;
    private String schoolName;
    @Transient
    private String region;

    /*一对多*/
    @OneToMany(mappedBy = "school",cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
    private List<Clazz> clazzes;

    public int getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public List<Clazz> getClazzes() {
        return clazzes;
    }

    public void setClazzes(List<Clazz> clazzes) {
        this.clazzes = clazzes;
    }
}
