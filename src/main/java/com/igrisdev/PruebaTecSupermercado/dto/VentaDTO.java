package com.igrisdev.PruebaTecSupermercado.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VentaDTO {
    // datos de la venta
    private Long id;
    private LocalDate fecha;
    private String estado;

    // datos de la sucursal
    private Long idSucursal;

    // lista de detalles
    private List<DetalleVentaDTO> detalle;

    // total de la venta
    private Double total;
}
