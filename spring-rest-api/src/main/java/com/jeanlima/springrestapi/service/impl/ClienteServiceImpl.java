package com.jeanlima.springrestapi.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.jeanlima.springrestapi.exception.RegraNegocioException;
import com.jeanlima.springrestapi.model.Cliente;
import com.jeanlima.springrestapi.repository.ClienteRepository;
import com.jeanlima.springrestapi.rest.dto.ClienteDTO;
import com.jeanlima.springrestapi.service.ClienteService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService{
    private final ClienteRepository repository;

    @Override
    @Transactional
    public Cliente salvar(ClienteDTO dto) {
        Cliente cliente = new Cliente();
        
        String nome = dto.getNome();
        String cpf = dto.getCpf();

        cliente.setCpf(cpf);
        cliente.setNome(nome);

        repository.save(cliente);
        return cliente;
    }
    @Override
    public void atualizaNome(Integer id, String nome) {
        repository
            .findById(id)
            .map( cliente -> {
                cliente.setNome(nome);
                return repository.save(cliente);
            }).orElseThrow(() -> new RegraNegocioException("Código de cliente inválido."));
    }
    @Override
    public void atualizaCpf(Integer id, String cpf) {
        repository
            .findById(id)
            .map( cliente -> {
                cliente.setCpf(cpf);
                return repository.save(cliente);
            }).orElseThrow(() -> new RegraNegocioException("Código de cliente inválido."));
    }
    @Override
    public Optional<Cliente> obterClienteCompleto(Integer id) {
        return repository.findByIdFetchPedidos(id);
    }

    
}
