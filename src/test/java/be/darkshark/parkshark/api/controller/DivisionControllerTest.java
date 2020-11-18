package be.darkshark.parkshark.api.controller;

import be.darkshark.parkshark.service.DivisionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DivisionController.class)
class DivisionControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    DivisionService divisionService;

    @Test
    void whenCallToCreateDivision_TheDivisionServiceIsCalledOnce() throws Exception {
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

}
