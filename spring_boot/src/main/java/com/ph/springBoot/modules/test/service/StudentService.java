package com.ph.springBoot.modules.test.service;

import com.ph.springBoot.modules.common.vo.Result;
import com.ph.springBoot.modules.common.vo.SearchVo;
import com.ph.springBoot.modules.test.entity.Student;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StudentService {
    /*jpa插入数据*/
    Result<Student> insertStudent(Student student);

    /*jpa根据ID查询*/
    Student getStudentByStudentId(int studentId);

    /*jpa分页查询*/
    Page<Student> getStudentBySearchVo(SearchVo searchVo);

    /*自定义查询*/
    List<Student> getStudentByStudentName(String studentName,int cardId);
}
