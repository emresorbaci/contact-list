package com.example.web.web.service;


import com.example.web.web.model.Contact;

import java.util.List;

public interface IContactService {

    List<Contact> findPaginated(int pageNo, int pageSize);

}
