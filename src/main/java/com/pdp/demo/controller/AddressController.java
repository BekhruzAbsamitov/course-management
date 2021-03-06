package com.pdp.demo.controller;



import com.pdp.demo.entity.Address;
import com.pdp.demo.payload.AddressDto;
import com.pdp.demo.payload.Result;
import com.pdp.demo.service.AddressService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    public List<Address> getAddresses(){

        List<Address> addressList = addressService.getAddresses();

        return addressList;

    }



    @GetMapping("/{id}")
    public Address getAddress(@PathVariable Integer id){

        Address address = addressService.getAddress(id);

        return address;
    }

    @PostMapping
    public Result addAddress(@RequestBody AddressDto addressdto){

        return addressService.addAddress(addressdto);

    }


    @PutMapping("/edit/{id}")
    public Result editAddress(@PathVariable Integer id, @RequestBody AddressDto addressdto){

        Result result = addressService.editAddress(id,addressdto);

        return result;

    }

    @DeleteMapping("/delete/{id}")
    public Result deleteAddress(@PathVariable Integer id){

        Result result = addressService.deleteAddress(id);

        return result;

    }


}
