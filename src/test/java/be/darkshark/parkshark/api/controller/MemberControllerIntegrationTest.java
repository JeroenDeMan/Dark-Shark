package be.darkshark.parkshark.api.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class MemberControllerIntegrationTest {
    @LocalServerPort
    int localServerPort;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void whenCallToCreateMember_TheMemberServiceIsCalledOnce () throws Exception {
        mockMvc.perform(post("/members").contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"firstName\": \"Jeroen\",\n" +
                        "  \"lastName\": \"De Man\",\n" +
                        "  \"address\": {\n" +
                        "    \"street\": \"Some Street\",\n" +
                        "    \"houseNumber\": \"5\",\n" +
                        "    \"postalCode\": 9160,\n" +
                        "    \"city\": \"Lokeren\"\n" +
                        "  },\n" +
                        "  \"phoneNumber\": {\n" +
                        "    \"countryCode\": \"+32\",\n" +
                        "    \"phoneNumber\": 477889911\n" +
                        "  },\n" +
                        "  \"mailAddress\": \"some@mail.com\",\n" +
                        "  \"id\": 1,\n" +
                        "  \"licensePlate\": {\n" +
                        "    \"licenseNumber\": \"010-aba\",\n" +
                        "    \"licenseCountry\": \"Belgium\"\n" +
                        "  },\n" +
                        "  \"memberShipLevel\": \"Bronze\",\n" +
                        "  \"registrationDate\": \"01-02-2020\"\n" +
                        "}")
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
    }

    @Test
    public void whenGettingAllMembers_theMemberControllerGetAllMembersIsCalled() throws Exception {
        mockMvc.perform(get("/members")).andExpect(status().isOk());
    }
}
