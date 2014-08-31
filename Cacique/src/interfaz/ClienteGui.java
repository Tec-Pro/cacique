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
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelos.Cliente;

/**
 *
 * @author jacinto
 */
public class ClienteGui extends javax.swing.JInternalFrame {

    private DefaultTableModel clientes;
    private DefaultTableModel cobrosDefault;
    private DefaultTableModel ventasDefault;//Tabla Default para tener las opciones de instar y elimnar filas

    /**
     * Creates new form ProveedorGui
     */
    public ClienteGui() {
        initComponents();
        clientes = (DefaultTableModel) tablaClientes.getModel(); //convierto la tabla;
        ventasDefault = (DefaultTableModel) ventasRealizadas.getModel();
        Calendar miCalendario = Calendar.getInstance();
        java.util.Date eldia = miCalendario.getTime();
        int diaHoy = miCalendario.get(Calendar.DAY_OF_MONTH);
        int mes = miCalendario.get(Calendar.MONTH);
        int anio = miCalendario.get(Calendar.YEAR);
        nacimiento.setDate(Date.valueOf(anio + "-" + (mes + 1) + "-" + diaHoy));
    }

    public void setActionListener(ActionListener lis) {
        this.guardar.addActionListener(lis);
        this.borrar.addActionListener(lis);
        this.nuevo.addActionListener(lis);
        this.modificar.addActionListener(lis);
        this.cobrarFactura.addActionListener(lis);
        this.eliminarVenta.addActionListener(lis);
        this.verHistorial.addActionListener(lis);
        this.ver.addActionListener(lis);
        this.realizarEntrega.addActionListener(lis);
        this.autos.addActionListener(lis);
        this.presupuestos.addActionListener(lis);
        this.pagos.addActionListener(lis);
    }

    public void habilitarCamposVentas(boolean b) {
        cobrarFactura.setEnabled(b);
        eliminarVenta.setEnabled(b);
        verHistorial.setEnabled(b);
        ver.setEnabled(b);
    }

    public void habilitarCampos(boolean b) {
        nombre.setEnabled(b);
        telefono.setEnabled(b);
        celular.setEnabled(b);
        email.setEnabled(b);
        facebook.setEnabled(b);
        dni.setEnabled(b);
        nacimiento.setEnabled(b);
        direccion.setEnabled(b);        
    }

    public void limpiarCampos() {
        id.setText("");
        nombre.setText("");
        telefono.setText("");
        celular.setText("");
        ventasDefault.setRowCount(0);
        adeuda.setText("");
        adeudaActual.setText("");
        facebook.setText("");
        dni.setText("");
        direccion.setText("");
        email.setText("");
        Calendar miCalendario = Calendar.getInstance();
        java.util.Date eldia = miCalendario.getTime();
        int diaHoy = miCalendario.get(Calendar.DAY_OF_MONTH);
        int mes = miCalendario.get(Calendar.MONTH);
        int anio = miCalendario.get(Calendar.YEAR);
        nacimiento.setDate(Date.valueOf(anio + "-" + (mes + 1) + "-" + diaHoy));

    }

    public void CargarCampos(Cliente cliente) {
        id.setText(cliente.getString("id"));
        nombre.setText(cliente.getString("nombre"));
        telefono.setText(cliente.getString("telefono"));
        celular.setText(cliente.getString("celular"));
        facebook.setText(cliente.getString("facebook"));
        dni.setText(cliente.getString("dni"));
        nacimiento.setDate(cliente.getDate("nacimiento"));
        direccion.setText(cliente.getString("direccion"));
        email.setText(cliente.getString("email"));
    }

    public JButton getPagos() {
        return pagos;
    }

    
    
    public DefaultTableModel getClientes() {
        return clientes;
    }

    public DefaultTableModel getCobrosDefault() {
        return cobrosDefault;
    }

    public void setCobrosDefault(DefaultTableModel cobrosDefault) {
        this.cobrosDefault = cobrosDefault;
    }

    public DefaultTableModel getVentasDefault() {
        return ventasDefault;
    }

    public void setVentasDefault(DefaultTableModel ventasDefault) {
        this.ventasDefault = ventasDefault;
    }

    public JTable getVentasRealizadas() {
        return ventasRealizadas;
    }

    public void setVentasRealizadas(JTable ventasRealizadas) {
        this.ventasRealizadas = ventasRealizadas;
    }

    public JButton getAutos() {
        return autos;
    }

    public JButton getBorrar() {
        return borrar;
    }

    public JTextField getBusqueda() {
        return busqueda;
    }

    public JTextField getCelular() {
        return celular;
    }

    public JButton getCobrarFactura() {
        return cobrarFactura;
    }

    public JTextField getDireccion() {
        return direccion;
    }

    public JTextField getDni() {
        return dni;
    }

    public JButton getEliminarVenta() {
        return eliminarVenta;
    }

    public JTextField getEmail() {
        return email;
    }

    public JTextField getFacebook() {
        return facebook;
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

    public JDateChooser getNacimiento() {
        return nacimiento;
    }

    public JTextField getNombre() {
        return nombre;
    }

    public JButton getNuevo() {
        return nuevo;
    }

    public JButton getPresupuestos() {
        return presupuestos;
    }

    public JButton getRealizarEntrega() {
        return realizarEntrega;
    }

    public JTable getTablaClientes() {
        return tablaClientes;
    }

    public JTextField getTelefono() {
        return telefono;
    }

    public JComboBox getVer() {
        return ver;
    }

    public JButton getVerHistorial() {
        return verHistorial;
    }

    public JLabel getAdeuda() {
        return adeuda;
    }

    public JLabel getAdeudaActual() {
        return adeudaActual;
    }

    public JTextField getCalenText() {
        return ((JTextField) nacimiento.getDateEditor().getUiComponent());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scroolClientes = new javax.swing.JScrollPane();
        panelEnteroClientes = new javax.swing.JPanel();
        panelClientes = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaClientes = new javax.swing.JTable();
        busqueda = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        nombre = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        dni = new javax.swing.JTextField();
        id = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        celular = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        email = new javax.swing.JTextField();
        direccion = new javax.swing.JTextField();
        telefono = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        facebook = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        nacimiento = new com.toedter.calendar.JDateChooser();
        jPanel4 = new javax.swing.JPanel();
        borrar = new javax.swing.JButton();
        nuevo = new javax.swing.JButton();
        modificar = new javax.swing.JButton();
        guardar = new javax.swing.JButton();
        realizarEntrega = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        ventasRealizadas = new javax.swing.JTable();
        ver = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        adeuda = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        adeudaActual = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        autos = new javax.swing.JButton();
        pagos = new javax.swing.JButton();
        presupuestos = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        verHistorial = new javax.swing.JButton();
        cobrarFactura = new javax.swing.JButton();
        eliminarVenta = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Gestión de Clientes");
        setPreferredSize(new java.awt.Dimension(876, 600));
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        scroolClientes.setName(""); // NOI18N
        scroolClientes.setPreferredSize(new java.awt.Dimension(855, 480));
        scroolClientes.setRequestFocusEnabled(false);

        panelEnteroClientes.setPreferredSize(new java.awt.Dimension(825, 475));

        panelClientes.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Clientes", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Schoolbook L", 3, 15))); // NOI18N

        tablaClientes.setAutoCreateRowSorter(true);
        tablaClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Teléfono", "Celular"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaClientes.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(tablaClientes);

        busqueda.setToolTipText("Búsqueda personalizada");

        javax.swing.GroupLayout panelClientesLayout = new javax.swing.GroupLayout(panelClientes);
        panelClientes.setLayout(panelClientesLayout);
        panelClientesLayout.setHorizontalGroup(
            panelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(busqueda)
        );
        panelClientesLayout.setVerticalGroup(
            panelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelClientesLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos del cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Schoolbook L", 3, 15))); // NOI18N

        nombre.setEnabled(false);

        jLabel1.setText("Nombre");

        jLabel2.setText("Dni");

        dni.setEnabled(false);
        dni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dniActionPerformed(evt);
            }
        });

        id.setEnabled(false);

        jLabel3.setText("ID");

        jLabel4.setText("Celular");

        celular.setEnabled(false);
        celular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                celularActionPerformed(evt);
            }
        });

        jLabel5.setText("Telefono");

        jLabel6.setText("Direccion");

        jLabel7.setText("E-Mail");

        email.setEnabled(false);
        email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailActionPerformed(evt);
            }
        });

        direccion.setEnabled(false);
        direccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                direccionActionPerformed(evt);
            }
        });

        telefono.setEnabled(false);

        jLabel8.setText("Facebook");

        facebook.setEnabled(false);
        facebook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                facebookActionPerformed(evt);
            }
        });

        jLabel9.setText("Nacimiento");

        nacimiento.setEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(jLabel3))
                            .addComponent(jLabel4)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel6))
                                .addComponent(jLabel1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(celular)
                            .addComponent(nombre)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 172, Short.MAX_VALUE))
                            .addComponent(dni)
                            .addComponent(facebook)
                            .addComponent(direccion)
                            .addComponent(telefono)
                            .addComponent(email)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(55, 55, 55)
                        .addComponent(nacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 79, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(1, 1, 1)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(celular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(telefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(direccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(facebook, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(nacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel4.setLayout(new java.awt.GridLayout(1, 0));

        borrar.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        borrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaz/Icons/borrar.png"))); // NOI18N
        borrar.setToolTipText("Borrar cliente seleccionado");
        borrar.setEnabled(false);
        borrar.setPreferredSize(new java.awt.Dimension(55, 33));
        jPanel4.add(borrar);

        nuevo.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        nuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaz/Icons/nuevo.png"))); // NOI18N
        nuevo.setToolTipText("Crear cliente nuevo");
        nuevo.setPreferredSize(new java.awt.Dimension(55, 33));
        jPanel4.add(nuevo);

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
        jPanel4.add(modificar);

        guardar.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaz/Icons/guardar.png"))); // NOI18N
        guardar.setToolTipText("Guardar cambios realizados");
        guardar.setEnabled(false);
        guardar.setPreferredSize(new java.awt.Dimension(55, 33));
        jPanel4.add(guardar);

        realizarEntrega.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        realizarEntrega.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaz/Icons/pagar.png"))); // NOI18N
        realizarEntrega.setToolTipText("Realizar entrega");
        realizarEntrega.setEnabled(false);
        realizarEntrega.setPreferredSize(new java.awt.Dimension(55, 33));
        jPanel4.add(realizarEntrega);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ventas realizadas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Schoolbook L", 3, 14))); // NOI18N
        jPanel6.setPreferredSize(new java.awt.Dimension(327, 409));

        ventasRealizadas.setAutoCreateRowSorter(true);
        ventasRealizadas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Fecha", "Monto", "Monto Actual", "Abonado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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
        ventasRealizadas.setToolTipText("Realice doble click sobre la compra para verla en datalle");
        ventasRealizadas.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane5.setViewportView(ventasRealizadas);

        ver.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Todas", "Cta. Corriente", "Pagas" }));
        ver.setEnabled(false);

        jLabel10.setText("Ver:");

        jLabel11.setText("Cta.cte:");

        jLabel12.setText("Actual:");

        jPanel1.setLayout(new java.awt.GridLayout(2, 1));

        jPanel3.setLayout(new java.awt.GridLayout(1, 0));

        autos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaz/Icons/auto.png"))); // NOI18N
        autos.setText(" ");
        autos.setToolTipText("Ver autos del cliente");
        autos.setEnabled(false);
        autos.setPreferredSize(new java.awt.Dimension(72, 28));
        autos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                autosActionPerformed(evt);
            }
        });
        jPanel3.add(autos);

        pagos.setText("Pagos");
        pagos.setEnabled(false);
        jPanel3.add(pagos);

        presupuestos.setText("Presupuestos");
        presupuestos.setEnabled(false);
        presupuestos.setPreferredSize(new java.awt.Dimension(72, 28));
        jPanel3.add(presupuestos);

        jPanel1.add(jPanel3);

        jPanel5.setLayout(new java.awt.GridLayout(1, 0));

        verHistorial.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        verHistorial.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaz/Icons/Details.png"))); // NOI18N
        verHistorial.setToolTipText("Detalles de la Factura");
        verHistorial.setEnabled(false);
        verHistorial.setPreferredSize(new java.awt.Dimension(131, 33));
        jPanel5.add(verHistorial);

        cobrarFactura.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        cobrarFactura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaz/Icons/pagar.png"))); // NOI18N
        cobrarFactura.setToolTipText("Realizar Cobro");
        cobrarFactura.setEnabled(false);
        cobrarFactura.setPreferredSize(new java.awt.Dimension(55, 33));
        jPanel5.add(cobrarFactura);

        eliminarVenta.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        eliminarVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaz/Icons/borrar.png"))); // NOI18N
        eliminarVenta.setToolTipText("Eliminar Venta");
        eliminarVenta.setEnabled(false);
        eliminarVenta.setPreferredSize(new java.awt.Dimension(55, 33));
        jPanel5.add(eliminarVenta);

        jPanel1.add(jPanel5);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 452, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel10)
                        .addGap(3, 3, 3)
                        .addComponent(ver, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(adeuda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12)
                        .addGap(0, 0, 0)
                        .addComponent(adeudaActual, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(2, 2, 2))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ver, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10)
                        .addComponent(jLabel11)
                        .addComponent(adeuda, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(adeudaActual, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(11, 11, 11)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout panelEnteroClientesLayout = new javax.swing.GroupLayout(panelEnteroClientes);
        panelEnteroClientes.setLayout(panelEnteroClientesLayout);
        panelEnteroClientesLayout.setHorizontalGroup(
            panelEnteroClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEnteroClientesLayout.createSequentialGroup()
                .addGroup(panelEnteroClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelClientes, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        panelEnteroClientesLayout.setVerticalGroup(
            panelEnteroClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEnteroClientesLayout.createSequentialGroup()
                .addComponent(panelClientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(7, 7, 7)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 557, Short.MAX_VALUE)
        );

        scroolClientes.setViewportView(panelEnteroClientes);
        panelEnteroClientes.getAccessibleContext().setAccessibleName("");
        panelEnteroClientes.getAccessibleContext().setAccessibleDescription("");

        getContentPane().add(scroolClientes);
        scroolClientes.getAccessibleContext().setAccessibleName("Panel");
        scroolClientes.getAccessibleContext().setAccessibleDescription("");

        getAccessibleContext().setAccessibleName("clienteGui");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void dniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dniActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dniActionPerformed

    private void celularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_celularActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_celularActionPerformed

    private void emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailActionPerformed

    private void direccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_direccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_direccionActionPerformed

    private void facebookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_facebookActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_facebookActionPerformed

    private void modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_modificarActionPerformed

    private void autosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_autosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_autosActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel adeuda;
    private javax.swing.JLabel adeudaActual;
    private javax.swing.JButton autos;
    private javax.swing.JButton borrar;
    private javax.swing.JTextField busqueda;
    private javax.swing.JTextField celular;
    private javax.swing.JButton cobrarFactura;
    private javax.swing.JTextField direccion;
    private javax.swing.JTextField dni;
    private javax.swing.JButton eliminarVenta;
    private javax.swing.JTextField email;
    private javax.swing.JTextField facebook;
    private javax.swing.JButton guardar;
    private javax.swing.JTextField id;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JButton modificar;
    private com.toedter.calendar.JDateChooser nacimiento;
    private javax.swing.JTextField nombre;
    private javax.swing.JButton nuevo;
    private javax.swing.JButton pagos;
    private javax.swing.JPanel panelClientes;
    private javax.swing.JPanel panelEnteroClientes;
    private javax.swing.JButton presupuestos;
    private javax.swing.JButton realizarEntrega;
    private javax.swing.JScrollPane scroolClientes;
    private javax.swing.JTable tablaClientes;
    private javax.swing.JTextField telefono;
    private javax.swing.JTable ventasRealizadas;
    private javax.swing.JComboBox ver;
    private javax.swing.JButton verHistorial;
    // End of variables declaration//GEN-END:variables
}
