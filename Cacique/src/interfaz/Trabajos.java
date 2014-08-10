/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package interfaz;

import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionListener;
import java.util.Calendar;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelos.Auto;
import modelos.Cliente;
import modelos.Trabajo;

/**
 *
 * @author nico
 */
public class Trabajos extends javax.swing.JInternalFrame {
    DefaultTableModel autosDefault;
    Cliente clienteModel;
    Auto autoModel;
    /**
     * Creates new form Trabajos
     */
    public Trabajos() {
        initComponents();
        autosDefault= (DefaultTableModel) tablaAutos.getModel();
    }

     public void setActionListener(ActionListener lis) {
        this.guardar.addActionListener(lis);
        this.borrar.addActionListener(lis);
        this.nuevo.addActionListener(lis);
        this.modificar.addActionListener(lis);
        this.venta.addActionListener(lis);
     }
     
     public void bloquearCampos(Boolean b){
         anticongelante.setEnabled(b);
         bombaAgua.setEnabled(b);
         cambioBateria.setEnabled(b);
         correaDistrib.setEnabled(b);
         correaMulticanal.setEnabled(b);
         costoTotal.setEnabled(b);
         descripcionBateria.setEnabled(b);
         fecha.setEnabled(b);
         filtroAceite.setEnabled(b);
         aceiteCaja.setEnabled(b);
         aceiteDif.setEnabled(b);
         filtroAire.setEnabled(b);
         filtroCombustible.setEnabled(b);
         filtroHabitaculo.setEnabled(b);
         kilometraje.setEnabled(b);
         liquidoFreno.setEnabled(b);
         observGeneral.setEnabled(b);
         observaciones.setEnabled(b);
         precioBateria.setEnabled(b);
         tensorMulticanal.setEnabled(b);
         tensores.setEnabled(b);
         tipoBateria.setEnabled(b);
         tablaAutos.setEnabled(b);
         if(b)
            actualizarBateria();
         
     }

    public JLabel getIdTrabajo() {
        return idTrabajo;
    }

    public Cliente getClienteModel() {
        return clienteModel;
    }

    public Auto getAutoModel() {
        return autoModel;
    }

    public void setClienteModel(Cliente clienteModel) {
        this.clienteModel = clienteModel;
    }

    public void setAutoModel(Auto autoModel) {
        this.autoModel = autoModel;
    }
     
     public void limpiarCampos(){
               anticongelante.setSelected(false);
         bombaAgua.setSelected(false);
         cambioBateria.setSelected(false);
         correaDistrib.setSelected(false);
         correaMulticanal.setSelected(false);
         costoTotal.setText("");
         descripcionBateria.setText("");
         fecha.setDate(Calendar.getInstance().getTime());
         filtroAceite.setSelected(false);
         aceiteCaja.setSelected(false);
         aceiteDif.setSelected(false);
         filtroAire.setSelected(false);
         filtroCombustible.setSelected(false);
         filtroHabitaculo.setSelected(false);
         kilometraje.setText("");
         liquidoFreno.setSelected(false);
         observGeneral.setText("");
         observaciones.setText("");
         precioBateria.setText("");
         tensorMulticanal.setSelected(false);
         tensores.setSelected(false);
         tipoBateria.setText("");  
         duenio.setText("");
         auto.setText("");
         idTrabajo.setText("");
     }

     
     public void cargarTrabajo(Trabajo t){
         anticongelante.setSelected(t.getBoolean("anticongelante"));
         bombaAgua.setSelected(t.getBoolean("bomba_agua"));
         cambioBateria.setSelected(t.getBoolean("bateria"));
         correaDistrib.setSelected(t.getBoolean("correa_distribucion"));
         correaMulticanal.setSelected(t.getBoolean("correa_multicanal"));
         costoTotal.setText(t.getBigDecimal("costo").toString());
         descripcionBateria.setText(t.getString("descripcion_bateria"));
         fecha.setDate(t.getDate("fecha"));
         filtroAceite.setSelected(t.getBoolean("filtro_aceite"));
         aceiteCaja.setSelected(t.getBoolean("aceite_caja"));
         aceiteDif.setSelected(t.getBoolean("aceite_diferencial"));
         filtroAire.setSelected(t.getBoolean("filtro_aire"));
         filtroCombustible.setSelected(t.getBoolean("filtro_combustible"));
         filtroHabitaculo.setSelected(t.getBoolean("filtro_habitaculo"));
         kilometraje.setText(t.getString("kilometraje"));
         liquidoFreno.setSelected(t.getBoolean("liquido_freno"));
         observGeneral.setText(t.getString("descripcion_adicional"));
         observaciones.setText(t.getString("observaciones"));
         precioBateria.setText(t.getBigDecimal("importe_bateria").toString());
         tensorMulticanal.setSelected(t.getBoolean("tensor_correa_multicanal"));
         tensores.setSelected(t.getBoolean("tensores"));
         tipoBateria.setText(t.getString("tipo_bateria"));
         duenio.setText(t.parent(Cliente.class).getString("nombre"));
         auto.setText(t.parent(Auto.class).getString("patente"));
         actualizarBateria();
         Object idTrab= t.getId();
         if(idTrab!=null){
             idTrabajo.setText(idTrab.toString());
         }
     }

     private void  actualizarBateria(){
          descripcionBateria.setEnabled(cambioBateria.isSelected());
       precioBateria.setEnabled(cambioBateria.isSelected());
       tipoBateria.setEnabled(cambioBateria.isSelected());
       if(!cambioBateria.isSelected()){
              precioBateria.setText("");
                descripcionBateria.setText("");
        tipoBateria.setText("");
       }
       
     }
    public DefaultTableModel getAutosDefault() {
        return autosDefault;
    }



    public JCheckBox getAceiteCaja() {
        return aceiteCaja;
    }

    public JCheckBox getAceiteDif() {
        return aceiteDif;
    }

    public JCheckBox getAnticongelante() {
        return anticongelante;
    }

    public JLabel getAuto() {
        return auto;
    }

    public JCheckBox getBombaAgua() {
        return bombaAgua;
    }

    public JButton getBorrar() {
        return borrar;
    }

    public JButton getBotImprimir() {
        return botImprimir;
    }

    public JTextField getBusquedaAuto() {
        return busquedaAuto;
    }

  

    public JCheckBox getCambioBateria() {
        return cambioBateria;
    }

    public JCheckBox getCorreaDistrib() {
        return correaDistrib;
    }

    public JCheckBox getCorreaMulticanal() {
        return correaMulticanal;
    }

    public JTextField getCostoTotal() {
        return costoTotal;
    }

    public JTextField getDescripcionBateria() {
        return descripcionBateria;
    }

    public JLabel getDuenio() {
        return duenio;
    }

    public JDateChooser getFecha() {
        return fecha;
    }

    public JCheckBox getFiltroAceite() {
        return filtroAceite;
    }

    public JCheckBox getFiltroAire() {
        return filtroAire;
    }

    public JCheckBox getFiltroCombustible() {
        return filtroCombustible;
    }

    public JCheckBox getFiltroHabitaculo() {
        return filtroHabitaculo;
    }

    public JButton getGuardar() {
        return guardar;
    }

    public JTextField getKilometraje() {
        return kilometraje;
    }

    public JCheckBox getLiquidoFreno() {
        return liquidoFreno;
    }

    public JButton getModificar() {
        return modificar;
    }

    public JButton getNuevo() {
        return nuevo;
    }

    public JTextArea getObservGeneral() {
        return observGeneral;
    }

    public JTextArea getObservaciones() {
        return observaciones;
    }

    public JTextField getPrecioBateria() {
        return precioBateria;
    }

    public JTable getTablaAutos() {
        return tablaAutos;
    }

 

    public JCheckBox getTensorMulticanal() {
        return tensorMulticanal;
    }

    public JCheckBox getTensores() {
        return tensores;
    }

    public JTextField getTipoBateria() {
        return tipoBateria;
    }

    public JButton getVenta() {
        return venta;
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
        jPanel3 = new javax.swing.JPanel();
        busquedaAuto = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaAutos = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        duenio = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        auto = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        fecha = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        kilometraje = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        observaciones = new javax.swing.JTextArea();
        jPanel6 = new javax.swing.JPanel();
        filtroHabitaculo = new javax.swing.JCheckBox();
        filtroAceite = new javax.swing.JCheckBox();
        filtroCombustible = new javax.swing.JCheckBox();
        filtroAire = new javax.swing.JCheckBox();
        aceiteDif = new javax.swing.JCheckBox();
        aceiteCaja = new javax.swing.JCheckBox();
        liquidoFreno = new javax.swing.JCheckBox();
        anticongelante = new javax.swing.JCheckBox();
        correaMulticanal = new javax.swing.JCheckBox();
        tensorMulticanal = new javax.swing.JCheckBox();
        correaDistrib = new javax.swing.JCheckBox();
        tensores = new javax.swing.JCheckBox();
        bombaAgua = new javax.swing.JCheckBox();
        jPanel7 = new javax.swing.JPanel();
        cambioBateria = new javax.swing.JCheckBox();
        jLabel6 = new javax.swing.JLabel();
        tipoBateria = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        descripcionBateria = new javax.swing.JTextField();
        precioBateria = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        observGeneral = new javax.swing.JTextArea();
        venta = new javax.swing.JButton();
        costoTotal = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        idTrabajo = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        borrar = new javax.swing.JButton();
        nuevo = new javax.swing.JButton();
        modificar = new javax.swing.JButton();
        guardar = new javax.swing.JButton();
        botImprimir = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Trabajos");
        setPreferredSize(new java.awt.Dimension(940, 573));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Autos del cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Schoolbook L", 1, 18))); // NOI18N

        tablaAutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Auto", "Patente", "Modelo", "ID Dueño", "Dueño", "DNI dueño"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaAutos.setEnabled(false);
        jScrollPane3.setViewportView(tablaAutos);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(busquedaAuto, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(busquedaAuto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3))
        );

        jLabel1.setText("Dueño");

        duenio.setFont(new java.awt.Font("Cantarell", 1, 18)); // NOI18N
        duenio.setForeground(new java.awt.Color(41, 178, 45));

        jLabel2.setText("auto");

        auto.setFont(new java.awt.Font("Cantarell", 1, 18)); // NOI18N
        auto.setForeground(new java.awt.Color(41, 178, 45));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel1)
                .addGap(4, 4, 4)
                .addComponent(duenio, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(auto, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(103, 103, 103))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(duenio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(auto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Trabajo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Schoolbook L", 1, 14))); // NOI18N

        jLabel3.setText("Fecha");

        fecha.setEnabled(false);

        jLabel4.setText("Kilometraje");

        kilometraje.setEnabled(false);

        jLabel5.setText("Observaciones");

        jScrollPane5.setAutoscrolls(true);

        observaciones.setColumns(14);
        observaciones.setRows(7);
        observaciones.setEnabled(false);
        jScrollPane5.setViewportView(observaciones);

        jPanel6.setLayout(new java.awt.GridLayout(7, 3, 1, 1));

        filtroHabitaculo.setText("Cambio filtro habitáculo");
        filtroHabitaculo.setEnabled(false);
        jPanel6.add(filtroHabitaculo);

        filtroAceite.setText("Cambio filtro aceite");
        filtroAceite.setEnabled(false);
        jPanel6.add(filtroAceite);

        filtroCombustible.setText("Cambio filtro combustible");
        filtroCombustible.setEnabled(false);
        jPanel6.add(filtroCombustible);

        filtroAire.setText("Cambio filtro aire");
        filtroAire.setEnabled(false);
        jPanel6.add(filtroAire);

        aceiteDif.setText("Cambio aceite diferencial");
        aceiteDif.setEnabled(false);
        jPanel6.add(aceiteDif);

        aceiteCaja.setText("Cambio aceite caja");
        aceiteCaja.setEnabled(false);
        jPanel6.add(aceiteCaja);

        liquidoFreno.setText("Cambio líquido freno");
        liquidoFreno.setEnabled(false);
        jPanel6.add(liquidoFreno);

        anticongelante.setText("Cambio anticongelante");
        anticongelante.setEnabled(false);
        jPanel6.add(anticongelante);

        correaMulticanal.setText("Cambio correa multicanal");
        correaMulticanal.setEnabled(false);
        jPanel6.add(correaMulticanal);

        tensorMulticanal.setText("Tensor correa multicanal");
        tensorMulticanal.setEnabled(false);
        jPanel6.add(tensorMulticanal);

        correaDistrib.setText("Cambio Correa distrib");
        correaDistrib.setEnabled(false);
        jPanel6.add(correaDistrib);

        tensores.setText("Cambio tensores");
        tensores.setEnabled(false);
        jPanel6.add(tensores);

        bombaAgua.setText("Cambio bomba de agua");
        bombaAgua.setEnabled(false);
        jPanel6.add(bombaAgua);

        cambioBateria.setText("Cambio bateria");
        cambioBateria.setEnabled(false);
        cambioBateria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cambioBateriaActionPerformed(evt);
            }
        });

        jLabel6.setText("Tipo de bateria");

        tipoBateria.setEnabled(false);

        jLabel8.setText("Descripcion de bateria");

        descripcionBateria.setEnabled(false);

        precioBateria.setEnabled(false);

        jLabel7.setText("Precio bateria");

        jLabel10.setText("Observacion general");

        observGeneral.setColumns(20);
        observGeneral.setRows(5);
        observGeneral.setEnabled(false);
        jScrollPane4.setViewportView(observGeneral);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(cambioBateria, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(precioBateria, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(descripcionBateria, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tipoBateria, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4)))
                .addGap(0, 0, 0))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(precioBateria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addComponent(cambioBateria, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(descripcionBateria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tipoBateria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        venta.setText("Realizarle una venta");

        costoTotal.setEnabled(false);

        jLabel9.setText("Costo total");

        jLabel11.setText("ID trabajo");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(costoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(venta, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(kilometraje, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(idTrabajo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(kilometraje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11)
                        .addComponent(idTrabajo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane5))
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(costoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(venta))
                    .addComponent(jLabel9))
                .addGap(5, 5, 5))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel8.setLayout(new java.awt.GridLayout(1, 0));

        borrar.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        borrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaz/Icons/borrar.png"))); // NOI18N
        borrar.setToolTipText("Borrar cliente seleccionado");
        borrar.setEnabled(false);
        borrar.setPreferredSize(new java.awt.Dimension(55, 33));
        jPanel8.add(borrar);

        nuevo.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        nuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaz/Icons/nuevo.png"))); // NOI18N
        nuevo.setToolTipText("Crear cliente nuevo");
        nuevo.setPreferredSize(new java.awt.Dimension(55, 33));
        jPanel8.add(nuevo);

        modificar.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaz/Icons/modificar.png"))); // NOI18N
        modificar.setToolTipText("Modificar cliente seleccionado");
        modificar.setEnabled(false);
        modificar.setPreferredSize(new java.awt.Dimension(55, 33));
        modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarActionPerformed(evt);
            }
        });
        jPanel8.add(modificar);

        guardar.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaz/Icons/guardar.png"))); // NOI18N
        guardar.setToolTipText("Guardar cambios realizados");
        guardar.setEnabled(false);
        guardar.setPreferredSize(new java.awt.Dimension(55, 33));
        jPanel8.add(guardar);

        botImprimir.setText("Imprimir");
        botImprimir.setToolTipText("Imprimir tarjeta");
        botImprimir.setEnabled(false);
        jPanel8.add(botImprimir);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 593, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0))
        );

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1)
                .addGap(3, 3, 3))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_modificarActionPerformed

    private void cambioBateriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cambioBateriaActionPerformed
       descripcionBateria.setEnabled(cambioBateria.isSelected());
       precioBateria.setEnabled(cambioBateria.isSelected());
       tipoBateria.setEnabled(cambioBateria.isSelected());
       precioBateria.setText("");
       descripcionBateria.setText("");
       tipoBateria.setText("");
    }//GEN-LAST:event_cambioBateriaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox aceiteCaja;
    private javax.swing.JCheckBox aceiteDif;
    private javax.swing.JCheckBox anticongelante;
    private javax.swing.JLabel auto;
    private javax.swing.JCheckBox bombaAgua;
    private javax.swing.JButton borrar;
    private javax.swing.JButton botImprimir;
    private javax.swing.JTextField busquedaAuto;
    private javax.swing.JCheckBox cambioBateria;
    private javax.swing.JCheckBox correaDistrib;
    private javax.swing.JCheckBox correaMulticanal;
    private javax.swing.JTextField costoTotal;
    private javax.swing.JTextField descripcionBateria;
    private javax.swing.JLabel duenio;
    private com.toedter.calendar.JDateChooser fecha;
    private javax.swing.JCheckBox filtroAceite;
    private javax.swing.JCheckBox filtroAire;
    private javax.swing.JCheckBox filtroCombustible;
    private javax.swing.JCheckBox filtroHabitaculo;
    private javax.swing.JButton guardar;
    private javax.swing.JLabel idTrabajo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextField kilometraje;
    private javax.swing.JCheckBox liquidoFreno;
    private javax.swing.JButton modificar;
    private javax.swing.JButton nuevo;
    private javax.swing.JTextArea observGeneral;
    private javax.swing.JTextArea observaciones;
    private javax.swing.JTextField precioBateria;
    private javax.swing.JTable tablaAutos;
    private javax.swing.JCheckBox tensorMulticanal;
    private javax.swing.JCheckBox tensores;
    private javax.swing.JTextField tipoBateria;
    private javax.swing.JButton venta;
    // End of variables declaration//GEN-END:variables
}
