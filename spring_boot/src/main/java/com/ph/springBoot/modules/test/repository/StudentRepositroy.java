package com.ph.springBoot.modules.test.repository;

import com.ph.springBoot.modules.test.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepositroy extends JpaRepository<Student,Integer> {


    /*精确查询*/
    List<Student> findByStudentName(String studentName);
    /*模糊查询*/
    List<Student> findByStudentNameLike(String studentName);
    /*查询两条数据*/
    List<Student> findTop2ByStudentNameLike(String studentName);

    /*自定义查询*/
    //@Query(value = "select s from Student s where s.studentName = ?1 and s.studentCard.cardId = ?2")
    //@Query(value = "select s from Student s where s.studentName = :studentName and s.studentCard.cardId = :cardId")
    @Query(nativeQuery = true,value = "select * from p_student where student_name = :studentName and card_id = :cardId")
    List<Student> getStudentsByParams(@Param("studentName") String studentName,
                                      @Param("cardId") int cardId);
}
