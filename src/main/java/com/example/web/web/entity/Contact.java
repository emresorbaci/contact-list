package com.example.web.web.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@Entity
@Table(name = "Contacts")
public class Contact {
    @Id
    @Column(name = "name")
    private String name;

    @Column(name = "url")
    private String url;

    public String getName() {
        return name;
    }

    public Contact() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
