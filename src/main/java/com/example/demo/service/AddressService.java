package com.example.demo.service;

import com.example.demo.entity.Address;
import com.example.demo.entity.District;
import com.example.demo.payload.AddressDto;
import com.example.demo.payload.Result;
import com.example.demo.repository.AddressRepository;
import com.example.demo.repository.DistrictRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    AddressRepository addressRepository;
    DistrictRepository districtRepository;

    public AddressService(AddressRepository addressRepository, DistrictRepository districtRepository) {
        this.addressRepository = addressRepository;
        this.districtRepository = districtRepository;
    }

    public List<Address> getAddresses() {

        List<Address> addressList = addressRepository.findAll();

        return addressList;

    }

    public Address getAddress(Integer id) {

        Optional<Address> optionalAddress = addressRepository.findById(id);

        return optionalAddress.orElse(null);

    }

    public Result addAddress(AddressDto addressdto) {

        Address address = new Address();

        address.setStreetName(addressdto.getStreet_name());

        address.setHomeNumber(addressdto.getHome_number());

        Optional<District> optionalDistrict = districtRepository.findById(addressdto.getDistrict_id());

        if (!optionalDistrict.isPresent()) {

            return new Result("District not found", false);

        }

        address.setDistrict(optionalDistrict.get());

        addressRepository.save(address);

        return new Result("Successfully added", true);

    }

    public Result editAddress(Integer id, AddressDto addressdto) {

        Optional<Address> addressOptional = addressRepository.findById(id);

        if (!addressOptional.isPresent()) {

            return new Result("Address not found", false);
        }

        Address address = addressOptional.get();

        address.setStreetName(addressdto.getStreet_name());

        address.setHomeNumber(addressdto.getHome_number());

        Optional<District> optionalDistrict = districtRepository.findById(addressdto.getDistrict_id());

        if (!optionalDistrict.isPresent()) {

            return new Result("District not found", false);

        }


        address.setDistrict(optionalDistrict.get());

        addressRepository.save(address);

        return new Result("Successfully edited", true);

    }


    public Result deleteAddress(Integer id) {

        boolean existsById = addressRepository.existsById(id);

        if (existsById) {

            addressRepository.deleteById(id);

            return new Result("Address  deleted", true);
        }

        return new Result("Address topilmadi", false);


    }

}
