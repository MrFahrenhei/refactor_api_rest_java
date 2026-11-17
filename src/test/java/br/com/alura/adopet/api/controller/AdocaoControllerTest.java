package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.service.AdocaoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
class AdocaoControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AdocaoService service;

    @Test
    void shouldReturnNotFoundForAdoptionRequestWithErros() throws Exception {
        //Arrange
        String json = "{}";
        //act
        var response = mvc.perform(
                post("/adocoes")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        //assert
        Assertions.assertEquals(400, response.getStatus());
    }

    @Test
    void shouldReturnNotFoundForAdoptionRequestWithoutErros() throws Exception {
        //Arrange
        String json = """
                {
                    "idPet": 1,
                    "idTutor":1,
                    "motivo":"Motivo qualquer"
                }
                """;

        //act
        var response = mvc.perform(
                post("/adocoes")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        //assert
        Assertions.assertEquals(200, response.getStatus());
    }
}