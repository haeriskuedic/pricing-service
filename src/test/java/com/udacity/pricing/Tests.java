package com.udacity.pricing;

import com.udacity.pricing.api.PricingController;
import com.udacity.pricing.service.PricingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.net.URI;
import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PricingController.class)
public class Tests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    PricingService pricingService;

    @Test
    public void getPriceWithInvalidId() throws Exception {
        int invalid_id = 20;

        this.mockMvc.perform(
                get(new URI("/services/price?vehicleId=" + invalid_id))
                        .contentType(String.valueOf(StandardCharsets.UTF_8))
                        .accept(String.valueOf(StandardCharsets.UTF_8))).
        andExpect(status().isNotFound());
    }

    @Test
    public void getPriceFromValidId() throws Exception {
        int valid_id = 9;

        mockMvc.perform(
                MockMvcRequestBuilders.get("/services/price?vehicleId=" + valid_id).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void testGetPrice() throws Exception {
        mockMvc.perform(get("/services/price").param("vehicleId", "1"))
                .andExpect(status().isOk());
    }

}
