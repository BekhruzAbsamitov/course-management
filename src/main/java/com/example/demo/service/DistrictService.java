package com.example.demo.service;

import com.example.demo.entity.District;
import com.example.demo.entity.Region;
import com.example.demo.payload.DistrictDto;
import com.example.demo.payload.Result;
import com.example.demo.repository.DistrictRepository;
import com.example.demo.repository.RegionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DistrictService {

    DistrictRepository districtRepository;
    RegionRepository regionRepository;

    public DistrictService(DistrictRepository districtRepository, RegionRepository regionRepository) {
        this.districtRepository = districtRepository;
        this.regionRepository = regionRepository;
    }

    public List<District> getDistricts() {

        return districtRepository.findAll();

    }


    public Result addDistrictService(DistrictDto districtDto) {
        District district = new District();
        district.setName(districtDto.getName());

        Optional<Region> optionalRegion = regionRepository.findById(districtDto.getRegion_id());

        if (optionalRegion.isEmpty()) {

            return new Result("Region topilmadi", false);
        }
        district.setRegion(optionalRegion.get());


        districtRepository.save(district);


        return new Result("Muvafaqiyatli saqlandi", true);

    }


    public District getDistrict(Integer id) {

        Optional<District> optionalDistrict = districtRepository.findById(id);

        return optionalDistrict.orElse(null);

    }

    public Result editDistrict(Integer id, DistrictDto districtDto) {

        Optional<District> optionalDistrict = districtRepository.findById(id);
        if (optionalDistrict.isEmpty()) {
            return new Result("District topilmadi", false);
        }

        District district = optionalDistrict.get();
        district.setName(districtDto.getName());
        Optional<Region> optionalRegion = regionRepository.findById(districtDto.getRegion_id());
        if (optionalRegion.isEmpty()) {
            return new Result("Region topilmadi", false);
        }

        district.setRegion(optionalRegion.get());

        districtRepository.save(district);

        return new Result("Successfully edited", true);

    }

    public Result deleteDistrict(Integer id) {

        boolean existsById = districtRepository.existsById(id);

        if (existsById) {

            districtRepository.deleteById(id);

            return new Result("District  deleted", true);

        }

        return new Result("District topilmadi", false);

    }


}
