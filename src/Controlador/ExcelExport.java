/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author luisj
 */
public class ExcelExport {

    public ExcelExport() {

    }

    public void exportarTabla(String ruta, javax.swing.JTable tablaDatos) {
        boolean guardado = false;
        String rutaArchivo = ruta + ".xlsx";
        try {
            Workbook wb = new XSSFWorkbook();
            Sheet sheet = wb.createSheet("customer");
            Row rowCol = sheet.createRow(0);
            for (int i = 0; i < tablaDatos.getColumnCount(); i++){
                Cell cell = rowCol.createCell(i);
                cell.setCellValue(tablaDatos.getColumnName(i));
            }
            for(int j = 0; j < tablaDatos.getRowCount(); j++){
                Row row = sheet.createRow(j+1);
                for(int k = 0; k < tablaDatos.getColumnCount(); k++){
                    Cell cell = row.createCell(k);
                    if(tablaDatos.getValueAt(j, k) != null){
                        cell.setCellValue(tablaDatos.getValueAt(j, k).toString());
                    }
                }
            }
           java.io.FileOutputStream out = new java.io.FileOutputStream(new java.io.File(rutaArchivo));
           wb.write(out);
           wb.close();
           out.close();
           javax.swing.JOptionPane.showMessageDialog(null, "Datos exportados con éxito", "Exportar", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        } catch (java.io.FileNotFoundException ex) {
            System.out.print(ex.getMessage());
            guardado = false;
        } catch (java.io.IOException io) {
            System.out.print(io.getMessage());
            guardado = false;
        }

        
    }
}
