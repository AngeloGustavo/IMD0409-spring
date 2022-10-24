package com.jeanlima.springrestapi.service;

import java.math.BigDecimal;

import com.jeanlima.springrestapi.model.Produto;
import com.jeanlima.springrestapi.rest.dto.ProdutoDTO;

public interface ProdutoService {
    Produto salvar( ProdutoDTO dto );
    void atualizaDescricao(Integer id, String descricao);
    void atualizaPreco(Integer id, BigDecimal preco);
}
