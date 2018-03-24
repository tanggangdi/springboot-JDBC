package com.example.student.service;

import com.example.student.Repository.StudentRepositories;
import com.example.student.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentService {
    @Autowired
    private StudentRepositories studentRepositories;

    @Transactional
    public void insertTwo(){
        Student  studentA=new Student();
        studentA.setName("A");
        studentA.setAge(20);
        studentRepositories.save(studentA);

        Student studentB=new Student();
        studentB.setName("B");
        studentB.setAge(22);
        studentRepositories.save(studentB);
    }
}
