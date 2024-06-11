package br.com.logistreams.utils;

import org.springframework.data.domain.PageRequest;

public final class PageRequestBuilder {
    public static final int DEFAULT_PAGE_NUMBER = 1;
    public static final int DEFAULT_PAGE_SIZE = 5;


    private PageRequestBuilder() {}

    public static PageRequest build(Integer pageNumber, Integer pageSize) {
        int queryPageNumber = 0;
        int queryPageSize = 0;

        if(pageNumber == null) {
            queryPageNumber = DEFAULT_PAGE_NUMBER;
        } else if(pageNumber <= 0) {
            throw new IllegalStateException("Page number can't be negative");
        } else {
            queryPageNumber = pageNumber;
        }

        if(pageSize == null) {
            queryPageSize = DEFAULT_PAGE_SIZE;
        } else if(pageSize <= 0 || pageSize >= 25) {
            throw new IllegalStateException("Page number can't be greater than 25 nor lower than 0");
        } else {
            queryPageSize = pageSize;
        }

        return PageRequest.of(queryPageNumber - 1, queryPageSize);
    }
}
