package br.com.logistreams.application.infrastructure.web.controller;

import br.com.logistreams.application.infrastructure.web.dto.input.InventoryInputDTO;
import br.com.logistreams.application.infrastructure.web.mapper.InventoryMapper;
import br.com.logistreams.domain.ports.input.inventory.CreateInventoryInputPort;
import br.com.logistreams.fixtures.InventoryFixtures;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(InventoryController.class)
class InventoryControllerMvcTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateInventoryInputPort createInventoryInputPort;

    @MockBean
    private InventoryMapper inventoryMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @DisplayName("When performed a POST request with a VALID INVENTORY, should return 200")
    @Test
    void test1() throws Exception {
        InventoryInputDTO inventoryInputDTO = InventoryFixtures.gimmeValidInventoryInputDto();

        mockMvc.perform(post("/v1/inventories")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(inventoryInputDTO)))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @DisplayName("When performed a POST request with an INVALID INVENTORY, should return 400")
    @ParameterizedTest(name = "When 'name' = {0}")
    @MethodSource(value = "inventoryValuesProvider")
    void test2(String name) throws Exception {
        InventoryInputDTO inventoryInputDTO = new InventoryInputDTO(name);

        mockMvc.perform(post("/v1/inventories")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(inventoryInputDTO)))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }


    public static Stream<String> inventoryValuesProvider() {
        return Stream.of(
                "",
                "       ",
                null
        );
    }
}