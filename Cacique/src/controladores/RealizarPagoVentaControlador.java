/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import abm.ABMVenta;
import busqueda.Busqueda;
import interfaz.AplicacionGui;
import interfaz.PagoFacturaGui;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelos.Articulo;
import modelos.ArticulosVentas;
import modelos.Cliente;
import modelos.Pago;
import modelos.Venta;
import net.sf.jasperreports.engine.JRException;
import org.javalite.activejdbc.Base;

/**
 *
 * @author jacinto
 */
public class RealizarPagoVentaControlador implements ActionListener {

    private PagoFacturaGui pagoFacturaGui;
    private Cliente cli;
    private BigDecimal ctaCte;
    private BigDecimal ctaCteActual;
    private Busqueda busqueda;
    private AplicacionGui apgui;
    private ControladorCliente cl;
    private ControladorJReport reportePago;

    public RealizarPagoVentaControlador(PagoFacturaGui pagoFacturaGui, Cliente cli, BigDecimal ctaCte, BigDecimal ctaCteActual, AplicacionGui apgui, ControladorCliente cl) {
        try {
            this.apgui = apgui;
            this.pagoFacturaGui = pagoFacturaGui;
            this.pagoFacturaGui.setActionListener(this);
            this.cli = cli;
            this.ctaCte = ctaCte;
            this.ctaCteActual = ctaCteActual;
            CargarDatosCli();
            busqueda = new Busqueda();
            this.cl = cl;
            reportePago=  new ControladorJReport("pago.jasper");
        } catch (JRException ex) {
            Logger.getLogger(RealizarPagoVentaControlador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RealizarPagoVentaControlador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(RealizarPagoVentaControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void CargarDatosCli() {
        pagoFacturaGui.setCliente(cli.getString("nombre"));
        pagoFacturaGui.setCuenta(ctaCte.toString());
        if (ctaCte.signum() == -1) {
            pagoFacturaGui.getCuenta().setForeground(Color.red);
            ctaCte = ctaCte.negate();
            pagoFacturaGui.setCuenta(ctaCte.toString());
            ctaCte = ctaCte.negate();
        } else {
            pagoFacturaGui.getCuenta().setForeground(Color.black);
            pagoFacturaGui.setCuenta(ctaCte.toString());
        }
        pagoFacturaGui.setCuentaActual(ctaCteActual.toString());
        if (ctaCteActual.signum() == -1) {
            pagoFacturaGui.getCuentaActual().setForeground(Color.red);
            ctaCteActual = ctaCteActual.negate();
            pagoFacturaGui.setCuentaActual(ctaCteActual.toString());
            ctaCteActual = ctaCteActual.negate();
        } else {
            pagoFacturaGui.getCuentaActual().setForeground(Color.black);
            pagoFacturaGui.setCuentaActual(ctaCteActual.toString());
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == pagoFacturaGui.getAceptar()) {
            if (pagoFacturaGui.getMonto().getText().equals("")) {
                JOptionPane.showMessageDialog(pagoFacturaGui, "Por favor ingrese el monto", "Error!", JOptionPane.ERROR_MESSAGE);
            } else {
                int queCobrar = pagoFacturaGui.getPagar().getSelectedIndex();
                if (queCobrar == -1) {
                    JOptionPane.showMessageDialog(pagoFacturaGui, "Por favor ingrese que cuenta corriente se debe cobrar", "Error!", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        Base.openTransaction();
                        String clienteId = cli.getString("id");
                        int idCliente = cli.getInteger("id");
                        BigDecimal entrega = new BigDecimal(pagoFacturaGui.getMonto().getText());
                        Pago pago = new Pago();
                        pago.set("fecha", pagoFacturaGui.getCalendarioText().getText());
                        pago.set("monto", entrega);
                        pago.set("cliente_id", idCliente);
                        pago.set("descripcion", pagoFacturaGui.getDescripcion().getText());
                        pago.saveIt();
                        String pagoId = Pago.findFirst("fecha = ? and monto = ? and cliente_id = ?", pagoFacturaGui.getCalendarioText().getText(), entrega, idCliente).getString("id");
                        BigDecimal cuentaCliente = new BigDecimal(cli.getString("cuenta"));
                        entrega = entrega.add(cuentaCliente);
                        Iterator<Venta> itrVenta = cargarDeuda(clienteId).iterator();
                        Venta ventaAPagar = null;
                        boolean sePuedePagar = true;
                        BigDecimal montoVentaAPagar = new BigDecimal(0);
                        BigDecimal aux;
                        ABMVenta ambV = new ABMVenta();
                        while (sePuedePagar) {
                            sePuedePagar = false;
                            while (itrVenta.hasNext()) {
                                Venta venta = itrVenta.next();
                                String ventaId = venta.getString("id");
                                if (queCobrar == 1) {
                                    aux = montoVentaNoAbonada(ventaId);
                                } else {
                                    aux = venta.getBigDecimal("monto");
                                }
                                if (entrega.compareTo(aux) >= 0) {
                                    sePuedePagar = true;
                                    if (montoVentaAPagar.compareTo(aux) <= 0) {
                                        ventaAPagar = Venta.findById(ventaId);
                                        montoVentaAPagar = new BigDecimal(aux.toString());
                                    }
                                }
                            }
                            if (sePuedePagar) {
                                entrega = entrega.subtract(montoVentaAPagar);
                                ambV.pagar(ventaAPagar, montoVentaAPagar);
                                ventaAPagar.set("pago_id", pagoId);
                                ventaAPagar.saveIt();
                                itrVenta = cargarDeuda(clienteId).iterator();
                                montoVentaAPagar = new BigDecimal(0);
                                aux = null;
                                ventaAPagar = null;
                            }
                        }
                        cli.set("cuenta", entrega);
                        cli.saveIt();
                        Base.commitTransaction();
                        JOptionPane.showMessageDialog(apgui, "¡Cobro registrado exitosamente!");
                        reportePago.mostrarPago(Integer.valueOf(pagoId));
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(RealizarPagoVentaControlador.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(RealizarPagoVentaControlador.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (JRException ex) {
                        Logger.getLogger(RealizarPagoVentaControlador.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
            cl.cargarVentas();
            cl.calcularCtaCte();
            cl.calcularCtaCteActual();
            pagoFacturaGui.dispose();
            try {
                this.finalize();
            } catch (Throwable ex) {
                Logger.getLogger(RealizarPagoVentaControlador.class.getName()).log(Level.SEVERE, null, ex);
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
