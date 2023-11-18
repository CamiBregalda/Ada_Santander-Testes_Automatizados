package com.ada.banco.domain.usecase;

import com.ada.banco.domain.gateway.ContaGateway;
import com.ada.banco.domain.model.Conta;

import java.math.BigDecimal;

public class RealizarTransferencia {

    private ContaGateway contaGateway;

    public RealizarTransferencia(ContaGateway contaGateway) {
        this.contaGateway = contaGateway;
    }

    public void execute(Long idContaOrigem, Long idContaDestino, BigDecimal valor) throws Exception {
        Conta contaOrigem = contaGateway.buscarContaPeloId(idContaOrigem);
        Conta contaDestino = contaGateway.buscarContaPeloId(idContaDestino);

        if (contaOrigem == null) {
            throw new Exception("Conta de origem não encontrada");
        }

        if (contaDestino == null) {
            throw new Exception("Conta de destino não encontrada");
        }

        if (contaOrigem.getSaldo().compareTo(valor) < 0) {
            throw new Exception("Saldo insuficiente na conta de origem");
        }

        contaOrigem.debitar(valor);
        contaDestino.creditar(valor);

        contaGateway.salvar(contaOrigem);
        contaGateway.salvar(contaDestino);
    }
}
