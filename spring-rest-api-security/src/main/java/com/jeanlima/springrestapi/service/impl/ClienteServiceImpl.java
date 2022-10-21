package com.jeanlima.springrestapi.service.impl;

import org.springframework.stereotype.Service;

import com.jeanlima.springrestapi.model.Cliente;
import com.jeanlima.springrestapi.rest.dto.ClienteDTO;
import com.jeanlima.springrestapi.service.ClienteService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService{@Override
    public Cliente salvar(ClienteDTO dto) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void atualizaCliente(Integer id, ClienteDTO dto) {
        Integer idCliente = dto.getCliente();
        Cliente cliente = clientesRepository
                .findById(idCliente)
                .orElseThrow(() -> new RegraNegocioException("Código de cliente inválido."));

        Pedido pedido = new Pedido();
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);
        pedido.setStatus(StatusPedido.REALIZADO);

        List<ItemPedido> itemsPedido = converterItems(pedido, dto.getItems());
        repository.save(pedido);
        itemsPedidoRepository.saveAll(itemsPedido);
        pedido.setItens(itemsPedido);
        return pedido;        
    }
    
}
