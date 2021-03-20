package com.pdp.demo.service;

import com.pdp.demo.entity.Address;
import com.pdp.demo.entity.Company;
import com.pdp.demo.entity.Contact;
import com.pdp.demo.entity.District;
import com.pdp.demo.payload.CompanyDto;
import com.pdp.demo.payload.Response;
import com.pdp.demo.payload.Result;
import com.pdp.demo.repository.AddressRepository;
import com.pdp.demo.repository.CompanyRepository;
import com.pdp.demo.repository.ContactRepository;
import com.pdp.demo.repository.DistrictRepository;
import org.springframework.stereotype.Service;

import java.util.List;
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
        company.setCompanyCategories(companydto.getCompanyCategoryList());

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

      /*
             second way creating contact info
            Contact newContact = new Contact();
            newContact.setPhoneNumber(companydto.getContact().getPhoneNumber());
            newContact.setEmail(companydto.getContact().getEmail());
            newContact.setWebsite(companydto.getContact().getWebsite());
            newContact.setTelegram(companydto.getContact().getTelegram());
            Contact savedContact = contactRepository.save(newContact);
      */

        }
        return new Result("Such district not found", false);


    }


    public List<Company> get() {

        return companyRepository.findAll();
    }


    public Company getById(Integer id) {

        Optional<Company> optionalCompany = companyRepository.findById(id);
        return optionalCompany.orElse(null);
    }

    public Result delete(Integer id) {

        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (optionalCompany.isPresent()) {
            Company company = optionalCompany.get();
            companyRepository.delete(company);

            Contact contact = company.getContact();
            contactRepository.delete(contact);

            Address address = company.getAddress();
            addressRepository.delete(address);

            return new Result("Company deleted", true);
        }
        return new Result("Such company not found", false);

    }

    public Result edit(Integer id, CompanyDto companyDto) {

        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (!optionalCompany.isPresent())
            return new Result("Such company not found", false);

        Company company = optionalCompany.get();
        company.setName(companyDto.getName());
        company.setDescription(companyDto.getDescription());
        company.setCompanyCategories(companyDto.getCompanyCategoryList());

        Contact contact = companyDto.getContact();
        company.setContact(contact);
        contactRepository.save(contact);


        Optional<District> optionalDistrict = districtRepository.findById(companyDto.getDistrictId());
        if (optionalDistrict.isPresent()) {
            District district = optionalDistrict.get();

            Address address = company.getAddress();
            address.setHomeNumber(companyDto.getHomeNumber());
            address.setStreetName(companyDto.getStreetName());
            address.setDistrict(district);
            addressRepository.save(address);

            company.setAddress(address);
            companyRepository.save(company);
            return new Result("Company edited", true);
        }
        return new Result("Such district not found", false);

    }

    public Response getResponse(String regionName, String courseName) {
        return companyRepository.findCompany(regionName, courseName);
    }
}
