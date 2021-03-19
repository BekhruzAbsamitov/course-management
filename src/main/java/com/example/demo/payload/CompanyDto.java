package com.example.demo.payload;

import com.example.demo.entity.CompanyCategory;
import com.example.demo.entity.Contact;
import lombok.Data;

import java.util.List;

@Data
public class CompanyDto {

    private String name;
    private String description;
    private Integer homeNumber;
    private String streetName;
    private Integer districtId;
    private Contact contact;
    private List<CompanyCategory> companyCategoryList;
}
