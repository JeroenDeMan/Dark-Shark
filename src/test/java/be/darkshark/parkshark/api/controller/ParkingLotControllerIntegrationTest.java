package be.darkshark.parkshark.api.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class ParkingLotControllerIntegrationTest {
    @LocalServerPort
    int localServerPort;

    @Autowired
    MockMvc mockMvc;

    @Test
    void whenCallToCreateParkingLot_TheParkingLotServiceIsCalledOnce() throws Exception {
        mockMvc.perform(post("/divisions").contentType(MediaType.APPLICATION_JSON)
                                          .content("{\"name\" : \"testdivisions\", \n" +
                                                  "\"originalName\": \"original_test_name\",\n" +
                                                  "\"director_id\": \"1\",\n" +
                                                  "\"parent_division_id\":\"\"}"));

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

    @Test
    public void whenAParkingLotById_theParkingLotControllerGetAllIsCalled() throws Exception {
        mockMvc.perform(post("/divisions").contentType(MediaType.APPLICATION_JSON)
                                          .content("{\"name\" : \"testdivisions\", \n" +
                                                  "\"originalName\": \"original_test_name\",\n" +
                                                  "\"director_id\": \"1\",\n" +
                                                  "\"parent_division_id\":\"\"}"));

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
                                                     " }"));

        mockMvc.perform(get("/parking-lots/1")).andExpect(status().isOk());
    }

}
