package com.example.sv.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name="subject")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int subjectId;
    String subjectDescription;
}
