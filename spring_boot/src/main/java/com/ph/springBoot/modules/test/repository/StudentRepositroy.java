package com.ph.springBoot.modules.test.repository;

import com.ph.springBoot.modules.test.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepositroy extends JpaRepository<Student,Integer> {
}
