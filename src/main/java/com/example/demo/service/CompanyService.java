package com.example.demo.service;

import com.example.demo.entity.Address;
import com.example.demo.entity.Company;
import com.example.demo.entity.Contact;
import com.example.demo.entity.District;
import com.example.demo.payload.CompanyDto;
import com.example.demo.payload.Result;
import com.example.demo.repository.AddressRepository;
import com.example.demo.repository.CompanyRepository;
import com.example.demo.repository.ContactRepository;
import com.example.demo.repository.DistrictRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CompanyService {

    final CompanyRepository companyRepository;
    final ContactRepository contactRepository;
    final AddressRepository addressRepository;
    final DistrictRepository districtRepository;

    public CompanyService(CompanyRepository companyRepository, ContactRepository contactRepository, AddressRepository addressRepository, DistrictRepository districtRepository) {
        this.companyRepository = companyRepository;
        this.contactRepository = contactRepository;
        this.addressRepository = addressRepository;
        this.districtRepository = districtRepository;
    }

    public Result add(CompanyDto companydto) {

        Company company = new Company();
        company.setName(companydto.getName());
        company.setDescription(companydto.getDescription());
        company.setCompanyCategories(company.getCompanyCategories());

        Address address = new Address();
        address.setHomeNumber(companydto.getHomeNumber());
        address.setStreetName(companydto.getStreetName());

        Optional<District> optionalDistrict = districtRepository.findById(companydto.getDistrictId());
        if (optionalDistrict.isPresent()) {
            District district = optionalDistrict.get();
            address.setDistrict(district);
            Address save = addressRepository.save(address);
            company.setAddress(save);

            Contact savedContact = contactRepository.save(companydto.getContact());
            company.setContact(savedContact);
            companyRepository.save(company);
            return new Result("Company added", true);
        }
        return new Result("Such district not found", false);


    }
}
