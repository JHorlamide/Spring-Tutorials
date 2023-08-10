package com.ltp.contacts.web;

import com.ltp.contacts.pojo.Contact;
import com.ltp.contacts.service.ContactService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Contact Controller")
@RestController
@RequestMapping("/api/v1/contacts")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @Operation(summary = "Retrieves Contacts", description = "Provides a list of all contacts")
    @GetMapping
    public ResponseEntity<List<Contact>> getContacts() {
        var contacts = contactService.getAllContacts();
        return new ResponseEntity<List<Contact>>(contacts, HttpStatus.OK);
    }

    @Operation(summary = "Get contact by Id", description = "Returns a contact based on a given id")
    @GetMapping("{id}")
    public ResponseEntity<Contact> getContact(@PathVariable String id) {
        Contact contact = contactService.getContactById(id);
        return new ResponseEntity<>(contact, HttpStatus.OK);
    }

    @Operation(summary = "Create Contact", description = "Creates a contact from the provided payload")
    @PostMapping
    public ResponseEntity<HttpStatus> createContact(@Valid @RequestBody Contact contact) {
        contactService.addContact(contact);
        return new ResponseEntity<HttpStatus>(HttpStatus.CREATED);
    }

    @Operation(summary = "Update Contact", description = "Update the details of a contact based on a given Id")
    @PutMapping("{id}")
    public ResponseEntity<Contact> updateContact(
            @PathVariable String id, @Valid @RequestBody Contact updateContactBodyField) {
        contactService.updateContact(updateContactBodyField, id);
        return new ResponseEntity<Contact>(contactService.getContactById(id), HttpStatus.OK);
    }

    @Operation(summary = "Delete Contact", description = "Deletes a contact based on a given Id")
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteContact(@PathVariable String id) {
        contactService.deleteContact(id);
        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
    }
}
