/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 *
 * @author nico
 */
public class AplicacionGui extends javax.swing.JFrame {

    /**
     * Creates new form AplicacionGui
     */
    public AplicacionGui() {
        initComponents();
    }

    public JDesktopPane getContenedor() {
        return contenedor;
    }

    public JButton getArticulos() {
        return articulos;
    }

    public void setActionListener(ActionListener lis) {
        this.getArticulos().addActionListener(lis);
        this.proveedores.addActionListener(lis);
        this.importar.addActionListener(lis);
        this.clientes.addActionListener(lis);
        this.cargarBackup.addActionListener(lis);
        this.crearBackup.addActionListener(lis);
        this.cambiosEmail.addActionListener(lis);
        this.modificarUsuario.addActionListener(lis);
        this.cerrarSesion.addActionListener(lis);
        this.enviar.addActionListener(lis);
        this.registrarCompra.addActionListener(lis);
        this.registrarVenta.addActionListener(lis);
        this.configServer.addActionListener(lis);
        this.botArtSinStock.addActionListener(lis);
        this.botCumple.addActionListener(lis);
        this.botAuto.addActionListener(lis);
        this.botTrabajos.addActionListener(lis);
        this.historialPresupuestos.addActionListener(lis);
        this.presupuesto.addActionListener(lis);
        this.tocaCambio.addActionListener(lis);
        this.corriente.addActionListener(lis);
        this.reconectarse.addActionListener(lis);
    }

    public JButton getHistorialPresupuestos() {
        return historialPresupuestos;
    }

    public JButton getPresupuesto() {
        return presupuesto;
    }

    public JButton getTocaCambio() {
        return tocaCambio;
    }
    
    public JMenuItem getReconectarse(){
        return reconectarse;
    }

    public JMenuItem getCerrarSesion() {
        return cerrarSesion;
    }

    public JButton getBotCumple() {
        return botCumple;
    }

    public JButton getBotTrabajos() {
        return botTrabajos;
    }

    public JMenuItem getEnviar() {
        return enviar;
    }

    
    public JMenuItem getModificarUsuario() {
        return modificarUsuario;
    }

    public JButton getProveedores() {
        return proveedores;
    }

    public JButton getImportar() {
        return importar;
    }

    public JButton getCorriente() {
        return corriente;
    }
    
    public JButton getClientes() {
        return clientes;
    }

    public JButton getBotArtSinStock() {
        return botArtSinStock;
    }

    public JMenuItem getCargarBackup() {
        return cargarBackup;
    }

    public JMenuItem getCrearBackup() {
        return crearBackup;
    }

    public JMenuItem getCambiosEmail() {
        return cambiosEmail;
    }

    public JButton getRegistrarCompra() {
        return registrarCompra;
    }

    public JButton getRegistrarVenta() {
        return registrarVenta;
    }

    public JMenuItem getConfigServer() {
        return configServer;
    }

    public JButton getBotAuto() {
        return botAuto;
    }

    public void setBotAuto(JButton botAuto) {
        this.botAuto = botAuto;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        contenedor = new javax.swing.JDesktopPane();
        panelBotones = new javax.swing.JPanel();
        articulos = new javax.swing.JButton();
        proveedores = new javax.swing.JButton();
        clientes = new javax.swing.JButton();
        registrarCompra = new javax.swing.JButton();
        registrarVenta = new javax.swing.JButton();
        importar = new javax.swing.JButton();
        botArtSinStock = new javax.swing.JButton();
        botCumple = new javax.swing.JButton();
        botAuto = new javax.swing.JButton();
        botTrabajos = new javax.swing.JButton();
        tocaCambio = new javax.swing.JButton();
        presupuesto = new javax.swing.JButton();
        historialPresupuestos = new javax.swing.JButton();
        corriente = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        cambiosEmail = new javax.swing.JMenuItem();
        modificarUsuario = new javax.swing.JMenuItem();
        cerrarSesion = new javax.swing.JMenuItem();
        salir = new javax.swing.JMenuItem();
        configServer = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        crearBackup = new javax.swing.JMenuItem();
        cargarBackup = new javax.swing.JMenuItem();
        enviar = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        tecPro = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        reconectarse = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Lubricentro");
        setIconImage(new ImageIcon(getClass().getResource("/interfaz/Icons/logo.jpg")).getImage());

        panelBotones.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelBotones.setLayout(new java.awt.GridLayout(1, 0));

        articulos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaz/Icons/productos.png"))); // NOI18N
        articulos.setToolTipText("Gestión de artículos");
        panelBotones.add(articulos);

        proveedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaz/Icons/proveedor.png"))); // NOI18N
        proveedores.setToolTipText("Gestión de proveedores");
        panelBotones.add(proveedores);

        clientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaz/Icons/clients.png"))); // NOI18N
        clientes.setToolTipText("Gestión de clientes");
        panelBotones.add(clientes);

        registrarCompra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaz/Icons/compras.png"))); // NOI18N
        registrarCompra.setToolTipText("Registrar Compra");
        panelBotones.add(registrarCompra);

        registrarVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaz/Icons/ventas.png"))); // NOI18N
        registrarVenta.setToolTipText("Registrar Venta");
        panelBotones.add(registrarVenta);

        importar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaz/Icons/importar.png"))); // NOI18N
        importar.setToolTipText("Importar datos desde Excel");
        panelBotones.add(importar);

        botArtSinStock.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaz/Icons/sinstock.png"))); // NOI18N
        botArtSinStock.setToolTipText("Artículos sin stock");
        panelBotones.add(botArtSinStock);

        botCumple.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaz/Icons/cumple.png"))); // NOI18N
        botCumple.setToolTipText("registros de cumpleaños");
        panelBotones.add(botCumple);

        botAuto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaz/Icons/auto.png"))); // NOI18N
        botAuto.setToolTipText("Gestión de automóviles");
        panelBotones.add(botAuto);

        botTrabajos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaz/Icons/rsz_1mecanico.png"))); // NOI18N
        botTrabajos.setToolTipText("Realizar trabajos");
        panelBotones.add(botTrabajos);

        tocaCambio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaz/Icons/rsz_mr-bees_icon_oil-can.png"))); // NOI18N
        tocaCambio.setToolTipText("Autos con más de 6 meses sin cambio de aceite");
        panelBotones.add(tocaCambio);

        presupuesto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaz/Icons/presupuesto.png"))); // NOI18N
        presupuesto.setToolTipText("Realizar presupuesto");
        panelBotones.add(presupuesto);

        historialPresupuestos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaz/Icons/historialPresupuestos.png"))); // NOI18N
        historialPresupuestos.setToolTipText("Presupuestos realizados");
        panelBotones.add(historialPresupuestos);

        corriente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaz/Icons/cuentacorriente.png"))); // NOI18N
        corriente.setToolTipText("Cuenta corriente");
        panelBotones.add(corriente);

        jMenu1.setText("Archivo");

        cambiosEmail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaz/Icons/mail.png"))); // NOI18N
        cambiosEmail.setText("Cambiar correo");
        jMenu1.add(cambiosEmail);

        modificarUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaz/Icons/user.png"))); // NOI18N
        modificarUsuario.setText("Modificar datos del usuario");
        jMenu1.add(modificarUsuario);

        cerrarSesion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaz/Icons/cerrar.png"))); // NOI18N
        cerrarSesion.setText("Cerrar sesión");
        jMenu1.add(cerrarSesion);

        salir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaz/Icons/apagar.png"))); // NOI18N
        salir.setText("Salir");
        salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirActionPerformed(evt);
            }
        });
        jMenu1.add(salir);

        configServer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaz/Icons/server.png"))); // NOI18N
        configServer.setText("Configurar servidor");
        jMenu1.add(configServer);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Backup");

        crearBackup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaz/Icons/guardarBack.png"))); // NOI18N
        crearBackup.setText("Crear Backup");
        jMenu2.add(crearBackup);

        cargarBackup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaz/Icons/subirBack.png"))); // NOI18N
        cargarBackup.setText("Cargar Backup");
        jMenu2.add(cargarBackup);

        enviar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaz/Icons/enviarEmail.png"))); // NOI18N
        enviar.setText("Enviar ");
        jMenu2.add(enviar);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Acerca de");

        tecPro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaz/Icons/acerca.png"))); // NOI18N
        tecPro.setText("Tec-Pro Software");
        tecPro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tecProActionPerformed(evt);
            }
        });
        jMenu3.add(tecPro);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Reconexión");

        reconectarse.setText("Reconectarse");
        jMenu4.add(reconectarse);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBotones, javax.swing.GroupLayout.DEFAULT_SIZE, 841, Short.MAX_VALUE)
            .addComponent(contenedor, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(contenedor, javax.swing.GroupLayout.DEFAULT_SIZE, 676, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
        int ret = JOptionPane.showConfirmDialog(this, "¿Desea salir de la aplicación?", "Cerrar aplicación", JOptionPane.YES_NO_OPTION);
        if (ret == JOptionPane.YES_OPTION) {
            System.exit(0);
        }

    }//GEN-LAST:event_salirActionPerformed

    private void tecProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tecProActionPerformed
        AcercaDe acercaDe = new AcercaDe(this, true);
        acercaDe.setLocationRelativeTo(this);
        acercaDe.setVisible(true);
    }//GEN-LAST:event_tecProActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton articulos;
    private javax.swing.JButton botArtSinStock;
    private javax.swing.JButton botAuto;
    private javax.swing.JButton botCumple;
    private javax.swing.JButton botTrabajos;
    private javax.swing.JMenuItem cambiosEmail;
    private javax.swing.JMenuItem cargarBackup;
    private javax.swing.JMenuItem cerrarSesion;
    private javax.swing.JButton clientes;
    private javax.swing.JMenuItem configServer;
    private javax.swing.JDesktopPane contenedor;
    private javax.swing.JButton corriente;
    private javax.swing.JMenuItem crearBackup;
    private javax.swing.JMenuItem enviar;
    private javax.swing.JButton historialPresupuestos;
    private javax.swing.JButton importar;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem modificarUsuario;
    private javax.swing.JPanel panelBotones;
    private javax.swing.JButton presupuesto;
    private javax.swing.JButton proveedores;
    private javax.swing.JMenuItem reconectarse;
    private javax.swing.JButton registrarCompra;
    private javax.swing.JButton registrarVenta;
    private javax.swing.JMenuItem salir;
    private javax.swing.JMenuItem tecPro;
    private javax.swing.JButton tocaCambio;
    // End of variables declaration//GEN-END:variables
}
