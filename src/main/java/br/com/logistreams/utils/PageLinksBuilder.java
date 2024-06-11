package br.com.logistreams.utils;

import br.com.logistreams.dtos.output.Links;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

public class PageLinksBuilder<T> {
    private final Class<?> controllerClass;

    public PageLinksBuilder(Class<?> controllerClassName) {
        this.controllerClass = controllerClassName;
    }

    public Links buildLinks(Page<T> page) {
        Links links = new Links();
        String pageNumber = "page_number";
        String pageSize = "page_size";

        links.setSelf(WebMvcLinkBuilder.linkTo(controllerClass)
                .toUriComponentsBuilder()
                .queryParam(pageNumber, page.getNumber() + 1)
                .queryParam(pageSize, page.getSize())
                .toUriString());

        links.setFirst(WebMvcLinkBuilder.linkTo(controllerClass)
                .toUriComponentsBuilder()
                .queryParam(pageNumber,  1)
                .queryParam(pageSize, page.getSize())
                .toUriString());

        links.setLast(WebMvcLinkBuilder.linkTo(controllerClass)
                .toUriComponentsBuilder()
                .queryParam(pageNumber, page.getTotalPages())
                .queryParam(pageSize, page.getSize())
                .toUriString());

        if(page.getNumber() != 0) {
            links.setPrevious(WebMvcLinkBuilder.linkTo(controllerClass)
                    .toUriComponentsBuilder()
                    .queryParam(pageNumber,  page.getNumber())
                    .queryParam(pageSize, page.getSize())
                    .toUriString());
        }

        if(page.getNumber() < page.getTotalPages() - 1) {
            links.setNext(WebMvcLinkBuilder.linkTo(controllerClass)
                    .toUriComponentsBuilder()
                    .queryParam(pageNumber,  page.getNumber() + 2)
                    .queryParam(pageSize, page.getSize())
                    .toUriString());
        }

        return links;
    }
}
