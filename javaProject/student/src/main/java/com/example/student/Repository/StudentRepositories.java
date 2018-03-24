package com.example.student.Repository;

import com.example.student.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepositories extends JpaRepository<Student ,Integer> {
    //通过年龄来查询
    public List<Student>  findByAge(Integer age);
}
