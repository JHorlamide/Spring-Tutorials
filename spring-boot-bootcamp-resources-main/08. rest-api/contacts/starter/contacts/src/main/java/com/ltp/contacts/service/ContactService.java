package com.ltp.contacts.service;

import java.util.List;

import com.ltp.contacts.pojo.Contact;

public interface ContactService {
    Contact getContactById(String id);

    List<Contact> getAllContacts();

    void addContact(Contact contact);

    void updateContact(Contact contact, String id);

    void deleteContact(String id);
}
