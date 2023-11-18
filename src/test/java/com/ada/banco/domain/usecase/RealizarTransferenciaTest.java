package com.ada.banco.domain.usecase;

import com.ada.banco.domain.gateway.ContaGateway;
import com.ada.banco.domain.model.Conta;
import com.ada.banco.dummy.ContaGatewayDummyImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class RealizarTransferenciaTest {

    private RealizarTransferencia realizarTransferencia;
    private ContaGateway contaGateway;
    private CriarNovaConta criarNovaConta;

    @BeforeEach
    public void setUp() {
        this.contaGateway = new ContaGatewayDummyImpl();
        this.realizarTransferencia = new RealizarTransferencia(contaGateway);
        this.criarNovaConta = new CriarNovaConta(contaGateway);
    }
    @Test
    void execute_TransferenciaBemSucedida() throws Exception {
        // Arrange
        Conta contaOrigem = criarContaOrigem();
        BigDecimal saldoOrigem = contaOrigem.getSaldo();
        Conta contaDestino = criarContaDestino();
        BigDecimal saldoDestino = contaDestino.getSaldo();

        BigDecimal valor = new BigDecimal(20);

        criarNovaConta.execute(contaOrigem);
        criarNovaConta.execute(contaDestino);

        // Act
        realizarTransferencia.execute(contaOrigem.getId(), contaDestino.getId(), valor);

        // Assert
        assertAll(
                () -> assertEquals((saldoOrigem.subtract(valor)), contaOrigem.getSaldo()),
                () -> assertEquals((saldoDestino.add(valor)), contaDestino.getSaldo())
        );
    }

    @Test
    void execute_ContaOrigemNaoEncontrada_DeveLancarException() throws Exception {
        // Arrange
        Long idContaOrigem = 1L;
        Conta contaDestino = criarContaDestino();
        BigDecimal valor = new BigDecimal(100);

        criarNovaConta.execute(contaDestino);

        // Act + Assert
        assertThrows(Exception.class, () -> realizarTransferencia.execute(idContaOrigem, contaDestino.getId(), valor));
    }

    @Test
    void execute_ContaDestinoNaoEncontrada_DeveLancarException() throws Exception {
        // Arrange
        Conta contaOrigem = criarContaOrigem();
        Long idContaDestino = 2L;
        BigDecimal valor = BigDecimal.valueOf(100);

        criarNovaConta.execute(contaOrigem);

        // Act + Assert
        assertThrows(Exception.class, () -> realizarTransferencia.execute(contaOrigem.getId(), idContaDestino, valor));
    }

    @Test
    void execute_SaldoInsuficiente_DeveLancarException() {
        // Arrange
        Conta contaOrigem = criarContaOrigem();
        Conta contaDestino = criarContaDestino();
        BigDecimal valor = BigDecimal.valueOf(2000);

        // Act + Assert
        assertThrows(Exception.class, () -> realizarTransferencia.execute(contaOrigem.getId(), contaDestino.getId(), valor));
    }

    private Conta criarContaOrigem(){
        return new Conta(111L, 0001L, 1234L, new BigDecimal("1000.00"), "Alice", "12345678911");
    }

    private Conta criarContaDestino(){
        return new Conta(300L, 0001L, 5678L, new BigDecimal("500.00"), "Pedro", "98765432102");
    }
}