package com.igrisdev.PruebaTecSupermercado.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate fecha;
    private String estado;
    private Double total;
    @ManyToOne
    private  Sucursal sucursal;

    @OneToMany(mappedBy = "venta")
    private List<DetalleVenta> detalle = new ArrayList<>();
}
