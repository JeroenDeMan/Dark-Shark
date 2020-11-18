package be.darkshark.parkshark.api.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
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
public class DivisionControllerIntegrationTest {
    @LocalServerPort
    int localServerPort;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void whenCallToCreateMember_TheMemberServiceIsCalledOnce() throws Exception {
        mockMvc.perform(post("/divisions").contentType(MediaType.APPLICATION_JSON)
                                          .content("{\"name\" : \"testdivisions\", \n" +
                                                  "\"originalName\": \"original_test_name\",\n" +
                                                  "\"director_id\": \"1\",\n" +
                                                  "\"parent_division_id\":\"\"}")
                                          .accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
    }

    @Test
    public void whenGettingAllMembers_theMemberControllerGetAllMembersIsCalled() throws Exception {
        mockMvc.perform(get("/divisions")).andExpect(status().isOk());
    }
}
