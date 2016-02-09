/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.UIManager;

/**
 *
 * @author nico
 */
public class ImportarExcelGui extends javax.swing.JInternalFrame {

    /**
     * Creates new form ImportarExcelGui
     */
    public ImportarExcelGui() {
        initComponents();
    }

    public JButton getAceptar() {
        return aceptar;
    }

    public JButton getCancelar() {
        return cancelar;
    }

    public JComboBox getCategoria() {
        return categoria;
    }

    public JFileChooser getSelectorArchivos() {
        return selectorArchivos;
    }
    
    public void setActionListener(ActionListener lis){
        this.aceptar.addActionListener(lis);
        this.cancelar.addActionListener(lis);
        
    }

    public JProgressBar getProgreso() {
        return progreso;
    }

    public JComboBox getProveedor() {
        return proveedor;
    }

    public JCheckBox getPorcenAutomatico() {
        return porcenAutomatico;
    }

    public JTextField getPorcentaje() {
        return porcentaje;
    }
    
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        selectorArchivos = new javax.swing.JFileChooser();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        categoria = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        proveedor = new javax.swing.JComboBox();
        porcenAutomatico = new javax.swing.JCheckBox();
        porcentaje = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        cancelar = new javax.swing.JButton();
        aceptar = new javax.swing.JButton();
        progreso = new javax.swing.JProgressBar();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("Importar desde un excel");
        setPreferredSize(new java.awt.Dimension(668, 475));

        selectorArchivos.setControlButtonsAreShown(false);

        jLabel1.setText("Seleccione una categoría: ");

        categoria.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Artículos" }));

        jLabel2.setText("Proveedor de la lista :");

        porcenAutomatico.setText("Precio de venta automático");
        porcenAutomatico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                porcenAutomaticoActionPerformed(evt);
            }
        });

        porcentaje.setToolTipText("Porcentaje del precio de venta en base al de compra");
        porcentaje.setEnabled(false);

        jLabel3.setText("%");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(proveedor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(categoria, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(porcenAutomatico)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addGap(6, 6, 6)
                        .addComponent(porcentaje, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(categoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(porcenAutomatico)
                    .addComponent(porcentaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel2.setLayout(new java.awt.GridLayout(1, 0));

        cancelar.setText("Cancelar");
        jPanel2.add(cancelar);

        aceptar.setText("Aceptar");
        jPanel2.add(aceptar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(progreso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(selectorArchivos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(selectorArchivos, javax.swing.GroupLayout.PREFERRED_SIZE, 287, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(progreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void porcenAutomaticoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_porcenAutomaticoActionPerformed
        porcentaje.setEnabled(porcenAutomatico.isSelected());
    }//GEN-LAST:event_porcenAutomaticoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aceptar;
    private javax.swing.JButton cancelar;
    private javax.swing.JComboBox categoria;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JCheckBox porcenAutomatico;
    private javax.swing.JTextField porcentaje;
    private javax.swing.JProgressBar progreso;
    private javax.swing.JComboBox proveedor;
    private javax.swing.JFileChooser selectorArchivos;
    // End of variables declaration//GEN-END:variables
}
