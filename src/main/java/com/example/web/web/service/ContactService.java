package com.example.web.web.service;

import com.example.web.web.entity.Contact;
import com.example.web.web.repository.ContactRepository;
import com.example.web.web.util.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.enums.CSVReaderNullFieldIndicator;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public List<Contact> findAll(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Contact> contactPage = repository.findAll(pageable);

        return contactPage.getContent();
    }

    public List<Contact> findByName(String name, int pageNo, int pageSize){
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
