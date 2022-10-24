package com.jeanlima.springrestapi.service.impl;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.jeanlima.springrestapi.exception.RegraNegocioException;
import com.jeanlima.springrestapi.model.Produto;
import com.jeanlima.springrestapi.repository.ProdutoRepository;
import com.jeanlima.springrestapi.rest.dto.ProdutoDTO;
import com.jeanlima.springrestapi.service.ProdutoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProdutoServiceImpl implements ProdutoService{
    private final ProdutoRepository repository;

    @Override
    @Transactional
    public Produto salvar(ProdutoDTO dto) {
        Produto produto = new Produto();

        produto.setDescricao(dto.getDescricao());
        produto.setPreco(dto.getPreco());

        repository.save(produto);
        return produto;
    }
    @Override
    public void atualizaDescricao(Integer id, String descricao) {
        repository
            .findById(id)
            .map( produto -> {
                produto.setDescricao(descricao);
                return repository.save(produto);
            }).orElseThrow(() -> new RegraNegocioException("Código de produto inválido."));
    }
    @Override
    public void atualizaPreco(Integer id, BigDecimal preco) {
        repository
            .findById(id)
            .map( produto -> {
                produto.setPreco(preco);
                return repository.save(produto);
            }).orElseThrow(() -> new RegraNegocioException("Código de produto inválido."));
    }
}
