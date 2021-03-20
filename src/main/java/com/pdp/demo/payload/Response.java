package com.pdp.demo.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response {

    private Integer homeNumber;
    private String streetName;
    private String email;
    private String phoneNumber;
    private String telegram;
    private String website;
    private String companyName;
    private String courseName;

}
