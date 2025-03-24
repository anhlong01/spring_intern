package com.example.sv.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "register", uniqueConstraints = @UniqueConstraint(columnNames = {"studentId", "subjectId"}))
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Register {
    @Id
    @Column(name = "registerId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    int registerId;
    int studentId;
    int subjectId;
}
