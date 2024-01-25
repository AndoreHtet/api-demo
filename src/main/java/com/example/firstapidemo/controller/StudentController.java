package com.example.firstapidemo.controller;

import com.example.firstapidemo.dto.StudentDto;
import com.example.firstapidemo.entity.Student;
import com.example.firstapidemo.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StudentController {

    public final StudentService studentService;

    @PatchMapping(value = "/student/update",consumes = MediaType.TEXT_PLAIN_VALUE)
    public StudentDto partialStudentUpdateStudent(@RequestBody String email,
                                                  @RequestParam int id){
        return studentService.partialUpdateStudent(email,id);
    }

    @PutMapping("/student/student-list")
    public StudentDto updateStudent(@RequestBody StudentDto studentDto){
        return studentService.updateStudent(studentDto);
    }

    @DeleteMapping("/student/delete")
    public ResponseEntity deleteStudentById(@RequestParam int id){
        studentService.deleteStudentById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/student/student-list")
    public StudentDto createStudent(@RequestBody StudentDto studentDto){
        return studentService.createStudent(studentDto);
    }

    @GetMapping("/student/student-list")
    public List<StudentDto> listAllStudent(){
        return studentService.listAllStudent();
    }
    @GetMapping("/student")
    public ResponseEntity<StudentDto> findStudent(@RequestParam int id){
        StudentDto studentDto = studentService.getStudentById(id);
        return ResponseEntity.ok(studentDto);
    }
}
