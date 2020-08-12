package com.ph.springBoot.modules.test.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="p_student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int studentId;
    private String studentName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime createDate;

    /*一对一*/
    @OneToOne(targetEntity = Card.class ,cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id")
    private Card studentCard;

    /*多对多*/
    @ManyToMany(mappedBy = "students",cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
    private List<Clazz> clazzes;

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public Card getStudentCard() {
        return studentCard;
    }

    public void setStudentCard(Card studentCard) {
        this.studentCard = studentCard;
    }

    public List<Clazz> getClazzes() {
        return clazzes;
    }

    public void setClazzes(List<Clazz> clazzes) {
        this.clazzes = clazzes;
    }
}
