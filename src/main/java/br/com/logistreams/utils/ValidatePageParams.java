package br.com.logistreams.utils;

public class ValidatePageParams {
    public static int validatePageNumber(Integer pageNumber) {
        if(pageNumber == null || pageNumber <= 0) {
            return 1;
        } else {
            return pageNumber;
        }
    }

    public static int validatePageSize(Integer pageSize) {
        if(pageSize == null || pageSize <= 0) {
            return 5;
        } else if(pageSize >= 25) {
            return 25;
        } else {
            return pageSize;
        }
    }
}
