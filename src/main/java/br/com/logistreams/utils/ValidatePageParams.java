package br.com.logistreams.utils;

import br.com.logistreams.application.adapters.web.exception.BusinessLogicException;
import br.com.logistreams.application.adapters.web.exception.ErrorsEnum;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
@Component
public class ValidatePageParams {
    private static final LoggerService log = new LoggerService(LoggerFactory.getLogger(ValidatePageParams.class));
    public static final int DEFAULT_PAGE_NUMBER = 1;
    public static final int DEFAULT_PAGE_SIZE = 10;

    public int validatePageNumber(Integer pageNumber, int totalPages) {
        if(pageNumber == null) {
            return DEFAULT_PAGE_NUMBER;
        } else if(pageNumber > totalPages){
            throw new BusinessLogicException(ErrorsEnum.PAGE_NUMBER_OUT_OF_RANGE);
        } else {
            return pageNumber;
        }
    }

    public int validatePageSize(Integer pageSize) {
        if(pageSize == null) {
            return DEFAULT_PAGE_SIZE;
        } else {
            return pageSize;
        }
    }
}
