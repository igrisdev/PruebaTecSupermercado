package com.igrisdev.PruebaTecSupermercado.controller;

import com.igrisdev.PruebaTecSupermercado.dto.VentaDTO;
import com.igrisdev.PruebaTecSupermercado.service.IVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    @Autowired
    private IVentaService ventaService;

    @GetMapping
    public ResponseEntity<List<VentaDTO>> traerVentas() {
        return ResponseEntity.ok(ventaService.traerVentas());
    }

    @PostMapping
    public ResponseEntity<VentaDTO> crearVenta(@RequestBody VentaDTO dto) {
        VentaDTO creado = ventaService.crearVenta(dto);

        return ResponseEntity.created(URI.create("/api/ventas/" + creado.getId())).body(creado);
    }

    @PutMapping("/{id}")
    public VentaDTO actualizarVenta(@PathVariable Long id, @RequestBody VentaDTO dto) {
        return ventaService.actualizarVenta(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarVenta(@PathVariable Long id) {
        ventaService.eliminarVenta(id);
        return ResponseEntity.noContent().build();
    }
}
