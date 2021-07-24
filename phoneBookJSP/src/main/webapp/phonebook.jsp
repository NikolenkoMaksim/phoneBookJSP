<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ page import="ru.academits.nikolenko.model.Contact" %>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="">
<head>
    <%
        List<Contact> contactList = (List<Contact>) request.getAttribute("contactList");
        Contact currentContact = (Contact) request.getAttribute("currentContact");
    %>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" type="text/css" href="css/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="css/phoneBook.css"/>
    <title>Phone book</title>
</head>
<body>
<div class="container">
    <div class="row">
        <h1 class="h2 col text-center mt-5 mb-5">Телефонная книга</h1>
    </div>

    <form action="add" method="POST">
        <div class="row mb-2 pl-4">
            <div class="col mb-2">
                <label class="form-label">
                    <span class="form-field">Фамилия:</span>
                    <input type="text" class="ml-2 form-control input-sm form-input" name="lastName"
                           value='<%=currentContact.getLastName() == null ? "" : currentContact.getLastName() %>'/>
                    <span class="error-message">
                     <c:if test="${not empty contactValidation.lastNameError}">
                         <c:out value="${contactValidation.lastNameError}">
                         </c:out>
                     </c:if>
                </span>
                </label>
            </div>
        </div>
        <div class="row mb-2 pl-4">
            <div class="col mb-2">
                <label class="form-label">
                    <span class="form-field">Имя:</span>
                    <input type="text" class="ml-1 form-control form-input" name="firstName"
                           value='<%=currentContact.getFirstName() == null ? "" : currentContact.getFirstName() %>'/>
                    <span class="error-message">
                    <c:if test="${not empty contactValidation.firstNameError}">
                        <c:out value="${contactValidation.firstNameError}">
                        </c:out>
                    </c:if>
                </span>
                </label>
            </div>
        </div>
        <div class="row mb-2 pl-4">
            <div class="col mb-2">
                <label class="form-label">
                    <span class="form-field">Телефон:</span>
                    <input type="number" class="ml-1 form-control input-sm form-input" name="phone"
                           value='<%=currentContact.getPhone() == null ? "" : currentContact.getPhone() %>'/>
                    <span class="error-message">
                     <c:if test="${not empty contactValidation.phoneError}">
                         <c:out value="${contactValidation.phoneError}">
                         </c:out>
                     </c:if>
                </span>
                </label>
            </div>
        </div>
        <div class="row">
            <div class="col text-center">
                <button type="submit" class="btn btn-primary">Добавить</button>
            </div>
            <div class="col text-center">
            </div>
        </div>
    </form>

    <c:if test="${not empty contactValidation.globalError}">
        <div class="row mt-4">
            <div class="col">
                <label class="server-error-message-container">
                <span class="error-message">
                    <c:out value="${contactValidation.globalError}">
                    </c:out>
                </span>
                </label>
            </div>
        </div>
    </c:if>

    <c:if test="${not empty deleteResult.error}">
        <div class="row mt-4">
            <div class="col">
                <label class="server-error-message-container">
                <span class="error-message">
                    <c:out value="${deleteResult.error}">
                    </c:out>
                </span>
                </label>
            </div>
        </div>
    </c:if>

    <div class="row filter-container mt-5">
        <div class="col">
            <div class="d-inline-block">
                <form action="filter" method="POST">
                    <label class="filter-label mb-0 mr-2 ">
                        <input type="text" class="form-control input-sm form-input" name="filter" placeholder="Ведите текст"/>
                    </label>
                    <button type="submit" class="btn btn-primary">Отфильтровать</button>
                </form>
            </div>
            <div class="d-inline-block">
                <form action="phonebook" method="GET">
                    <button class="btn btn-primary">Сбросить фильтр</button>
                </form>
            </div>

        </div>
    </div>

    <div class="table-responsive">
        <table class="table table-bordered table-sm contacts-table mt-4">
            <thead class="table-primary">
            <tr>
                <th scope="col" class="text-center limit-width">№</th>
                <th scope="col" class="pl-3">Фамилия</th>
                <th scope="col" class="pl-3">Имя</th>
                <th scope="col" class="pl-3">Телефон</th>
                <th scope="col" class="pl-3 delete-cell">Удалить</th>
            </tr>
            </thead>
            <tbody>
            <% if (contactList.toArray().length == 0) {%>
            <tr class="empty-row text-center">
                <td scope="row"></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
            <% }%>

            <% int number = 0;
                for (Contact contact : contactList) {
                    number++;
            %>
            <tr>
                <td class="text-center pl-2 pt-2">
                    <% out.println(number); %>
                </td>
                <td class="pl-3 pt-2">
                    <% out.println(contact.getLastName()); %>
                </td>
                <td class="pl-3 pt-2">
                    <% out.println(contact.getFirstName()); %>
                </td>
                <td class="pl-3 pt-2">
                    <% out.println(contact.getPhone()); %>
                </td>
                <td class="pl-3">
                    <button class='btn btn-primary' type='button' onclick="deleteContact(<%= contact.getId() %>)">
                        Удалить
                    </button>
                </td>
            </tr>
            <%}%>
            </tbody>
        </table>
    </div>
</div>

<script type="text/javascript">
    function deleteContact(contactId) {
        let form = document.createElement('form');
        form.style.visibility = 'hidden';
        form.method = 'POST';
        form.action = '/delete';

        let input = document.createElement('input');
        input.name = "contactsIds";
        input.value = contactId;
        form.appendChild(input);

        document.body.appendChild(form);
        form.submit();
    }
</script>
</body>
</html>