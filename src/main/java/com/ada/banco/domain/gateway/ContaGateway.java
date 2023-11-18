package com.ada.banco.domain.gateway;

import com.ada.banco.domain.model.Conta;

import java.math.BigDecimal;

public interface ContaGateway {
    Conta buscarPorCpf(String cpf);
    Conta salvar(Conta conta);
    Conta buscarContaPeloId(Long id);
    void atualizar(Conta conta);
}
