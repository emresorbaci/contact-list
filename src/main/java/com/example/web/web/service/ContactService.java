package com.example.web.web.service;

import com.example.web.web.model.Contact;
import com.example.web.web.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService implements IContactService {

    @Autowired
    private ContactRepository repository;

    @Override
    public List<Contact> findPaginated(int pageNo, int pageSize) {

        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Contact> contactPage = repository.findAll(pageable);

        return contactPage.toList();
    }
}
