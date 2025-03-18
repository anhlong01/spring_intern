package com.example.sv.services;

import com.example.sv.entity.Mark;
import com.example.sv.entity.Student;
import com.example.sv.entity.Subject;
import com.example.sv.exception.AppException;
import com.example.sv.exception.ErrorCode;
import com.example.sv.repository.MarkRepository;
import com.example.sv.repository.StudentRepository;
import com.example.sv.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    MarkRepository markRepository;
    @Autowired
    SubjectRepository subjectRepository;
    public List<Student> findAll(){
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(int id){
        return studentRepository.findByStudentId(id);
    }

    public List<String> getSubjectById(int id){
        List<Mark> list = markRepository.findByStudentId(id);
        return list.stream().map(mark ->{
            Subject subject = subjectRepository.findBySubjectId(mark.getSubjectId());
            return subject.getSubjectDescription();
        }).collect(Collectors.toList());
    }

    public HashMap<String, Float> getMarkById(int id){
        HashMap<String, Float> map= new HashMap();
        List<Mark> list = markRepository.findByStudentId(id);
        if(list.size()==0)
            throw new AppException(ErrorCode.SUBJECT_NOT_FOUND);
        for(Mark mark: list){
            Subject subject = subjectRepository.findBySubjectId(mark.getSubjectId());
            map.put(subject.getSubjectDescription(), mark.getScore());
        }
        return map;
    }

    public boolean checkPass(int id){
        HashMap<String, Float> map = getMarkById(id);
        for(float value: map.values()){
            if (value<4)
                return false;
        }
        return true;
    }
}
