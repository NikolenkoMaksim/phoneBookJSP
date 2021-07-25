package ru.academits.nikolenko.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.academits.nikolenko.PhoneBook;
import ru.academits.nikolenko.coverter.ContactsIdsConverter;
import ru.academits.nikolenko.service.ContactService;
import ru.academits.nikolenko.service.DeleteResults;

import java.util.stream.Collectors;

public class DeleteContactsServlet extends HttpServlet {
    private static final long serialVersionUID = 3244259754264550630L;

    private ContactsIdsConverter contactsIdsConverter = PhoneBook.contactsIdsConverter;

    private ContactService contactService = PhoneBook.contactService;

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String contactsIdsString = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
            int[] contactsIds = contactsIdsConverter.getIds(contactsIdsString);

            DeleteResults deleteResults = contactService.deleteContacts(contactsIds);
            contactService.saveLastDeleteResults(deleteResults);

            resp.sendRedirect("/phonebook");
        } catch (NumberFormatException nfe) {
            DeleteResults deleteResults = new DeleteResults(false, "Не верный формат id's контактов");
            System.out.println("error in DeleteContactsServlet: ");
            nfe.printStackTrace();
        } catch (Exception e) {
            System.out.println("error in DeleteContactsServlet: ");
            e.printStackTrace();
        }
    }
}
