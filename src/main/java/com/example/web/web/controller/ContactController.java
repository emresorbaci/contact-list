package com.example.web.web.controller;

import com.example.web.web.entity.Contact;
import com.example.web.web.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;
    //TODO csv deki virgül işini sor
    // TODO arama işlemi tam isimle mi olacak yoksa yazmaya bailayınca mı olacak

    @GetMapping("/contacts")
    public ResponseEntity<List<Contact>> getContacts(@RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber,
                                                     @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
        return ResponseEntity.ok(contactService.findAll(pageNumber, pageSize));
    }

    @GetMapping("/contacts/{name}")
    public ResponseEntity<List<Contact>> getContactsByName(@PathVariable String name, @RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber,
                                                     @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
        return ResponseEntity.ok(contactService.findByName(name, pageNumber, pageSize));
    }

    @PostMapping("/contacts")
    public Contact addContact(@RequestBody Contact contact) {
        return contactService.save(contact);
    }

}
