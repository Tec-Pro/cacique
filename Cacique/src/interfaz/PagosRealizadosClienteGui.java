/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;
import java.math.RoundingMode;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.table.DefaultTableModel;
import modelos.Cliente;
import modelos.Pago;

/**
 *
 * @author jacinto
 */
public class PagosRealizadosClienteGui extends javax.swing.JInternalFrame {
    /**
     * Creates new form PagosRealizadosClienteGui
     */
    public PagosRealizadosClienteGui() {
        initComponents();           
    }

    public void setCliente(Cliente c){
        Iterator<Pago> it = c.getAll(Pago.class).iterator();
        DefaultTableModel pagosDefault = (DefaultTableModel) pagosRealizados.getModel();
        pagosDefault.setRowCount(0);
        while (it.hasNext()) {
            Pago pago = it.next();
            Object row[] = new String[3];
            Date sqlFecha = pago.getDate("fecha");
            SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
            row[0] = sdf.format(sqlFecha);
            row[1] = pago.getBigDecimal("monto").setScale(2, RoundingMode.CEILING).toString();
            row[2]= pago.getString("descripcion");
            pagosDefault.addRow(row);
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        pagosRealizados = new javax.swing.JTable();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Pagos realizados por el cliente");

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
        jScrollPane3.setViewportView(pagosRealizados);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable pagosRealizados;
    // End of variables declaration//GEN-END:variables
}
