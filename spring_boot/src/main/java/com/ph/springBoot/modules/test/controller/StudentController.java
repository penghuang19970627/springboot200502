package com.ph.springBoot.modules.test.controller;

import com.ph.springBoot.modules.common.vo.Result;
import com.ph.springBoot.modules.common.vo.SearchVo;
import com.ph.springBoot.modules.test.entity.Student;
import com.ph.springBoot.modules.test.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    /*jpa根据ID查询*/
    @GetMapping("/student/{studentId}")
    public Student getStudentByStudentId(@PathVariable int studentId){
        return studentService.getStudentByStudentId(studentId);
    }

    /*jpa分页查询*/
    /*127.0.0.1/ph/students*/
    /*{"currentPage":"1","pageSize":"5","keyWord":"h","orderBy":"studentName","sort":"asc"}*/
    @PostMapping(value = "/students" , consumes = "application/json")
    public Page<Student> getStudentBySearchVo(@RequestBody SearchVo searchVo){
        return studentService.getStudentBySearchVo(searchVo);
    }

    /*自定义查询*/
    /*127.0.0.1/ph/students?studentName=zhangying*/
    @GetMapping("/students")
    public List<Student> getStudentByParams(@RequestParam String studentName,
                                            /*required这个属性表示cardId并不是必须的，defaultValue设置默认值*/
                                            @RequestParam(required = false,defaultValue = "0") Integer cardId){
        return studentService.getStudentByStudentName(studentName,cardId);
    }


}
