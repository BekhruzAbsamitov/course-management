package com.example.demo.payload;

import com.example.demo.entity.Role;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {

    private String firstName;
    private String lastName;
    private Integer age;
    private String phoneNumber;
    private Integer homeNumber;
    private String streetName;
    private Integer districtId;
    private List<Role> roleList;

}
