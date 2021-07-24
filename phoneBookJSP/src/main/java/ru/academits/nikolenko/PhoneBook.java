package ru.academits.nikolenko;

import ru.academits.nikolenko.coverter.ContactConverter;
import ru.academits.nikolenko.coverter.ContactsIdsConverter;
import ru.academits.nikolenko.coverter.FilterConverter;
import ru.academits.nikolenko.dao.ContactDao;
import ru.academits.nikolenko.service.ContactService;

public class PhoneBook {
    public static ContactDao contactDao = new ContactDao();

    public static ContactService contactService = new ContactService();

    public static ContactConverter contactConverter = new ContactConverter();

    public static ContactsIdsConverter contactsIdsConverter = new ContactsIdsConverter();

    public static FilterConverter filterConverter = new FilterConverter();
}
