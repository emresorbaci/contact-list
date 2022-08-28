package com.example.web.web.service;

import com.example.web.web.entity.Contact;
import com.example.web.web.repository.ContactRepository;
import com.example.web.web.util.CSVReader;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final ContactRepository repository;

    @PostConstruct
    public void initDataFromCSV() {
        try {
            List<Contact> contacts = CSVReader.readLineByLine();
            saveAll(contacts);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Page<Contact> findAll(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Contact> contactPage = repository.findAll(pageable);

        return contactPage;
    }

    public List<Contact> findByName(String name, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Contact> contactPage = repository.findByName(name, pageable);

        return contactPage.getContent();
    }

    private void saveAll(List<Contact> contacts) {
        repository.saveAll(contacts);
    }

    public Contact save(Contact contact) {
        return repository.save(contact);
    }


}
