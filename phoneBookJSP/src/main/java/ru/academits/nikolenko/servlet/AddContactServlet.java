package ru.academits.nikolenko.servlet;

import ru.academits.nikolenko.PhoneBook;
import ru.academits.nikolenko.coverter.ContactConverter;
import ru.academits.nikolenko.model.Contact;
import ru.academits.nikolenko.service.ContactService;
import ru.academits.nikolenko.service.ContactValidation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

public class AddContactServlet extends HttpServlet {
    private static final long serialVersionUID = -6760969942499715147L;

    private ContactService contactService = PhoneBook.contactService;
    private ContactConverter contactConverter = PhoneBook.contactConverter;

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String contactParams = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        Contact contact = contactConverter.convertFormStringParam(contactParams);

        ContactValidation contactValidation = contactService.addContact(contact);
        contactService.saveLastContactValidation(contactValidation);

        if (contactValidation.isValid()) {
            contactService.saveLastContact(new Contact());
        } else {
            contactService.saveLastContact(contact);
        }

        resp.sendRedirect("/phonebook");
    }
}
