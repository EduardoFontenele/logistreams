package br.com.logistreams.application.infrastructure.web.input.inventory;

import br.com.logistreams.application.infrastructure.web.dto.output.inventory.InventoryOutputDTO;
import br.com.logistreams.application.infrastructure.web.exception.ErrorsEnum;
import br.com.logistreams.application.infrastructure.web.mapper.InventoryMapper;
import br.com.logistreams.domain.entity.Inventory;
import br.com.logistreams.domain.ports.input.inventory.ListInventoryByIdInputPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FindInventoryByIdEndpoint.class)
class FindInventoryByIdEndpointTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ListInventoryByIdInputPort listInventoryByIdInputPort;

    @MockBean
    InventoryMapper inventoryMapper;

    @Test
    @DisplayName("Given a valid ID, when product is present, then return product DTO")
    void test1() throws Exception {
        Inventory inventory = new Inventory(1L, "Camisas");
        InventoryOutputDTO inventoryOutputDTO = new InventoryOutputDTO();
        inventoryOutputDTO.setId(1L);
        inventoryOutputDTO.setName(inventory.getName());
        Map<String, Object> links = new HashMap<>();
        links.put("self", "http://localhost:8080/v1/inventories/1");
        links.put("inventories", "http://localhost:8080/v1/inventories");
        inventoryOutputDTO.set_links(links);

        given(listInventoryByIdInputPort.execute(1L)).willReturn(inventory);
        given(inventoryMapper.toInventoryOutputDTO(inventory)).willReturn(inventoryOutputDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/inventories/{id}", 1))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.id").value(inventoryOutputDTO.getId()))
                .andExpect(jsonPath("$.name").value(inventoryOutputDTO.getName()))
                .andExpect(jsonPath("$._links.self").value(inventoryOutputDTO.get_links().get("self")))
                .andExpect(jsonPath("$._links.inventories").value(inventoryOutputDTO.get_links().get("inventories")));
    }

    @Test
    @DisplayName("Given an negative ID, should throw exception and return error response")
    void test2() throws Exception {
        ErrorsEnum error = ErrorsEnum.INVALID_ID;

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/inventories/{id}", -1))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.errorMessage").value(error.getErrorMessage()))
                .andExpect(jsonPath("$.httpStatus").value(error.getHttpStatus().getReasonPhrase()));
    }

    @Test
    @DisplayName("Given an ID equals to zero, should throw exception and return error response")
    void test3() throws Exception {
        ErrorsEnum error = ErrorsEnum.INVALID_ID;

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/inventories/{id}", 0))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.errorMessage").value(error.getErrorMessage()))
                .andExpect(jsonPath("$.httpStatus").value(error.getHttpStatus().getReasonPhrase()));
    }

}