package com.vetcontrol.util;

import com.vetcontrol.entity.TipoMovimiento;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
public class ReporteExcelExporter {

    public static ByteArrayInputStream exportarMovimientos(List<Object[]> datos, TipoMovimiento tipoMovimiento) throws IOException {
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Movimientos - " + tipoMovimiento.name());

            Row headerRow = sheet.createRow(0);
            String[] columnas = {"Producto", "Cantidad de movimientos"};
            for (int i = 0; i < columnas.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columnas[i]);
            }

            int fila = 1;
            for (Object[] filaDato : datos) {
                Row row = sheet.createRow(fila++);
                row.createCell(0).setCellValue(filaDato[0].toString());
                row.createCell(1).setCellValue(Long.parseLong(filaDato[1].toString()));
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }
}