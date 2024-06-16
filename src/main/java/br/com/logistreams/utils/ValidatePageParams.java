package br.com.logistreams.utils;

import br.com.logistreams.application.infrastructure.web.exception.BusinessLogicException;
import br.com.logistreams.application.infrastructure.web.exception.ErrorsEnum;
import org.slf4j.LoggerFactory;

public final class ValidatePageParams {
    private static final LoggerService log = new LoggerService(LoggerFactory.getLogger(ValidatePageParams.class));

    public static int validatePageNumber(Integer pageNumber, int totalPages) {
        if(pageNumber == null) {
            return 1;
        } else if(pageNumber <= 0) {
            log.validationError(ErrorsEnum.INVALID_PAGE_NUMBER);
            throw new BusinessLogicException(ErrorsEnum.INVALID_PAGE_NUMBER);
        } else if(pageNumber > totalPages){
            throw new BusinessLogicException(ErrorsEnum.PAGE_NUMBER_OUT_OF_RANGE);
        } else {
            return pageNumber;
        }
    }

    public static int validatePageSize(Integer pageSize) {
        if(pageSize == null) {
            return 10;
        } else if(pageSize <= 0) {
            log.validationError(ErrorsEnum.INVALID_PAGE_SIZE);
            throw new BusinessLogicException(ErrorsEnum.INVALID_PAGE_SIZE);
        } else if(pageSize > 30) {
            log.validationError(ErrorsEnum.INVALID_PAGE_SIZE);
            throw new BusinessLogicException(ErrorsEnum.INVALID_PAGE_SIZE);
        } else {
            return pageSize;
        }
    }
}
