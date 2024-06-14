package br.com.logistreams.application.infrastructure.web.dto.input;

import br.com.logistreams.application.infrastructure.web.exception.DefaultMessages;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank(message = DefaultMessages.NOT_EMPTY)
    @NotEmpty
    @Size(max = 255, message = DefaultMessages.SIZE_EXCEEDED)
    private String name;
}