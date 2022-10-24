package com.jeanlima.springrestapi.rest.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/*{
    "produto": 1,
    "quantidade": 10
}*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDTO {
    private String descricao;
    private BigDecimal preco;
}
