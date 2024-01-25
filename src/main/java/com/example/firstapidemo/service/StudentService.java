package com.example.firstapidemo.service;

import com.example.firstapidemo.dao.StudentDao;
import com.example.firstapidemo.dto.StudentDto;
import com.example.firstapidemo.entity.Student;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentDao studentDao;

    public StudentDto createStudent(StudentDto studentDto){
       return toDto(studentDao.save(toEntity(studentDto)));
    }

    public StudentDto updateStudent(StudentDto studentDto){
        if (studentDao.existsById(studentDto.id())){
        Student student = toEntity(studentDto);
        student.setId(studentDto.id());
        return toDto(studentDao.saveAndFlush(student));
        }else throw new EntityNotFoundException(studentDto.id() + "Not Found!");
    }

    public void deleteStudentById(int id){
        if (studentDao.existsById(id)){
            studentDao.deleteById(id);
        }else throw new EntityNotFoundException(id + "Not Found!");
    }

    public List<StudentDto> listAllStudent(){
        return studentDao.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
    public StudentDto getStudentById(int id){
        return toDto(studentDao.findById(id).orElseThrow());
    }

    private StudentDto toDto(Student student){
        return new StudentDto(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                student.getSchool()
        );
    }

    private Student toEntity(StudentDto studentDto){
        return new Student(studentDto.firstName(),
                studentDto.lastName(),
                studentDto.email(),
                studentDto.school());
    }
    @Transactional
    public StudentDto partialUpdateStudent(String email, int id) {
        Student student = studentDao.findById(id).orElseThrow();
        student.setEmail(email);
        return toDto(student);
    }
}
