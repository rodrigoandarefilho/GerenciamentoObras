package br.com.publica.obras.controller;

import br.com.publica.obras.domain.dto.ObraPublicaDTO;
import br.com.publica.obras.domain.model.ObraPublicaModel;
import br.com.publica.obras.repository.ObraPublicaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ObraPublicaControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ObraPublicaRepository obraPublicaRepository;
    @Autowired
    private JacksonTester<ObraPublicaDTO> obraPublicaDTOJson;
    @Autowired
    private JacksonTester<ObraPublicaModel> obraPublicaModelJson;

    @Test
    @DisplayName("Deveria devolver codigo http 400 quando informacoes estao invalidas")
    void cadastrarObraPublica_Cenario1() throws Exception {
        var response = mockMvc
                .perform(post("/obrapublica"))
                .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }
}