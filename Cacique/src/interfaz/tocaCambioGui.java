/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import controladores.ControladorAuto;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelos.Auto;
import modelos.Cliente;
import org.javalite.activejdbc.Base;
import org.javalite.activejdbc.LazyList;

/**
 *
 * @author nico
 */
public class tocaCambioGui extends javax.swing.JInternalFrame {

    DefaultTableModel tocaCambioDefault;
    AplicacionGui aplicacionGui;

    /**
     *
     * Creates new form CumpleaniosGui
     */
    public tocaCambioGui(AplicacionGui aplicacionGui) {
        initComponents();
        this.aplicacionGui = aplicacionGui;
        tocaCambioDefault = (DefaultTableModel) tocaCambio.getModel();
        tocaCambio.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaTocaCambio(evt);
            }
        });
        cargarCumple();

    }

    private void tablaTocaCambio(MouseEvent evt) {
        abrirAuto();
    }

    private void abrirAuto() {
        int row = tocaCambio.getSelectedRow();
        if (row > -1) {
            AutoGui hcg = new AutoGui();
            hcg.getBusqueda().setText((String) tocaCambio.getValueAt(tocaCambio.getSelectedRow(), 1));
            Trabajos t = new Trabajos();
            ControladorAuto hcc = new ControladorAuto(hcg, t);
            hcc.realizarBusquedaDuenio();
            aplicacionGui.getContenedor().add(hcg);
            hcg.setVisible(true);
            hcg.toFront();
        }
    }

    public void cargarCumple() {
        tocaCambioDefault.setRowCount(0);
        Date referenceDate = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(referenceDate);
        c.add(Calendar.MONTH, -6);
         Base.openTransaction();
        LazyList<Auto> auto = Auto.where("ult_cambio_aceite <= ?", c.getTime());
        Base.commitTransaction();
        Iterator<Auto> it = auto.iterator();
        while (it.hasNext()) {
            Auto au = it.next();
             Base.openTransaction();
            Cliente cli = Cliente.findById(au.get("cliente_id"));
            Base.commitTransaction();
            String cols[] = new String[8];
            cols[0] = au.getString("id");
            cols[1] = au.getString("patente");
            cols[2] = dateToMySQLDate(au.getDate("ult_cambio_aceite"), true);
            if(cli != null){
                cols[3] = cli.getString("nombre");
                cols[4] = cli.getString("telefono");
                cols[5] = cli.getString("celular");
                cols[6] = cli.getString("email");
                cols[7] = cli.getString("facebook");
            }
            tocaCambioDefault.addRow(cols);

        }
    }
    /*va true si se quiere usar para mostrarla por pantalla es decir 12/12/2014 y false si va 
     para la base de datos, es decir 2014/12/12*/

    public String dateToMySQLDate(Date fecha, boolean paraMostrar) {
        if (fecha != null) {
            if (paraMostrar) {
                java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
                return sdf.format(fecha);
            } else {
                java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
                return sdf.format(fecha);
            }
        } else {
            return "";
        }
    }

    public DefaultTableModel getTocaCambioDefault() {
        return tocaCambioDefault;
    }

    public JTable getCumpleHoy() {
        return tocaCambio;
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
        tocaCambio = new javax.swing.JTable();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Seis meses sin cambio de aceite");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Autos con mas de 6 meses sin cambio de aceite", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Schoolbook L", 1, 18))); // NOI18N

        tocaCambio.setAutoCreateRowSorter(true);
        tocaCambio.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id Auto", "Patente", "Ultimo Cambio", "Nombre Dueño", "Telefono", "Celular", "Email", "Facebook"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, true, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tocaCambio.setName("Cumple"); // NOI18N
        tocaCambio.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tocaCambio);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 717, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tocaCambio;
    // End of variables declaration//GEN-END:variables
}
