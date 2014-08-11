/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import abm.ABMPresupuesto;
import busqueda.Busqueda;
import com.toedter.calendar.JDateChooser;
import interfaz.AplicacionGui;
import interfaz.PresupuestoRealizadosGui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelos.Articulo;
import modelos.ArticulosPresupuestos;
import modelos.Cliente;
import modelos.Presupuesto;

/**
 *
 * @author jacinto
 */
public class ControladorPresupuestosRealizados implements ActionListener {

    private JTextField nombre;
    private String desde;
    private String hasta;
    private JDateChooser calenDesde;
    private JDateChooser calenHasta;
    private JTable tablaFacturas;
    private DefaultTableModel facturasDefault;
    private DefaultTableModel factDefault;
    private JTable tablaFact;
    private AplicacionGui apgui;
    private List<Cliente> cl;
    private List<Presupuesto> vl;
    private Busqueda buscar;
    private LinkedList<ArticulosPresupuestos> prodPresupuesto;
    private ABMPresupuesto abmPresupuesto;
    private PresupuestoRealizadosGui presupuestosRealizadosGui;

    public ControladorPresupuestosRealizados(AplicacionGui apgui, final PresupuestoRealizadosGui presupuestosRealizadosGui) {
        desde = "0-0-0";
        hasta = "9999-0-0";
        this.apgui = apgui;
        this.presupuestosRealizadosGui = presupuestosRealizadosGui;
        nombre = presupuestosRealizadosGui.getFiltroNombre();
        calenDesde = presupuestosRealizadosGui.getDesde();
        calenHasta = presupuestosRealizadosGui.getHasta();
        cl = new LinkedList<Cliente>();
        vl = new LinkedList<Presupuesto>();
        tablaFacturas = presupuestosRealizadosGui.getTablaFacturas();
        facturasDefault = presupuestosRealizadosGui.getTablaFacturasDefault();
        buscar = new Busqueda();
        cl = buscar.filtroCliente("", "");
        vl = buscar.filtroPresupuesto("", "", "");
        factDefault = presupuestosRealizadosGui.getTablaFacturaDefault();
        tablaFact = presupuestosRealizadosGui.getTablaFactura();
        prodPresupuesto = new LinkedList<ArticulosPresupuestos>();
        abmPresupuesto = new ABMPresupuesto();
        this.presupuestosRealizadosGui.setActionListener(this);
        tablaFacturas.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tablaFacturasMouseReleased(evt);
            }
        });


        nombre.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                filtroNombreKeyReleased(evt);
            }
        });
        presupuestosRealizadosGui.getCalenDesdeText().addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                desde = presupuestosRealizadosGui.getCalenDesdeText().getText();
                actualizarListaFacturas();

            }
        });
        presupuestosRealizadosGui.getCalenHastaText().addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                hasta = presupuestosRealizadosGui.getCalenHastaText().getText();
                actualizarListaFacturas();

            }
        });
        calenDesde.getJCalendar().addPropertyChangeListener("calendar", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent e) {
                calenDesdePropertyChange(e);
            }
        });
        calenHasta.getJCalendar().addPropertyChangeListener("calendar", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent e) {
                calenHastaPropertyChange(e);
            }
        });
        actualizarListaFacturas();
    }

    public void actualizarListaFacturas() {
        facturasDefault.setRowCount(0);
        cl = buscar.filtroCliente("", "");
        for (Cliente c : cl) {
            vl = buscar.filtroPresupuesto(c.getId().toString(), desde, hasta);
            for (Presupuesto v : vl) {
                String row[] = new String[3];
                row[0] = v.getId().toString();
                row[1] = c.getString("nombre");
                row[2] = v.get("fecha").toString();
                facturasDefault.addRow(row);
            }
        }

    }

    public void setNombre(String nombre) {
        this.nombre.setText(nombre);
    }

    private void actualizarFactura() {
        factDefault.setRowCount(0);
        for (ArticulosPresupuestos pv : prodPresupuesto) {
            Articulo p = Articulo.findFirst("id = ?", pv.get("articulo_id"));
            Object cols[] = new Object[7];
            cols[0] = p.get("id");
            cols[1] = pv.getBigDecimal("cantidad");
            cols[2] = p.get("codigo");
            cols[3] = p.get("descripcion");
            cols[4] = BigDecimal.valueOf(pv.getFloat("precio_final")).divide(pv.getBigDecimal("cantidad")).setScale(2, RoundingMode.CEILING);
            cols[5] = p.getBigDecimal("stock_actual");
            cols[6] = BigDecimal.valueOf(pv.getFloat("precio_final")).setScale(2, RoundingMode.CEILING);
            factDefault.addRow(cols);
        }
        setTotal();
    }

    public void calenDesdePropertyChange(PropertyChangeEvent e) {
        final Calendar c = (Calendar) e.getNewValue();
        desde = c.get(Calendar.YEAR) + "-" + c.get(Calendar.MONTH) + "-" + c.get(Calendar.DATE);
        actualizarListaFacturas();
    }

    public void calenHastaPropertyChange(PropertyChangeEvent e) {
        final Calendar c = (Calendar) e.getNewValue();
        hasta = c.get(Calendar.YEAR) + "-" + c.get(Calendar.MONTH) + "-" + c.get(Calendar.DATE);
        actualizarListaFacturas();
    }

    public void filtroNombreKeyReleased(java.awt.event.KeyEvent evt) {
        filtroNombre();
    }

    public void filtroNombre(){
        cl = buscar.filtroCliente(nombre.getText(), "");
        facturasDefault.setRowCount(0);
        for (Cliente c : cl) {
            vl = buscar.filtroPresupuesto(c.getId().toString(), desde, hasta);
            for (Presupuesto v : vl) {
                String row[] = new String[3];
                row[0] = v.getId().toString();
                row[1] = c.getString("nombre");
                row[2] = v.get("fecha").toString();
                facturasDefault.addRow(row);
            }
        }
    }
    private void setTotal() {
        Float total = Float.parseFloat("0.0");
        for (int i = 0; i < tablaFact.getRowCount(); i++) {
            total += Float.parseFloat(presupuestosRealizadosGui.getTablaFactura().getValueAt(i, 6).toString());
        }
        presupuestosRealizadosGui.getTotalFactura().setText(total.toString());
    }

    public void tablaFacturasMouseReleased(java.awt.event.MouseEvent evt) {
        int r = tablaFacturas.getSelectedRow();
        Cliente c = buscar.buscarCliente(tablaFacturas.getValueAt(r, 0));
        presupuestosRealizadosGui.getClienteFactura().setText(tablaFacturas.getValueAt(r, 1).toString());
        presupuestosRealizadosGui.getCalendarioFactura().setDate(Date.valueOf(tablaFacturas.getValueAt(r, 2).toString()));
        if (r > -1) {
            prodPresupuesto = buscar.filtroPresupuestados(tablaFacturas.getValueAt(r, 0).toString(), "");
        }
        actualizarFactura();
    }

    private void limpiarFactura() {
        factDefault.setRowCount(0);
        presupuestosRealizadosGui.getClienteFactura().setText("");
        presupuestosRealizadosGui.getCalendarioFactura().setDate(Date.valueOf("0000-1-1"));
        presupuestosRealizadosGui.getTotalFactura().setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == presupuestosRealizadosGui.getEliminarPresupuesto()) {
            int r = tablaFacturas.getSelectedRow();
            if (r < 0) {
                JOptionPane.showMessageDialog(presupuestosRealizadosGui, "No hay ningun presupuesto seleccionada");
                return;
            }
            Presupuesto v = new Presupuesto();
            v.setId(facturasDefault.getValueAt(r, 0));
            abmPresupuesto.baja(v);
            actualizarListaFacturas();
            limpiarFactura();
            JOptionPane.showMessageDialog(presupuestosRealizadosGui, "Presupuesto borrada exitosamente");
        }
    }
}
