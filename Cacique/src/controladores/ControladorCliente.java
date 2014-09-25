    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import abm.ABMCliente;
import abm.ABMVenta;
import abm.ManejoIp;
import busqueda.Busqueda;
import com.toedter.calendar.JDateChooser;
import interfaz.AplicacionGui;
import interfaz.AutoGui;
import interfaz.ClienteGui;
import interfaz.HistorialComprasGui;
import interfaz.PagoFacturaGui;
import interfaz.PagosRealizadosClienteGui;
import interfaz.PresupuestoRealizadosGui;
import interfaz.Trabajos;
import interfaz.VentaGui;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelos.Articulo;
import modelos.ArticulosVentas;
import modelos.Cliente;
import modelos.Pago;
import modelos.Venta;
import net.sf.jasperreports.engine.JRException;
import org.javalite.activejdbc.LazyList;

/**
 *
 * @author jacinto
 */
public class ControladorCliente implements ActionListener {

    private JTextField nomcli;
    private ClienteGui clienteGui;
    private AplicacionGui aplicacionGui;
    private DefaultTableModel tablaCliDefault;
    private DefaultTableModel tablaVentasDefault;
    private java.util.List<Cliente> listClientes;
    private JTable tablaCliente;
    private JTable tablaVentas;
    private ABMCliente abmCliente;
    private Boolean isNuevo;
    private Boolean editandoInfo;
    private Cliente cliente;
    private JComboBox ver;
    Busqueda busqueda;
    VentaGui ventaGui;
    private Color Color;
    private JDateChooser nacimiento;
    private ControladorJReport reportePago;
    PresupuestoRealizadosGui prg;
    AutoGui ag;
    ControladorPresupuestosRealizados cpr;
    ControladorAuto ca;
    PagoFacturaGui pagoFacturaGui;
    HistorialComprasGui hcg;
    PagosRealizadosClienteGui hcc;

    public ControladorCliente(ClienteGui clienteGui, AplicacionGui aplicacionGui, VentaGui ventaGui, PresupuestoRealizadosGui prg, AutoGui ag, ControladorPresupuestosRealizados cpr, ControladorAuto ca) {
        try {
            this.aplicacionGui = aplicacionGui;
            this.clienteGui = clienteGui;
            this.ventaGui = ventaGui;
            this.clienteGui.setActionListener(this);
            isNuevo = true;
            editandoInfo = false;
            busqueda = new Busqueda();
            tablaCliDefault = clienteGui.getClientes();
            tablaVentasDefault = clienteGui.getVentasDefault();
            tablaVentas = clienteGui.getVentasRealizadas();
            tablaCliente = clienteGui.getTablaClientes();;
            listClientes = new LinkedList();
            abmCliente = new ABMCliente();
            cliente = new Cliente();
            this.prg = prg;
            nacimiento = clienteGui.getNacimiento();
            listClientes = Cliente.findAll();
            this.ag = ag;
            this.cpr = cpr;
            this.ca = ca;
            hcg = new HistorialComprasGui();
            this.aplicacionGui.getContenedor().add(hcg);
            pagoFacturaGui = new PagoFacturaGui();
            aplicacionGui.getContenedor().add(pagoFacturaGui);
            actualizarLista();
            hcc = new PagosRealizadosClienteGui();
            this.aplicacionGui.getContenedor().add(hcc);
            nomcli = clienteGui.getBusqueda();
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
            tablaVentas.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    tablaVentasMouseClicked(evt);
                }
            });
            ver = clienteGui.getVer();
            reportePago = new ControladorJReport("pago.jasper");
        } catch (JRException ex) {
            Logger.getLogger(ControladorCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ControladorCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void busquedaKeyReleased(KeyEvent evt) {
        System.out.println("apreté el caracter: " + evt.getKeyChar());
        realizarBusqueda();
    }

    private void tablaVentasMouseClicked(MouseEvent evt) {
        if (evt.getClickCount() == 2) {
            Integer idFac = Integer.valueOf((String) clienteGui.getVentasRealizadas().getValueAt(clienteGui.getVentasRealizadas().getSelectedRow(), 0));

            Venta factura = Venta.findById(idFac);
            Object idCliente = factura.get("cliente_id");
            DefaultTableModel tablita = ventaGui.getTablaFacturaDefault();
            tablita.setRowCount(0);
            Cliente cli = Cliente.findById(idCliente);
            System.out.println(cli.getId());
            if (cli != null) {
                String nombre = idCliente + " " + cli.getString("nombre");
                ventaGui.getClienteFactura().setText(nombre);
                LazyList<ArticulosVentas> pr = ArticulosVentas.find("venta_id = ?", idFac);
                Iterator<ArticulosVentas> it = pr.iterator();
                BigDecimal porcentaje;
                while (it.hasNext()) {
                    ArticulosVentas prod = it.next();
                    Articulo producto = Articulo.findFirst("id = ?", prod.get("articulo_id"));
                    if (producto != null) {
                        BigDecimal precio;
                        precio = prod.getBigDecimal("precio_final").setScale(2, RoundingMode.CEILING);
                        BigDecimal cantidad = prod.getBigDecimal("cantidad").setScale(2, RoundingMode.CEILING);
                        Object cols[] = new Object[7];
                        cols[0] = producto.get("id");
                        cols[1] = cantidad;
                        cols[2] = producto.get("codigo");
                        cols[3] = producto.get("descripcion");
                        cols[4] = precio;
                        cols[5] = producto.get("stock_actual");
                        cols[6] = precio.multiply(cantidad).setScale(2, RoundingMode.CEILING);
                        ventaGui.getTablaFacturaDefault().addRow(cols);
                    }
                }
                ventaGui.getTotalFactura().setText(String.valueOf(factura.getFloat("monto")));
                System.out.println("sali");
                ventaGui.paraVerVenta(true);
                ventaGui.setVisible(true);
                ventaGui.toFront();
                System.out.println("termine");
            } else {
                JOptionPane.showMessageDialog(aplicacionGui, "El cliente asociado a esta factura ya no existe", "CLIENTE INEXISTENTE", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Carga todos los clientes
    public void cargarTodos() {

        listClientes = Cliente.findAll();
        if (!listClientes.isEmpty()) {
            realizarBusqueda();
        }

    }

    // doble click en un cliente = muestra los datos y habilita los botones
    private void tablaClienteMouseClicked(MouseEvent evt) {
        if (evt.getClickCount() == 2) {
            clienteGui.habilitarCampos(false);
            clienteGui.getBorrar().setEnabled(true);
            clienteGui.getModificar().setEnabled(true);
            clienteGui.getGuardar().setEnabled(false);
            clienteGui.getNuevo().setEnabled(true);
            clienteGui.getRealizarEntrega().setEnabled(true);
            clienteGui.getAutos().setEnabled(true);
            clienteGui.getPresupuestos().setEnabled(true);
            clienteGui.getPagos().setEnabled(true);
            clienteGui.getAgregarAuto().setEnabled(true);
            System.out.println("hice doble click en un cliente");
            clienteGui.limpiarCampos();
            cliente = busqueda.buscarCliente(tablaCliente.getValueAt(tablaCliente.getSelectedRow(), 0));
            clienteGui.CargarCampos(cliente);
            cargarVentas();
            clienteGui.habilitarCamposVentas(true);
            calcularCtaCte();
            calcularCtaCteActual();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == clienteGui.getNuevo()) { //crear un nuevo cliente  
            System.out.println("Boton nuevo pulsado");
            clienteGui.limpiarCampos();
            clienteGui.habilitarCampos(true);
            isNuevo = true;
            editandoInfo = true;
            clienteGui.getBorrar().setEnabled(false);
            clienteGui.getRealizarEntrega().setEnabled(false);
            clienteGui.getModificar().setEnabled(false);
            clienteGui.getGuardar().setEnabled(true);
            clienteGui.getAutos().setEnabled(false);
            clienteGui.getAgregarAuto().setEnabled(false);
            clienteGui.getPresupuestos().setEnabled(false);
            clienteGui.getPagos().setEnabled(false);
            clienteGui.habilitarCamposVentas(false);
        }
        if (e.getSource() == clienteGui.getGuardar() && editandoInfo && isNuevo) { //Guardar
            System.out.println("Boton guardar pulsado");

            if (cargarDatosCliente(cliente)) {
                if (abmCliente.alta(cliente)) {
                    clienteGui.habilitarCampos(false);
                    clienteGui.limpiarCampos();
                    editandoInfo = false;
                    JOptionPane.showMessageDialog(clienteGui, "¡Cliente guardado exitosamente!");
                    clienteGui.getNuevo().setEnabled(true);
                    clienteGui.getGuardar().setEnabled(false);
                } else {
                    JOptionPane.showMessageDialog(clienteGui, "Ocurrió un error, revise los datos", "Error!", JOptionPane.ERROR_MESSAGE);
                }
                realizarBusqueda();
            }


        }
        if (e.getSource() == clienteGui.getBorrar()) { //borrar cliente 
            System.out.println("Boton borrar pulsado");

            clienteGui.habilitarCamposVentas(false);
            clienteGui.habilitarCampos(false);
            if (cliente.getString("id") != null && !editandoInfo) {
                Integer resp = JOptionPane.showConfirmDialog(clienteGui, "¿Desea borrar el cliente " + clienteGui.getNombre().getText(), "Confirmar borrado", JOptionPane.YES_NO_OPTION);
                if (resp == JOptionPane.YES_OPTION) {
                    Boolean seBorro = abmCliente.baja(cliente);
                    if (seBorro) {
                        JOptionPane.showMessageDialog(clienteGui, "¡Cliente borrado exitosamente!");
                        clienteGui.limpiarCampos();
                        realizarBusqueda();
                        clienteGui.getBorrar().setEnabled(false);
                        clienteGui.getModificar().setEnabled(false);
                        clienteGui.getRealizarEntrega().setEnabled(false);
                        clienteGui.getAutos().setEnabled(false);
                        clienteGui.getPresupuestos().setEnabled(false);
                        clienteGui.getPagos().setEnabled(false);
                        clienteGui.getAgregarAuto().setEnabled(false);
                    } else {
                        JOptionPane.showMessageDialog(clienteGui, "Ocurrió un error, no se borró el cliente", "Error!", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(clienteGui, "No se seleccionó un cliente");
            }

        }
        if (e.getSource() == clienteGui.getModificar()) { //modificar cliente
            System.out.println("Boton modificar pulsado");
            clienteGui.habilitarCampos(true);
            editandoInfo = true;
            isNuevo = false;
            clienteGui.getBorrar().setEnabled(false);
            clienteGui.getGuardar().setEnabled(true);
            clienteGui.getModificar().setEnabled(false);
            clienteGui.getRealizarEntrega().setEnabled(false);
            clienteGui.getAutos().setEnabled(false);
            clienteGui.getPresupuestos().setEnabled(false);
            clienteGui.getPagos().setEnabled(false);
            clienteGui.getAgregarAuto().setEnabled(false);
            clienteGui.habilitarCamposVentas(false);
        }

        if (e.getSource() == clienteGui.getGuardar() && editandoInfo && !isNuevo) {
            System.out.println("Boton guardar pulsado");

            if (cargarDatosCliente(cliente)) {
                if (abmCliente.modificar(cliente)) {
                    clienteGui.habilitarCampos(false);
                    clienteGui.limpiarCampos();
                    editandoInfo = false;
                    JOptionPane.showMessageDialog(clienteGui, "¡Cliente modificado exitosamente!");
                    clienteGui.getNuevo().setEnabled(true);
                    clienteGui.getGuardar().setEnabled(false);
                    clienteGui.getAutos().setEnabled(false);
                    clienteGui.getAgregarAuto().setEnabled(false);
                    clienteGui.getPresupuestos().setEnabled(false);
                    clienteGui.getPagos().setEnabled(false);
                    clienteGui.habilitarCamposVentas(false);
                } else {
                    JOptionPane.showMessageDialog(clienteGui, "Ocurrió un error,revise los datos", "Error!", JOptionPane.ERROR_MESSAGE);
                }
                realizarBusqueda();
            }

        }
        if (e.getSource() == clienteGui.getRealizarEntrega()) {
            RealizarPagoVentaControlador rpvc = new RealizarPagoVentaControlador(pagoFacturaGui, cliente, calcularCtaCte(), calcularCtaCteActual(), aplicacionGui, this);
            pagoFacturaGui.setVisible(true);
            pagoFacturaGui.toFront();
            calcularCtaCte();
            calcularCtaCteActual();
            cargarVentas();
        }
        if (e.getSource() == clienteGui.getEliminarVenta()) {
            int row = tablaVentas.getSelectedRow();

            if (row > -1) {
                String id = (String) tablaVentas.getValueAt(row, 0);
                Venta v = Venta.findById(id);
                ABMVenta abmV = new ABMVenta();
                if (abmV.bajaConDevolucion(v)) {
                    JOptionPane.showMessageDialog(clienteGui, "¡Venta eliminada exitosamente!");
                    cargarVentas();
                } else {
                    JOptionPane.showMessageDialog(clienteGui, "Ocurrió un error, la venta no ha sido eliminada", "Error!", JOptionPane.ERROR_MESSAGE);
                }
                calcularCtaCte();
                calcularCtaCteActual();
            }

        }
        if (e.getSource() == clienteGui.getVer()) {
            cargarVentas();
        }
        if ((e.getSource() == clienteGui.getVerHistorial())) {
            HistorialComprasControlador hcc = new HistorialComprasControlador(aplicacionGui, hcg, clienteGui, cliente);
            hcg.setVisible(true);
            hcg.toFront();
        }
        if (e.getSource() == clienteGui.getCobrarFactura()) {
            int row = tablaVentas.getSelectedRow();

            if (row > -1) {
                String p = (String) tablaVentas.getValueAt(row, 4);
                if (p.equals("Si")) {
                    JOptionPane.showMessageDialog(clienteGui, "Ocurrió un error, la venta ya ha sido abonada", "Error!", JOptionPane.ERROR_MESSAGE);
                } else {
                    int reply = JOptionPane.showConfirmDialog(null, "¿Desea cobrar la factura con el precio actual?", "¿Cuanto Cobrar?", JOptionPane.YES_NO_OPTION);
                    if (reply == JOptionPane.YES_OPTION || reply == JOptionPane.NO_OPTION) {
                        try {
                            boolean cobroNuevo = false;
                            if (reply == JOptionPane.YES_OPTION) {
                                cobroNuevo = true;
                            }

                            String id = (String) tablaVentas.getValueAt(row, 0);
                            BigDecimal monto = null;
                            if (cobroNuevo) {
                                monto = new BigDecimal((String) tablaVentas.getValueAt(row, 3));
                            } else {
                                monto = new BigDecimal((String) tablaVentas.getValueAt(row, 2));
                            }
                            Venta v = Venta.findById(id);
                            ABMVenta ambV = new ABMVenta();
                            v.set("pago", true);
                            v.set("monto", monto);
                            v.saveIt();
                            ambV.pagar(v, monto);
                            String clienteId = clienteGui.getId().getText();
                            int idCliente2 = Integer.parseInt(clienteId);
                            Calendar c = Calendar.getInstance();
                            Date d = c.getTime();

                            Pago pago = Pago.createIt("fecha", d, "monto", monto, "cliente_id", idCliente2, "descripcion", "Cobro de factura con nro : " + v.getString("id"));
                            pago.saveIt();
                            String pagoId = pago.getString("id");//Pago.findFirst("fecha = ? and monto = ? and cliente_id = ?", d, monto, idCliente2).getString("id");
                            v.set("pago_id", pagoId);
                            v.saveIt();

                            JOptionPane.showMessageDialog(clienteGui, "¡Cobro registrado exitosamente!");
                            reportePago.mostrarPago(Integer.valueOf(pagoId));

                            cargarVentas();
                            calcularCtaCte();
                            calcularCtaCteActual();
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(ControladorCliente.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (SQLException ex) {
                            Logger.getLogger(ControladorCliente.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (JRException ex) {
                            Logger.getLogger(ControladorCliente.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    } else {
                        JOptionPane.showMessageDialog(clienteGui, "Ocurrió un error, el cobro no ha sido registrado", "Error!", JOptionPane.ERROR_MESSAGE);
                    }
                }

            } else {

                JOptionPane.showMessageDialog(clienteGui, "Ocurrió un error, el cobro no ha sido registrado", "Error!", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (e.getSource() == clienteGui.getPresupuestos()) {
            prg.getFiltroNombre().setText(cliente.getString("nombre"));
            cpr.filtroNombre();
            prg.setVisible(true);
            prg.toFront();
        }
        if (e.getSource() == clienteGui.getAutos()) {
            ag.getBusquedaDuenio().setText(cliente.getString("nombre"));
            ca.realizarBusquedaDuenio();
            ag.setVisible(true);
            ag.toFront();
        }
        if(e.getSource()==clienteGui.getAgregarAuto()){
            ca.apreteNuevo(cliente.getString("id"), cliente.getString("nombre"));
            ag.setVisible(true);
            ag.toFront();
        }
        if (e.getSource() == clienteGui.getPagos()) {     
            hcc.setCliente(cliente);
            hcc.setVisible(true);
            hcc.toFront();
        }
    }

    public void actualizarPrecio() {
        BigDecimal importe;
        BigDecimal total = new BigDecimal(0);
        for (int i = 0; i < ventaGui.getTablaFactura().getRowCount(); i++) {
            importe = ((BigDecimal) ventaGui.getTablaFactura().getValueAt(i, 1)).multiply((BigDecimal) ventaGui.getTablaFactura().getValueAt(i, 4)).setScale(2, RoundingMode.CEILING);
            ventaGui.getTablaFactura().setValueAt(importe, i, 6);
        }
        for (int i = 0; i < ventaGui.getTablaFactura().getRowCount(); i++) {
            total = total.add((BigDecimal) ventaGui.getTablaFactura().getValueAt(i, 6)).setScale(2, RoundingMode.CEILING);;

        }
        ventaGui.getTotalFactura().setText(total.toString());
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

    // calcula la cuenta corriente con precios viejos
    public BigDecimal calcularCtaCte() {

        BigDecimal aux;
        BigDecimal total = new BigDecimal(0);
        for (int i = 0; i < tablaVentas.getRowCount(); i++) {
            if (!(((String) tablaVentas.getValueAt(i, 4)).equals("Si"))) {
                aux = new BigDecimal((String) tablaVentas.getValueAt(i, 2));
                total = total.add(aux);;
            }
        }
        BigDecimal cta = cliente.getBigDecimal("cuenta");
        cta = cta.subtract(total).setScale(2, RoundingMode.CEILING);
        if (cta.signum() == -1) {
            clienteGui.getAdeuda().setForeground(Color.red);
            cta = cta.negate();
            clienteGui.getAdeuda().setText(cta.toString());
            cta = cta.negate();
            return cta;
        } else {
            clienteGui.getAdeuda().setForeground(Color.black);
            clienteGui.getAdeuda().setText(cta.toString());
            return cta;
        }
    }

    //calcula la cuenta corriente con precios actuales
    public BigDecimal calcularCtaCteActual() {

        BigDecimal aux;
        BigDecimal total = new BigDecimal(0);
        for (int i = 0; i < tablaVentas.getRowCount(); i++) {
            if (!(((String) tablaVentas.getValueAt(i, 4)).equals("Si"))) {
                aux = new BigDecimal((String) tablaVentas.getValueAt(i, 3));
                total = total.add(aux);;
            }
        }
        BigDecimal cta = cliente.getBigDecimal("cuenta");
        cta = cta.subtract(total).setScale(2, RoundingMode.CEILING);
        if (cta.signum() == -1) {
            clienteGui.getAdeudaActual().setForeground(Color.red);
            cta = cta.negate();
            clienteGui.getAdeudaActual().setText(cta.toString());
            cta = cta.negate();
            return cta;
        } else {
            clienteGui.getAdeudaActual().setForeground(Color.black);
            clienteGui.getAdeudaActual().setText(cta.toString());
            return cta;
        }
    }

    private void realizarBusqueda() {
        listClientes = busqueda.buscarCliente(nomcli.getText());
        actualizarLista();
    }

    private boolean cargarDatosCliente(Cliente c) {
        boolean ret = true;
        try {
            String nombre = TratamientoString.eliminarTildes(clienteGui.getNombre().getText());
            System.out.println(nombre);
            c.set("nombre", nombre);
        } catch (ClassCastException e) {
            ret = false;
            JOptionPane.showMessageDialog(clienteGui, "Error en el nombre", "Error!", JOptionPane.ERROR_MESSAGE);
        }
        try {
            String telefono = TratamientoString.eliminarTildes(clienteGui.getTelefono().getText());
            c.set("telefono", telefono);
        } catch (ClassCastException e) {
            ret = false;
            JOptionPane.showMessageDialog(clienteGui, "Error en el telefono", "Error!", JOptionPane.ERROR_MESSAGE);
        }
        try {
            String celular = TratamientoString.eliminarTildes(clienteGui.getCelular().getText());
            c.set("celular", celular);
        } catch (ClassCastException e) {
            ret = false;
            JOptionPane.showMessageDialog(clienteGui, "Error en el celular", "Error!", JOptionPane.ERROR_MESSAGE);
        }
        try {
            String facebook = TratamientoString.eliminarTildes(clienteGui.getFacebook().getText());
            c.set("facebook", facebook);
        } catch (ClassCastException e) {
            ret = false;
            JOptionPane.showMessageDialog(clienteGui, "Error en el facebook", "Error!", JOptionPane.ERROR_MESSAGE);
        }
        try {
            String direccion = TratamientoString.eliminarTildes(clienteGui.getDireccion().getText());
            c.set("direccion", direccion);
        } catch (ClassCastException e) {
            ret = false;
            JOptionPane.showMessageDialog(clienteGui, "Error en el direccion", "Error!", JOptionPane.ERROR_MESSAGE);
        }
        try {
            String dni = TratamientoString.eliminarTildes(clienteGui.getDni().getText());
            c.set("dni", dni);
        } catch (ClassCastException e) {
            ret = false;
            JOptionPane.showMessageDialog(clienteGui, "Error en el dni", "Error!", JOptionPane.ERROR_MESSAGE);
        }
        String laFecha = clienteGui.getCalenText().getText();
        Date date = clienteGui.getNacimiento().getDate();
        c.set("nacimiento", date); //saco la fecha);
        c.set("email", clienteGui.getEmail().getText());
        return ret;
    }

    //carga las ventas realizadas al cliente en la tabla
    public void cargarVentas() {

        tablaVentasDefault.setRowCount(0);
        Iterator<Venta> itr = busqueda.filtroVenta(cliente.getString("id"), "0-0-0", "9999-0-0").iterator();
        while (itr.hasNext()) {
            Venta v = itr.next();
            Object row[] = new String[5];
            row[0] = v.getString("id");
            row[1] = v.getDate("fecha").toString();
            if (v.getBoolean("pago")) {
                row[2] = v.get("monto").toString();
                row[3] = "-";
                row[4] = "Si";
            } else {
                row[2] = v.get("monto").toString();
                BigDecimal montox = null;
                BigDecimal cuenta;
                System.out.println(v.getString("id") + " " + v.getDate("fecha").toString() + " " + v.getBoolean("pago"));
                Iterator<ArticulosVentas> itr2 = busqueda.filtroVendidos(v.getString("id")).iterator();
                while (itr2.hasNext()) {
                    ArticulosVentas arvs = itr2.next();
                    System.out.println(arvs.getInteger("articulo_id"));
                    Articulo art = Articulo.findById(arvs.getInteger("articulo_id"));
                    if (art != null) {
                        System.out.println(art == null);
                        cuenta = (art.getBigDecimal("precio_venta")).multiply(arvs.getBigDecimal("cantidad")).setScale(2, RoundingMode.CEILING);
                        if (montox == null) {
                            montox = new BigDecimal(String.valueOf((cuenta).setScale(2, RoundingMode.CEILING)));
                        } else {
                            montox = new BigDecimal(String.valueOf(montox.add(cuenta).setScale(2, RoundingMode.CEILING)));
                        }
                    }
                }
                montox.setScale(2, RoundingMode.CEILING);
                row[3] = montox.toString();
                row[4] = "No";
            }
            if (ver.getSelectedIndex() == 0) {
                tablaVentasDefault.addRow(row);
            } else {
                if (ver.getSelectedIndex() == 1) {
                    if (!(v.getBoolean("pago"))) {
                        tablaVentasDefault.addRow(row);
                    }
                } else {
                    if (ver.getSelectedIndex() == 2) {
                        if ((v.getBoolean("pago"))) {
                            tablaVentasDefault.addRow(row);
                        }
                    }
                }
            }
        }


    }

    public LinkedList<Venta> cargarDeuda(String id) {

        Iterator<Venta> itr = busqueda.filtroVenta(id, "0-0-0", "9999-0-0").iterator();
        LinkedList<Venta> retorno = new LinkedList<Venta>();
        while (itr.hasNext()) {
            Venta v = itr.next();
            if (!(v.getBoolean("pago"))) {
                retorno.add(v);
            }
        }
        return retorno;
    }

    public BigDecimal montoVentaNoAbonada(String id) {

        BigDecimal montox = null;
        BigDecimal cuenta;
        Iterator<ArticulosVentas> itr2 = busqueda.filtroVendidos(id).iterator();
        while (itr2.hasNext()) {
            ArticulosVentas arvs = itr2.next();
            Articulo art = Articulo.findById(arvs.getInteger("articulo_id"));
            cuenta = (art.getBigDecimal("precio_venta")).multiply(arvs.getBigDecimal("cantidad")).setScale(2, RoundingMode.CEILING);
            if (montox == null) {
                montox = new BigDecimal(String.valueOf((cuenta).setScale(2, RoundingMode.CEILING)));
            } else {
                montox = new BigDecimal(String.valueOf(montox.add(cuenta).setScale(2, RoundingMode.CEILING)));
            }
        }

        return montox.setScale(2, RoundingMode.CEILING);
    }
}
