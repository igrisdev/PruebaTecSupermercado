package com.igrisdev.PruebaTecSupermercado.service;

import com.igrisdev.PruebaTecSupermercado.dto.SucursalDTO;

import java.util.List;

public interface ISucursalService {
    List<SucursalDTO> traerSucursales();

    SucursalDTO crearSucursal(SucursalDTO sucursalDto);

    SucursalDTO actualizarSucursal(Long id, SucursalDTO sucursalDto);

    void eliminarSucursal(Long id);
}
