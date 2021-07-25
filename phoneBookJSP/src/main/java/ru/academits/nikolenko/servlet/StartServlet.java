package ru.academits.nikolenko.servlet;

import ru.academits.nikolenko.PhoneBook;
import ru.academits.nikolenko.service.ContactService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StartServlet extends HttpServlet {
    private static final long serialVersionUID = 5909297255492112405L;

    private ContactService contactService = PhoneBook.contactService;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            req.setAttribute("contactList", contactService.getAllContacts());
            req.setAttribute("contactValidation", contactService.getLastContactValidation());
            req.setAttribute("currentContact", contactService.getLastContact());
            req.setAttribute("deleteResult", contactService.getLastDeleteResults());
            req.getRequestDispatcher("phonebook.jsp").forward(req, resp);
        } catch (Exception e) {
            System.out.println("error in StartServlet: ");
            e.printStackTrace();
        }
    }
}
