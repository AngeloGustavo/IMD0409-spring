package com.jeanlima.springrestapi.service;

import java.util.Optional;

import com.jeanlima.springrestapi.model.Cliente;
import com.jeanlima.springrestapi.rest.dto.ClienteDTO;

public interface ClienteService {
    Cliente salvar( ClienteDTO dto );
    void atualizaNome(Integer id, String nome);
    void atualizaCpf(Integer id, String cpf);
    Optional<Cliente> obterClienteCompleto(Integer id);
}
