package com.example.student.Controller;
import com.example.student.domain.Student;
import com.example.student.Repository.StudentRepositories;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
public class StudentController {

    private  final  static Logger LOGGER= LoggerFactory.getLogger(StudentController.class);
    @Autowired
    private StudentRepositories studentRepositories;

    /**
     * 查询所有学生列表
     * @return
     */
    @GetMapping(value = "/students")
    public List<Student> getStudent(){
        LOGGER.info("getStudent");
        return studentRepositories.findAll();
    }

    /**
     * 加入一个学生，
     * 参数为名字和年龄
     * @return
     */
    @PostMapping(value = "/students")
    public Student StudentAdd(@Valid Student student, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            System.out.println(bindingResult.getFieldError().getDefaultMessage());
            return null;
        }
        student.setName(student.getName());
        student.setAge(student.getAge());
        return studentRepositories.save(student);
    }
    /**
   *查询一
     */
    @GetMapping(value = "/students/{id}")
    public Object StudentFind(@PathVariable("id") Integer id){
        return studentRepositories.findById(id).get();
    }

    /**
     * 更新一个学生的信息
     */
    @PutMapping(value = "/students/{id}")
    public Student UpdateStudent(@PathVariable("id") int id,@RequestParam("name") String name,@RequestParam("age") int age){
        Student student=new Student();
        student.setId(id);
        student.setAge(age);
        student.setName(name);
        return studentRepositories.save(student);
    }
    /**
     * 删除一个学生
     */
   @DeleteMapping(value="/students/{id}")
    public void  DeleteStudent(@PathVariable("id") Integer id){
        studentRepositories.deleteById(id);
    }

    /**
     * 通过年龄来查询列表，自定以
     */
    @GetMapping(value = "/students/age/{age}")
    public  List<Student>  StudentAge(@PathVariable("age") Integer age){
        return studentRepositories.findByAge(age);
    }
}
