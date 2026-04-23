package com.igrisdev.PruebaTecSupermercado.repository;

import com.igrisdev.PruebaTecSupermercado.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
