package com.example.sv.entity;

import com.example.sv.key.CompositeKey;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@IdClass(CompositeKey.class)
@Table(name="mark")
public class Mark {
    @Id
    @Column(name="studentId")
    int studentId;
    @Id
    @Column(name="subjectId")
    int subjectId;
    float score;
}


