package com.jeanlima.springrestapi.rest.dto;

import java.util.List;

import com.jeanlima.springrestapi.model.Pedido;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * {
    "nome" : "Angelo",
    "cpf" : "000.000.000-00"
}
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {
    private String nome;
    private String cpf;
    private List<Pedido> pedidos;
}
