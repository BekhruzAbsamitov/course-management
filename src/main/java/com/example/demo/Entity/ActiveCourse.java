package com.example.demo.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class ActiveCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer max_students;

    @Column(nullable = false)
    private Integer current_students;

    @Column(nullable = false)
    private Date datetime;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Boolean status;

    @OneToOne
    private Course course;


}
