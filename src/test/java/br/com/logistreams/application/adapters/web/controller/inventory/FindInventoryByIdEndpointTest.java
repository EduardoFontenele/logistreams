package br.com.logistreams.application.adapters.web.controller.inventory;

import br.com.logistreams.application.adapters.web.dto.output.inventory.ListInventoryDTO;
import br.com.logistreams.application.adapters.web.exception.ErrorsEnum;
import br.com.logistreams.application.adapters.web.mapper.InventoryMapper;
import br.com.logistreams.application.core.domain.Inventory;
import br.com.logistreams.application.core.ports.input.inventory.ListInventoryByIdInputPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
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
@AutoConfigureMockMvc(addFilters = false)
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
        ListInventoryDTO listInventoryDTO = new ListInventoryDTO();
        listInventoryDTO.setId(1L);
        listInventoryDTO.setName(inventory.getName());
        Map<String, Object> links = new HashMap<>();
        links.put("self", "http://localhost:8080/v1/inventories/1");
        links.put("inventories", "http://localhost:8080/v1/inventories");
        listInventoryDTO.set_links(links);

        given(listInventoryByIdInputPort.execute(1L)).willReturn(inventory);
        given(inventoryMapper.toInventoryOutputDTO(inventory)).willReturn(listInventoryDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/inventories/{id}", 1))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.id").value(listInventoryDTO.getId()))
                .andExpect(jsonPath("$.name").value(listInventoryDTO.getName()))
                .andExpect(jsonPath("$._links.self").value(listInventoryDTO.get_links().get("self")))
                .andExpect(jsonPath("$._links.inventories").value(listInventoryDTO.get_links().get("inventories")));
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