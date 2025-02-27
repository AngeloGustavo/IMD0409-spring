package com.jeanlima.springrestapi.rest.controllers;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.jeanlima.springrestapi.model.Cliente;
import com.jeanlima.springrestapi.model.ItemPedido;
import com.jeanlima.springrestapi.model.Pedido;
import com.jeanlima.springrestapi.repository.ClienteRepository;
import com.jeanlima.springrestapi.repository.PedidoRepository;
import com.jeanlima.springrestapi.rest.dto.AtualizacaoCpfClienteDTO;
import com.jeanlima.springrestapi.rest.dto.AtualizacaoNomeClienteDTO;
import com.jeanlima.springrestapi.rest.dto.ClienteDTO;
import com.jeanlima.springrestapi.rest.dto.InformacaoItemPedidoDTO;
import com.jeanlima.springrestapi.rest.dto.InformacoesClienteDTO;
import com.jeanlima.springrestapi.rest.dto.InformacoesPedidoDTO;
import com.jeanlima.springrestapi.service.ClienteService;

@RequestMapping("/api/clientes")
@RestController //anotação especializadas de controller - todos já anotados com response body!
public class ClienteController {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private ClienteService service;

    /*@GetMapping("{id}")
    public Cliente getClienteById( @PathVariable Integer id ){
        return repository
                .findById(id)
                .orElseThrow(() -> //se nao achar lança o erro!
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Cliente não encontrado"));
    }*/


    @GetMapping("{id}")
    public InformacoesClienteDTO getClienteById( @PathVariable Integer id ){
        return service
                .obterClienteCompleto(id)
                .map( p -> converter(p) )
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado."));
    }
    private InformacoesClienteDTO converter(Cliente cliente) {
        return InformacoesClienteDTO
                .builder()
                .id(cliente.getId())
                .cpf(cliente.getCpf())
                .nome(cliente.getNome())
                .pedidos(converter(cliente.getPedidos()))
                .build();
    }

    private List<InformacoesPedidoDTO> converter(List<Pedido> pedidos){
        PedidoController a = new PedidoController();
        if(CollectionUtils.isEmpty(pedidos)){
            return Collections.emptyList();
        }
        return pedidos.stream().map(
            pedido -> InformacoesPedidoDTO
            .builder()
            .codigo(pedido.getId())
            .dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
            .cpf(pedido.getCliente().getCpf())
            .nomeCliente(pedido.getCliente().getNome())
            .total(pedido.getTotal())
            .status(pedido.getStatus().name())
            .items(converter2(pedido.getItens()))
            .build()
        ).collect(Collectors.toList());
    }

    private List<InformacaoItemPedidoDTO> converter2(List<ItemPedido> itens){
        if(CollectionUtils.isEmpty(itens)){
            return Collections.emptyList();
        }
        return itens.stream().map(
                item -> InformacaoItemPedidoDTO
                            .builder()
                            .descricaoProduto(item.getProduto().getDescricao())
                            .precoUnitario(item.getProduto().getPreco())
                            .quantidade(item.getQuantidade())
                            .build()
        ).collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public int save( @RequestBody ClienteDTO dto ){
        Cliente cliente = service.salvar(dto);
        return cliente.getId();
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete( @PathVariable Integer id ){
        repository.findById(id)
                .map( cliente -> {
                    repository.delete(cliente );
                    return cliente;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Cliente não encontrado") );

    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update( @PathVariable Integer id,
                        @RequestBody Cliente cliente ){
        repository
                .findById(id)
                .map( clienteExistente -> {
                    cliente.setId(clienteExistente.getId());
                    repository.save(cliente);
                    return clienteExistente;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Cliente não encontrado") );
    }

    @PatchMapping("{id}/atualizaNome/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateNome(@PathVariable Integer id ,
                             @RequestBody AtualizacaoNomeClienteDTO dto){
        String novoNome = dto.getNome();
        service.atualizaNome(id, novoNome);
    }
    @PatchMapping("{id}/atualizaCpf/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCpf(@PathVariable Integer id ,
                             @RequestBody AtualizacaoCpfClienteDTO dto){
        String novoCpf = dto.getCpf();
        service.atualizaCpf(id, novoCpf);
    }

    @GetMapping
    public List<Cliente> find( Cliente filtro ){
        ExampleMatcher matcher = ExampleMatcher
                                    .matching()
                                    .withIgnoreCase()
                                    .withStringMatcher(
                                            ExampleMatcher.StringMatcher.CONTAINING );

        Example example = Example.of(filtro, matcher);
        return repository.findAll(example);
    }

}
