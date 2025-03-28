package com.example.sv.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="mark")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Mark {
    @Id
    @Column(name="markId")
    int markId;
    @Column(name = "registerId")
    int registerId;
    float score;
}


