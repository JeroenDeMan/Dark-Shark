package be.darkshark.parkshark.api.controller;


import be.darkshark.parkshark.service.AllocationService;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(AllocationController.class)
class AllocationControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    AllocationService allocationService;

    @Test
    public void whenCalledToCreateAllocation_theAllocationServiceIsCalledOnce() throws Exception {
        mockMvc.perform(post("/allocations").contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"licencePlate\": \"010-aba\",\n" +
                        "  \"memberId\": 1,\n" +
                        "  \"parkingLotId\": 1\n" +
                        "}").accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
    }

    @Test
    public void whenCalledToStopAllocation_theAllocationServiceToStopIsCalledOnce() throws Exception {
        long allocationId = 1L;
        long memberId = 1L;

        mockMvc.perform(put("/allocations/" + allocationId + "/" + memberId).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

}
