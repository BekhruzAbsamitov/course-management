package com.example.demo.controller;

import com.example.demo.entity.District;
import com.example.demo.entity.Region;
import com.example.demo.payload.DistrictDto;
import com.example.demo.payload.RegionDto;
import com.example.demo.payload.Result;
import com.example.demo.repository.RegionRepository;
import com.example.demo.service.DistrictService;
import com.example.demo.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/region")
public class RegionController {

    final RegionRepository regionRepository;

    public RegionController(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    @Autowired
    RegionService regionService;

    @GetMapping
    public List<Region> getRegions() {

        List<Region> regionList = regionService.getRegions();

        return regionList;

    }

    @GetMapping("/{id}")
    public Region getRegion(@PathVariable Integer id){

        Region region = regionService.getRegion(id);

        return region;
    }

    @PostMapping
    public Result addRegion(@RequestBody RegionDto regiondto){
        Result result = regionService.addRegionService(regiondto);

        return result;

    }

    @PutMapping("/edit/{id}")
    public Result editRegion(@PathVariable Integer id, @RequestBody RegionDto regiondto){

        Result result = regionService.editRegion(id, regiondto);

        return result;


    }

    @DeleteMapping("/delete/{id}")
    public Result deleteRegion(@PathVariable Integer id){

        Result result = regionService.deleteRegion(id);

        return result;

    }

}
