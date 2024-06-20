package br.com.logistreams.utils;

import br.com.logistreams.application.infrastructure.web.exception.BusinessLogicException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidatePageParamsTest {
    @Test
    @DisplayName("Given a null page number, when validating page number, should return the default page number")
    public void testValidatePageNumber_NullPageNumber_ReturnsDefault() {
        // Arrange
        ValidatePageParams validator = new ValidatePageParams();
        int totalPages = 10;

        // Act
        int validatedPageNumber = validator.validatePageNumber(null, totalPages);

        // Assert
        assertEquals(ValidatePageParams.DEFAULT_PAGE_NUMBER, validatedPageNumber);
    }

    @Test
    @DisplayName("Given a valid page number within total pages, when validating page number, should return the page number")
    public void testValidatePageNumber_ValidPageNumber_ReturnsPageNumber() {
        // Arrange
        ValidatePageParams validator = new ValidatePageParams();
        int totalPages = 10;
        int pageNumber = 5;

        // Act
        int validatedPageNumber = validator.validatePageNumber(pageNumber, totalPages);

        // Assert
        assertEquals(pageNumber, validatedPageNumber);
    }

    @Test
    @DisplayName("Given a page number greater than total pages, when validating page number, should throw BusinessLogicException")
    public void testValidatePageNumber_PageNumberGreaterThanTotalPages_ThrowsException() {
        // Arrange
        ValidatePageParams validator = new ValidatePageParams();
        int totalPages = 5;
        int pageNumber = 10;

        // Act & Assert
        assertThrows(BusinessLogicException.class, () -> validator.validatePageNumber(pageNumber, totalPages));
    }

    @Test
    @DisplayName("Given a null page size, when validating page size, should return the default page size")
    public void testValidatePageSize_NullPageSize_ReturnsDefault() {
        // Arrange
        ValidatePageParams validator = new ValidatePageParams();

        // Act
        int validatedPageSize = validator.validatePageSize(null);

        // Assert
        assertEquals(ValidatePageParams.DEFAULT_PAGE_SIZE, validatedPageSize);
    }

    @Test
    @DisplayName("Given a valid page size, when validating page size, should return the page size")
    public void testValidatePageSize_ValidPageSize_ReturnsPageSize() {
        // Arrange
        ValidatePageParams validator = new ValidatePageParams();
        int pageSize = 20;

        // Act
        int validatedPageSize = validator.validatePageSize(pageSize);

        // Assert
        assertEquals(pageSize, validatedPageSize);
    }
}