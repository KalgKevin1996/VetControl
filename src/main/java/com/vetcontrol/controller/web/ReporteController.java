package com.vetcontrol.controller.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vetcontrol.entity.TipoMovimiento;
import com.vetcontrol.service.ReporteService;
import com.vetcontrol.util.PdfReporteUtil;
import com.vetcontrol.util.ReporteExcelExporter;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/reportes")
@RequiredArgsConstructor
public class ReporteController {

    private final ReporteService reporteService;

    @GetMapping("/dashboard")
    public String mostrarDashboard(Model model) throws JsonProcessingException {
        model.addAttribute("totalMovimientos", reporteService.contarMovimientos());

        ObjectMapper mapper = new ObjectMapper();
        model.addAttribute("labels", mapper.writeValueAsString(reporteService.obtenerLabelsTiposMovimiento()));
        model.addAttribute("values", mapper.writeValueAsString(reporteService.obtenerValoresTiposMovimiento()));
        model.addAttribute("totalProductos", reporteService.contarProductos());
        model.addAttribute("activos", reporteService.contarProductosActivos());
        model.addAttribute("inactivos", reporteService.contarProductosInactivos());
        List<Object[]> topIngresos = reporteService.topProductosIngresos();
        model.addAttribute("topIngresosNombres", mapper.writeValueAsString(topIngresos.stream().map(d -> d[0]).toList()));
        model.addAttribute("topIngresosValores", mapper.writeValueAsString(topIngresos.stream().map(d -> d[1]).toList()));
        List<Object[]> topSalidas = reporteService.topProductosSalidas();
        model.addAttribute("topSalidasNombres", mapper.writeValueAsString(topSalidas.stream().map(d -> d[0]).toList()));
        model.addAttribute("topSalidasValores", mapper.writeValueAsString(topSalidas.stream().map(d -> d[1]).toList()));


        return "reportes/dashboard";
    }
    @GetMapping("/exportar/pdf")
    public void exportarReportePDF(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=reporte_resumen.pdf");

        long total = reporteService.contarProductos();
        long activos = reporteService.contarProductosActivos();
        long inactivos = reporteService.contarProductosInactivos();
        List<Object[]> topIngresos = reporteService.topProductosIngresos();
        List<Object[]> topSalidas = reporteService.topProductosSalidas();

        PdfReporteUtil.exportarResumen(response, total, activos, inactivos, topIngresos, topSalidas);
    }

    @GetMapping("/exportar-excel/{tipo}")
    public void exportarExcel(@PathVariable("tipo") String tipo,
                              HttpServletResponse response) throws IOException {
        TipoMovimiento tipoMovimiento = TipoMovimiento.valueOf(tipo.toUpperCase());
        List<Object[]> datos = reporteService.obtenerDatosPorTipoMovimiento(tipoMovimiento);

        ByteArrayInputStream stream = ReporteExcelExporter.exportarMovimientos(datos, tipoMovimiento);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=reporte_" + tipo.toLowerCase() + ".xlsx");

        IOUtils.copy(stream, response.getOutputStream());
    }





}
