package ru.academits.nikolenko.dao;

import ru.academits.nikolenko.model.Contact;
import ru.academits.nikolenko.service.ContactValidation;
import ru.academits.nikolenko.service.DeleteResults;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Anna on 17.06.2017.
 * Modified by Maksim on 24.07.2021
 */
public class ContactDao {
    private List<Contact> contactList = new ArrayList<>();
    private AtomicInteger idSequence = new AtomicInteger(0);
    private Contact lastContact = new Contact();
    private ContactValidation lastContactValidation = new ContactValidation();
    private DeleteResults lastDeleteResults = new DeleteResults();

    public ContactDao() {
        Contact contact = new Contact();
        contact.setId(getNewId());
        contact.setFirstName("Иван");
        contact.setLastName("Иванов");
        contact.setPhone("9123456789");
        contact.setImportant(false);
        contactList.add(contact);
    }

    private int getNewId() {
        return idSequence.addAndGet(1);
    }

    public List<Contact> getAllContacts() {
        return contactList;
    }

    public void add(Contact contact) {
        contact.setId(getNewId());
        contact.setImportant(false);
        contactList.add(contact);
    }

    public void saveLastContact(Contact contact) {
        this.lastContact = contact;
    }

    public Contact getLastContact() {
        return lastContact;
    }

    public void saveLastContactValidation(ContactValidation contactValidation) {
        this.lastContactValidation = contactValidation;
    }

    public ContactValidation getLastContactValidation() {
        return lastContactValidation;
    }

    public boolean deleteContact(int contactId) {
        Contact contact = findContact(contactId);

        if (contact == null) {
            return false;
        }

        return contactList.remove(contact);
    }

    private Contact findContact(int contactId) {
        for (Contact currentContact : contactList) {
            if (contactId == currentContact.getId()) {
                return currentContact;
            }
        }

        return null;
    }

    public void saveLastDeleteResults(DeleteResults deleteResults) {
        this.lastDeleteResults = deleteResults;
    }

    public DeleteResults getLastDeleteResults() {
        return lastDeleteResults;
    }

    public List<Contact> getFilteredContacts(String filter) {
        List<Contact> result = new ArrayList<>();
        String upperCaseFilter = filter.toUpperCase();

        for (Contact contact : contactList) {
            String upperCaseFirstName = contact.getFirstName().toUpperCase();
            String upperCaseLastName = contact.getLastName().toUpperCase();
            String upperCasePhone = contact.getPhone().toUpperCase();

            if (upperCaseFirstName.contains(upperCaseFilter) || upperCaseLastName.contains(upperCaseFilter) || upperCasePhone.contains(upperCaseFilter)) {
                result.add(contact);
            }
        }

        return result;
    }
}
