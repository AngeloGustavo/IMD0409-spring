package com.jeanlima.springrestapi.rest.dto;

import com.jeanlima.springrestapi.model.Produto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstoqueDTO {
    private Produto produto;
    private Integer quantide;
}
