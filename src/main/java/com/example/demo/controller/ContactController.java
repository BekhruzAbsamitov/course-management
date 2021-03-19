package com.example.demo.controller;

import com.example.demo.entity.Contact;
import com.example.demo.payload.ContactDto;
import com.example.demo.payload.Result;
import com.example.demo.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contact")
public class ContactController {

    ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping
    public List<Contact> getContact(){

        List<Contact> contactList = contactService.getContacts();

        return contactList;

    }


    @GetMapping("/{id}")
    public Contact getContact(@PathVariable Integer id){

        Contact contact = contactService.getContact(id);

        return contact;

    }

    @PostMapping
    public Result addContact(@RequestBody ContactDto contactdto){

        Result result = contactService.addContact(contactdto);

        return result;

    }

    @PutMapping("/edit/{id}")
    public Result editContact(@PathVariable Integer id, @RequestBody ContactDto contactdto){

        Result result = contactService.editContact(id, contactdto);

        return result;

    }


    @DeleteMapping("/delete/{id}")
    public Result deleteContact(@PathVariable Integer id){

        Result result = contactService.deleteContact(id);

        return result;

    }

}
