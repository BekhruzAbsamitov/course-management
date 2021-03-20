package com.pdp.demo.payload;

import com.pdp.demo.entity.CompanyCategory;
import com.pdp.demo.entity.Contact;
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
