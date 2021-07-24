package ru.academits.nikolenko.coverter;

import ru.academits.nikolenko.model.Filter;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class FilterConverter {
    public Filter getFilter(String filterString) throws UnsupportedEncodingException {
        int idx = filterString.indexOf("=");
        String filterValue = URLDecoder.decode(filterString.substring(idx + 1), "UTF-8");

        return new Filter(filterValue);
    }
}
