package com.example.sv.controllers;

import com.example.sv.dto.response.ApiResponse;
import com.example.sv.entity.Mark;
import com.example.sv.entity.Student;
import com.example.sv.entity.Subject;
import com.example.sv.exception.AppException;
import com.example.sv.exception.ErrorCode;
import com.example.sv.key.CompositeKey;
import com.example.sv.repository.MarkRepository;
import com.example.sv.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("student")
public class StudentController {
    @Autowired
    StudentService studentService;
    @Autowired
    MarkRepository markRepository;
    @GetMapping
    ApiResponse<List<Student>> getAllStudents(){
        List<Student> list = studentService.findAll();
        ApiResponse<List<Student>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(list);
        return apiResponse;
    }

    @GetMapping("/{id}")
    ApiResponse<Optional<Student>> getStudentById(@PathVariable int id){
        Optional<Student> student = studentService.getStudentById(id);
        if(!student.isPresent())
            throw new AppException(ErrorCode.STUDENT_NOT_FOUND);
        ApiResponse<Optional<Student>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(student);
        return apiResponse;
    }

    @GetMapping("/subject/{id}")
    ApiResponse<List<String>> getSubjectById(@PathVariable int id){
        List<String> list = studentService.getSubjectById(id);
        if(list.size()==0)
            throw new AppException(ErrorCode.SUBJECT_NOT_FOUND);
        ApiResponse<List<String>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(list);
        return apiResponse;
    }

    @GetMapping("/mark/{id}")
    ApiResponse<HashMap<String, Float>> getMarkById(@PathVariable int id){
        HashMap<String, Float> map = studentService.getMarkById(id);
        ApiResponse<HashMap<String, Float>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(map);
        return apiResponse;
    }

    @PostMapping("/mark/insert")
    ApiResponse<Mark> insertMark(@RequestParam int studentId, @RequestParam int subjectId, @RequestParam float mark){
        boolean exist = markRepository.existsByCompositeKey(studentId, subjectId);
        if(exist)
            throw new AppException(ErrorCode.RECORD_EXIST);
        Mark newMark = new Mark(studentId, subjectId, mark);
        markRepository.save(newMark);
        ApiResponse<Mark> apiResponse = new ApiResponse<>();
        apiResponse.setResult(newMark);
        return apiResponse;
    }

    @GetMapping("/mark/check/{id}")
    ApiResponse<String> checkPass(@PathVariable int id){
        boolean pass = studentService.checkPass(id);
        ApiResponse<String> apiResponse = new ApiResponse<>();
        if(pass)
            apiResponse.setResult("Sinh viên này qua môn rồi");
        else
            apiResponse.setResult("Sinh viên này tạch môn rồi");
        return apiResponse;
    }
}
