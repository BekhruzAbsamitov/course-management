package com.pdp.demo.service;


import com.pdp.demo.entity.Address;
import com.pdp.demo.entity.District;
import com.pdp.demo.entity.User;
import com.pdp.demo.payload.Result;
import com.pdp.demo.payload.UserDto;
import com.pdp.demo.repository.AddressRepository;
import com.pdp.demo.repository.DistrictRepository;
import com.pdp.demo.repository.RoleRepository;
import com.pdp.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    final UserRepository userRepository;
    final AddressRepository addressRepository;
    final DistrictRepository districtRepository;
    final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, AddressRepository addressRepository, DistrictRepository districtRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.districtRepository = districtRepository;
        this.roleRepository = roleRepository;
    }

    public Result add(UserDto userDto) {

        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setAge(userDto.getAge());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setRoles(userDto.getRoleList());


        Address address = new Address();
        address.setHomeNumber(userDto.getHomeNumber());
        address.setStreetName(userDto.getStreetName());

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


    public Result edit(Integer id, UserDto userDto) {

        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User editing = optionalUser.get();
            editing.setFirstName(userDto.getFirstName());
            editing.setLastName(userDto.getLastName());
            editing.setAge(userDto.getAge());
            editing.setPhoneNumber(userDto.getPhoneNumber());
            editing.setRoles(userDto.getRoleList());

            Address address = editing.getAddress();
            address.setHomeNumber(userDto.getHomeNumber());
            address.setStreetName(userDto.getStreetName());

            Optional<District> optionalDistrict = districtRepository.findById(userDto.getDistrictId());
            if (optionalDistrict.isPresent()) {
                District district = optionalDistrict.get();
                address.setDistrict(district);
                Address savedAddress = addressRepository.save(address);
                editing.setAddress(savedAddress);
                return new Result("User edited", true);

            } else {
                return new Result("Such district not found", false);
            }
        }
        return new Result("Such user not found", false);

    }


    public Result delete(Integer id) {

        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            Address address = user.getAddress();
            Integer addressId = address.getId();
            userRepository.delete(user);
            boolean exists = addressRepository.existsById(addressId);
            if (exists) {
                addressRepository.delete(address);


                return new Result("User deleted", true);
            } else {
                return new Result("Such address not found", false);
            }
        }
        return new Result("Such user not found", false);

    }
}
