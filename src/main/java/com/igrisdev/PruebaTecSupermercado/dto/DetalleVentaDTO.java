package com.igrisdev.PruebaTecSupermercado.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetalleVentaDTO {
    private Long id;
    private String nombreProd;
    private Integer cantProd;
    private Double precio;
    private Double subtotal;
}
