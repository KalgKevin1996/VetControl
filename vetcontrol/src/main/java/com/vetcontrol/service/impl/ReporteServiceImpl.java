package com.vetcontrol.service.impl;

import com.vetcontrol.entity.TipoMovimiento;
import com.vetcontrol.repository.MovimientoInventarioRepository;
import com.vetcontrol.service.ReporteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReporteServiceImpl implements ReporteService {

    private final MovimientoInventarioRepository movimientoRepo;

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
}
