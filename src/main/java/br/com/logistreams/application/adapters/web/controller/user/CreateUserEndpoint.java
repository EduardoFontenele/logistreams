package br.com.logistreams.application.adapters.web.controller.user;

import br.com.logistreams.application.adapters.web.dto.input.user.CreateUserDTO;
import br.com.logistreams.application.core.ports.input.user.CreateUserInputPort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CreateUserEndpoint {
    private final CreateUserInputPort createUserInputPort;

    @PostMapping(value = "/v1/users", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createNewUser(@RequestBody @Valid CreateUserDTO userDTO) {

        return ResponseEntity.ok().build();
    }
}
