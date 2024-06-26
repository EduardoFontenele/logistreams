package br.com.logistreams.application.adapters.web.dto.input.user;

import br.com.logistreams.application.adapters.web.exception.DefaultMessages;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUserDTO {
    @NotEmpty(message = DefaultMessages.NOT_EMPTY)
    @Size(max = 255, message = DefaultMessages.SIZE_EXCEEDED)
    private String username;

    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$", message = DefaultMessages.PASSWORD_PATTERN_MISMATCH)
    private String password;

    @Email(message = DefaultMessages.INVALID_EMAIL_FORMAT)
    private String email;
}
