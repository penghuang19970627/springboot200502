package com.ph.springBoot.modules.test.service.Impl;

import com.ph.springBoot.modules.common.vo.Result;
import com.ph.springBoot.modules.test.entity.Student;
import com.ph.springBoot.modules.test.repository.StudentRepositroy;
import com.ph.springBoot.modules.test.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepositroy studentRepositroy;

    @Override
    @Transactional
    public Result<Student> insertStudent(Student student) {
        student.setCreateDate(LocalDateTime.now());
        studentRepositroy.saveAndFlush(student);
        return new Result<Student>(Result.ResultStatus.SUCCESS.status,"Insert success.",student);
    }
}
