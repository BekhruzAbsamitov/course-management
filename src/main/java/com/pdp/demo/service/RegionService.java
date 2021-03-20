package com.pdp.demo.service;


import com.pdp.demo.entity.Region;
import com.pdp.demo.payload.RegionDto;
import com.pdp.demo.payload.Result;
import com.pdp.demo.repository.RegionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class RegionService {

    RegionRepository regionRepository;

    public RegionService(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    public Result addRegionService(RegionDto regiondto) {
        Region region = new Region();
        region.setName(regiondto.getName());

        regionRepository.save(region);


        return new Result("Muvafaqiyatli saqlandi", true);

    }


    public List<Region> getRegions() {
        return regionRepository.findAll();
    }

    public Region getRegion(Integer id) {
        Optional<Region> optionalRegion = regionRepository.findById(id);
        return optionalRegion.orElse(null);

    }


    public Result editRegion(Integer id, RegionDto regiondto) {

        Optional<Region> optionalRegion = regionRepository.findById(id);

        if (!optionalRegion.isPresent()) {
            return new Result("Region topilmadi", false);
        }

        Region region = optionalRegion.get();

        region.setName(regiondto.getName());

        regionRepository.save(region);

        return new Result("Muvafaqiyatli tahrirlandi ", true);

    }


    public Result deleteRegion(Integer id) {

        boolean existsById = regionRepository.existsById(id);
        if (existsById) {
            regionRepository.deleteById(id);
            return new Result("Region  deleted", true);
        }
        return new Result("Region topilmadi", false);
    }
}
