package ru.academits.nikolenko.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.academits.nikolenko.PhoneBook;
import ru.academits.nikolenko.coverter.FilterConverter;
import ru.academits.nikolenko.model.Contact;
import ru.academits.nikolenko.model.Filter;
import ru.academits.nikolenko.service.ContactService;

import java.util.List;
import java.util.stream.Collectors;

public class GetFilteredContactsServlet extends HttpServlet {
    private static final long serialVersionUID = 6607163531760027756L;

    private FilterConverter filterConverter = PhoneBook.filterConverter;
    private ContactService contactService = PhoneBook.contactService;

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String filterString = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
            Filter filter = filterConverter.getFilter(filterString);
            List<Contact> contactList = contactService.getFilteredContacts(filter.getFilter());

            req.setAttribute("contactList", contactList);
            req.setAttribute("contactValidation", contactService.getLastContactValidation());
            req.setAttribute("currentContact", contactService.getLastContact());
            req.setAttribute("currentDeleteResult", contactService.getLastDeleteResults());
            req.getRequestDispatcher("phonebook.jsp").forward(req, resp);
        } catch (Exception e) {
            System.out.println("error in GetFilteredContactsServlet: ");
            e.printStackTrace();
        }
    }
}
