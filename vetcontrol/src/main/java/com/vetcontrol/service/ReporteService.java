package com.vetcontrol.service;

import java.util.List;

public interface ReporteService {
    List<Object[]> topProductosSalidas();

    List<Object[]> topProductosIngresos();

    public long contarProductosActivos();
    public long contarProductosInactivos();

    long contarProductos();

    long contarMovimientos();
    List<String> obtenerLabelsTiposMovimiento();
    List<Long> obtenerValoresTiposMovimiento();
}
