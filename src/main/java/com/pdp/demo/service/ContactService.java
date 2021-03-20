package com.pdp.demo.service;

import com.pdp.demo.entity.Contact;
import com.pdp.demo.payload.ContactDto;
import com.pdp.demo.payload.Result;
import com.pdp.demo.repository.ContactRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public List<Contact> getContacts(){
        return contactRepository.findAll();
    }

    public Contact getContact(Integer id){

        Optional<Contact> optionalContact = contactRepository.findById(id);

        return   optionalContact.orElse(null);

    }

    public Result addContact(ContactDto contactDto){

        Contact contact = new Contact();

        contact.setPhoneNumber(contactDto.getPhoneNumber());

        contact.setTelegram(contactDto.getTelegram());

        contact.setEmail(contactDto.getEmail());

        contact.setWebsite(contactDto.getWebsite());

        contactRepository.save(contact);

        return new Result("Successfully added",true);


    }


    public Result editContact(Integer id, ContactDto contactDto){

        Optional<Contact> optionalContact = contactRepository.findById(id);

        if (!optionalContact.isPresent()){

            return new Result("Contact not found",false);

        }

        Contact contact = optionalContact.get();

        contact.setWebsite(contactDto.getWebsite());

        contact.setTelegram(contactDto.getTelegram());

        contact.setPhoneNumber(contactDto.getPhoneNumber());

        contact.setEmail(contactDto.getEmail());

        contactRepository.save(contact);

        return new Result("Successfully edited",true);

    }

    public Result deleteContact(Integer id){

        boolean existsById = contactRepository.existsById(id);

        if (existsById){

           contactRepository.deleteById(id);

            return new Result("Contact  deleted",true);

        }

        return new Result("Contact not found",false);

    }


}
