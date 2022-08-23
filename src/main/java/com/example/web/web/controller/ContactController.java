package com.example.web.web.controller;

import com.example.web.web.model.Contact;
import com.example.web.web.service.IContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ContactController {

    @Autowired
    private IContactService contactService;

    @GetMapping("/contacts/{pageNo}/{pageSize}")
    public List<Contact> getpaginatedContacts(@PathVariable int pageNo, @PathVariable int pageSize) {

        return contactService.findPaginated(pageNo, pageSize);
    }

}
