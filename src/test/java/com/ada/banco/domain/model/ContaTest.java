package com.ada.banco.domain.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ContaTest {

    @Test
    void getId() {
        Conta conta = criarConta();

        assertEquals(111L, conta.getId());
    }

    @Test
    void setId() {
        Conta conta = criarConta();

        conta.setId(222L);

        assertEquals(222L, conta.getId());
    }

    @Test
    void getAgencia() {
        Conta conta = criarConta();

        assertEquals(0001L, conta.getAgencia());
    }

    @Test
    void setAgencia() {
        Conta conta = criarConta();

        conta.setAgencia(2222L);

        assertEquals(2222L, conta.getAgencia());
    }

    @Test
    void getDigito() {
        Conta conta = criarConta();

        assertEquals(1234L, conta.getDigito());
    }

    @Test
    void setDigito() {
        Conta conta = criarConta();

        conta.setDigito(4321L);

        assertEquals(4321L, conta.getDigito());
    }

    @Test
    void getSaldo() {
        Conta conta = criarConta();

        assertEquals(new BigDecimal("1000.00"), conta.getSaldo());
    }

    @Test
    void setSaldo() {
        Conta conta = criarConta();

        conta.setSaldo(new BigDecimal("2000.00"));

        assertEquals(new BigDecimal("2000.00"), conta.getSaldo());
    }

    @Test
    void getTitular() {
        Conta conta = criarConta();

        assertEquals("Alice", conta.getTitular());
    }

    @Test
    void setTitular() {
        Conta conta = criarConta();

        conta.setTitular("Pedro");

        assertEquals("Pedro", conta.getTitular());
    }

    @Test
    void getCpf() {
        Conta conta = criarConta();

        assertEquals("12345678911", conta.getCpf());
    }

    @Test
    void setCpf() {
        Conta conta = criarConta();

        conta.setCpf("98765432100");

        assertEquals("98765432100", conta.getCpf());
    }

    @Test
    void debitar() {
        Conta conta = criarConta();

        conta.debitar(new BigDecimal("100.00"));

        assertEquals(new BigDecimal("900.00"), conta.getSaldo());
    }

    @Test
    void creditar() {
        Conta conta = criarConta();

        conta.creditar(new BigDecimal("100.00"));

        assertEquals(new BigDecimal("1100.00"), conta.getSaldo());
    }

    private Conta criarConta(){
        return new Conta(111L, 0001L, 1234L, new BigDecimal("1000.00"), "Alice", "12345678911");
    }
}