package be.darkshark.parkshark.api.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class DivisionControllerIntegrationTest {
    @LocalServerPort
    int localServerPort;

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
//    @Sql("insert-employee.sql")
    public void setup() {
    }

    @Test
//    @Sql("insert-employee.sql")
    public void whenCallToCreateDivision_TheDivisionServiceIsCalledOnce() throws Exception {
        mockMvc.perform(post("/divisions").contentType(MediaType.APPLICATION_JSON)
                                          .content("{\"name\" : \"testdivisions\", \n" +
                                                  "\"originalName\": \"original_test_name\",\n" +
                                                  "\"director_id\": \"1\",\n" +
                                                  "\"parent_division_id\":\"\"}")
                                          .accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
    }

    @Test
    public void whenGettingAllDivisions_theMemberControllerGetAllDivisionsIsCalled() throws Exception {
        mockMvc.perform(get("/divisions")).andExpect(status().isOk());
    }

    @Test
    @Sql("insert-employee.sql")
    public void whenGettingADivision_theMemberControllerGetADivisionIsCalled() throws Exception {
        mockMvc.perform(post("/divisions").contentType(MediaType.APPLICATION_JSON)
                                          .content("{\"name\" : \"testdivisions\", \n" +
                                                  "\"originalName\": \"original_test_name\",\n" +
                                                  "\"director_id\": \"1\",\n" +
                                                  "\"parent_division_id\":\"\"}"));

        mockMvc.perform(get("/divisions/1")).andExpect(status().isOk());
    }
}
