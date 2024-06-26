package br.com.logistreams.application.adapters.web.controller.inventory;

import br.com.logistreams.application.adapters.web.exception.ErrorsEnum;
import br.com.logistreams.application.core.ports.input.inventory.DeleteInventoryByIdInputPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DeleteInventoryEndpoint.class)
@AutoConfigureMockMvc(addFilters = false)
class DeleteInventoryEndpointTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    DeleteInventoryByIdInputPort deleteInventoryByIdInputPort;

    @Test
    @DisplayName("Given a valid ID, when inventory exists, then remove inventory and return 204")
    void test1() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/v1/inventories/{id}", 1L))
                .andExpect(status().is2xxSuccessful());

        verify(deleteInventoryByIdInputPort).execute(1L);
    }

    @Test
    @DisplayName("Given an invalid ID, should throw exception exception and return response error")
    void test2() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/v1/inventories/{id}", -1L))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.httpStatus").value(ErrorsEnum.INVALID_ID.getHttpStatus().getReasonPhrase()))
                .andExpect(jsonPath("$.errorMessage").value(ErrorsEnum.INVALID_ID.getErrorMessage()));

        verifyNoInteractions(deleteInventoryByIdInputPort);
    }
}