package br.com.logistreams.utils;

import br.com.logistreams.application.infrastructure.web.dto.output.Links;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

public class PagedResponseLinksBuilder<T> {
    private final Class<?> controllerClass;

    public PagedResponseLinksBuilder(Class<?> controllerClassName) {
        this.controllerClass = controllerClassName;
    }

    public Links buildLinks(int queryPageNumber, int queryPageSize, int totalPages) {
        Links links = new Links();
        String pageNumber = "page_number";
        String pageSize = "page_size";

        links.setSelf(WebMvcLinkBuilder.linkTo(controllerClass)
                .toUriComponentsBuilder()
                .queryParam(pageNumber, queryPageNumber)
                .queryParam(pageSize, queryPageSize)
                .toUriString());

        links.setFirst(WebMvcLinkBuilder.linkTo(controllerClass)
                .toUriComponentsBuilder()
                .queryParam(pageNumber, 1)
                .queryParam(pageSize, queryPageSize)
                .toUriString());

        links.setLast(WebMvcLinkBuilder.linkTo(controllerClass)
                .toUriComponentsBuilder()
                .queryParam(pageNumber, totalPages)
                .queryParam(pageSize, queryPageSize)
                .toUriString());

        if (queryPageNumber > 1 && totalPages >= queryPageNumber) {
            links.setPrevious(WebMvcLinkBuilder.linkTo(controllerClass)
                    .toUriComponentsBuilder()
                    .queryParam(pageNumber, queryPageNumber - 1)
                    .queryParam(pageSize, queryPageSize)
                    .toUriString());
        }

        if (queryPageNumber < totalPages) {
            links.setNext(WebMvcLinkBuilder.linkTo(controllerClass)
                    .toUriComponentsBuilder()
                    .queryParam(pageNumber, queryPageNumber + 1)
                    .queryParam(pageSize, queryPageSize)
                    .toUriString());
        }

        return links;
    }
}
