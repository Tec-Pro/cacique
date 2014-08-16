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
import javax.swing.JComboBox;
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
        this.botImprimir.addActionListener(lis);
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
         aceiteDiferencial.setEnabled(b);
         filtroAire.setEnabled(b);
         filtroCombustible.setEnabled(b);
         filtroHabitaculo.setEnabled(b);
         kilometraje.setEnabled(b);
         liquidoFreno.setEnabled(b);
         observGeneral.setEnabled(b);
         observaciones.setEnabled(b);
         precioBateria.setEnabled(b);
         tensorCorreaMulti.setEnabled(b);
         tensores.setEnabled(b);
         tipoBateria.setEnabled(b);
         tablaAutos.setEnabled(b);
         aceiteMotor.setEnabled(b);
         mecanico.setEnabled(b);
         aceiteUsa.setEnabled(b);
         
         if(b){
            actualizarBateria();
            actualizarAceite();
         }
         
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

    public JTextField getAceiteUsa() {
        return aceiteUsa;
    }

    public void setClienteModel(Cliente clienteModel) {
        this.clienteModel = clienteModel;
    }

    public void setAutoModel(Auto autoModel) {
        this.autoModel = autoModel;
    }
     
     public void limpiarCampos(){
               anticongelante.setSelectedItem("nada");
         bombaAgua.setSelectedItem("nada");
         cambioBateria.setSelected(false);
         correaDistrib.setSelectedItem("nada");
         correaMulticanal.setSelectedItem("nada");
         costoTotal.setText("");
         descripcionBateria.setText("");
         fecha.setDate(Calendar.getInstance().getTime());
         filtroAceite.setSelectedItem("nada");
         aceiteCaja.setSelectedItem("nada");
         aceiteDiferencial.setSelectedItem("nada");
         filtroAire.setSelectedItem("nada");
         filtroCombustible.setSelectedItem("nada");
         filtroHabitaculo.setSelectedItem("nada");
         kilometraje.setText("");
         liquidoFreno.setSelectedItem("nada");
         observGeneral.setText("");
         observaciones.setText("");
         precioBateria.setText("");
         tensorCorreaMulti.setSelectedItem("nada");
         tensores.setSelectedItem("nada");
         tipoBateria.setText("");  
         duenio.setText("");
         auto.setText("");
         idTrabajo.setText("");
         aceiteMotor.setSelectedItem("nada");
         mecanico.setText("");
         aceiteUsa.setText("");
     }

     
     public void cargarTrabajo(Trabajo t){
         anticongelante.setSelectedItem(t.getString("anticongelante"));
         bombaAgua.setSelectedItem(t.getString("bomba_agua"));
         cambioBateria.setSelected(t.getBoolean("bateria"));
         correaDistrib.setSelectedItem(t.getString("correa_distribucion"));
         correaMulticanal.setSelectedItem(t.getString("correa_multicanal"));
         costoTotal.setText(t.getBigDecimal("costo").toString());
         descripcionBateria.setText(t.getString("descripcion_bateria"));
         fecha.setDate(t.getDate("fecha"));
         filtroAceite.setSelectedItem(t.getString("filtro_aceite"));
         aceiteCaja.setSelectedItem(t.getString("aceite_caja"));
         aceiteDiferencial.setSelectedItem(t.getString("aceite_diferencial"));
         filtroAire.setSelectedItem(t.getString("filtro_aire"));
         filtroCombustible.setSelectedItem(t.getString("filtro_combustible"));
         filtroHabitaculo.setSelectedItem(t.getString("filtro_habitaculo"));
         kilometraje.setText(t.getString("kilometraje"));
         liquidoFreno.setSelectedItem(t.getString("liquido_freno"));
         observGeneral.setText(t.getString("descripcion_adicional"));
         observaciones.setText(t.getString("observaciones"));
         precioBateria.setText(t.getBigDecimal("importe_bateria").toString());
         tensorCorreaMulti.setSelectedItem(t.getString("tensor_correa_multicanal"));
         tensores.setSelectedItem(t.getString("tensores"));
         tipoBateria.setText(t.getString("tipo_bateria"));
         duenio.setText(t.parent(Cliente.class).getString("nombre"));
         aceiteMotor.setSelectedItem(t.getString("aceite_motor"));
         auto.setText(t.parent(Auto.class).getString("patente"));
         mecanico.setText(t.getString("mecanico"));
         aceiteUsa.setText(t.getString("aceite_usa"));
         actualizarBateria();
         actualizarAceite();
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
     private void actualizarAceite(){
         if(aceiteMotor.getSelectedIndex()==1){
             aceiteUsa.setEnabled(true);
         }else{
             aceiteUsa.setEnabled(false);
             aceiteUsa.setText("");
         
         }
     }
    public DefaultTableModel getAutosDefault() {
        return autosDefault;
    }



    public JComboBox getAceiteCaja() {
        return aceiteCaja;
    }

    public JComboBox getAceiteDif() {
        return aceiteDiferencial;
    }

    public JComboBox getAnticongelante() {
        return anticongelante;
    }

    public JLabel getAuto() {
        return auto;
    }

    public JComboBox getBombaAgua() {
        return bombaAgua;
    }

    public JButton getBorrar() {
        return borrar;
    }

    public JTextField getMecanico() {
        return mecanico;
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

    public JComboBox getCorreaDistrib() {
        return correaDistrib;
    }

    public JComboBox getCorreaMulticanal() {
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

    public JComboBox getFiltroAceite() {
        return filtroAceite;
    }

    public JComboBox getFiltroAire() {
        return filtroAire;
    }

    public JComboBox getFiltroCombustible() {
        return filtroCombustible;
    }

    public JComboBox getFiltroHabitaculo() {
        return filtroHabitaculo;
    }

    public JButton getGuardar() {
        return guardar;
    }

    public JTextField getKilometraje() {
        return kilometraje;
    }

    public JComboBox getLiquidoFreno() {
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

 

    public JComboBox getTensorMulticanal() {
        return correaMulticanal;
    }

    public JComboBox getTensores() {
        return tensores;
    }

    public JTextField getTipoBateria() {
        return tipoBateria;
    }

    public JButton getVenta() {
        return venta;
    }

    public JComboBox getAceiteMotor() {
        return aceiteMotor;
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
        jPanel13 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        filtroCombustible = new javax.swing.JComboBox();
        jPanel14 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        filtroHabitaculo = new javax.swing.JComboBox();
        jPanel15 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        filtroAceite = new javax.swing.JComboBox();
        jPanel16 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        filtroAire = new javax.swing.JComboBox();
        jPanel17 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        aceiteDiferencial = new javax.swing.JComboBox();
        jPanel18 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        aceiteCaja = new javax.swing.JComboBox();
        jPanel19 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        liquidoFreno = new javax.swing.JComboBox();
        jPanel20 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        anticongelante = new javax.swing.JComboBox();
        jPanel21 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        correaMulticanal = new javax.swing.JComboBox();
        jPanel22 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        correaDistrib = new javax.swing.JComboBox();
        jPanel23 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        tensores = new javax.swing.JComboBox();
        jPanel24 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        bombaAgua = new javax.swing.JComboBox();
        jPanel25 = new javax.swing.JPanel();
        jPanel26 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        tensorCorreaMulti = new javax.swing.JComboBox();
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
        jLabel12 = new javax.swing.JLabel();
        mecanico = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        aceiteMotor = new javax.swing.JComboBox();
        aceiteUsa = new javax.swing.JTextField();
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
        setPreferredSize(new java.awt.Dimension(1061, 620));

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
                .addComponent(busquedaAuto)
                .addContainerGap())
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
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

        jPanel13.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel21.setText("Filtro combustible");
        jPanel13.add(jLabel21);

        filtroCombustible.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "reviso", "cambio", "nada" }));
        filtroCombustible.setSelectedIndex(2);
        filtroCombustible.setEnabled(false);
        filtroCombustible.setPreferredSize(new java.awt.Dimension(85, 21));
        jPanel13.add(filtroCombustible);

        jPanel6.add(jPanel13);

        jPanel14.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel22.setText("Filtro habitáculo");
        jPanel14.add(jLabel22);

        filtroHabitaculo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "reviso", "cambio", "nada" }));
        filtroHabitaculo.setSelectedIndex(2);
        filtroHabitaculo.setEnabled(false);
        filtroHabitaculo.setPreferredSize(new java.awt.Dimension(85, 21));
        jPanel14.add(filtroHabitaculo);

        jPanel6.add(jPanel14);

        jPanel15.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel23.setText("Filtro aceite");
        jPanel15.add(jLabel23);

        filtroAceite.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "reviso", "cambio", "nada" }));
        filtroAceite.setSelectedIndex(2);
        filtroAceite.setEnabled(false);
        filtroAceite.setPreferredSize(new java.awt.Dimension(85, 21));
        jPanel15.add(filtroAceite);

        jPanel6.add(jPanel15);

        jPanel16.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel24.setText("Filtro aire");
        jPanel16.add(jLabel24);

        filtroAire.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "reviso", "cambio", "nada" }));
        filtroAire.setSelectedIndex(2);
        filtroAire.setEnabled(false);
        filtroAire.setPreferredSize(new java.awt.Dimension(85, 21));
        jPanel16.add(filtroAire);

        jPanel6.add(jPanel16);

        jPanel17.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel25.setText("Aceite diferencial");
        jPanel17.add(jLabel25);

        aceiteDiferencial.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "reviso", "cambio", "nada" }));
        aceiteDiferencial.setSelectedIndex(2);
        aceiteDiferencial.setEnabled(false);
        aceiteDiferencial.setPreferredSize(new java.awt.Dimension(85, 21));
        jPanel17.add(aceiteDiferencial);

        jPanel6.add(jPanel17);

        jPanel18.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel26.setText("Aceite caja");
        jPanel18.add(jLabel26);

        aceiteCaja.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "reviso", "cambio", "nada" }));
        aceiteCaja.setSelectedIndex(2);
        aceiteCaja.setEnabled(false);
        aceiteCaja.setPreferredSize(new java.awt.Dimension(85, 21));
        aceiteCaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aceiteCajaActionPerformed(evt);
            }
        });
        jPanel18.add(aceiteCaja);

        jPanel6.add(jPanel18);

        jPanel19.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel27.setText("Líquido freno");
        jPanel19.add(jLabel27);

        liquidoFreno.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "reviso", "cambio", "nada" }));
        liquidoFreno.setSelectedIndex(2);
        liquidoFreno.setEnabled(false);
        liquidoFreno.setPreferredSize(new java.awt.Dimension(85, 21));
        liquidoFreno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                liquidoFrenoActionPerformed(evt);
            }
        });
        jPanel19.add(liquidoFreno);

        jPanel6.add(jPanel19);

        jPanel20.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel28.setText("Anticongelante");
        jPanel20.add(jLabel28);

        anticongelante.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "reviso", "cambio", "nada" }));
        anticongelante.setSelectedIndex(2);
        anticongelante.setEnabled(false);
        anticongelante.setPreferredSize(new java.awt.Dimension(85, 21));
        jPanel20.add(anticongelante);

        jPanel6.add(jPanel20);

        jPanel21.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel29.setText("Correa multicanal");
        jPanel21.add(jLabel29);

        correaMulticanal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "reviso", "cambio", "nada" }));
        correaMulticanal.setSelectedIndex(2);
        correaMulticanal.setEnabled(false);
        correaMulticanal.setPreferredSize(new java.awt.Dimension(85, 21));
        jPanel21.add(correaMulticanal);

        jPanel6.add(jPanel21);

        jPanel22.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel30.setText("Correa distrib");
        jPanel22.add(jLabel30);

        correaDistrib.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "reviso", "cambio", "nada" }));
        correaDistrib.setSelectedIndex(2);
        correaDistrib.setEnabled(false);
        correaDistrib.setPreferredSize(new java.awt.Dimension(85, 21));
        jPanel22.add(correaDistrib);

        jPanel6.add(jPanel22);

        jPanel23.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel31.setText("Tensores");
        jPanel23.add(jLabel31);

        tensores.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "reviso", "cambio", "nada" }));
        tensores.setSelectedIndex(2);
        tensores.setEnabled(false);
        tensores.setPreferredSize(new java.awt.Dimension(85, 21));
        jPanel23.add(tensores);

        jPanel6.add(jPanel23);

        jPanel24.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel32.setText("Bomba de agua");
        jPanel24.add(jLabel32);

        bombaAgua.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "reviso", "cambio", "nada" }));
        bombaAgua.setSelectedIndex(2);
        bombaAgua.setEnabled(false);
        bombaAgua.setPreferredSize(new java.awt.Dimension(85, 21));
        jPanel24.add(bombaAgua);

        jPanel6.add(jPanel24);

        jPanel25.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        jPanel6.add(jPanel25);

        jPanel26.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel34.setText("Tensor correa multic");
        jPanel26.add(jLabel34);

        tensorCorreaMulti.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "reviso", "cambio", "nada" }));
        tensorCorreaMulti.setSelectedIndex(2);
        tensorCorreaMulti.setEnabled(false);
        tensorCorreaMulti.setPreferredSize(new java.awt.Dimension(85, 21));
        jPanel26.add(tensorCorreaMulti);

        jPanel6.add(jPanel26);

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

        jLabel12.setText("Mecánico que lo realizó");

        mecanico.setEnabled(false);

        jLabel35.setText("Aceite motor");

        aceiteMotor.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "reviso", "cambio", "nada" }));
        aceiteMotor.setSelectedIndex(2);
        aceiteMotor.setEnabled(false);
        aceiteMotor.setPreferredSize(new java.awt.Dimension(85, 21));
        aceiteMotor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aceiteMotorActionPerformed(evt);
            }
        });
        aceiteMotor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                aceiteMotorPropertyChange(evt);
            }
        });

        aceiteUsa.setToolTipText("Aceite que usa");
        aceiteUsa.setEnabled(false);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel5)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addComponent(jLabel35)
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(aceiteMotor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(aceiteUsa, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)))
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mecanico, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(costoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(venta, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(79, 79, 79))))
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel35)
                            .addComponent(aceiteMotor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addComponent(aceiteUsa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 196, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(costoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(venta))
                    .addComponent(jLabel9))
                .addGap(6, 6, 6)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(mecanico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13))
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
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 593, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
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

    private void liquidoFrenoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_liquidoFrenoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_liquidoFrenoActionPerformed

    private void aceiteCajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aceiteCajaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_aceiteCajaActionPerformed

    private void aceiteMotorPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_aceiteMotorPropertyChange

       
    }//GEN-LAST:event_aceiteMotorPropertyChange

    private void aceiteMotorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aceiteMotorActionPerformed
       if(aceiteMotor.getSelectedIndex()==1){
           aceiteUsa.setEnabled(true);
       }else{
           aceiteUsa.setText("");
           aceiteUsa.setEnabled(false);

       }        // TODO add your handling code here:
    }//GEN-LAST:event_aceiteMotorActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox aceiteCaja;
    private javax.swing.JComboBox aceiteDiferencial;
    private javax.swing.JComboBox aceiteMotor;
    private javax.swing.JTextField aceiteUsa;
    private javax.swing.JComboBox anticongelante;
    private javax.swing.JLabel auto;
    private javax.swing.JComboBox bombaAgua;
    private javax.swing.JButton borrar;
    private javax.swing.JButton botImprimir;
    private javax.swing.JTextField busquedaAuto;
    private javax.swing.JCheckBox cambioBateria;
    private javax.swing.JComboBox correaDistrib;
    private javax.swing.JComboBox correaMulticanal;
    private javax.swing.JTextField costoTotal;
    private javax.swing.JTextField descripcionBateria;
    private javax.swing.JLabel duenio;
    private com.toedter.calendar.JDateChooser fecha;
    private javax.swing.JComboBox filtroAceite;
    private javax.swing.JComboBox filtroAire;
    private javax.swing.JComboBox filtroCombustible;
    private javax.swing.JComboBox filtroHabitaculo;
    private javax.swing.JButton guardar;
    private javax.swing.JLabel idTrabajo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
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
    private javax.swing.JComboBox liquidoFreno;
    private javax.swing.JTextField mecanico;
    private javax.swing.JButton modificar;
    private javax.swing.JButton nuevo;
    private javax.swing.JTextArea observGeneral;
    private javax.swing.JTextArea observaciones;
    private javax.swing.JTextField precioBateria;
    private javax.swing.JTable tablaAutos;
    private javax.swing.JComboBox tensorCorreaMulti;
    private javax.swing.JComboBox tensores;
    private javax.swing.JTextField tipoBateria;
    private javax.swing.JButton venta;
    // End of variables declaration//GEN-END:variables
}
