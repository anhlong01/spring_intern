package com.example.sv.services;

import com.example.sv.entity.Mark;
import com.example.sv.entity.Register;
import com.example.sv.entity.Student;
import com.example.sv.entity.Subject;
import com.example.sv.exception.AppException;
import com.example.sv.exception.ErrorCode;
import com.example.sv.repository.MarkRepository;
import com.example.sv.repository.RegisterRepository;
import com.example.sv.repository.StudentRepository;
import com.example.sv.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    MarkRepository markRepository;
    @Autowired
    SubjectRepository subjectRepository;
    @Autowired
    RegisterRepository registerRepository;
    public List<Student> findAll(){
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(int id){
        return studentRepository.findByStudentId(id);
    }

    public List<String> getSubjectById(int id){
        List<String> list = registerRepository.getSubjectById(id);
        if(list.size()==0)
            throw new AppException(ErrorCode.SUBJECT_NOT_FOUND);
        return list;

    }

    public HashMap<String, Float> getMarkById(int id){
        List<Object[]> list= markRepository.findByStudentId(id);
        HashMap<String, Float> map = new HashMap<>();
        if(list.size()==0)
            throw new AppException(ErrorCode.SUBJECT_NOT_FOUND);
        for(Object[] o:list){
            String subjectDesription = (String) o[0];
            Float score = (Float) o[1];
            map.put(subjectDesription, score);
        }
        return map;
    }

    public List<Subject> findAllSubject(){
        return subjectRepository.findAll();
    }

    public boolean checkPass(int id){
        HashMap<String, Float> map = getMarkById(id);
        for(float value: map.values()){
            if (value<4)
                return false;
        }
        return true;
    }

    public Mark insertMark(int studentId, int subjectId, float score){
        boolean existInRegister = registerRepository.existsByStudentIdAndSubjectId(studentId, subjectId);
        if (!existInRegister)
            throw new AppException(ErrorCode.NOT_REGISTERED);
        Register register = registerRepository.findByStudentIdAndSubjectId(studentId, subjectId);
        boolean existInMark = markRepository.existsByRegisterId(register.getRegisterId());
        if(existInMark)
            throw new AppException(ErrorCode.RECORD_EXIST);
        Mark mark= new Mark();
        mark.setRegisterId(register.getRegisterId());
        mark.setScore(score);
        return markRepository.save(mark);
    }

}
