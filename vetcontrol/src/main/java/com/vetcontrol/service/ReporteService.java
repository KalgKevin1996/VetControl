package com.vetcontrol.service;

import java.util.List;

public interface ReporteService {
    long contarMovimientos();
    List<String> obtenerLabelsTiposMovimiento();
    List<Long> obtenerValoresTiposMovimiento();
}
