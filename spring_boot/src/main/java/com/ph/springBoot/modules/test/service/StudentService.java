package com.ph.springBoot.modules.test.service;

import com.ph.springBoot.modules.common.vo.Result;
import com.ph.springBoot.modules.test.entity.Student;

public interface StudentService {
    /*jpa插入数据*/
    Result<Student> insertStudent(Student student);
}
