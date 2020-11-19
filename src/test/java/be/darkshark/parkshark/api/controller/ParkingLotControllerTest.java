package be.darkshark.parkshark.api.controller;

import be.darkshark.parkshark.service.ParkingLotService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ParkingLotController.class)
class ParkingLotControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    ParkingLotService parkingLotService;

    @Test
    void whenCallToCreateParkingLot_TheParkingLotServiceIsCalledOnce() throws Exception {
        mockMvc.perform(post("/parking-lots").contentType(MediaType.APPLICATION_JSON)
                                             .content("{\"name\" : \"Parking Lot 1\", \n" +
                                                     "\"parkingCategory\": \"underground_building\",\n" +
                                                     "\"capacity\": \"5\",\n" +
                                                     "\"contactPersonId\":\"1\",\n" +
                                                     "\"address\": {\"street\": \"Some Street\",\n" +
                                                     " \"houseNumber\":\"5\",\n" +
                                                     " \"postalCode\": 9160,\n" +
                                                     " \"city\": \"Lokeren\"},\n" +
                                                     " \"pricePerHour\": 22.22,\n" +
                                                     " \"divisionId\": 1\n" +
                                                     " }")
                                             .accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());

    }

    @Test
    public void whenGettingAllParkingLots_theParkingLotControllerGetAllIsCalled() throws Exception {
        mockMvc.perform(get("/parking-lots")).andExpect(status().isOk());
    }

}
