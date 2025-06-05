package com.vetcontrol.service.impl;

import com.vetcontrol.entity.TipoMovimiento;
import com.vetcontrol.repository.MovimientoInventarioRepository;
import com.vetcontrol.repository.ProductoRepository;
import com.vetcontrol.service.ReporteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReporteServiceImpl implements ReporteService {
    private final MovimientoInventarioRepository movimientoInventarioRepository;


    private final MovimientoInventarioRepository movimientoRepo;
    private final ProductoRepository productoRepository;

    @Override
    public long contarMovimientos() {
        return movimientoRepo.count();
    }

    @Override
    public List<String> obtenerLabelsTiposMovimiento() {
        return Arrays.stream(TipoMovimiento.values())
                .map(Enum::name)
                .toList();
    }

    @Override
    public List<Long> obtenerValoresTiposMovimiento() {
        return Arrays.stream(TipoMovimiento.values())
                .map(tipo -> movimientoRepo.countByTipoMovimiento(tipo))
                .toList();
    }

    @Override
    public long contarProductos() {
        return productoRepository.count();
    }

    @Override
    public long contarProductosActivos() {
        return productoRepository.countByActivoTrue();
    }

    @Override
    public long contarProductosInactivos() {
        return productoRepository.countByActivoFalse();
    }

    @Override
    public List<Object[]> topProductosIngresos() {
        return movimientoRepo.top5ProductosPorTipoMovimiento(TipoMovimiento.ENTRADA);
    }

    @Override
    public List<Object[]> topProductosSalidas() {
        return movimientoRepo.top5ProductosPorTipoMovimiento(TipoMovimiento.SALIDA);
    }

    @Override
    public List<Object[]> obtenerDatosPorTipoMovimiento(TipoMovimiento tipoMovimiento) {
        return movimientoRepo.top5ProductosPorTipoMovimiento(tipoMovimiento);

    }

}
