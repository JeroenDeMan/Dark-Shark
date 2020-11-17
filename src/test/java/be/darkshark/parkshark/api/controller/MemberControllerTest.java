package be.darkshark.parkshark.api.controller;

import be.darkshark.parkshark.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(MemberController.class)
class MemberControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    MemberService memberService;

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
}
