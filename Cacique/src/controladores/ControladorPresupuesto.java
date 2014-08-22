/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import abm.ABMPresupuesto;
import busqueda.Busqueda;
import interfaz.AplicacionGui;
import interfaz.PresupuestoGui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableModel;
import modelos.Articulo;
import modelos.Cliente;
import modelos.Presupuesto;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.util.Pair;
/**
 *
 * @author jacinto
 */
public class ControladorPresupuesto implements ActionListener, CellEditorListener {

    private JTextField textan;
    private JTextField textFram;
    private JTextField textcodprod;
    private List prodlista;
    private List clientelista;
    private Busqueda busqueda;
    private ABMPresupuesto abmPresupuesto;
    private PresupuestoGui PresupuestoGui;
    private JTable tablap;
    private JTable tablac;
    private DefaultTableModel tablaClientes;
    private DefaultTableModel tablaProd;
    private JTable tablafac;
//    private ControladorJReport reporteFactura;
//    private ControladorJReport reporteFacturaSinPagar;
    private AplicacionGui apgui;

    public ControladorPresupuesto(PresupuestoGui presupuestoGui, AplicacionGui apgui) throws JRException, ClassNotFoundException, SQLException {
        this.apgui = apgui;
        prodlista = new LinkedList<Articulo>();
        clientelista = new LinkedList<Cliente>();
        busqueda = new Busqueda();
        abmPresupuesto = new ABMPresupuesto();
        this.PresupuestoGui = presupuestoGui;       
        tablap = presupuestoGui.getTablaArticulos();
        tablap.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaProdMouseClicked(evt);
            }
        });
        tablac = presupuestoGui.getTablaClientes();
        tablac.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaClienteMouseClicked(evt);
            }
        });
        textan = presupuestoGui.getBusquedaNombre();
        textan.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                busquedaClienteKeyReleased(evt);
            }
        });
        tablafac = presupuestoGui.getTablaFactura();
        textcodprod = presupuestoGui.getBusquedaCodigoArticulo();
        textcodprod.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                busquedaProductoKeyReleased(evt);
            }
        });
        tablaClientes = presupuestoGui.getTablaClientesDefault();
        tablaProd = presupuestoGui.getTablaArticulosDefault();
        clientelista = busqueda.filtroCliente("", "");
        prodlista = busqueda.filtroProducto("", "");
        actualizarListaCliente();
        actualizarListaProd();
         this.PresupuestoGui.setActionListener(this);

    }

    private void busquedaClienteKeyReleased(KeyEvent evt) {
        actualizarListaCliente();
    }

    private void busquedaProductoKeyReleased(KeyEvent evt) {
        actualizarListaProd();
    }

    private void tablaProdMouseClicked(MouseEvent evt) {

        BigDecimal porcentaje;
        int[] rows = PresupuestoGui.getTablaArticulos().getSelectedRows();
        if (rows.length > 0) {
            for (int i = 0; i < rows.length; i++) {

                if (!existeProdFacc(Integer.valueOf((String) tablap.getValueAt(rows[i], 0)))) {
                    Articulo p = Articulo.findFirst("id = ?", (tablap.getValueAt(rows[i], 0)));
                    Object cols[] = new Object[7];
                    cols[0] = p.get("id");
                    cols[1] = BigDecimal.valueOf(1).setScale(2, RoundingMode.CEILING);
                    cols[2] = p.get("codigo");
                    cols[3] = p.get("descripcion");
                    cols[4] = BigDecimal.valueOf(p.getFloat("precio_venta")).setScale(2, RoundingMode.CEILING);
                    cols[5] = p.getBigDecimal("stock_actual");
                    cols[6] = BigDecimal.valueOf(p.getFloat("precio_venta")).setScale(2, RoundingMode.CEILING);
                   
                    PresupuestoGui.getTablaFacturaDefault().addRow(cols);
                    setCellEditor();
                    actualizarPrecio();
                } else {
                    System.out.println("que hace guacho");
                }
            }
        }
    }

    private void tablaClienteMouseClicked(MouseEvent evt) {
        int row = PresupuestoGui.getTablaClientes().getSelectedRow();
        if (row > -1) {
            String id = (String) tablac.getValueAt(row, 0);
            String nom = (String) tablac.getValueAt(row, 1);
            PresupuestoGui.getClienteFactura().setText(id + " " + nom);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == PresupuestoGui.getBorrarArticulosSeleccionados()) {//boton borrar articulos seleccionados
            int[] rows = PresupuestoGui.getTablaFactura().getSelectedRows();
            if (rows.length > 0) {
                Integer[] idABorrar = new Integer[rows.length];
                for (int i = 0; i < rows.length; i++) {
                    idABorrar[i] = (Integer) tablafac.getValueAt(rows[i], 0);
                }
                int i = 0;
                int cantABorrar = 0;
                while (cantABorrar < rows.length) {
                    while (i < PresupuestoGui.getTablaFactura().getRowCount()) {
                        if ((Integer) PresupuestoGui.getTablaFactura().getValueAt(i, 0) == idABorrar[cantABorrar]) {
                            PresupuestoGui.getTablaFacturaDefault().removeRow(i);
                            cantABorrar++;
                        }
                        i++;
                    }
                    i = 0;
                }
                actualizarPrecio();
            }
        }
        if (e.getSource() == PresupuestoGui.getFacturaNueva()) {
            PresupuestoGui.limpiarVentana();
            PresupuestoGui.paraVerVenta(false);
            PresupuestoGui.getRealizarVenta().setEnabled(true);
        }
        if (e.getSource() == PresupuestoGui.getRealizarVenta()) {//Boton realizar venta
            if (PresupuestoGui.getClienteFactura().getText().equals("") || PresupuestoGui.getCalenFacturaText().getText().equals("") || PresupuestoGui.getTablaFactura().getRowCount() == 0) {
                JOptionPane.showMessageDialog(PresupuestoGui, "Fecha, cliente vacio o no hay productos cargados", "Error!", JOptionPane.ERROR_MESSAGE);
            } else {
                System.out.println("entre a registrar venta");

                Presupuesto v = new Presupuesto();
                LinkedList<Pair> parDeProductos = new LinkedList();
                LinkedList<BigDecimal> preciosFinales = new LinkedList();
                String laFecha = PresupuestoGui.getCalenFacturaText().getText(); //saco la fecha
                String cliente = PresupuestoGui.getClienteFactura().getText();
                Integer idCliente = Integer.valueOf(cliente.split(" ")[0]); //saco el id cliente
                v.set("cliente_id", idCliente);
                for (int i = 0; i < PresupuestoGui.getTablaFactura().getRowCount(); i++) {

                    Articulo producto = Articulo.findFirst("id = ?", tablafac.getValueAt(i, 0));
                    BigDecimal cantidad = ((BigDecimal) tablafac.getValueAt(i, 1)).setScale(2, RoundingMode.CEILING); //saco la cantidad
                    BigDecimal precioFinal = ((BigDecimal) tablafac.getValueAt(i, 6)).setScale(2, RoundingMode.CEILING);
                    preciosFinales.add(precioFinal);
                    Pair par = new Pair(producto, cantidad); //creo el par
                    parDeProductos.add(par); //meto el par a la lista
                }
                v.set("fecha", laFecha);
                v.setPreciosFinales(preciosFinales);
                v.setProductos(parDeProductos);
                BigDecimal bd = new BigDecimal(PresupuestoGui.getTotalFactura().getText());
                v.set("monto", bd);
                v.set("realizado",PresupuestoGui.getRealizado().getText());
                v.set("patente",PresupuestoGui.getPatente().getText() );
                if (abmPresupuesto.alta(v)) {
                    JOptionPane.showMessageDialog(apgui, "Presupuesto realizada con exito.");
                    PresupuestoGui.limpiarVentana();

                } else {
                    JOptionPane.showMessageDialog(apgui, "Ocurrió un error inesperado, presupuesto no realizada");
                }
            }
        }
    }

    public void cargarTodos() {
        actualizarListaCliente();
        actualizarListaProd();
    }

    private void actualizarListaCliente() {

        tablaClientes.setRowCount(0);
        clientelista = busqueda.filtroCliente(textan.getText(), "");
        Iterator<Cliente> it = clientelista.iterator();
        while (it.hasNext()) {
            Cliente a = it.next();
            String row[] = new String[2];
            row[0] = a.getId().toString();
            row[1] = a.getString("nombre");
            tablaClientes.addRow(row);
        }
    }

    private void actualizarListaProd() {
        tablaProd.setRowCount(0);
        prodlista = Articulo.where("codigo like ? or descripcion like ? or equivalencia_1 like ? or equivalencia_2 like ? or equivalencia_3 like ?", "%" + textcodprod.getText() + "%", "%" + textcodprod.getText() + "%", "%" + textcodprod.getText() + "%", "%" + textcodprod.getText() + "%", "%" + textcodprod.getText() + "%");
        Iterator<Articulo> it = prodlista.iterator();
        while (it.hasNext()) {
            Articulo a = it.next();
            String rowArray[] = new String[4];
            rowArray[0] = a.getId().toString();
            rowArray[1] = a.getString("codigo");
            rowArray[2] = a.getString("marca");
            rowArray[3] = a.getString("descripcion");
            tablaProd.addRow(rowArray);
        } // VER (EQUIVALENCIAS BUSCADOR)       
    }

    private boolean existeProdFacc(int id) {
        boolean ret = false;
        for (int i = 0; i < tablafac.getRowCount() && !ret; i++) {
            ret = (Integer) tablafac.getValueAt(i, 0) == id;
        }
        return ret;
    }

    public void setCellEditor() {
        for (int i = 0; i < tablafac.getRowCount(); i++) {
            tablafac.getCellEditor(i, 1).addCellEditorListener(this);
            tablafac.getCellEditor(i, 4).addCellEditorListener(this);
            
        }
    }

    public void actualizarPrecio() {
        BigDecimal importe;
        BigDecimal total = new BigDecimal(0);
        for (int i = 0; i < tablafac.getRowCount(); i++) {
            importe = ((BigDecimal) tablafac.getValueAt(i, 1)).multiply((BigDecimal) PresupuestoGui.getTablaFactura().getValueAt(i, 4)).setScale(2, RoundingMode.CEILING);
            tablafac.setValueAt(importe, i, 6);
        }
        for (int i = 0; i < tablafac.getRowCount(); i++) {
            total = total.add((BigDecimal) tablafac.getValueAt(i, 6)).setScale(2, RoundingMode.CEILING);;
        }
        PresupuestoGui.getTotalFactura().setText(total.toString());
    }

    @Override
    public void editingStopped(ChangeEvent e) {
        actualizarPrecio();
    }

    @Override
    public void editingCanceled(ChangeEvent e) {
        //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
