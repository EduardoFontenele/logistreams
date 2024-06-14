package br.com.logistreams.application.infrastructure.web.dto.input;

import br.com.logistreams.application.infrastructure.web.exception.DefaultMessages;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
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
public class InventoryInputDTO {
    @NotEmpty(message = DefaultMessages.NOT_EMPTY)
    @Size(max = 255, message = DefaultMessages.SIZE_EXCEEDED)
    private String name;
}