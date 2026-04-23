package com.igrisdev.PruebaTecSupermercado.repository;

import com.igrisdev.PruebaTecSupermercado.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    // buscar producto por nombre
    Optional<Producto> findByNombre(String nombre);
}
