package com.ada.banco.domain.usecase;

import com.ada.banco.domain.gateway.ContaGateway;
import com.ada.banco.domain.model.Conta;
import com.ada.banco.dummy.ContaGatewayDummyImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class BuscarContaTest {

    private CriarNovaConta criarNovaConta;
    private BuscarConta buscarConta;
    private ContaGateway contaGateway;

    @BeforeEach
    public void setUp() {
        this.contaGateway = new ContaGatewayDummyImpl();
        criarNovaConta = new CriarNovaConta(contaGateway);
        this.buscarConta = new BuscarConta(contaGateway);
    }

    @Test
    @DisplayName("Deve buscar os dados de uma conta com sucesso")
    public void execute_DeveConsultarUmaContaComSucesso() throws Exception {
        Conta contaSalva = criarConta();

        criarNovaConta.execute(contaSalva);
        Conta conta = buscarConta.execute(contaSalva.getId());

        assertAll(
                () -> assertNotNull(conta),
                () -> assertEquals(contaSalva.getId(), conta.getId()),
                () -> assertEquals(contaSalva, conta)
        );
    }

    @Test
    @DisplayName("Deve lançar uma exception caso a conta não for encontrada")
    public void execute_DeveLancarUmaExceptionCasoAContaNaoSejaEncontrada(){
        Long id = 999L;

        assertThrows(Exception.class, () -> buscarConta.execute(id));
    }

    private Conta criarConta(){
        return new Conta(111L, 0001L, 1234L, new BigDecimal("1000.00"), "Alice", "12345678911");
    }
}