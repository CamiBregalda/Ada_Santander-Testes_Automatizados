package com.ada.banco.domain.usecase;

import com.ada.banco.domain.gateway.ContaGateway;
import com.ada.banco.domain.model.Conta;
import com.ada.banco.dummy.ContaGatewayDummyImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class CriarNovaContaTest {

    private CriarNovaConta criarNovaConta;
    private ContaGateway contaGateway;

    @BeforeEach
    public void setUp() {
        this.contaGateway = new ContaGatewayDummyImpl();
        this.criarNovaConta = new CriarNovaConta(contaGateway);
    }

    @Test
    public  void deveLancarUmaExceptionCasoUsuarioJaPossuaConta(){
        Conta novaConta = new Conta(101L, 0001L, 1234L, new BigDecimal("1000.00"), "Alice", "12345678900");

        assertThrows(Exception.class, () -> criarNovaConta.execute(novaConta));
    }


    @Test
    public void deveCriarUmaContaComSucesso() throws Exception {
        Conta novaConta = new Conta(111L, 0001L, 1234L, new BigDecimal("1000.00"), "Alice", "12345678911");

        Conta contaCriada = criarNovaConta.execute(novaConta);

        assertAll(
                () -> assertEquals(111L, contaCriada.getId()),
                () -> assertEquals("Alice", contaCriada.getTitular())
        );
    }


}
