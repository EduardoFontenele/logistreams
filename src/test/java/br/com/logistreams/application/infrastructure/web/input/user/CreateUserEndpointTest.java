package br.com.logistreams.application.infrastructure.web.input.user;

import br.com.logistreams.application.infrastructure.web.dto.input.user.CreateUserDTO;
import br.com.logistreams.application.infrastructure.web.exception.DefaultMessages;
import br.com.logistreams.application.infrastructure.web.exception.ErrorsEnum;
import br.com.logistreams.domain.ports.input.user.CreateUserInputPort;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(CreateUserEndpoint.class)
@AutoConfigureMockMvc(addFilters = false)
class CreateUserEndpointTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    CreateUserInputPort createUserInputPort;

    @Test
    @DisplayName("Given a valid user DTO, when create new user, then create new user and return 200")
    void test1() throws Exception {
        CreateUserDTO dto = CreateUserDTO.builder()
                .username("mia.marques")
                .password("@4Dmin1234")
                .email("mia_marques@gmail.com")
                .build();

        String body = objectMapper.writeValueAsString(dto);

        mockMvc.perform(post("/v1/users")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .content(body))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    @DisplayName("Given an invalid user DTO, should throw validation exception and return 400")
    void test2() throws Exception {
        CreateUserDTO dto = CreateUserDTO.builder()
                .username("")
                .password("")
                .email("still love her")
                .build();

        String body = objectMapper.writeValueAsString(dto);

        mockMvc.perform(post("/v1/users")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .content(body))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.httpStatus").value(ErrorsEnum.INVALID_FIELDS.getHttpStatus().getReasonPhrase()))
                .andExpect(jsonPath("$.errorMessage").value(ErrorsEnum.INVALID_FIELDS.getErrorMessage()))
                .andExpect(jsonPath("$.fields.username").value(DefaultMessages.NOT_EMPTY))
                .andExpect(jsonPath("$.fields.password").value(DefaultMessages.PASSWORD_PATTERN_MISMATCH))
                .andExpect(jsonPath("$.fields.email").value(DefaultMessages.INVALID_EMAIL_FORMAT));
    }
}