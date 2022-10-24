package com.jeanlima.springrestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jeanlima.springrestapi.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto,Integer>{
    
}
