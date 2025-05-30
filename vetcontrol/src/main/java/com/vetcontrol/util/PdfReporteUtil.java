package com.vetcontrol.util;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;

import java.awt.*;
import java.io.IOException;
import java.util.List;

public class PdfReporteUtil {

    public static void exportarResumen(HttpServletResponse response,
                                       long totalProductos,
                                       long activos,
                                       long inactivos,
                                       List<Object[]> topIngresos,
                                       List<Object[]> topSalidas) throws IOException {

        Document documento = new Document(PageSize.A4);
        PdfWriter.getInstance(documento, response.getOutputStream());

        documento.open();

        Font tituloFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, Color.BLUE);
        Font seccionFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14, Color.BLACK);

        Paragraph titulo = new Paragraph("Resumen de Reportes de Inventario", tituloFont);
        titulo.setAlignment(Paragraph.ALIGN_CENTER);
        documento.add(titulo);
        documento.add(new Paragraph(" ")); // espacio

        // Sección resumen
        documento.add(new Paragraph("Resumen de Productos", seccionFont));
        PdfPTable tablaResumen = new PdfPTable(2);
        tablaResumen.setWidthPercentage(100);
        tablaResumen.setSpacingBefore(10f);
        tablaResumen.addCell("Total de Productos");
        tablaResumen.addCell(String.valueOf(totalProductos));
        tablaResumen.addCell("Productos Activos");
        tablaResumen.addCell(String.valueOf(activos));
        tablaResumen.addCell("Productos Inactivos");
        tablaResumen.addCell(String.valueOf(inactivos));
        documento.add(tablaResumen);

        documento.add(new Paragraph(" "));

        // Top 5 ingresos
        documento.add(new Paragraph("Top 5 Productos con más Ingresos", seccionFont));
        PdfPTable tablaIngresos = new PdfPTable(2);
        tablaIngresos.setWidthPercentage(100);
        tablaIngresos.setSpacingBefore(10f);
        tablaIngresos.addCell("Producto");
        tablaIngresos.addCell("Cantidad");

        for (Object[] fila : topIngresos) {
            tablaIngresos.addCell(fila[0].toString());
            tablaIngresos.addCell(fila[1].toString());
        }
        documento.add(tablaIngresos);

        documento.add(new Paragraph(" "));

        // Top 5 salidas
        documento.add(new Paragraph("Top 5 Productos con más Salidas", seccionFont));
        PdfPTable tablaSalidas = new PdfPTable(2);
        tablaSalidas.setWidthPercentage(100);
        tablaSalidas.setSpacingBefore(10f);
        tablaSalidas.addCell("Producto");
        tablaSalidas.addCell("Cantidad");

        for (Object[] fila : topSalidas) {
            tablaSalidas.addCell(fila[0].toString());
            tablaSalidas.addCell(fila[1].toString());
        }
        documento.add(tablaSalidas);

        documento.close();
    }
}
