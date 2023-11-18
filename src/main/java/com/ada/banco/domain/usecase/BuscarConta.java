package com.ada.banco.domain.usecase;

import com.ada.banco.domain.gateway.ContaGateway;
import com.ada.banco.domain.model.Conta;

import java.math.BigDecimal;

public class BuscarConta {

    private ContaGateway contaGateway;

    public BuscarConta(ContaGateway contaGateway) {
        this.contaGateway = contaGateway;
    }

    public Conta execute(Long id) {
        Conta conta = contaGateway.buscarContaPeloId(id);

        if (conta == null) {
            throw new RuntimeException("Conta não encontrada");
        }

        return conta;
    }
}
