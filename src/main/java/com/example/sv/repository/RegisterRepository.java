package com.example.sv.repository;

import com.example.sv.entity.Register;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegisterRepository extends JpaRepository<Register, Integer> {
    @Query("SELECT s.subjectDescription " +
            "FROM Subject s " +
            "JOIN Register r ON s.subjectId = r.subjectId " +
            "WHERE r.studentId = :id")
    List<String> getSubjectById(@Param("id") int id);

    boolean existsByStudentIdAndSubjectId(int studentId, int subjectId);
    Register findByStudentIdAndSubjectId(int studentId, int subjectId);
}
