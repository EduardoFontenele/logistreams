package br.com.logistreams.application.infrastructure.web.endpoint;

import br.com.logistreams.application.infrastructure.web.dto.input.InventoryInputDTO;
import br.com.logistreams.application.infrastructure.web.exception.DefaultMessages;
import br.com.logistreams.application.infrastructure.web.exception.ErrorsEnum;
import br.com.logistreams.domain.ports.input.inventory.CreateInventoryInputPort;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
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
        InventoryInputDTO inventoryInputDTO = new InventoryInputDTO("Camisas");
        String json = objectMapper.writeValueAsString(inventoryInputDTO);

        mockMvc.perform(post("/v1/inventories")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(json))
                .andExpect(status().is2xxSuccessful());

        verify(createInventoryInputPort).execute(inventoryInputDTO.getName());
    }

    @Test
    @DisplayName("When called a invalid InventoryRequest DTO, should execute use case and return 400")
    void test2() throws Exception {
        InventoryInputDTO inventoryInputDTO = new InventoryInputDTO("");
        String json = objectMapper.writeValueAsString(inventoryInputDTO);

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