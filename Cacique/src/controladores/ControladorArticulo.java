/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import abm.ABMArticulo;
import abm.ManejoIp;

import interfaz.ArticuloGui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelos.Articulo;
import modelos.Proveedor;
import net.sf.jasperreports.engine.JRException;
import org.javalite.activejdbc.Base;

/**
 *
 * @author nico
 */
public class ControladorArticulo implements ActionListener, FocusListener {

    private ArticuloGui articuloGui;
    private DefaultTableModel tablaArtDefault;
    private java.util.List<Articulo> listArticulos;
    private java.util.List<Proveedor> listProveedores;
    private JTable tablaArticulos;
    private ABMArticulo abmArticulo;
    private Boolean isNuevo;
    private Boolean editandoInfo;
    private Articulo articulo;

    public ControladorArticulo(ArticuloGui articuloGui) throws JRException, ClassNotFoundException, SQLException {
        isNuevo = true; //para saber si es nuevo o no
        editandoInfo = false; // se esta editando la info
        articulo = new Articulo();
        this.articuloGui = articuloGui;
        this.articuloGui.setActionListener(this);
        tablaArtDefault = articuloGui.getTablaArticulosDefault();
        tablaArticulos = articuloGui.getArticulos();
        listArticulos = new LinkedList();
        listProveedores = new LinkedList();
        abmArticulo = new ABMArticulo();
        abrirBase();
        listArticulos = Articulo.findAll();
        cerrarBase();
        actualizarLista();
       // reporteArticulos = new ControladorJReport("listadoArticulos.jasper");
        articuloGui.getBusqueda().addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                busquedaKeyReleased(evt);
            }
        });
        articuloGui.getArticulos().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });

    }

    public void busquedaKeyReleased(java.awt.event.KeyEvent evt) {
        System.out.println("apreté el caracter: " + evt.getKeyChar());
        realizarBusqueda();
    }

    private void realizarBusqueda() {
        abrirBase();
        if (articuloGui.getFiltroEquiv().isSelected()) {// se esta buscando por los equivalentes a este
                   listArticulos = Articulo.where("equivalencia_1 like ? or equivalencia_2 like ? or equivalencia_3 like ? ", "%" + articuloGui.getBusqueda().getText() + "%", "%" + articuloGui.getBusqueda().getText() + "%", "%" + articuloGui.getBusqueda().getText() + "%");

        }
        else{
                    listArticulos = Articulo.where("codigo like ? or descripcion like ? or marca like ? or id like ? or nombre like ? or id like ?", "%" + articuloGui.getBusqueda().getText() + "%", "%" + articuloGui.getBusqueda().getText() + "%", "%" + articuloGui.getBusqueda().getText() + "%", "%" + articuloGui.getBusqueda().getText() + "%", "%" + articuloGui.getBusqueda().getText() + "%", "%" + articuloGui.getBusqueda().getText() + "%");

        }
        actualizarLista();
        cerrarBase();

    }

    public void cargarTodos() {
        abrirBase();
        listArticulos = Articulo.findAll();
        if (!listArticulos.isEmpty()) {
            realizarBusqueda();
            System.out.println("cargue todo");
        }
        cerrarBase();
    }

    public void tablaMouseClicked(java.awt.event.MouseEvent evt) {
        if (evt.getClickCount() == 2) {
            //bloqueo los campos y habilito botones
            articuloGui.habilitarCampos(false);
            articuloGui.getBorrar().setEnabled(true);
            articuloGui.getModificar().setEnabled(true);
            articuloGui.getGuardar().setEnabled(false);
            articuloGui.getNuevo().setEnabled(true);
            editandoInfo = false;
            articuloGui.limpiarCampos();
            abrirBase();
            articulo = Articulo.findFirst("codigo = ?", tablaArticulos.getValueAt(tablaArticulos.getSelectedRow(), 0));
            articuloGui.CargarCampos(articulo);
            cerrarBase();

        }
    }

    private void actualizarLista() {
        abrirBase();
        tablaArtDefault.setRowCount(0);
        Iterator<Articulo> it = listArticulos.iterator();
        while (it.hasNext()) {
            Articulo art = it.next();
            Object row[] = new String[7];
            row[0] = art.getString("codigo");
            row[1] = art.getString("nombre");
            row[2] = art.getString("marca");
            row[3] = art.getString("descripcion");
            row[4] = art.getBigDecimal("precio_compra").setScale(2, RoundingMode.CEILING).toString();
            row[5] = art.getBigDecimal("precio_venta").setScale(2, RoundingMode.CEILING).toString();
            row[6]= art.getString("id");
            tablaArtDefault.addRow(row);

        }
        articuloGui.getCantidadArticulos().setText(String.valueOf(tablaArticulos.getRowCount()));
        
        cerrarBase();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == articuloGui.getNuevo()) {
            System.out.println("Boton nuevo pulsado");
            articuloGui.limpiarCampos();
            articuloGui.habilitarCampos(true);
            isNuevo = true;
            editandoInfo = true;
            articuloGui.getBorrar().setEnabled(false);
            articuloGui.getModificar().setEnabled(false);
            articuloGui.getGuardar().setEnabled(true);
            cargarProveedores();
            articuloGui.getProveedores().setSelectedItem("");
        }
        
        if (e.getSource() == articuloGui.getFiltroEquiv()) {
            realizarBusqueda();
        }
        
        if (e.getSource() == articuloGui.getGuardar() && editandoInfo && isNuevo) {
            System.out.println("Boton guardar pulsado");
            if (cargarDatosProd(articulo)) {
                abrirBase();
                if (abmArticulo.alta(articulo)) {

                    articuloGui.habilitarCampos(false);
                    articuloGui.limpiarCampos();
                    editandoInfo = false;
                    JOptionPane.showMessageDialog(articuloGui, "¡Artículo guardado exitosamente!");
                    articuloGui.getNuevo().setEnabled(true);
                    articuloGui.getGuardar().setEnabled(false);
                } else {
                    JOptionPane.showMessageDialog(articuloGui, "codigo repetido, no se guardó el artículo", "Error!", JOptionPane.ERROR_MESSAGE);
                }
                cerrarBase();
                realizarBusqueda();
            }
        }
        if (e.getSource() == articuloGui.getBorrar()) {

            System.out.println("Boton borrar pulsado");
            articuloGui.habilitarCampos(false);
            
            
            if (articulo.getString("codigo") != null && !editandoInfo) {
                Integer resp = JOptionPane.showConfirmDialog(articuloGui, "¿Desea borrar el artículo " + articuloGui.getCodigo().getText(), "Confirmar borrado", JOptionPane.YES_NO_OPTION);
                if (resp == JOptionPane.YES_OPTION) {
                    abrirBase();
                    Boolean seBorro = abmArticulo.baja(articulo);
                    cerrarBase();
                    if (seBorro) {
                        JOptionPane.showMessageDialog(articuloGui, "¡Artículo borrado exitosamente!");
                        articuloGui.limpiarCampos();
                        realizarBusqueda();
                        articuloGui.getBorrar().setEnabled(false);
                        articuloGui.getModificar().setEnabled(false);
                    } else {
                        JOptionPane.showMessageDialog(articuloGui, "Ocurrió un error, no se borró el artículo", "Error!", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(articuloGui, "No se seleccionó un artículo");
            }



        }
        if (e.getSource() == articuloGui.getModificar()) {
            System.out.println("Boton modificar pulsado");
            
            articuloGui.habilitarCampos(true);
            articuloGui.getCodigo().setEnabled(false);
            editandoInfo = true;
            isNuevo = false;
            //articuloGui.getCodigo().setEnabled(false);
            articuloGui.getBorrar().setEnabled(false);
            articuloGui.getGuardar().setEnabled(true);
            articuloGui.getModificar().setEnabled(false);
            cargarProveedores();
            articuloGui.getProveedores().setSelectedItem(articulo.getNombreProv());
        }

        if (e.getSource() == articuloGui.getGuardar() && editandoInfo && !isNuevo) {
            System.out.println("Boton guardar pulsado");
            if (cargarDatosProd(articulo)) {
                abrirBase();
                if (abmArticulo.modificar(articulo)) {
                    

                    articuloGui.habilitarCampos(false);
                    articuloGui.limpiarCampos();
                    editandoInfo = false;
                    JOptionPane.showMessageDialog(articuloGui, "¡Artículo modificado exitosamente!");
                    articuloGui.getNuevo().setEnabled(true);
                    articuloGui.getGuardar().setEnabled(false);
                } else {
                    JOptionPane.showMessageDialog(articuloGui, "Ocurrió un error,revise los datos", "Error!", JOptionPane.ERROR_MESSAGE);
                }
                cerrarBase();
                realizarBusqueda();
            }
        }
       /* if (e.getSource() == articuloGui.getExportar()) {
            try {
                reporteArticulos.mostrarReporte();
            } catch (ClassNotFoundException | SQLException | JRException ex) {
                Logger.getLogger(AplicacionGui.class.getName()).log(Level.SEVERE, null, ex);
            }
        }*/
    }

    /*private void actualizarPrecioVenta() {

     try {
     Double precioCompra = Double.valueOf(articuloGui.getPrecioCompra().getText());
     BigDecimal precioVenta = BigDecimal.valueOf(precioCompra * 5.0).setScale(2, RoundingMode.CEILING);
     articuloGui.getPrecioVenta().setText(precioVenta.toString());
     } catch (NumberFormatException | ClassCastException e) {
     }
     }*/
    private void abrirBase() {
        if (!Base.hasConnection()) {
            try{             Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://"+ManejoIp.ipServer+"/cacique", "tecpro", "tecpro");             }catch(Exception e){                 JOptionPane.showMessageDialog(null, "Ocurrió un error, no se realizó la conexión con el servidor, verifique la conexión \n "+e.getMessage(),null,JOptionPane.ERROR_MESSAGE); }
        }
    }

    private void cerrarBase() {
        if (Base.hasConnection()) {
            Base.close();
        }
    }

    private boolean cargarDatosProd(Articulo art) {
        boolean ret = true;
        try {
            String codigo = articuloGui.getCodigo().getText();
            art.set("codigo", codigo);
        } catch (ClassCastException e) {
            ret = false;
            JOptionPane.showMessageDialog(articuloGui, "Error en el codigo", "Error!", JOptionPane.ERROR_MESSAGE);
        }
        try {
            String marca = articuloGui.getMarca().getText();
            art.set("marca", marca);
        } catch (ClassCastException e) {
            ret = false;
            JOptionPane.showMessageDialog(articuloGui, "Error en la marca", "Error!", JOptionPane.ERROR_MESSAGE);
        }
        
                try {
            String nombre = articuloGui.getNombre().getText();
            art.set("nombre", nombre);
        } catch (ClassCastException e) {
            ret = false;
            JOptionPane.showMessageDialog(articuloGui, "Error en el nombre", "Error!", JOptionPane.ERROR_MESSAGE);
        }
        
        try {
            String desc = articuloGui.getDescripcion().getText();
            art.set("descripcion", desc);
        } catch (ClassCastException e) {
            ret = false;
            JOptionPane.showMessageDialog(articuloGui, "Error en la descripcion", "Error!", JOptionPane.ERROR_MESSAGE);
        }
        try {
            Double precioCompra = Double.valueOf(articuloGui.getPrecioCompra().getText());
            art.set("precio_compra", BigDecimal.valueOf(precioCompra).setScale(2, RoundingMode.CEILING));
        } catch (NumberFormatException | ClassCastException e) {
            ret = false;
            JOptionPane.showMessageDialog(articuloGui, "Error en precio de compra", "Error!", JOptionPane.ERROR_MESSAGE);
        }
        try {
            Double precioVenta = Double.valueOf(articuloGui.getPrecioVenta().getText());
            art.set("precio_venta", BigDecimal.valueOf(precioVenta).setScale(2, RoundingMode.CEILING));
        } catch (NumberFormatException | ClassCastException e) {
            ret = false;
            JOptionPane.showMessageDialog(articuloGui, "Error en precio de venta", "Error!", JOptionPane.ERROR_MESSAGE);
        }
        try {
            Float stock= Float.valueOf(articuloGui.getStock().getText());
            art.set("stock_actual", articuloGui.getStock().getText());
            if(stock<0){
                            JOptionPane.showMessageDialog(articuloGui, "Stock negativo", "Error!", JOptionPane.ERROR_MESSAGE);
            ret = false;
            }
                 } catch (NumberFormatException | ClassCastException e) {
            ret = false;
            JOptionPane.showMessageDialog(articuloGui, "Error en el stock", "Error!", JOptionPane.ERROR_MESSAGE);
        }
      
        try {
            Float stock= Float.valueOf(articuloGui.getStockMinimo().getText());
            art.set("stock_minimo", articuloGui.getStockMinimo().getText());
            if(stock<0){
                            JOptionPane.showMessageDialog(articuloGui, "Stock minimo negativo", "Error!", JOptionPane.ERROR_MESSAGE);
            ret = false;
            }
                 } catch (NumberFormatException | ClassCastException e) {
            ret = false;
            JOptionPane.showMessageDialog(articuloGui, "Error en el stock minimo", "Error!", JOptionPane.ERROR_MESSAGE);
        }
        Object prv=articuloGui.getProveedores().getSelectedItem();
        if(prv!=null){
            art.setNombreProv(prv.toString());
        }
        else{
            art.setNombreProv("");
        }
        
                        try {
            String equivalencia1 = articuloGui.getEquivalencia1().getText();
            art.set("equivalencia_1", equivalencia1);
        } catch (ClassCastException e) {
            ret = false;
            JOptionPane.showMessageDialog(articuloGui, "Error en la equivalencia 1", "Error!", JOptionPane.ERROR_MESSAGE);
        }
                                                try {
            String equivalencia2 = articuloGui.getEquivalencia2().getText();
            art.set("equivalencia_2", equivalencia2);
        } catch (ClassCastException e) {
            ret = false;
            JOptionPane.showMessageDialog(articuloGui, "Error en la equivalencia 2", "Error!", JOptionPane.ERROR_MESSAGE);
        }
                                try {
            String equivalencia1 = articuloGui.getEquivalencia3().getText();
            art.set("equivalencia_3", equivalencia1);
        } catch (ClassCastException e) {
            ret = false;
            JOptionPane.showMessageDialog(articuloGui, "Error en la equivalencia 3", "Error!", JOptionPane.ERROR_MESSAGE);
        }
        return ret;
    }

    @Override
    public void focusGained(FocusEvent fe) {
    }

    @Override
    public void focusLost(FocusEvent fe) {
        if (fe.getSource() == articuloGui.getPrecioCompra()) {
            System.out.println("perdi el foco de precio compra");
        }
    }

    private void cargarProveedores() {
        abrirBase();
        articuloGui.getProveedores().removeAllItems();
        listProveedores = Proveedor.findAll();
        Iterator<Proveedor> it = listProveedores.iterator();
        while (it.hasNext()) {
            Proveedor prov = it.next();
            articuloGui.getProveedores().addItem(prov.get("nombre"));
        }
        articuloGui.getProveedores().addItem("");
    }
}
