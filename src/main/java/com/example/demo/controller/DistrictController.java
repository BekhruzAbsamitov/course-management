package com.example.demo.controller;


import com.example.demo.entity.District;
import com.example.demo.payload.DistrictDto;
import com.example.demo.payload.Result;
import com.example.demo.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/district")
public class DistrictController {

    @Autowired
    DistrictService districtService;

    @GetMapping
    public List<District> getDistricts(){

        List<District> districtList = districtService.getDistricts();

        return districtList;

    }

    @GetMapping("/{id}")
    public District getDistrict(@PathVariable Integer id){

        District district = districtService.getDistrict(id);

        return district;
    }

    @PostMapping
    public Result addDistrict(@RequestBody DistrictDto districtdto){

        Result result = districtService.addDistrictService(districtdto);

        return result;

    }


    @PutMapping("/edit/{id}")
    public Result editDistrict(@PathVariable Integer id, @RequestBody DistrictDto districtdto){

        Result result = districtService.editDistrict(id, districtdto);

        return result;


    }

    @DeleteMapping("/delete/{id}")
    public Result deleteDistrict(@PathVariable Integer id){

        Result result = districtService.deleteDistrict(id);

        return result;

    }


}
