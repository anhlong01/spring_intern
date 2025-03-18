package com.example.sv.repository;

import com.example.sv.entity.Mark;
import com.example.sv.key.CompositeKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarkRepository extends JpaRepository<Mark, CompositeKey> {
    List<Mark> findByStudentId(int studentId);
    @Query("SELECT COUNT(*) > 0 AS recordExists FROM Mark e WHERE e.studentId = :id1 AND e.subjectId = :id2")
    boolean existsByCompositeKey(@Param("id1") int id1, @Param("id2") int id2);
}
