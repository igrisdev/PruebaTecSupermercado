package com.igrisdev.PruebaTecSupermercado.service;

import com.igrisdev.PruebaTecSupermercado.dto.DetalleVentaDTO;
import com.igrisdev.PruebaTecSupermercado.dto.SucursalDTO;
import com.igrisdev.PruebaTecSupermercado.dto.VentaDTO;
import com.igrisdev.PruebaTecSupermercado.exception.NotFoundException;
import com.igrisdev.PruebaTecSupermercado.mapper.Mapper;
import com.igrisdev.PruebaTecSupermercado.model.DetalleVenta;
import com.igrisdev.PruebaTecSupermercado.model.Producto;
import com.igrisdev.PruebaTecSupermercado.model.Sucursal;
import com.igrisdev.PruebaTecSupermercado.model.Venta;
import com.igrisdev.PruebaTecSupermercado.repository.ProductoRepository;
import com.igrisdev.PruebaTecSupermercado.repository.SucursalRepository;
import com.igrisdev.PruebaTecSupermercado.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class VentaService implements IVentaService {

    @Autowired
    private VentaRepository ventaRepo;
    @Autowired
    private ProductoRepository productoRepo;
    @Autowired
    private SucursalRepository sucursalRepo;

    @Override
    public List<VentaDTO> traerVentas() {
        List<Venta> ventas = ventaRepo.findAll();
        List<VentaDTO> ventasDto = new ArrayList<>();

        VentaDTO dto;

        for (Venta v : ventas) {
            dto = Mapper.toDTO(v);
            ventasDto.add(dto);
        }

    }

    @Override
    public VentaDTO crearVenta(VentaDTO ventaDto) {
        // validaciones
        if (ventaDto == null) throw new RuntimeException("VentaDTO es null");
        if (ventaDto.getIdSucursal() == null) throw new RuntimeException("Debe indicar la sucursal");
        if (ventaDto.getDetalle() == null || ventaDto.getDetalle().isEmpty())
            throw new RuntimeException("Debe incluir al menos un producto");

        // Buscar la sucursal
        Sucursal suc = sucursalRepo.findById(ventaDto.getIdSucursal()).orElse(null);

        if (suc == null) throw new NotFoundException("Sucursal no encontrada");

        // Crear la venta
        Venta vent = new Venta();
        vent.setFecha(ventaDto.getFecha());
        vent.setEstado(ventaDto.getEstado());
        vent.setSucursal(suc);
        vent.setTotal(ventaDto.getTotal());

        // lista de detalles
        List<DetalleVenta> detalles = new ArrayList<>();

        for (DetalleVentaDTO detDTO : ventaDto.getDetalle()) {
            Producto p = productoRepo.findByNombre(detDTO.getNombreProd()).orElse(null);

            if (p == null)
                throw new RuntimeException("Producto no encontrado: " + detDTO.getNombreProd());

            // crear detalle
            DetalleVenta detalleVenta = new DetalleVenta();
            detalleVenta.setProd(p);
            detalleVenta.setPrecio(detDTO.getPrecio());
            detalleVenta.setCantProd(detDTO.getCantProd());
            detalleVenta.setVenta(vent);

            detalles.add(detalleVenta);
        }

        // seteamos la lista de detalle venta
        vent.setDetalle(detalles);

        // guardamos en db
        ventaRepo.save(vent);

        // mapeo de salida
        VentaDTO ventaSalida = Mapper.toDTO(vent);
        return ventaSalida;
    }

    @Override
    public VentaDTO actualizarVenta(Long id, VentaDTO ventaDto) {
        Venta v = ventaRepo.findById(id).orElse(null);
        if (v == null) throw new NotFoundException("Venta no encontrada");

        if (ventaDto.getFecha() != null) v.setFecha(ventaDto.getFecha());
        if (ventaDto.getEstado() != null) v.setEstado(ventaDto.getEstado());
        if (ventaDto.getTotal() != null) v.setTotal(ventaDto.getTotal());
        if (ventaDto.getIdSucursal() != null) {
            Sucursal suc = sucursalRepo.findById(ventaDto.getIdSucursal()).orElse(null);
            if (suc == null) throw new NotFoundException("Sucursal no encontrada");
            v.setSucursal(suc);
        }
        ventaRepo.save(v);

        VentaDTO ventaSalida = Mapper.toDTO(v);

        return ventaSalida;
    }

    @Override
    public void eliminarVenta(Long id) {
        Venta v = ventaRepo.findById(id).orElse(null);
        if (v == null) throw new NotFoundException("Venta no encontrada");

        ventaRepo.delete(v);
    }
}
