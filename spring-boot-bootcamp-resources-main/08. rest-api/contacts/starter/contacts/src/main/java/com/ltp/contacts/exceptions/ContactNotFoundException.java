package com.ltp.contacts.exceptions;

public class ContactNotFoundException extends RuntimeException {
  // constructor gets called when exception is thrown
  // passing an error message into the parent constructor
  // allows us to access it later...
  public ContactNotFoundException(String id) {
    super("The id '" + id + "' does not exist in our records");
  }
}
