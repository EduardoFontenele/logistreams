package br.com.logistreams.application.infrastructure.web.input.inventory;

import br.com.logistreams.application.infrastructure.web.dto.input.inventory.CreateInventoryDTO;
import br.com.logistreams.application.infrastructure.web.exception.DefaultMessages;
import br.com.logistreams.application.infrastructure.web.exception.ErrorsEnum;
import br.com.logistreams.domain.ports.input.inventory.CreateInventoryInputPort;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CreateInventoryEndpoint.class)
@AutoConfigureMockMvc(addFilters = false)
class CreateInventoryEndpointTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    CreateInventoryInputPort createInventoryInputPort;

    @Test
    @DisplayName("When called a valid InventoryRequest DTO, should execute use case and return 200")
    void test1() throws Exception {
        CreateInventoryDTO createInventoryDTO = new CreateInventoryDTO("Camisas");
        String json = objectMapper.writeValueAsString(createInventoryDTO);

        mockMvc.perform(post("/v1/inventories")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(json))
                .andExpect(status().is2xxSuccessful());

        verify(createInventoryInputPort).execute(createInventoryDTO.getName());
    }

    @Test
    @DisplayName("When called a invalid InventoryRequest DTO, should execute use case and return 400")
    void test2() throws Exception {
        CreateInventoryDTO createInventoryDTO = new CreateInventoryDTO("");
        String json = objectMapper.writeValueAsString(createInventoryDTO);

        mockMvc.perform(post("/v1/inventories")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(json))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.httpStatus").value(ErrorsEnum.INVALID_FIELDS.getHttpStatus().getReasonPhrase()))
                .andExpect(jsonPath("$.errorMessage").value(ErrorsEnum.INVALID_FIELDS.getErrorMessage()))
                .andExpect(jsonPath("$.fields").exists())
                .andExpect(jsonPath("$.fields.name").value(DefaultMessages.NOT_EMPTY));

        verifyNoInteractions(createInventoryInputPort);
    }
}