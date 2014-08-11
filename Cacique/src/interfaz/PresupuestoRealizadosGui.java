/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.Calendar;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jacinto
 */
public class PresupuestoRealizadosGui extends javax.swing.JInternalFrame {

    private DefaultTableModel tablaFacturaDefault;
    private DefaultTableModel tablaFacturasDefault;

    /**
     * Creates new form VentaGui
     */
    public PresupuestoRealizadosGui() {
        initComponents();
        tablaFacturaDefault = (DefaultTableModel) tablaFactura.getModel();
        tablaFacturasDefault = (DefaultTableModel) tablaFacturas.getModel();
        Calendar miCalendario = Calendar.getInstance();
        java.util.Date eldia = miCalendario.getTime();
        int diaHoy = miCalendario.get(Calendar.DAY_OF_MONTH);
        int mes = miCalendario.get(Calendar.MONTH);
        int anio = miCalendario.get(Calendar.YEAR);
        calendarioFactura.setDate(Date.valueOf(anio + "-" + (mes + 1) + "-" + diaHoy));
        desde.setDateFormatString("yyyy-MM-dd");
        hasta.setDateFormatString("yyyy-MM-dd");
    }

    /**
     * Seteo el actionListener para los botones articulosALafactura,
     * clienteALafactura, facturaNueva, imprimir realizarVenta,
     * borrarArticulosSeleccionados, modificar
     *
     * @param
     * @return
     * @exception
     */
    public void setActionListener(ActionListener lis) {
        this.eliminarPresupuesto.addActionListener(lis);
    }

    public JDateChooser getDesde() {
        return desde;
    }

    public JDateChooser getHasta() {
        return hasta;
    }

    
    
    /**
     * Retorno la tabla factura con tipo TableModelDefault para pdoer realizar
     * inserciones y eliminaciones de filas más facilmente
     *
     * @param
     * @return DefaultTableModel
     * @exception
     */
    public DefaultTableModel getTablaFacturaDefault() {
        return tablaFacturaDefault;
    }

    /**
     * Retorno la tabla clientescon tipo TableModelDefault para pdoer realizar
     * inserciones y eliminaciones de filas más facilmente
     *
     * @param
     * @return DefaultTableModel
     * @exception
     */
    public DefaultTableModel getTablaFacturasDefault() {
        return tablaFacturasDefault;
    }

 
    /**
     * Retorno el campo del cliente de la factura
     *
     * @param
     * @return JTextField
     * @exception
     */
    public JTextField getClienteFactura() {
        return clienteFactura;
    }

    /**
     * Retorno boton facturaNueva en donde limpia los campos de la factura para
     * iniciar una nueva venta
     *
     * @param
     * @return JButton
     * @exception
     */
    public JButton getEliminarPresupuesto() {
        return eliminarPresupuesto;
    }

    /**
     * Retorno tabla factura con tipo JTable
     *
     * @param
     * @return JTable
     * @exception
     */
    public JTable getTablaFactura() {
        return tablaFactura;
    }

    /**
     * Retorno el campo totalFactura que contiene el resultado final del a
     * factura
     *
     * @param
     * @return JTextField
     * @exception
     */
    public JTextField getTotalFactura() {
        return totalFactura;
    }

    /**
     * Retorno el calendario que contiene la fecha de la factura
     *
     * @param
     * @return JDateChooser
     * @exception
     */
    public JDateChooser getCalendarioFactura() {
        return calendarioFactura;
    }

    public void limpiarVentana() {
        tablaFactura.clearSelection();
        while (tablaFactura.getRowCount() > 0) {
            tablaFacturaDefault.removeRow(0);
        }
        Calendar miCalendario = Calendar.getInstance();
        java.util.Date eldia = miCalendario.getTime();
        int diaHoy = miCalendario.get(Calendar.DAY_OF_MONTH);
        int mes = miCalendario.get(Calendar.MONTH);
        int anio = miCalendario.get(Calendar.YEAR);
        calendarioFactura.setDate(Date.valueOf(anio + "-" + (mes + 1) + "-" + diaHoy));
        clienteFactura.setText("");
        totalFactura.setText("");

    }

    public JTextField getCalenFacturaText() {
        return ((JTextField) calendarioFactura.getDateEditor().getUiComponent());
    }

    public JDateChooser getCalendarioDesde() {
        return desde;
    }

    public JDateChooser getCalendarioHasta() {
        return hasta;
    }

    public JTextField getFiltroNombre() {
        return filtroNombre;
    }

    public JTable getTablaFacturas() {
        return tablaFacturas;
    }

    public JTextField getCalenDesdeText(){
        return ((JTextField)desde.getDateEditor().getUiComponent());
    }
    
    public JTextField getCalenHastaText(){
        return ((JTextField)hasta.getDateEditor().getUiComponent());
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        fondoImagen = new javax.swing.JPanel();
        panelTitulo = new org.edisoncor.gui.panel.PanelImage();
        titulo = new javax.swing.JLabel();
        panelClientesAarticulos = new javax.swing.JPanel();
        panelImage1 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablaFacturas = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        filtroNombre = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        desde = new com.toedter.calendar.JDateChooser();
        hasta = new com.toedter.calendar.JDateChooser();
        panelFactura = new javax.swing.JPanel();
        labelCliente = new javax.swing.JLabel();
        clienteFactura = new javax.swing.JTextField();
        jScrollPane8 = new javax.swing.JScrollPane();
        tablaFactura = new javax.swing.JTable();
        labelTotal = new javax.swing.JLabel();
        totalFactura = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        calendarioFactura = new com.toedter.calendar.JDateChooser();
        panelControlFactura = new javax.swing.JPanel();
        eliminarPresupuesto = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Registro Presupuesto");
        setPreferredSize(new java.awt.Dimension(838, 440));

        jPanel1.setPreferredSize(new java.awt.Dimension(825, 448));

        jScrollPane1.setPreferredSize(new java.awt.Dimension(822, 448));

        fondoImagen.setPreferredSize(new java.awt.Dimension(820, 400));

        panelTitulo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelTitulo.setPreferredSize(new java.awt.Dimension(329, 49));

        titulo.setFont(new java.awt.Font("Century Schoolbook L", 3, 24)); // NOI18N
        titulo.setText("PRESUPUESTO");
        panelTitulo.add(titulo);

        panelClientesAarticulos.setBorder(null);

        panelImage1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Presupuestos realizados", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Schoolbook L", 3, 18))); // NOI18N

        tablaFacturas.setAutoCreateRowSorter(true);
        tablaFacturas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Cliente", "fecha"
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
        jScrollPane4.setViewportView(tablaFacturas);

        jLabel1.setFont(new java.awt.Font("Century Schoolbook L", 0, 14)); // NOI18N
        jLabel1.setText("Desde");

        jLabel2.setFont(new java.awt.Font("Century Schoolbook L", 0, 14)); // NOI18N
        jLabel2.setText("Nombre");

        filtroNombre.setToolTipText("filtrar facturas por nombre del cliente");

        jLabel4.setFont(new java.awt.Font("Century Schoolbook L", 0, 14)); // NOI18N
        jLabel4.setText("Hasta");

        desde.setDateFormatString("yyyy-MM-dd");

        hasta.setDateFormatString("yyyy-MM-dd");

        javax.swing.GroupLayout panelImage1Layout = new javax.swing.GroupLayout(panelImage1);
        panelImage1.setLayout(panelImage1Layout);
        panelImage1Layout.setHorizontalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelImage1Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGap(14, 14, 14)
                .addComponent(filtroNombre))
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelImage1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addComponent(hasta, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelImage1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(desde, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelImage1Layout.setVerticalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(filtroNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(desde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(hasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(8, 8, 8)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelClientesAarticulosLayout = new javax.swing.GroupLayout(panelClientesAarticulos);
        panelClientesAarticulos.setLayout(panelClientesAarticulosLayout);
        panelClientesAarticulosLayout.setHorizontalGroup(
            panelClientesAarticulosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImage1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelClientesAarticulosLayout.setVerticalGroup(
            panelClientesAarticulosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImage1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        panelFactura.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Factura", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Schoolbook L", 3, 18))); // NOI18N

        labelCliente.setFont(new java.awt.Font("Century Schoolbook L", 0, 14)); // NOI18N
        labelCliente.setText("Cliente");

        clienteFactura.setEditable(false);

        tablaFactura.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Cantidad", "Articulo","Descripcion", "Precio","Stock" ,"Importe"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.math.BigDecimal.class, java.lang.String.class, java.lang.String.class,java.math.BigDecimal.class,java.math.BigDecimal.class, java.math.BigDecimal.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane8.setViewportView(tablaFactura);

        labelTotal.setFont(new java.awt.Font("Century Schoolbook L", 0, 14)); // NOI18N
        labelTotal.setText("Total");

        totalFactura.setEditable(false);

        jLabel3.setFont(new java.awt.Font("Century Schoolbook L", 0, 14)); // NOI18N
        jLabel3.setText("Fecha");

        calendarioFactura.setDateFormatString("yyyy-MM-dd");

        javax.swing.GroupLayout panelFacturaLayout = new javax.swing.GroupLayout(panelFactura);
        panelFactura.setLayout(panelFacturaLayout);
        panelFacturaLayout.setHorizontalGroup(
            panelFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 527, Short.MAX_VALUE)
            .addGroup(panelFacturaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelCliente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(clienteFactura)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(4, 4, 4)
                .addComponent(calendarioFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFacturaLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(labelTotal)
                .addGap(0, 0, 0)
                .addComponent(totalFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelFacturaLayout.setVerticalGroup(
            panelFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFacturaLayout.createSequentialGroup()
                .addGroup(panelFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelCliente)
                        .addComponent(clienteFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3))
                    .addComponent(calendarioFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(totalFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelTotal))
                .addGap(4, 4, 4))
        );

        panelControlFactura.setLayout(new java.awt.GridLayout(1, 0));

        eliminarPresupuesto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaz/Icons/borrar.png"))); // NOI18N
        eliminarPresupuesto.setToolTipText("Eliminar Presupuesto");
        panelControlFactura.add(eliminarPresupuesto);

        javax.swing.GroupLayout fondoImagenLayout = new javax.swing.GroupLayout(fondoImagen);
        fondoImagen.setLayout(fondoImagenLayout);
        fondoImagenLayout.setHorizontalGroup(
            fondoImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 825, Short.MAX_VALUE)
            .addGroup(fondoImagenLayout.createSequentialGroup()
                .addComponent(panelClientesAarticulos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(fondoImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelFactura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelControlFactura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        fondoImagenLayout.setVerticalGroup(
            fondoImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fondoImagenLayout.createSequentialGroup()
                .addComponent(panelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(fondoImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(fondoImagenLayout.createSequentialGroup()
                        .addComponent(panelFactura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(0, 0, 0)
                        .addComponent(panelControlFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panelClientesAarticulos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        jScrollPane1.setViewportView(fondoImagen);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 827, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 827, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 827, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 412, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser calendarioFactura;
    private javax.swing.JTextField clienteFactura;
    private com.toedter.calendar.JDateChooser desde;
    private javax.swing.JButton eliminarPresupuesto;
    private javax.swing.JTextField filtroNombre;
    private javax.swing.JPanel fondoImagen;
    private com.toedter.calendar.JDateChooser hasta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JLabel labelCliente;
    private javax.swing.JLabel labelTotal;
    private javax.swing.JPanel panelClientesAarticulos;
    private javax.swing.JPanel panelControlFactura;
    private javax.swing.JPanel panelFactura;
    private javax.swing.JPanel panelImage1;
    private org.edisoncor.gui.panel.PanelImage panelTitulo;
    private javax.swing.JTable tablaFactura;
    private javax.swing.JTable tablaFacturas;
    private javax.swing.JLabel titulo;
    private javax.swing.JTextField totalFactura;
    // End of variables declaration//GEN-END:variables
}
