package br.com.publica.obras.controller;

import br.com.publica.obras.domain.responsavel.DadosCadastroResponsavel;
import br.com.publica.obras.domain.responsavel.DadosDetalhamentoResponsavel;
import br.com.publica.obras.domain.responsavel.Responsavel;
import br.com.publica.obras.repository.ResponsavelRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@DataJpaTest
@AutoConfigureMockMvc
class ResponsavelControllerTest {
    @Autowired
    private ResponsavelRepository responsavelRepository;

    @Test
    @DisplayName("Nao pode cadastrar responsavel sem codigo")
    void cadastrarResponsavelSemCodigo() {
        try {
            var responsavel = new Responsavel(cadastroResponsavel(null, "Rodrigo Andare Filho", "08562738998"));
            responsavelRepository.save(responsavel);
        } catch (Exception ex) {
            assertEquals("É obrigatório o código do responsável", ex.getMessage());
        }
    }

    private Responsavel responsavel(BigDecimal codigo, String nome, String cpf) {
        var responsavel = new Responsavel(cadastroResponsavel(new BigDecimal(10), "Rodrigo Andare Filho", "08562738998"));
        responsavelRepository.save(responsavel);
        return responsavel;
    }

    private DadosCadastroResponsavel cadastroResponsavel(BigDecimal codigo, String nome, String cpf) {
        return new DadosCadastroResponsavel(codigo, nome, cpf);
    }
}