package ru.academits.nikolenko.coverter;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class ContactsIdsConverter {
    public int[] getIds(String contactsIdsString) throws UnsupportedEncodingException {
        int idx = contactsIdsString.indexOf("=");
        String contactsIdsValue = URLDecoder.decode(contactsIdsString.substring(idx + 1), "UTF-8");
        String[] contactsIdsStringArray = contactsIdsValue.split("&");
        int[] ids = new int[contactsIdsStringArray.length];

        for (int i = 0; i < ids.length; i++) {
            ids[i] = Integer.parseInt(contactsIdsStringArray[i]);
        }

        return ids;
    }
}
