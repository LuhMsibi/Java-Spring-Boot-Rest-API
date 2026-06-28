package com.example.practice.Student;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {
    @GetMapping
    public List<Student> getStudents(){
        return List.of(
                new Student("Lungisani", 1L, "Luh@gmail.com", LocalDate.of(2001, Month.APRIL, 25), 25)
        );

    }

}
