package br.com.publica.obras.controller;

import br.com.publica.obras.domain.dto.responsavel.ResponsavelDTO;
import br.com.publica.obras.domain.entity.ResponsavelEntity;
import br.com.publica.obras.domain.model.ResponsavelModel;
import br.com.publica.obras.repository.ResponsavelRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ResponsavelControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ResponsavelRepository responsavelRepository;
    @Autowired
    private JacksonTester<ResponsavelDTO> responsavelDTOJson;
    @Autowired
    private JacksonTester<ResponsavelModel> responsavelModelJson;

    @Test
    @DisplayName("Deveria devolver codigo http 400 quando informacoes estao invalidas")
    void cadastrarResponsavel_Cenario1() throws Exception {
        var response = mockMvc
                .perform(post("/responsavel"))
                .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria devolver codigo http 200 quando informacoes estao validas")
    void cadastrarResponsavel_Cenario2() throws Exception {
        var responsavelDTO = new ResponsavelDTO(
                new BigDecimal(444),
                "Rodrigo Andare Filho",
                "234.345.765-34");

        when(responsavelRepository.save(any())).thenReturn(new ResponsavelEntity(responsavelDTO));

        var response = mockMvc
                .perform(post("/responsavel")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(responsavelDTOJson.write(responsavelDTO).getJson()))
                .andReturn().getResponse();

        var responsavelModel = new ResponsavelModel(
                null,
                responsavelDTO.nome(),
                responsavelDTO.cpf(),
                responsavelDTO.codigo()
        );

        var jsonEsperado = responsavelModelJson.write(responsavelModel).getJson();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }
}