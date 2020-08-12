package com.ph.springBoot.modules.test.controller;

import com.ph.springBoot.modules.common.vo.Result;
import com.ph.springBoot.modules.test.entity.Student;
import com.ph.springBoot.modules.test.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ph")
public class StudentController {

    @Autowired
    private StudentService studentService;

    /*jpa插入数据*/
    /*127.0.0.1/ph/student*/
    /*{"studentName":"penghuang","studentCard":{"cardId":"4"}}*/
    @PostMapping(value = "/student" , consumes = "application/json")
    public Result<Student> insertStudent(@RequestBody Student student){
        return studentService.insertStudent(student);
    }
}
