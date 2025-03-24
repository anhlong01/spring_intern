package com.example.sv.repository;

import com.example.sv.entity.Mark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarkRepository extends JpaRepository<Mark, Integer> {
    @Query("SELECT s.subjectDescription, m.score " +
            "FROM Register r " +
            "JOIN Mark m on m.registerId = r.registerId " +
            "JOIN Subject s on s.subjectId = r.subjectId " +
            "WHERE r.studentId = :id")
    List<Object[]> findByStudentId(@Param("id") int studentId);

    boolean existsByRegisterId( int registerId);
}
