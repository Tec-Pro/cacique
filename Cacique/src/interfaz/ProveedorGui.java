/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelos.Articulo;
import modelos.Proveedor;

/**
 *
 * @author nico
 */
public class ProveedorGui extends javax.swing.JInternalFrame {
    
        private DefaultTableModel proveedores;
        private DefaultTableModel pagosDefault;

    /**
     * Creates new form ProveedorGui
     */
    public ProveedorGui() {
        initComponents();
        
        proveedores = (DefaultTableModel) tablaProveedores.getModel(); //convierto la tabla
        pagosDefault = (DefaultTableModel) pagosRealizados.getModel();

    }

    public JButton getArticulosProvee() {
        return ArticulosProvee;
    }

    public JButton getComprasRealizadas() {
        return comprasRealizadas;
    }

    public JTextField getCelular() {
        return celular;
    }

    public JTextField getCuit() {
        return cuit;
    }

    public JTextField getDireccion() {
        return direccion;
    }

    public JTextField getEmail() {
        return email;
    }

    public JTextField getFormaPago() {
        return formaPago;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        panelProveedores = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaProveedores = new javax.swing.JTable();
        busqueda = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        nombre = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        telefono = new javax.swing.JTextField();
        id = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cuenta = new javax.swing.JTextField();
        modCuenta = new javax.swing.JCheckBox();
        jLabel5 = new javax.swing.JLabel();
        email = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        celular = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        direccion = new javax.swing.JTextField();
        formaPago = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        cuit = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        comprasRealizadas = new javax.swing.JButton();
        ArticulosProvee = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        borrar = new javax.swing.JButton();
        nuevo = new javax.swing.JButton();
        modificar = new javax.swing.JButton();
        realizarPago = new javax.swing.JButton();
        guardar = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        pagosRealizados = new javax.swing.JTable();
        borrarPago = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Gestión de Proveedores");
        setPreferredSize(new java.awt.Dimension(840, 570));

        panelProveedores.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Proveedores", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Schoolbook L", 3, 15))); // NOI18N

        tablaProveedores.setAutoCreateRowSorter(true);
        tablaProveedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Cuit"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaProveedores.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(tablaProveedores);

        busqueda.setToolTipText("Búsqueda personalizada");

        javax.swing.GroupLayout panelProveedoresLayout = new javax.swing.GroupLayout(panelProveedores);
        panelProveedores.setLayout(panelProveedoresLayout);
        panelProveedoresLayout.setHorizontalGroup(
            panelProveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE)
            .addComponent(busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        panelProveedoresLayout.setVerticalGroup(
            panelProveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelProveedoresLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos del proveedor", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Schoolbook L", 3, 15))); // NOI18N

        nombre.setDisabledTextColor(new java.awt.Color(16, 2, 245));
        nombre.setEnabled(false);

        jLabel1.setText("Nombre");

        jLabel2.setText("Telefono");

        telefono.setDisabledTextColor(new java.awt.Color(16, 2, 245));
        telefono.setEnabled(false);
        telefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                telefonoActionPerformed(evt);
            }
        });

        id.setDisabledTextColor(new java.awt.Color(16, 2, 245));
        id.setEnabled(false);

        jLabel3.setText("ID");

        jLabel4.setText("Cuenta");

        cuenta.setDisabledTextColor(new java.awt.Color(16, 2, 245));
        cuenta.setEnabled(false);
        cuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cuentaActionPerformed(evt);
            }
        });

        modCuenta.setText("modificar");
        modCuenta.setEnabled(false);
        modCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modCuentaActionPerformed(evt);
            }
        });

        jLabel5.setText("Email");

        email.setDisabledTextColor(new java.awt.Color(16, 2, 245));
        email.setEnabled(false);
        email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailActionPerformed(evt);
            }
        });

        jLabel6.setText("Celular");

        celular.setDisabledTextColor(new java.awt.Color(16, 2, 245));
        celular.setEnabled(false);
        celular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                celularActionPerformed(evt);
            }
        });

        jLabel7.setText("Dirección");

        direccion.setDisabledTextColor(new java.awt.Color(16, 2, 245));
        direccion.setEnabled(false);
        direccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                direccionActionPerformed(evt);
            }
        });

        formaPago.setDisabledTextColor(new java.awt.Color(16, 2, 245));
        formaPago.setEnabled(false);
        formaPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                formaPagoActionPerformed(evt);
            }
        });

        jLabel8.setText("Forma de pago");

        cuit.setDisabledTextColor(new java.awt.Color(16, 2, 245));
        cuit.setEnabled(false);
        cuit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cuitActionPerformed(evt);
            }
        });

        jLabel9.setText("Cuit");

        comprasRealizadas.setText("Compras realizadas");
        comprasRealizadas.setEnabled(false);
        comprasRealizadas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comprasRealizadasActionPerformed(evt);
            }
        });

        ArticulosProvee.setText("Artículos que provee");
        ArticulosProvee.setEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel3)
                                .addComponent(jLabel1))
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(email, javax.swing.GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6)
                                .addGap(6, 6, 6))
                            .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(cuenta, javax.swing.GroupLayout.DEFAULT_SIZE, 397, Short.MAX_VALUE)
                                    .addComponent(telefono, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nombre, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(48, 48, 48))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(46, 46, 46)
                        .addComponent(cuit))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(direccion)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(celular)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(formaPago))
                    .addComponent(comprasRealizadas, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modCuenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ArticulosProvee, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(comprasRealizadas)
                        .addGap(1, 1, 1)
                        .addComponent(ArticulosProvee)
                        .addGap(0, 0, 0)
                        .addComponent(modCuenta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(celular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(0, 0, 0)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(formaPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(telefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(1, 1, 1)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(cuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(0, 0, 0)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(direccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cuit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel4.setLayout(new java.awt.GridLayout(0, 1));

        borrar.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        borrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaz/Icons/borrar.png"))); // NOI18N
        borrar.setToolTipText("Borrar proveedor seleccionado");
        borrar.setEnabled(false);
        borrar.setPreferredSize(new java.awt.Dimension(55, 33));
        jPanel4.add(borrar);

        nuevo.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        nuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaz/Icons/nuevo.png"))); // NOI18N
        nuevo.setToolTipText("Crear proveedor nuevo");
        nuevo.setPreferredSize(new java.awt.Dimension(55, 33));
        jPanel4.add(nuevo);

        modificar.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaz/Icons/modificar.png"))); // NOI18N
        modificar.setToolTipText("Modificar proveedor seleccionado");
        modificar.setEnabled(false);
        modificar.setPreferredSize(new java.awt.Dimension(55, 33));
        modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarActionPerformed(evt);
            }
        });
        jPanel4.add(modificar);

        realizarPago.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        realizarPago.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaz/Icons/pagar.png"))); // NOI18N
        realizarPago.setToolTipText("Realizar pago a proveedor");
        realizarPago.setEnabled(false);
        realizarPago.setPreferredSize(new java.awt.Dimension(55, 33));
        jPanel4.add(realizarPago);

        guardar.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaz/Icons/guardar.png"))); // NOI18N
        guardar.setToolTipText("Guardar cambios realizados");
        guardar.setEnabled(false);
        guardar.setPreferredSize(new java.awt.Dimension(55, 33));
        jPanel4.add(guardar);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pagos realizados", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Schoolbook L", 3, 14))); // NOI18N

        pagosRealizados.setAutoCreateRowSorter(true);
        pagosRealizados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fecha", "Cantidad", "Descripcion"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        pagosRealizados.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane3.setViewportView(pagosRealizados);

        borrarPago.setText("Borrar pago seleccionado");
        borrarPago.setEnabled(false);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(borrarPago, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(borrarPago))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(panelProveedores, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(0, 0, 0))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelProveedores, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        jScrollPane1.setViewportView(jPanel1);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void telefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_telefonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_telefonoActionPerformed

    private void modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_modificarActionPerformed

    private void cuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cuentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cuentaActionPerformed

    private void modCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modCuentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_modCuentaActionPerformed

    private void emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailActionPerformed

    private void celularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_celularActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_celularActionPerformed

    private void direccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_direccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_direccionActionPerformed

    private void formaPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_formaPagoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_formaPagoActionPerformed

    private void cuitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cuitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cuitActionPerformed

    private void comprasRealizadasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comprasRealizadasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comprasRealizadasActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ArticulosProvee;
    private javax.swing.JButton borrar;
    private javax.swing.JButton borrarPago;
    private javax.swing.JTextField busqueda;
    private javax.swing.JTextField celular;
    private javax.swing.JButton comprasRealizadas;
    private javax.swing.JTextField cuenta;
    private javax.swing.JTextField cuit;
    private javax.swing.JTextField direccion;
    private javax.swing.JTextField email;
    private javax.swing.JTextField formaPago;
    private javax.swing.JButton guardar;
    private javax.swing.JTextField id;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JCheckBox modCuenta;
    private javax.swing.JButton modificar;
    private javax.swing.JTextField nombre;
    private javax.swing.JButton nuevo;
    private javax.swing.JTable pagosRealizados;
    private javax.swing.JPanel panelProveedores;
    private javax.swing.JButton realizarPago;
    private javax.swing.JTable tablaProveedores;
    private javax.swing.JTextField telefono;
    // End of variables declaration//GEN-END:variables

    public void setActionListener(ActionListener lis) {
        this.guardar.addActionListener(lis);
        this.borrar.addActionListener(lis);
        this.nuevo.addActionListener(lis);
        this.modificar.addActionListener(lis);
        this.realizarPago.addActionListener(lis);
        this.borrarPago.addActionListener(lis);        
//        this.exportar.addActionListener(lis);
        this.modCuenta.addActionListener(lis);
        this.ArticulosProvee.addActionListener(lis);
        this.comprasRealizadas.addActionListener(lis);

    }



    
        public void habilitarCampos(boolean b) {
        nombre.setEnabled(b);
        telefono.setEnabled(b);
        modCuenta.setEnabled(b);
        celular.setEnabled(b);
        email.setEnabled(b);
        direccion.setEnabled(b);
        formaPago.setEnabled(b);
        cuit.setEnabled(b);
        comprasRealizadas.setEnabled(b);
        ArticulosProvee.setEnabled(b);
        
        }
        
        public void limpiarCampos() {
        id.setText("");
        nombre.setText("");
        telefono.setText("");
        celular.setText("");
        email.setText("");
        direccion.setText("");
        formaPago.setText("");
        cuit.setText("");
        
        
        pagosDefault.setRowCount(0);
        modCuenta.setSelected(false);
        cuenta.setText("");
        cuenta.setEnabled(false);
        }
        
        public void CargarCampos(Proveedor prov) {
        id.setText(prov.getString("id"));
        nombre.setText(prov.getString("nombre"));
        telefono.setText(prov.getString("telefono"));
        cuenta.setText(prov.getString("cuenta_corriente"));
        celular.setText(prov.getString("celular"));
        email.setText(prov.getString("email"));
        direccion.setText(prov.getString("direccion"));
        formaPago.setText(prov.getString("forma_de_pago"));
        cuit.setText(prov.getString("cuit"));
        
        }



    public DefaultTableModel getProveedores() {
        return proveedores;
    }

    public JButton getBorrar() {
        return borrar;
    }

    public JButton getGuardar() {
        return guardar;
    }

    public JTextField getId() {
        return id;
    }

    public JButton getModificar() {
        return modificar;
    }

    public JTextField getNombre() {
        return nombre;
    }

    public JButton getNuevo() {
        return nuevo;
    }



    public JTable getTablaProveedores() {
        return tablaProveedores;
    }

    public JTextField getBusqueda() {
        return busqueda;
    }

    public JTextField getTelefono() {
        return telefono;
    }

//    public JButton getExportar() {
//        return exportar;
//    }

    public DefaultTableModel getPagosDefault() {
        return pagosDefault;
    }

    public void setPagosDefault(DefaultTableModel pagosDefault) {
        this.pagosDefault = pagosDefault;
    }




    public JTable getPagosRealizados() {
        return pagosRealizados;
    }

    public void setPagosRealizados(JTable pagosRealizados) {
        this.pagosRealizados = pagosRealizados;
    }

    public JButton getRealizarPago() {
        return realizarPago;
    }

    public void setRealizarPago(JButton realizarPago) {
        this.realizarPago = realizarPago;
    }

    public JButton getBorrarPago() {
        return borrarPago;
    }

    public JTextField getCuenta() {
        return cuenta;
    }

    public JCheckBox getModCuenta() {
        return modCuenta;
    }




    
}
