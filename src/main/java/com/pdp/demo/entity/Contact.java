package com.pdp.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String phoneNumber;


    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String website;

    @Column(nullable = false)
    private String telegram;



}
