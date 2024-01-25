package com.example.firstapidemo.dao;

import com.example.firstapidemo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentDao extends JpaRepository<Student,Integer> {
}
