package com.jeanlima.springrestapi.rest.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClienteDTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder 
public class AtualizacaoPrecoProdutoDTO {
    private BigDecimal preco;
}