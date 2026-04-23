package com.igrisdev.PruebaTecSupermercado.repository;

import com.igrisdev.PruebaTecSupermercado.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaRepository extends JpaRepository<Venta, Long> {
}
