package br.com.logistreams.utils;

import br.com.logistreams.application.adapters.web.dto.output.Links;
import br.com.logistreams.application.adapters.web.controller.inventory.ListInventoryEndpoint;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PagedResponseLinksBuilderTest {
    PagedResponseLinksBuilder<Object> builder = new PagedResponseLinksBuilder<>(ListInventoryEndpoint.class);
    Class<ListInventoryEndpoint> listInventoryEndpointClass = ListInventoryEndpoint.class;
    @Test
    @DisplayName("When in first page, should build all links but previous")
    public void testBuildLinks_FirstPage_AllLinks() {
        int queryPageNumber = 1;
        int queryPageSize = 10;
        int totalPages = 5;

        Links links = builder.buildLinks(queryPageNumber, queryPageSize, totalPages);

        String expectedSelfLink = WebMvcLinkBuilder.linkTo(listInventoryEndpointClass)
                .toUriComponentsBuilder()
                .queryParam("page_number", queryPageNumber)
                .queryParam("page_size", queryPageSize)
                .toUriString();
        assertEquals(expectedSelfLink, links.getSelf());

        String expectedFirstLink = WebMvcLinkBuilder.linkTo(listInventoryEndpointClass)
                .toUriComponentsBuilder()
                .queryParam("page_number", 1)
                .queryParam("page_size", queryPageSize)
                .toUriString();
        assertEquals(expectedFirstLink, links.getFirst());

        String expectedLastLink = WebMvcLinkBuilder.linkTo(listInventoryEndpointClass)
                .toUriComponentsBuilder()
                .queryParam("page_number", totalPages)
                .queryParam("page_size", queryPageSize)
                .toUriString();
        assertEquals(expectedLastLink, links.getLast());

        String expectedNextLink = WebMvcLinkBuilder.linkTo(listInventoryEndpointClass)
                .toUriComponentsBuilder()
                .queryParam("page_number", queryPageNumber + 1)
                .queryParam("page_size", queryPageSize)
                .toUriString();
        assertEquals(expectedNextLink, links.getNext());

        assertNull(links.getPrevious());
    }

    @Test
    @DisplayName("When in the last page, should build all links but next")
    public void testBuildLinks_LastPage_OnlySelfFirstLast() {
        int queryPageNumber = 5;
        int queryPageSize = 10;
        int totalPages = 5;

        Links links = builder.buildLinks(queryPageNumber, queryPageSize, totalPages);

        String expectedSelfLink = WebMvcLinkBuilder.linkTo(listInventoryEndpointClass)
                .toUriComponentsBuilder()
                .queryParam("page_number", queryPageNumber)
                .queryParam("page_size", queryPageSize)
                .toUriString();
        assertEquals(expectedSelfLink, links.getSelf());

        String expectedFirstLink = WebMvcLinkBuilder.linkTo(listInventoryEndpointClass)
                .toUriComponentsBuilder()
                .queryParam("page_number", 1)
                .queryParam("page_size", queryPageSize)
                .toUriString();
        assertEquals(expectedFirstLink, links.getFirst());

        String expectedLastLink = WebMvcLinkBuilder.linkTo(listInventoryEndpointClass)
                .toUriComponentsBuilder()
                .queryParam("page_number", totalPages)
                .queryParam("page_size", queryPageSize)
                .toUriString();
        assertEquals(expectedLastLink, links.getLast());

        String expectedPreviousLink = WebMvcLinkBuilder.linkTo(listInventoryEndpointClass)
                .toUriComponentsBuilder()
                .queryParam("page_number", totalPages - 1)
                .queryParam("page_size", queryPageSize)
                .toUriString();
        assertEquals(expectedPreviousLink, links.getPrevious());

        assertNull(links.getNext());
    }

    @Test
    @DisplayName("When page have next and previous pages, should build all links")
    public void testBuildLinks_MiddlePage_AllLinks() {
        int queryPageNumber = 3;
        int queryPageSize = 10;
        int totalPages = 5;

        Links links = builder.buildLinks(queryPageNumber, queryPageSize, totalPages);

        assertAll(() -> {
            assertNotNull(links.getSelf());
            assertNotNull(links.getFirst());
            assertNotNull(links.getLast());
            assertNotNull(links.getPrevious());
            assertNotNull(links.getNext());
        });
    }
}

