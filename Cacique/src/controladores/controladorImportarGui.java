/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import abm.ABMArticulo;
import abm.ABMCliente;
import abm.ABMCompra;
import abm.ABMProveedor;
import abm.ABMVenta;
import abm.ManejoIp;
import interfaz.ImportarExcelGui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelos.Articulo;
import modelos.ArticulosVentas;
import modelos.Cliente;
import modelos.Compra;
import modelos.Pago;
import modelos.Proveedor;
import modelos.Venta;
import net.sf.jasperreports.engine.util.Pair;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.javalite.activejdbc.Base;

/**
 *
 * @author nico
 */
public class controladorImportarGui implements ActionListener {

    ImportarExcelGui importarGui;
    HSSFWorkbook workbook;
    HSSFSheet sheet;
    ABMProveedor abmProveedor;
    ABMCliente abmCliente;
    Proveedor prov;
    Integer agregados;
    Integer modificados;
    private Cliente cliente;
    private Articulo articulo;
    private ABMArticulo abmArticulo;
    private Boolean importando;
    private Venta venta;
    private Compra compra;
    private ABMVenta abmVenta;
    private ABMCompra abmCompra;

    public controladorImportarGui(ImportarExcelGui importarGui) {
        this.importarGui = importarGui;
        this.importarGui.setActionListener(this);
        abmProveedor = new ABMProveedor();
        abmCliente = new ABMCliente();
        abmArticulo = new ABMArticulo();
        abmVenta = new ABMVenta();
        abmCompra = new ABMCompra();
        prov = new Proveedor();
        cliente = new Cliente();
        articulo = new Articulo();
        importando = false;
        cargarProveedores();
        importarGui.getCategoria().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                habilitarProveedores();
            }
        });
    }

    private void habilitarProveedores() {
        if (!importarGui.getCategoria().getSelectedItem().equals("Artículos")) {
            importarGui.getProveedor().setEnabled(false);
        } else {
            cargarProveedores();
            importarGui.getProveedor().setEnabled(true);
        }
    }

    public void cargarProveedores() {
        importarGui.getProveedor().removeAllItems();
        
        List<Proveedor> proveedores = Proveedor.findAll();
        Iterator<Proveedor> it = proveedores.iterator();
        while (it.hasNext()) {
            prov = it.next();
            importarGui.getProveedor().addItem(prov.get("nombre"));
        }
        importarGui.getProveedor().addItem("");
        importarGui.getProveedor().setSelectedItem("");
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == importarGui.getCancelar()) {
            if (importando) {
                int respu = JOptionPane.showConfirmDialog(importarGui, "¿Desea cancelar la importación de datos?", "Cancelar importación", JOptionPane.YES_NO_OPTION);
                if (respu == JOptionPane.YES_OPTION) {
                    importando = false;
                }
            }
            importarGui.setVisible(false);
        }
        if (ae.getSource() == importarGui.getAceptar()) {//Aceptar luego de buscar archivo
            agregados = 0;
            modificados = 0;
            boolean ret=true;
            if(importarGui.getPorcenAutomatico().isSelected()){
            try {
            Double porcentaje = Double.valueOf(importarGui.getPorcentaje().getText());
             
        } catch (NumberFormatException | ClassCastException e) {
            ret = false;
            JOptionPane.showMessageDialog(importarGui, "Error en el porcentaje", "Error!", JOptionPane.ERROR_MESSAGE);
        }
            }
           if(ret){
            if (importarGui.getCategoria().getSelectedItem().equals("Artículos")) {// importar clientes
                System.out.println("boton aceptar pulsado Articulos");
                if (importarGui.getSelectorArchivos().getSelectedFile() != null) {
                    if (importarGui.getSelectorArchivos().getSelectedFile().getName().contains(".xls") || importarGui.getSelectorArchivos().getSelectedFile().getName().contains(".xlsx")) {
                        try {
                            importarExcelArticulos();
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(controladorImportarGui.class.getName()).log(Level.SEVERE, null, ex);
                            JOptionPane.showMessageDialog(importarGui, "Archivo no encontrado", "Error!", JOptionPane.ERROR_MESSAGE);
                        } catch (IOException ex) {
                            Logger.getLogger(controladorImportarGui.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        JOptionPane.showMessageDialog(importarGui, "Archivo incorrecto", "Error!", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

            
            
        }
        }
    }



    
    
    
    
    private void importarExcelArticulos() throws FileNotFoundException, IOException {
        importarGui.getProgreso().setIndeterminate(true);
        
        final javax.swing.SwingWorker worker = new javax.swing.SwingWorker() {
            @Override
            protected Void doInBackground() throws Exception {
                boolean precioVentaAuto= importarGui.getPorcenAutomatico().isSelected();
                if (importarGui.getSelectorArchivos().getSelectedFile().getName().contains("xlsx")) {
                    importando = true;
                    XSSFWorkbook Libro_trabajo = new XSSFWorkbook(importarGui.getSelectorArchivos().getSelectedFile().getAbsolutePath());
                    XSSFSheet hoja = Libro_trabajo.getSheet("IMPORTARHOJA");
                    if (hoja == null) {
                        JOptionPane.showMessageDialog(null, "No se encontró la hoja IMPORTARHOJA, renombrela e intente nuevamente");
                    } else {
                        Iterator iterarFilas = hoja.rowIterator();
                        XSSFCell celdaCodigo;
                        XSSFCell celdaDescripcion;
                        XSSFCell celdaMarca;
                        XSSFCell celdaPrecioCompra;
                        XSSFCell celdaPrecioVenta;
                        XSSFCell celdaNombre;
                        String codigoString;
                        String descripcionString;
                        String marcaString;
                        String precioString;
                        String precioStringVenta;
                        String nombreString;
                        double precioFloat;
                        BigDecimal precioCompraBig;
                        BigDecimal precioVenta;
                        
                        agregados = 0;
                        modificados = 0;
                        iterarFilas.next();
                        while (iterarFilas.hasNext() && importando) {
                            XSSFRow row = (XSSFRow) iterarFilas.next();
                            celdaCodigo = (XSSFCell) row.getCell(0);
                            celdaDescripcion = (XSSFCell) row.getCell(1);
                            celdaMarca = (XSSFCell) row.getCell(2);
                            celdaNombre = (XSSFCell) row.getCell(3);
                            celdaPrecioCompra = (XSSFCell) row.getCell(4);
                            
                            celdaPrecioVenta = (XSSFCell) row.getCell(5);
                            descripcionString = "";
                            marcaString = "";
                            nombreString="";
                            precioCompraBig = new BigDecimal(0);
                            precioVenta = new BigDecimal(0);
                            
                            if (celdaCodigo != null) {
                                if (!celdaCodigo.toString().isEmpty()) {
                                    celdaCodigo.setCellType(Cell.CELL_TYPE_STRING);
                                    codigoString = celdaCodigo.getStringCellValue();
                                    if (celdaDescripcion != null) {
                                        celdaDescripcion.setCellType(Cell.CELL_TYPE_STRING);
                                        descripcionString = celdaDescripcion.toString();
                                    }
                                    if (celdaMarca != null) {
                                        celdaMarca.setCellType(Cell.CELL_TYPE_STRING);
                                        marcaString = celdaMarca.toString();
                                    }
                                    if (celdaNombre != null) {
                                        celdaNombre.setCellType(Cell.CELL_TYPE_STRING);
                                        nombreString = celdaNombre.toString();
                                    }
                                    if (celdaPrecioCompra != null) {
                                        celdaPrecioCompra.setCellType(Cell.CELL_TYPE_STRING);
                                        precioString = celdaPrecioCompra.toString();
                                        precioFloat = Float.parseFloat(precioString);
                                        precioCompraBig = BigDecimal.valueOf(precioFloat).setScale(2, RoundingMode.CEILING);

                                    }
                                    if(!precioVentaAuto){
                                    if (celdaPrecioVenta != null) {
                                        celdaPrecioVenta.setCellType(Cell.CELL_TYPE_STRING);
                                        precioStringVenta = celdaPrecioVenta.toString();
                                        precioFloat = Float.parseFloat(precioStringVenta);
                                        precioVenta = BigDecimal.valueOf(precioFloat).setScale(2, RoundingMode.CEILING);

                                    }
                                    }
                                    else{
                                        BigDecimal porcen= BigDecimal.valueOf(Double.valueOf(importarGui.getPorcentaje().getText()));
                                        precioVenta= new BigDecimal(0);
                                        precioVenta= (porcen.multiply(precioCompraBig)).divide(new BigDecimal(100));
                                        precioVenta= precioVenta.add(precioCompraBig);
                                    }

                                    articulo.set("codigo", codigoString, "descripcion", descripcionString, "marca", marcaString, "precio_compra", precioCompraBig, "precio_venta", precioVenta, "nombre", nombreString, "stock_actual", 0, "stock_minimo", 0);
                                    if (abmArticulo.findArticulo(articulo)) {
                                        System.out.println(abmArticulo.modificar(articulo));
                                        if (!importarGui.getProveedor().getSelectedItem().equals("")) {
                                            prov.set("nombre", importarGui.getProveedor().getSelectedItem());
                                            prov = abmProveedor.getProveedor(prov);
                                            prov.add(articulo);
                                        } else {
                                            articulo.set("proveedor_id", null);
                                        }
                                        System.out.println("modificando articulo"+precioVenta);
                                        
                                    } else {
                                        abmArticulo.alta(articulo);
                                        if (!importarGui.getProveedor().getSelectedItem().equals("")) {
                                            prov.set("nombre", importarGui.getProveedor().getSelectedItem());
                                            prov = abmProveedor.getProveedor(prov);
                                            prov.add(articulo);
                                        }
                                        System.out.println("nuevo articulo");
                                        agregados++;
                                    }
                                }
                            }
                        }
                    }
                } else if (importarGui.getSelectorArchivos().getSelectedFile().getName().contains("xls")) {
                    importando = true;
                    precioVentaAuto= importarGui.getPorcenAutomatico().isSelected();
                    workbook = new HSSFWorkbook(new FileInputStream(importarGui.getSelectorArchivos().getSelectedFile().getAbsolutePath()));
                    sheet = workbook.getSheet("IMPORTARHOJA");
                    if (sheet == null) {
                        JOptionPane.showMessageDialog(null, "No se encontró la hoja IMPORTARHOJA, renombrela e intente nuevamente");
                    } else {
                        Iterator<Row> iterarFilas = sheet.iterator();
                        Cell celdaCodigo;
                        Cell celdaDescripcion;
                        Cell celdaMarca;
                        Cell celdaPrecioCompra;
                        Cell celdaPrecioVenta;
                        Cell celdaNombre;
                        String codigoString;
                        String descripcionString;
                        String marcaString;
                        String precioString;
                        String nombreString;
                        double precioFloat;
                        BigDecimal precioCompraBig;
                        BigDecimal precioVenta;
                        
                        agregados = 0;
                        modificados = 0;
                        iterarFilas.next();
                        int j=0;
                        while (iterarFilas.hasNext() && importando) {
                            
                            Row row = iterarFilas.next();
                            j++;
                            celdaCodigo = row.getCell(0);
                            celdaDescripcion = row.getCell(1);
                            celdaMarca = row.getCell(2);
                            celdaPrecioCompra = row.getCell(4);
                            celdaPrecioVenta = row.getCell(5);
                            celdaNombre = row.getCell(3);
                            descripcionString = "";
                            marcaString = "";
                            precioCompraBig = new BigDecimal(0);
                            precioVenta = new BigDecimal(0);
                            nombreString = "";

                            if (celdaCodigo != null) {
                                if (!celdaCodigo.toString().isEmpty()) {
                                    celdaCodigo.setCellType(Cell.CELL_TYPE_STRING);
                                    codigoString = celdaCodigo.getStringCellValue();
                                    if (celdaDescripcion != null) {
                                        celdaDescripcion.setCellType(Cell.CELL_TYPE_STRING);
                                        descripcionString = celdaDescripcion.getStringCellValue();
                                    }
                                    if (celdaMarca != null) {
                                        celdaMarca.setCellType(Cell.CELL_TYPE_STRING);
                                        marcaString = celdaMarca.getStringCellValue();
                                    }
                                    if (celdaPrecioCompra != null) {
                                        celdaPrecioCompra.setCellType(Cell.CELL_TYPE_STRING);
                                        precioString = celdaPrecioCompra.getStringCellValue();
                                        precioFloat = Float.parseFloat(precioString);
                                        precioCompraBig = BigDecimal.valueOf(precioFloat).setScale(2, RoundingMode.CEILING);

                                    }
                                    
                                    
                                                                        if(!precioVentaAuto){
                                    if (celdaPrecioVenta != null) {
                                        celdaPrecioVenta.setCellType(Cell.CELL_TYPE_STRING);
                                        precioString = celdaPrecioVenta.toString();
                                        precioFloat = Float.parseFloat(precioString);
                                        precioVenta = BigDecimal.valueOf(precioFloat).setScale(2, RoundingMode.CEILING);

                                    } }
                                    else{
                                        BigDecimal porcen= BigDecimal.valueOf(Double.valueOf(importarGui.getPorcentaje().getText()));
                                        precioVenta= new BigDecimal(0);
                                        precioVenta= (porcen.multiply(precioCompraBig)).divide(new BigDecimal(100));
                                        precioVenta= precioVenta.add(precioCompraBig);
                                    }

                                    
   
                                    if (celdaNombre != null) {
                                        celdaNombre.setCellType(Cell.CELL_TYPE_STRING);
                                        nombreString = celdaNombre.getStringCellValue();
                                    }
                                    
                                    System.out.println(j);
                                    articulo.set("codigo", codigoString, "descripcion", descripcionString, "marca", marcaString, "precio_compra", precioCompraBig, "precio_venta", precioVenta, "nombre", nombreString, "stock_actual", 0, "stock_minimo", 0);
                                    if (abmArticulo.findArticulo(articulo)) {
                                        abmArticulo.modificar(articulo);
                                        if (!importarGui.getProveedor().getSelectedItem().equals("")) {
                                            prov.set("nombre", importarGui.getProveedor().getSelectedItem());
                                            prov = abmProveedor.getProveedor(prov);
                                            prov.add(articulo);
                                        } else {
                                            articulo.set("proveedor_id", null);
                                        }
                                        System.out.println("modificando articulo");
                                    } else {
                                        abmArticulo.alta(articulo);
                                        if (!importarGui.getProveedor().getSelectedItem().equals("")) {
                                            prov.set("nombre", importarGui.getProveedor().getSelectedItem());
                                            prov = abmProveedor.getProveedor(prov);
                                            prov.add(articulo);
                                        }
                                        System.out.println("nuevo articulo");
                                        agregados++;
                                    }
                                }
                            }
                        }
                    }
                }
                return null;
            }

            protected void done() {
                setProgress(100);
                JOptionPane.showMessageDialog(importarGui, "Importación de registros terminada, se han agregado " + agregados + " Articulos");
                importarGui.getProgreso().setIndeterminate(false);
                importando = false;
            }
        };
        worker.execute();
    }

}
