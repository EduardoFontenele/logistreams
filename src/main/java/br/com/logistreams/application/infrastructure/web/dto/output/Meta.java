package br.com.logistreams.application.infrastructure.web.dto.output;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Meta {
    private int currentPage;
    private int totalPages;
    private long totalRecords;
}
