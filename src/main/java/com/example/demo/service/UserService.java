package com.example.demo.service;


import com.example.demo.entity.Address;
import com.example.demo.entity.District;
import com.example.demo.entity.User;
import com.example.demo.payload.Result;
import com.example.demo.payload.UserDto;
import com.example.demo.repository.AddressRepository;
import com.example.demo.repository.DistrictRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    final UserRepository userRepository;
    final AddressRepository addressRepository;
    final DistrictRepository districtRepository;

    public UserService(UserRepository userRepository, AddressRepository addressRepository, DistrictRepository districtRepository) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.districtRepository = districtRepository;
    }

    public Result add(UserDto userDto) {

        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setAge(userDto.getAge());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setRoles(userDto.getRoleList());


        Address address = new Address();
        address.setHomeNumber(address.getHomeNumber());
        address.setStreetName(address.getStreetName());

        Optional<District> optionalDistrict = districtRepository.findById(userDto.getDistrictId());
        if (optionalDistrict.isPresent()) {
            District district = optionalDistrict.get();
            address.setDistrict(district);

            Address save = addressRepository.save(address);

            user.setAddress(save);
            userRepository.save(user);
            return new Result("User added", true);
        }
        return new Result("Such district not found", true);

    }


    public List<User> get() {

        return userRepository.findAll();
    }

    public User getById(Integer id) {

        Optional<User> optionalUserEntity = userRepository.findById(id);
        return optionalUserEntity.orElse(null);
    }
}
