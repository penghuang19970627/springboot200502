package com.ph.springBoot.modules.test.service.Impl;

import com.ph.springBoot.modules.common.vo.Result;
import com.ph.springBoot.modules.common.vo.SearchVo;
import com.ph.springBoot.modules.test.entity.Student;
import com.ph.springBoot.modules.test.repository.StudentRepositroy;
import com.ph.springBoot.modules.test.service.StudentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepositroy studentRepositroy;


    /*jpa插入数据*/
    @Override
    @Transactional
    public Result<Student> insertStudent(Student student) {
        student.setCreateDate(LocalDateTime.now());
        studentRepositroy.saveAndFlush(student);
        return new Result<Student>(Result.ResultStatus.SUCCESS.status,"Insert success.",student);
    }

   /*jpa根据ID查询*/
    @Override
    public Student getStudentByStudentId(int studentId) {
        return studentRepositroy.findById(studentId).get();
    }


    /*jpa分页查询*/
    @Override
    public Page<Student> getStudentBySearchVo(SearchVo searchVo) {
        String orderBy = StringUtils.isBlank(searchVo.getOrderBy()) ?
                "studentId" : searchVo.getOrderBy();

        Sort.Direction direction = StringUtils.isBlank(searchVo.getSort()) ||
                searchVo.getSort().equalsIgnoreCase("asc") ?
                Sort.Direction.ASC : Sort.Direction.DESC;
        /*Sort底层，访问权限控制为private，所以需要使用.by的方式*/
        Sort sort = Sort.by(direction,orderBy);

        /*分页，默认从0开始*/
        Pageable pageable = PageRequest.of(searchVo.getCurrentPage() - 1,searchVo.getPageSize(),sort);


        /*条件*/
        Student student = new Student();
        student.setStudentName(searchVo.getKeyWord());

        ExampleMatcher matcher = ExampleMatcher.matching()
                /*全部模糊查询*/
                .withMatcher("studentName", match -> match.contains())
                /*忽略字段，不管id是什么值都不参与条件*/
                .withIgnorePaths("studentId");

        Example<Student> example = Example.of(student,matcher);
        return studentRepositroy.findAll(example,pageable);
    }

    @Override
    public List<Student> getStudentByStudentName(String studentName, int cardId) {
        if (cardId > 0){
            /*自定义查询*/
            return studentRepositroy.getStudentsByParams(studentName,cardId);
        }else {
            /*精确查询*/
            //return Optional.ofNullable(studentRepositroy.findByStudentName(studentName)).orElse(Collections.emptyList());
            /*模糊查询*/
            return Optional.ofNullable(studentRepositroy.findByStudentNameLike(
                    String.format("%s%S%s","%",studentName,"%"))).orElse(Collections.emptyList());
            /*查询两条数据*/
            /*return Optional.ofNullable(studentRepositroy.findTop2ByStudentNameLike(
                    String.format("%s%S%s","%",studentName,"%"))).orElse(Collections.emptyList());*/
        }
    }


}
