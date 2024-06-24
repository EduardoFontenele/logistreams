package br.com.logistreams.application.infrastructure.web.input.inventory;

import br.com.logistreams.application.infrastructure.web.dto.output.inventory.InventoryOutputDTO;
import br.com.logistreams.application.infrastructure.web.exception.BusinessLogicException;
import br.com.logistreams.application.infrastructure.web.exception.ErrorsEnum;
import br.com.logistreams.application.infrastructure.web.mapper.InventoryMapper;
import br.com.logistreams.domain.ports.input.inventory.CountInventoriesInputPort;
import br.com.logistreams.domain.ports.input.inventory.ListInventoriesInputPort;
import br.com.logistreams.utils.PagedResponseLinksBuilder;
import br.com.logistreams.utils.ValidatePageParams;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.stream.Stream;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@WebMvcTest(ListInventoryEndpoint.class)
@AutoConfigureMockMvc(addFilters = false)
class ListInventoryEndpointMvcTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    ListInventoriesInputPort listInventoriesInputPort;
    @MockBean
    CountInventoriesInputPort countInventoriesInputPort;
    @MockBean
    InventoryMapper inventoryMapper;
    @MockBean
    PagedResponseLinksBuilder<InventoryOutputDTO> pagedResponseLinksBuilder;
    @MockBean
    ValidatePageParams validatePageParams;

    final String localhost = "http://localhost";

    @Test
    @DisplayName("Given a HTTP request, when page number and size are null and inventory repository is empty, should return a paged response")
    void test1() throws Exception {
        given(countInventoriesInputPort.execute()).willReturn(0L);

        String self = localhost + WebMvcLinkBuilder.linkTo(ListInventoryEndpoint.class).toUriComponentsBuilder()
                .queryParam("page_number", 1)
                .queryParam("page_size", 1)
                .toUriString();

        String first = localhost + WebMvcLinkBuilder.linkTo(ListInventoryEndpoint.class).toUriComponentsBuilder()
                .queryParam("page_number", 1)
                .queryParam("page_size", 1)
                .toUriString();

        String last = localhost + WebMvcLinkBuilder.linkTo(ListInventoryEndpoint.class).toUriComponentsBuilder()
                .queryParam("page_number", 1)
                .queryParam("page_size", 1)
                .toUriString();

        mockMvc.perform(get("/v1/inventories"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.data").isEmpty())
                .andExpect(jsonPath("$.meta.totalRecords").value(0))
                .andExpect(jsonPath("$.meta.totalPages").value(1))
                .andExpect(jsonPath("$.meta.currentPage").value(1))
                .andExpect(jsonPath("$.links").exists())
                .andExpect(jsonPath("$.links.self").value(self))
                .andExpect(jsonPath("$.links.first").value(first))
                .andExpect(jsonPath("$.links.last").value(last))
                .andExpect(jsonPath("$.links.next").doesNotExist())
                .andExpect(jsonPath("$.links.previous").doesNotExist());

        verify(countInventoriesInputPort, times(1)).execute();
        verifyNoInteractions(listInventoriesInputPort);
    }

    @ParameterizedTest
    @MethodSource(value = "pageNumberAndSizeProvider")
    @DisplayName("Given a HTTP request, when page number and size are not null and inventory repository is empty, should return an empty paged response")
    void test2(String pageNumber, String pageSize) throws Exception {
        given(countInventoriesInputPort.execute()).willReturn(0L);

        String self = localhost + WebMvcLinkBuilder.linkTo(ListInventoryEndpoint.class).toUriComponentsBuilder()
                .queryParam("page_number", 1)
                .queryParam("page_size", 1)
                .toUriString();

        String first = localhost + WebMvcLinkBuilder.linkTo(ListInventoryEndpoint.class).toUriComponentsBuilder()
                .queryParam("page_number", 1)
                .queryParam("page_size", 1)
                .toUriString();

        String last = localhost + WebMvcLinkBuilder.linkTo(ListInventoryEndpoint.class).toUriComponentsBuilder()
                .queryParam("page_number", 1)
                .queryParam("page_size", 1)
                .toUriString();

        mockMvc.perform(get("/v1/inventories")
                        .queryParam("page_number", pageNumber)
                        .queryParam("page_size", pageSize))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.data").isEmpty())
                .andExpect(jsonPath("$.meta.totalRecords").value(0))
                .andExpect(jsonPath("$.meta.totalPages").value(1))
                .andExpect(jsonPath("$.meta.currentPage").value(1))
                .andExpect(jsonPath("$.links").exists())
                .andExpect(jsonPath("$.links.self").value(self))
                .andExpect(jsonPath("$.links.first").value(first))
                .andExpect(jsonPath("$.links.last").value(last))
                .andExpect(jsonPath("$.links.next").doesNotExist())
                .andExpect(jsonPath("$.links.previous").doesNotExist());

        verify(countInventoriesInputPort, times(1)).execute();
        verifyNoInteractions(validatePageParams);
        verifyNoInteractions(listInventoriesInputPort);
    }

    @Test
    @DisplayName("Given a HTTP request, when page number and size are null and inventory repository is not empty, should return a paged response")
    void test3() throws Exception {
        long totalRecords = 100L;
        int totalPages = (int) Math.ceil((double) totalRecords/ ValidatePageParams.DEFAULT_PAGE_SIZE);

        given(countInventoriesInputPort.execute()).willReturn(totalRecords);
        given(validatePageParams.validatePageNumber(null, totalPages)).willReturn(ValidatePageParams.DEFAULT_PAGE_NUMBER);
        given(validatePageParams.validatePageSize(null)).willReturn(ValidatePageParams.DEFAULT_PAGE_SIZE);

        String self = localhost + WebMvcLinkBuilder.linkTo(ListInventoryEndpoint.class).toUriComponentsBuilder()
                .queryParam("page_number", ValidatePageParams.DEFAULT_PAGE_NUMBER)
                .queryParam("page_size", ValidatePageParams.DEFAULT_PAGE_SIZE)
                .toUriString();

        String first = localhost + WebMvcLinkBuilder.linkTo(ListInventoryEndpoint.class).toUriComponentsBuilder()
                .queryParam("page_number", ValidatePageParams.DEFAULT_PAGE_NUMBER)
                .queryParam("page_size", ValidatePageParams.DEFAULT_PAGE_SIZE)
                .toUriString();

        String last = localhost + WebMvcLinkBuilder.linkTo(ListInventoryEndpoint.class).toUriComponentsBuilder()
                .queryParam("page_number", 10)
                .queryParam("page_size", ValidatePageParams.DEFAULT_PAGE_SIZE)
                .toUriString();

        mockMvc.perform(get("/v1/inventories"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.data").isEmpty())
                .andExpect(jsonPath("$.meta.totalRecords").value(totalRecords))
                .andExpect(jsonPath("$.meta.totalPages").value(totalPages))
                .andExpect(jsonPath("$.meta.currentPage").value(ValidatePageParams.DEFAULT_PAGE_NUMBER))
                .andExpect(jsonPath("$.links").exists())
                .andExpect(jsonPath("$.links.self").value(self))
                .andExpect(jsonPath("$.links.first").value(first))
                .andExpect(jsonPath("$.links.last").value(last))
                .andExpect(jsonPath("$.links.next").exists())
                .andExpect(jsonPath("$.links.previous").doesNotExist());

        verify(countInventoriesInputPort, times(1)).execute();
        verify(listInventoriesInputPort, times(1)).execute(ValidatePageParams.DEFAULT_PAGE_NUMBER, ValidatePageParams.DEFAULT_PAGE_SIZE);
    }

    @Test
    @DisplayName("Given a HTTP request, when page number = 5, page size = null, and inventory repository is not empty, should return a paged response")
    void test4() throws Exception {
        int currentPage = 5;
        long totalRecords = 100L;
        int totalPages = (int) Math.ceil((double) totalRecords/ ValidatePageParams.DEFAULT_PAGE_SIZE);

        given(countInventoriesInputPort.execute()).willReturn(totalRecords);
        given(validatePageParams.validatePageNumber(currentPage, totalPages)).willReturn(5);
        given(validatePageParams.validatePageSize(null)).willReturn(10);

        String self = localhost + WebMvcLinkBuilder.linkTo(ListInventoryEndpoint.class).toUriComponentsBuilder()
                .queryParam("page_number", currentPage)
                .queryParam("page_size", ValidatePageParams.DEFAULT_PAGE_SIZE)
                .toUriString();

        String first = localhost + WebMvcLinkBuilder.linkTo(ListInventoryEndpoint.class).toUriComponentsBuilder()
                .queryParam("page_number", 1)
                .queryParam("page_size", ValidatePageParams.DEFAULT_PAGE_SIZE)
                .toUriString();

        String last = localhost + WebMvcLinkBuilder.linkTo(ListInventoryEndpoint.class).toUriComponentsBuilder()
                .queryParam("page_number", String.valueOf(totalPages))
                .queryParam("page_size", ValidatePageParams.DEFAULT_PAGE_SIZE)
                .toUriString();

        mockMvc.perform(get("/v1/inventories")
                        .queryParam("page_number", String.valueOf(currentPage)))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.data").isEmpty())
                .andExpect(jsonPath("$.meta.totalRecords").value(totalRecords))
                .andExpect(jsonPath("$.meta.totalPages").value(totalPages))
                .andExpect(jsonPath("$.meta.currentPage").value(currentPage))
                .andExpect(jsonPath("$.links").exists())
                .andExpect(jsonPath("$.links.self").value(self))
                .andExpect(jsonPath("$.links.first").value(first))
                .andExpect(jsonPath("$.links.last").value(last))
                .andExpect(jsonPath("$.links.next").exists())
                .andExpect(jsonPath("$.links.previous").exists());

        verify(countInventoriesInputPort, times(1)).execute();
        verify(listInventoriesInputPort, times(1)).execute(currentPage, ValidatePageParams.DEFAULT_PAGE_SIZE);
    }

    @Test
    @DisplayName("Given a HTTP request, when page number equals last, links should not have field 'next'")
    void test5() throws Exception {
        int currentPage = 10;
        long totalRecords = 100L;
        int totalPages = (int) Math.ceil((double) totalRecords/ ValidatePageParams.DEFAULT_PAGE_SIZE);

        given(countInventoriesInputPort.execute()).willReturn(totalRecords);
        given(validatePageParams.validatePageNumber(currentPage, totalPages)).willReturn(currentPage);
        given(validatePageParams.validatePageSize(null)).willReturn(ValidatePageParams.DEFAULT_PAGE_SIZE);

        mockMvc.perform(get("/v1/inventories")
                        .queryParam("page_number", String.valueOf(currentPage)))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.data").isEmpty())
                .andExpect(jsonPath("$.meta.totalRecords").value(totalRecords))
                .andExpect(jsonPath("$.meta.totalPages").value(totalPages))
                .andExpect(jsonPath("$.meta.currentPage").value(currentPage))
                .andExpect(jsonPath("$.links").exists())
                .andExpect(jsonPath("$.links.next").doesNotExist());

        verify(countInventoriesInputPort, times(1)).execute();
        verify(listInventoriesInputPort, times(1)).execute(currentPage, ValidatePageParams.DEFAULT_PAGE_SIZE);
    }

    @Test
    @DisplayName("Given a HTTP request, when page number equals first, links should not have field 'previous'")
    void test6() throws Exception {
        int currentPage = 1;
        long totalRecords = 100L;
        int totalPages = (int) Math.ceil((double) totalRecords/ ValidatePageParams.DEFAULT_PAGE_SIZE);

        given(countInventoriesInputPort.execute()).willReturn(totalRecords);
        given(validatePageParams.validatePageNumber(currentPage, totalPages)).willReturn(currentPage);
        given(validatePageParams.validatePageSize(null)).willReturn(ValidatePageParams.DEFAULT_PAGE_SIZE);

        mockMvc.perform(get("/v1/inventories")
                        .queryParam("page_number", String.valueOf(currentPage)))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.data").isEmpty())
                .andExpect(jsonPath("$.meta.totalRecords").value(totalRecords))
                .andExpect(jsonPath("$.meta.totalPages").value(totalPages))
                .andExpect(jsonPath("$.meta.currentPage").value(currentPage))
                .andExpect(jsonPath("$.links").exists())
                .andExpect(jsonPath("$.links.previous").doesNotExist());

        verify(countInventoriesInputPort, times(1)).execute();
        verify(listInventoriesInputPort, times(1)).execute(currentPage, ValidatePageParams.DEFAULT_PAGE_SIZE);
    }

    @Test
    @DisplayName("Given a HTTP request, when page number is smaller than 0, should throw BusinessLogicException")
    void test7() throws Exception {
        int currentPage = -1;
        int totalPages = 10;

        given(countInventoriesInputPort.execute()).willReturn(100L);
        given(validatePageParams.validatePageNumber(currentPage, totalPages)).willThrow(new BusinessLogicException(ErrorsEnum.INVALID_PAGE_NUMBER));

        mockMvc.perform(get("/v1/inventories")
                        .queryParam("page_number", String.valueOf(currentPage)))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(jsonPath("$.httpStatus").exists())
                .andExpect(jsonPath("$.errorMessage").exists());

        verify(countInventoriesInputPort, times(0)).execute();
        verify(listInventoriesInputPort, times(0)).execute(currentPage, ValidatePageParams.DEFAULT_PAGE_SIZE);
    }

    @ParameterizedTest
    @MethodSource(value = "validatePageSizeProvider")
    @DisplayName("Given a HTTP request, when page size is smaller than 0 or greater than 30, should throw BusinessLogicException")
    void test8(Integer pageSize) throws Exception {
        int totalPages = 10;

        given(countInventoriesInputPort.execute()).willReturn(100L);
        given(validatePageParams.validatePageNumber(null, totalPages)).willReturn(1);

        mockMvc.perform(get("/v1/inventories")
                        .queryParam("page_size", String.valueOf(pageSize))
                )
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(jsonPath("$.httpStatus").exists())
                .andExpect(jsonPath("$.errorMessage").exists());

        verify(countInventoriesInputPort, times(0)).execute();
        verify(listInventoriesInputPort, times(0)).execute(ValidatePageParams.DEFAULT_PAGE_NUMBER, pageSize);
    }

    static Stream<Arguments> pageNumberAndSizeProvider() {
        return Stream.of(
                Arguments.of("1", "1"),
                Arguments.of("1", "15"),
                Arguments.of("1", "30"),
                Arguments.of("10", "1"),
                Arguments.of("10", "15"),
                Arguments.of("10", "30")
        );
    }

    static Stream<Integer> validatePageSizeProvider() {
        return Stream.of(-1, 0, 31, 100000);
    }
 }