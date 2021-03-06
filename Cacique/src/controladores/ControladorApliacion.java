/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import abm.ManejoIp;
import interfaz.AplicacionGui;
import interfaz.ArticuloGui;
import interfaz.ArticulosSinStock;
import interfaz.AutoGui;
import interfaz.CambiarUsuarioGui;
import interfaz.CargarDatosEmail;
import interfaz.ClienteGui;
import interfaz.CompraGui;
import interfaz.ConfigurarServerGui;
import interfaz.CuentaCorrienteGui;
import interfaz.CumpleaniosGui;
import interfaz.EnviarManualGui;
import interfaz.ImportarExcelGui;
import interfaz.PresupuestoGui;
import interfaz.PresupuestoRealizadosGui;
import interfaz.ProveedorGui;
import interfaz.Trabajos;
import interfaz.VentaGui;
import interfaz.tocaCambioGui;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import net.sf.jasperreports.engine.JRException;
import org.javalite.activejdbc.Base;

/**
 *
 * @author nico
 */
public class ControladorApliacion implements ActionListener {

    private AplicacionGui aplicacionGui;
    private ArticuloGui articuloGui;
    private ControladorArticulo controladorArticulo;
    private ControladorProveedor controladorProveedor;
    private controladorImportarGui controladorImportarGui;
    private ProveedorGui proveedorGui;
    private ImportarExcelGui importarGui;
    private ClienteGui clienteGui;
    private ControladorCliente controladorCliente;
    private CargarDatosEmail emailGui;
    private ControladorLogin log;
    private File archivoBackup;
    private int selecEnviarBack = 0;
    private Modulo modulo;
    private EnvioEmailControlador envioEmailControlador;
    private ControladorCompra controladorCompra;
    private ControladorVenta controladorVenta;
    private CompraGui compraGui;
    private VentaGui ventaGui;
    private ArticulosSinStock articulosSinStock;
    private ControladorArticulosAgot controladorArtSinStock;
    private CumpleaniosGui cumpleGui;
    private AutoGui autoGui;
    private ControladorAuto controladorAuto;
    private Trabajos trabajoGui;
    private ControladorTrabajo controladorTrabajo;
    private PresupuestoGui presupuestoGui;
    private ControladorPresupuesto controladorPresupuesto;
    private ControladorPresupuestosRealizados controladorPresupuestosRealizados;
    private PresupuestoRealizadosGui presupuestoRealizadosGui;
    private tocaCambioGui tocacambioGui;
    private ControladorCuentaCorriente controladorCuentaCorriente;
    private CuentaCorrienteGui cuentaCorrienteGui;

    public ControladorApliacion() throws JRException, ClassNotFoundException, SQLException {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        if (!Base.hasConnection()) {
            Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://" + ManejoIp.ipServer + "/cacique", "tecpro", "tecpro");
        }
        aplicacionGui = new AplicacionGui();
        log = new ControladorLogin(aplicacionGui);
        log.run();
        aplicacionGui.setCursor(Cursor.WAIT_CURSOR);
        aplicacionGui.setActionListener(this);
        aplicacionGui.setExtendedState(JFrame.MAXIMIZED_BOTH);
        articuloGui = new ArticuloGui();
        proveedorGui = new ProveedorGui();
        clienteGui = new ClienteGui();
        compraGui = new CompraGui();
        ventaGui = new VentaGui();
        cuentaCorrienteGui = new CuentaCorrienteGui();
        controladorCuentaCorriente = new ControladorCuentaCorriente(cuentaCorrienteGui);
        cumpleGui = new CumpleaniosGui(clienteGui);
        autoGui = new AutoGui();
        trabajoGui = new Trabajos();
        articulosSinStock = new ArticulosSinStock();
        controladorVenta = new ControladorVenta(ventaGui, aplicacionGui);
        controladorProveedor = new ControladorProveedor(proveedorGui, aplicacionGui, articuloGui, compraGui);
        controladorArticulo = new ControladorArticulo(articuloGui);
        controladorArtSinStock = new ControladorArticulosAgot(articulosSinStock, articuloGui);
        controladorAuto = new ControladorAuto(autoGui, trabajoGui);
        controladorTrabajo = new ControladorTrabajo(trabajoGui, aplicacionGui, controladorVenta, ventaGui);
        importarGui = new ImportarExcelGui();
        controladorImportarGui = new controladorImportarGui(importarGui);
        presupuestoGui = new PresupuestoGui();
        controladorPresupuesto = new ControladorPresupuesto(presupuestoGui, aplicacionGui);
        controladorPresupuesto.setCellEditor();
        controladorImportarGui = new controladorImportarGui(importarGui);
        presupuestoRealizadosGui = new PresupuestoRealizadosGui();
        controladorPresupuestosRealizados = new ControladorPresupuestosRealizados(aplicacionGui, presupuestoRealizadosGui,presupuestoGui,controladorPresupuesto);
        controladorCompra = new ControladorCompra(compraGui, aplicacionGui);
        controladorCliente = new ControladorCliente(clienteGui, aplicacionGui, ventaGui, presupuestoRealizadosGui, autoGui, controladorPresupuestosRealizados, controladorAuto);
        tocacambioGui = new tocaCambioGui(aplicacionGui);
        aplicacionGui.getContenedor().add(tocacambioGui);
        aplicacionGui.getContenedor().add(proveedorGui);
        aplicacionGui.getContenedor().add(articuloGui);
        aplicacionGui.getContenedor().add(importarGui);
        aplicacionGui.getContenedor().add(clienteGui);
        aplicacionGui.getContenedor().add(compraGui);
        aplicacionGui.getContenedor().add(ventaGui);
        aplicacionGui.getContenedor().add(cumpleGui);
        aplicacionGui.getContenedor().add(trabajoGui);
        aplicacionGui.getContenedor().add(autoGui);
        aplicacionGui.getContenedor().add(presupuestoGui);
        aplicacionGui.getContenedor().add(articulosSinStock);
        aplicacionGui.getContenedor().add(presupuestoRealizadosGui);
        aplicacionGui.getContenedor().add(cuentaCorrienteGui);
        aplicacionGui.setCursor(Cursor.DEFAULT_CURSOR);
        

    }

    public static void main(String[] args) throws InterruptedException, ClassNotFoundException, SQLException, JRException {

        if (!Base.hasConnection()) {
            Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/cacique", "tecpro", "tecpro");
        
        }
        ManejoIp manejoIp = new ManejoIp();
        manejoIp.crearIp();
        manejoIp.conseguirDatos();
        Base.close();
        if (!Base.hasConnection()) {
            try {
                Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://" + ManejoIp.ipServer + "/cacique", "tecpro", "tecpro");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Ocurrió un error, no se realizó la conexión con el servidor, verifique la conexión \n " + e.getMessage(), null, JOptionPane.ERROR_MESSAGE);
                new ConfigurarServerGui(null, true).setVisible(true);
                JOptionPane.showMessageDialog(null, "Se cerrará el programa", null, JOptionPane.ERROR_MESSAGE);
                System.exit(1);

            }
        }

        ControladorApliacion controladorAplicacion = new ControladorApliacion();

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == aplicacionGui.getArticulos()) {
            controladorArticulo.cargarTodos();
            articuloGui.setVisible(true);
            articuloGui.toFront();
        }
        if (ae.getSource() == aplicacionGui.getProveedores()) {
            controladorProveedor.cargarTodos();
            proveedorGui.setVisible(true);
            proveedorGui.toFront();
        }
        if (ae.getSource() == aplicacionGui.getImportar()) {
            controladorImportarGui.cargarProveedores();
            importarGui.setVisible(true);
            importarGui.toFront();
        }
        if (ae.getSource() == aplicacionGui.getCorriente()) {
            controladorCuentaCorriente.cargarTodos();
            cuentaCorrienteGui.setVisible(true);
            cuentaCorrienteGui.toFront();

        }
        if (ae.getSource() == aplicacionGui.getClientes()) {
            controladorCliente.cargarTodos();
            clienteGui.setVisible(true);
            clienteGui.toFront();
        }
        if (ae.getSource() == aplicacionGui.getCrearBackup()) {
            modulo = new Modulo();
            modulo.conectar();
            modulo.GuardarRutaBackup();
            modulo.CrearBackup();
            String dir = (new File(System.getProperty("user.dir")).getAbsolutePath());
            System.out.println(dir);
        }
        if (ae.getSource() == aplicacionGui.getCargarBackup()) {
            int confirmado = JOptionPane.showConfirmDialog(null, "¿Confirmas la restauración de la Base de Datos?");
            if (JOptionPane.OK_OPTION == confirmado) {
                modulo = new Modulo();
                modulo.conectarMySQL();
                modulo.AbrirRutaBackup();
                try {
                    modulo.RestaurarBackup();
                } catch (InterruptedException ex) {
                    Logger.getLogger(ControladorApliacion.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(ControladorApliacion.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            //final de restaurar backup//
        }
        if (aplicacionGui.getCambiosEmail() == ae.getSource()) {
            emailGui = new CargarDatosEmail(aplicacionGui, true);
            emailGui.setLocationRelativeTo(aplicacionGui);
            emailGui.setVisible(true);
        }

        if (aplicacionGui.getModificarUsuario() == ae.getSource()) {
            CambiarUsuarioGui cambiarUsuarioGui = new CambiarUsuarioGui(aplicacionGui, true);
            cambiarUsuarioGui.setLocationRelativeTo(aplicacionGui);
            cambiarUsuarioGui.setVisible(true);
        }
        if (aplicacionGui.getCerrarSesion() == ae.getSource()) {
            int ret = JOptionPane.showConfirmDialog(articuloGui, "¿Desea cerrar sesión?", null, JOptionPane.YES_NO_OPTION);
            if (ret == JOptionPane.YES_OPTION) {
                log.getLog().getPass().setText("");
                aplicacionGui.setVisible(false);
                log.getLog().setVisible(true);
            }
        }
        if (aplicacionGui.getEnviar() == ae.getSource()) {

            JFileChooser chooser = new JFileChooser();
            chooser.setApproveButtonText("Enviar");
            chooser.addChoosableFileFilter(new Modulo.SQLFilter());
            chooser.showOpenDialog(null);
            if (chooser.getSelectedFile() != null) {
                archivoBackup = chooser.getSelectedFile();
                selecEnviarBack = 1;
            } else if (chooser.getSelectedFile() == null) {
                selecEnviarBack = 0;
            }
            if (selecEnviarBack == 1) {
                envioEmailControlador = new EnvioEmailControlador();
                EnviarManualGui enviarGui = new EnviarManualGui(aplicacionGui, true, archivoBackup.getAbsolutePath());
                enviarGui.setLocationRelativeTo(aplicacionGui);
                enviarGui.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "No se seleccionó ningun archivo!");
            }

        }
        if (ae.getSource() == aplicacionGui.getRegistrarCompra()) {
            controladorCompra.cargarTodos();
            compraGui.setVisible(true);
            compraGui.toFront();
        }
        if (ae.getSource() == aplicacionGui.getRegistrarVenta()) {
            controladorVenta.cargarTodos();
            ventaGui.setVisible(true);
            ventaGui.toFront();
        }
        if (ae.getSource() == aplicacionGui.getConfigServer()) {
            new ConfigurarServerGui(aplicacionGui, true).setVisible(true);
        }
        if (ae.getSource() == aplicacionGui.getBotArtSinStock()) {
            controladorArtSinStock.realizarBusqueda();
            articulosSinStock.setVisible(true);
            articulosSinStock.toFront();
        }
        if (ae.getSource() == aplicacionGui.getBotCumple()) {
            cumpleGui.cargarCumple();
            cumpleGui.setVisible(true);
            cumpleGui.toFront();
        }
        if (ae.getSource() == aplicacionGui.getBotAuto()) {
            controladorAuto.cargarTodos();
            autoGui.setVisible(true);
            autoGui.toFront();
        }
        if (ae.getSource() == aplicacionGui.getBotTrabajos()) {
            controladorTrabajo.cargarTodos();
            trabajoGui.setVisible(true);
            trabajoGui.toFront();
        }
        if (ae.getSource() == aplicacionGui.getTocaCambio()) {
            tocacambioGui.setVisible(true);
            tocacambioGui.toFront();
        }
        if (ae.getSource() == aplicacionGui.getPresupuesto()) {
            // controladorPresupuesto.cargarTodos();
            presupuestoGui.setVisible(true);
            presupuestoGui.toFront();
        }
        if (ae.getSource() == aplicacionGui.getHistorialPresupuestos()) {
            presupuestoRealizadosGui.setVisible(true);
            presupuestoRealizadosGui.toFront();
        }
        if(ae.getSource()==aplicacionGui.getReconectarse()){
            abrirBase();
        }
    }

    private static class SQLFilter extends javax.swing.filechooser.FileFilter {

        final static String sql = "sql";

        public SQLFilter() {
        }

        public boolean accept(File f) {
            if (f.isDirectory()) {
                return true;
            }
            String s = f.getName();
            int i = s.lastIndexOf('.');

            if (i > 0 && i < s.length() - 1) {
                String extension = s.substring(i + 1).toLowerCase();
                if (sql.equals(extension)) {
                    return true;
                } else {
                    return false;
                }
            }
            return false;
        }

        @Override
        public String getDescription() {
            return "Archivos .sql";
        }
    }
    
        private void abrirBase() {
        if (!Base.hasConnection()) {
            try {
                
                Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://" + ManejoIp.ipServer + "/cacique", "tecpro", "tecpro");
                            Base.connection().setAutoCommit(true);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Ocurrió un error, no se realizó la conexión con el servidor, verifique la conexión \n " + e.getMessage(), null, JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
