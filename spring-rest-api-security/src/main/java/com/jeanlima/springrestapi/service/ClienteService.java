package com.jeanlima.springrestapi.service;

import com.jeanlima.springrestapi.model.Cliente;
import com.jeanlima.springrestapi.rest.dto.ClienteDTO;

public interface ClienteService {
    Cliente salvar( ClienteDTO dto );
    void atualizaCliente(Integer id, ClienteDTO dto);
}
