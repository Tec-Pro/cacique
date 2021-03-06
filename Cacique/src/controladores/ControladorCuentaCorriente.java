/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import abm.ABMCuentaCorriente;
import busqueda.Busqueda;
import interfaz.AplicacionGui;
import interfaz.CuentaCorrienteGui;
import interfaz.PagarCorrienteGui;
import interfaz.imprimirCuentasCorrienges;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelos.Cliente;
import modelos.Corriente;
import org.javalite.activejdbc.Base;

/**
 *
 * @author jacinto
 */
public class ControladorCuentaCorriente implements ActionListener {

    private JTextField nomcli;
    private AplicacionGui aplicacionGui;
    private DefaultTableModel tablaCliDefault;
    private DefaultTableModel tablaCuentaDefault;
    private java.util.List<Cliente> listClientes;
    private java.util.List<Corriente> listCorriente;
    private JTable tablaCliente;
    private JTable tablaCuenta;
    private ABMCuentaCorriente abmCorriente;
    private Boolean isNuevo;
    private Boolean editandoInfo;
    private Cliente cliente;
    private JComboBox ver;
    Busqueda busqueda;
    private Corriente c;
    private CuentaCorrienteGui ctg;

    public ControladorCuentaCorriente(CuentaCorrienteGui ctg) {
        this.ctg = ctg;
        this.ctg.setActionListener(this);
        isNuevo = true;
        editandoInfo = false;
        busqueda = new Busqueda();
        tablaCliDefault = ctg.getClientesDefaul();
        tablaCuentaDefault = ctg.getCuentasDefaul();
        tablaCuenta = ctg.getTablaCuentas();
        tablaCliente = ctg.getTablaClientes();
        abmCorriente = new ABMCuentaCorriente();
        Base.openTransaction();
        listCorriente = Corriente.findAll();
        listClientes = Cliente.findAll();
        Base.commitTransaction();
        nomcli = ctg.getBusqueda();
        nomcli.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                busquedaKeyReleased(evt);
            }
        });
        tablaCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaClienteMouseClicked(evt);
            }
        });
        tablaCuenta.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaCuentaMouseClicked(evt);
            }
        });
    }

    private void busquedaKeyReleased(KeyEvent evt) {
        System.out.println("apreté el caracter: " + evt.getKeyChar());
        realizarBusqueda();
    }

    private void tablaClienteMouseClicked(MouseEvent evt) {
        if (evt.getClickCount() == 2) {
            ctg.limpiarCampos();
            cliente = busqueda.buscarCliente(tablaCliente.getValueAt(tablaCliente.getSelectedRow(), 0));

            cargarCuentas();
        }
    }

    private void tablaCuentaMouseClicked(MouseEvent evt) {
        ctg.limpiarCampos();
        Base.openTransaction();
        c = Corriente.findById(tablaCuenta.getValueAt(tablaCuenta.getSelectedRow(), 0));
        Base.commitTransaction();
        ctg.CargarCampos(c);
        ctg.getEditar().setEnabled(true);
        ctg.getEliminar().setEnabled(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ctg.getNueva()) {
            System.out.print("CLIC NUEVA CUENTA");
            cliente = busqueda.buscarCliente(tablaCliente.getValueAt(tablaCliente.getSelectedRow(), 0));
            ctg.nuevaCuenta();
            ctg.getClienteCuenta().setText(cliente.getString("id"));
            isNuevo = true;
            editandoInfo = true;
        }
        if (e.getSource() == ctg.getGuardar() && editandoInfo && isNuevo) { //Guardar
            Corriente c = new Corriente();
            c.set("id_cliente", ctg.getClienteCuenta().getText());
            c.set("id_venta", ctg.getIdVenta().getText());
            String descripcion = TratamientoString.eliminarTildes(ctg.getDescripcion().getText());
            c.set("descripcion", descripcion);
            c.set("monto", ctg.getMontoVenta().getText());
            Date date = ctg.getFecha().getDate();
            c.set("fecha", date); //saco la fecha);
            if (abmCorriente.alta(c)) {
                ctg.guardarCuenta();
                Base.openTransaction();
                listCorriente = Corriente.find("id_cliente like ? ", cliente.getId());
                Base.commitTransaction();
                editandoInfo = false;
                cargarCuentas();
                JOptionPane.showMessageDialog(ctg, "¡Cuenta guardada exitosamente!");
            } else {
                JOptionPane.showMessageDialog(ctg, "Ocurrió un error, revise los datos", "Error!", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (e.getSource() == ctg.getEliminar()) { //borrar          
            Base.openTransaction();
            Corriente c = Corriente.findById(tablaCuenta.getValueAt(tablaCuenta.getSelectedRow(), 0));
            Base.commitTransaction();
            if (ctg.getIdCuenta() != null && !editandoInfo) {
                Integer resp = JOptionPane.showConfirmDialog(ctg, "¿Desea borrar la cuenta?", "Confirmar borrado", JOptionPane.YES_NO_OPTION);
                if (resp == JOptionPane.YES_OPTION) {
                    Boolean seBorro = abmCorriente.baja(c);
                    if (seBorro) {
                        JOptionPane.showMessageDialog(ctg, "¡Cuenta borrada exitosamente!");
                        ctg.eliminarCuenta();
                        Base.openTransaction();
                        listCorriente = Corriente.find("id_cliente like ? ", cliente.getId());
                        Base.commitTransaction();
                        cargarCuentas();
                    } else {
                        JOptionPane.showMessageDialog(ctg, "Ocurrió un error, no se borró la cuenta", "Error!", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(ctg, "No se seleccionó una cuenta");
            }
        }
        if (e.getSource() == ctg.getEditar()) { //modificar
            ctg.editarCuenta();
            editandoInfo = true;
            isNuevo = false;
        }
        if (e.getSource() == ctg.getGuardar() && editandoInfo && !isNuevo) {
            String descripcion = TratamientoString.eliminarTildes(ctg.getDescripcion().getText());
            c.set("descripcion", descripcion);
            c.set("monto", ctg.getMontoVenta().getText());
            Date date = ctg.getFecha().getDate();
            c.set("fecha", date); //saco la fecha);
            if (abmCorriente.modificar(c)) {
                ctg.guardarCuenta();
                Base.openTransaction();
                listCorriente = Corriente.find("id_cliente like ? ", cliente.getId());
                Base.commitTransaction();
                editandoInfo = false;
                JOptionPane.showMessageDialog(ctg, "¡Cuenta modificada exitosamente!");
            } else {
                JOptionPane.showMessageDialog(ctg, "Ocurrió un error,revise los datos", "Error!", JOptionPane.ERROR_MESSAGE);
            }
            cargarCuentas();
        }
        if (e.getSource() == ctg.getPagar()) {
            new PagarCorrienteGui(aplicacionGui, true, Integer.valueOf((String) tablaCuenta.getValueAt(tablaCuenta.getSelectedRow(), 1)), Integer.valueOf((String) tablaCuenta.getValueAt(tablaCuenta.getSelectedRow(), 0)), this,false).setVisible(true);
            cargarCuentas();
        }
                if (e.getSource() == ctg.getPagarVarias()) {
            new PagarCorrienteGui(aplicacionGui, true, cliente.getInteger("id"),0, this,true).setVisible(true);
            cargarCuentas();
        }
        
        if(e.getSource()== ctg.getImprimir()){
            imprimirCuentasCorrienges imprimir= new imprimirCuentasCorrienges(aplicacionGui, true,cliente.getInteger("id"));
            imprimir.setVisible(true);
            imprimir.setLocationRelativeTo(null);
            
        }
        if(e.getSource()==ctg.getTodasLasCuentas()){
            cargarTodasCuentas();
        }

    }

    //carga las ventas realizadas al cliente en la tabla
    public void cargarCuentas() {
        Base.openTransaction();
        listCorriente = Corriente.find("id_cliente like ? ", cliente.getId());
        Base.commitTransaction();
        tablaCuentaDefault.setRowCount(0);
        Iterator<Corriente> itr = listCorriente.iterator();
        while (itr.hasNext()) {
            Corriente v = itr.next();
            Object row[] = new Object[9];

             
            BigDecimal haber = BigDecimal.valueOf(v.getFloat("haber")).setScale(2, RoundingMode.DOWN);
            BigDecimal monto = BigDecimal.valueOf(v.getFloat("monto")).setScale(2, RoundingMode.DOWN);
            row[0] = v.getString("id");
            row[1] = v.getString("id_cliente");
            row[2] = v.getString("id_venta");
            row[3] = monto.toString();
            row[4] = v.getString("descripcion");
            row[5] = haber.toString();
            BigDecimal debe = monto.subtract(haber).setScale(2, RoundingMode.CEILING);
            row[6] = debe.toString();
            row[7] = debe.compareTo(BigDecimal.ZERO) <= 0;
            row[8]= dateToMySQLDate(v.getDate("fecha"),true);
            tablaCuentaDefault.addRow(row);
        }
        actualizarTotalCuenta();
    }
    
    
    
    
    
    //carga las ventas realizadas al cliente en la tabla
    public void cargarTodasCuentas() {
        Base.openTransaction();
        listCorriente = Corriente.findAll();
        Base.commitTransaction();
        tablaCuentaDefault.setRowCount(0);
        Iterator<Corriente> itr = listCorriente.iterator();
        while (itr.hasNext()) {
            Corriente v = itr.next();
            Object row[] = new Object[9];
            BigDecimal haber = BigDecimal.valueOf(v.getFloat("haber")).setScale(2, RoundingMode.DOWN);
            BigDecimal monto = BigDecimal.valueOf(v.getFloat("monto")).setScale(2, RoundingMode.DOWN);
            row[0] = v.getString("id");
            row[1] = v.getString("id_cliente");
            row[2] = v.getString("id_venta");
            row[3] = monto.toString();
            row[4] = v.getString("descripcion");
            row[5] = haber.toString();
            BigDecimal debe = monto.subtract(haber).setScale(2, RoundingMode.CEILING);
            row[6] = debe.toString();
            row[7] = debe.compareTo(BigDecimal.ZERO) <= 0;
            row[8]= dateToMySQLDate(v.getDate("fecha"),true);
            tablaCuentaDefault.addRow(row);
        }
        actualizarTotalCuenta();
    }

    // Carga todos los clientes
    public void cargarTodos() {
        Base.openTransaction();
        listClientes = Cliente.findAll();
        if (!listClientes.isEmpty()) {
            realizarBusqueda();
        }
        Base.commitTransaction();
    }

    private void realizarBusqueda() {
        listClientes = busqueda.buscarCliente(nomcli.getText());
        actualizarLista();
    }

    private void actualizarLista() {

        tablaCliDefault.setRowCount(0);
        Iterator<Cliente> it = listClientes.iterator();
        while (it.hasNext()) {
            Cliente c = it.next();
            Object row[] = new String[6];
            row[0] = c.getString("id");
            row[1] = c.getString("nombre");
            row[2] = c.getString("telefono");
            row[3] = c.getString("celular");
            tablaCliDefault.addRow(row);

        }
    }

    public void actualizarTotalCuenta() {
        float big = 0;
        for (int i = 0; i < tablaCuenta.getRowCount(); i++) {
            big += Float.valueOf((String) tablaCuenta.getValueAt(i, 6));
            
        }
        big-= cliente.getFloat("cuenta_corriente_manual");
        ctg.getTotal().setText(BigDecimal.valueOf(big).setScale(2, RoundingMode.CEILING).toString());
    }
    
        /*va true si se quiere usar para mostrarla por pantalla es decir 12/12/2014 y false si va 
    para la base de datos, es decir 2014/12/12*/
    public String dateToMySQLDate(Date fecha, boolean paraMostrar) {
        if(fecha!=null){
        if(paraMostrar){
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(fecha);
        }
        else{
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(fecha);
        }
        }
        else{
            return "";
        }
    }
}
