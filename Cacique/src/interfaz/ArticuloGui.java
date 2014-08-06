/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import abm.ManejoIp;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelos.Articulo;
import modelos.Proveedor;
import org.javalite.activejdbc.Base;

/**
 *
 * @author nico
 */
public class ArticuloGui extends javax.swing.JInternalFrame {

    private DefaultTableModel tablaArticulosDefault; //Tabla Default para tener las opciones de instar y elimnar filas

    /**
     * Creates new form ArticuloGui
     */
    public ArticuloGui() {
        initComponents();
        tablaArticulosDefault = (DefaultTableModel) articulos.getModel(); //convierto la tabla

    }

    public void setActionListener(ActionListener lis) {
        this.guardar.addActionListener(lis);
        this.borrar.addActionListener(lis);
        this.nuevo.addActionListener(lis);
        this.modificar.addActionListener(lis);
        this.exportar.addActionListener(lis);
        this.filtroEquiv.addActionListener(lis);
    }

    public void habilitarCampos(boolean b) {
        marca.setEnabled(b);
        codigo.setEnabled(b);
        nombre.setEnabled(b);
        descripcion.setEnabled(b);
        precioCompra.setEnabled(b);
        stock.setEnabled(b);
        precioVenta.setEnabled(b);
        stockMinimo.setEnabled(b);
        proveedores.setEnabled(b);
        equivalencia1.setEnabled(b);
        equivalencia2.setEnabled(b);
        equivalencia3.setEnabled(b);
    }

    public void limpiarCampos() {
        marca.setText("");
        nombre.setText("");
        stock.setText("");
        stockMinimo.setText("");
        codigo.setText("");
        descripcion.setText("");
        precioCompra.setText("");
        precioVenta.setText("");
        proveedores.removeAllItems();
        equivalencia1.setText("");
                equivalencia2.setText("");

                        equivalencia3.setText("");

                        

    }
    
        private void abrirBase() {
        if (!Base.hasConnection()) {
            try {
                Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://" + ManejoIp.ipServer + "/cacique", "tecpro", "tecpro");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Ocurrió un error, no se realizó la conexión con el servidor, verifique la conexión \n " + e.getMessage(), null, JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void CargarCampos(Articulo art) {
        abrirBase();
        codigo.setText(art.getString("codigo"));
        nombre.setText(art.getString("nombre"));
        marca.setText(art.getString("marca"));
        precioCompra.setText(art.getBigDecimal("precio_compra").setScale(2).toString());
        stock.setText(art.getString("stock_actual"));
        precioVenta.setText(art.getBigDecimal("precio_venta").setScale(2).toString());
        stockMinimo.setText(art.getString("stock_minimo"));

        descripcion.setText(art.getString("descripcion"));
       
        proveedores.removeAllItems();
        Proveedor proveedor = art.parent(Proveedor.class);
        if (proveedor != null) {
            String nombreProv = proveedor.getString("nombre");
            proveedores.addItem(nombreProv);
            proveedores.setSelectedItem(nombreProv);
        } else {
            proveedores.addItem("");
            proveedores.setSelectedItem("");
        }
        
        Date date= art.getDate("ultima_compra");
        
        ultimaCompra.setText(dateToMySQLDate(date, true));
        equivalencia1.setText(art.getString("equivalencia_1"));
                equivalencia2.setText(art.getString("equivalencia_2"));

                        equivalencia3.setText(art.getString("equivalencia_3"));

    }

        /*va true si se quiere usar para mostrarla por pantalla es decir 12/12/2014 y false si va 
     para la base de datos, es decir 2014/12/12*/
    public String dateToMySQLDate(Date fecha, boolean paraMostrar) {
        if(fecha!=null){
        if (paraMostrar) {
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
            return sdf.format(fecha);
        } else {
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
            return sdf.format(fecha);
        }
        }else{
            return "";
        }
    }
    public DefaultTableModel getTablaArticulosDefault() {
        return tablaArticulosDefault;
    }

    public JTable getArticulos() {
        return articulos;
    }

    public JTextField getEquivalencia1() {
        return equivalencia1;
    }

    public JTextField getEquivalencia2() {
        return equivalencia2;
    }

    public JTextField getEquivalencia3() {
        return equivalencia3;
    }



    public JButton getBorrar() {
        return borrar;
    }

    public JTextField getBusqueda() {
        return busqueda;
    }

    public JTextField getCodigo() {
        return codigo;
    }

    public JTextArea getDescripcion() {
        return descripcion;
    }

    public JButton getExportar() {
        return exportar;
    }

    public JCheckBox getFiltroEquiv() {
        return filtroEquiv;
    }

    public JButton getGuardar() {
        return guardar;
    }

    public JTextField getMarca() {
        return marca;
    }

    public JButton getModificar() {
        return modificar;
    }

    public JTextField getNombre() {
        return nombre;
    }

    public JLabel getCantidadArticulos() {
        return cantidadArticulos;
    }

    public JButton getNuevo() {
        return nuevo;
    }

    public JTextField getPrecioCompra() {
        return precioCompra;
    }

    public JTextField getPrecioVenta() {
        return precioVenta;
    }

    public JComboBox getProveedores() {
        return proveedores;
    }

    public JTextField getStock() {
        return stock;
    }

    public JTextField getStockMinimo() {
        return stockMinimo;
    }

    public JTextField getUltimaCompra() {
        return ultimaCompra;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        busqueda = new javax.swing.JTextField();
        filtroEquiv = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        exportar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        articulos = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        borrar = new javax.swing.JButton();
        nuevo = new javax.swing.JButton();
        modificar = new javax.swing.JButton();
        guardar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        codigo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        marca = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        descripcion = new javax.swing.JTextArea();
        precioCompra = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        precioVenta = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        proveedores = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        nombre = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        ultimaCompra = new javax.swing.JTextField();
        stock = new javax.swing.JTextField();
        stockMinimo = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        equivalencia1 = new javax.swing.JTextField();
        equivalencia2 = new javax.swing.JTextField();
        equivalencia3 = new javax.swing.JTextField();
        cantidadArticulos = new javax.swing.JLabel();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Gestión de artículos");
        setPreferredSize(new java.awt.Dimension(882, 475));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Artículos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Schoolbook L", 1, 14))); // NOI18N
        jPanel2.setPreferredSize(new java.awt.Dimension(855, 420));

        busqueda.setToolTipText("Búsqueda personalizada");

        filtroEquiv.setText("buscar equivalentes");

        jLabel4.setText("Artículos encontrados: ");

        exportar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaz/Icons/exportar.png"))); // NOI18N
        exportar.setText("Exportar listado");
        exportar.setToolTipText("Exportar listado de articulos");

        articulos.setAutoCreateRowSorter(true);
        articulos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id fabrica", "Nombre", "Marca", "Descrip.", "Precio compra", "Precio venta", "Id interno"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        articulos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(articulos);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos del artículo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Schoolbook L", 1, 14))); // NOI18N

        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        borrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaz/Icons/borrar.png"))); // NOI18N
        borrar.setText("Borrar");
        borrar.setToolTipText("Borrar artículo seleccionado");
        borrar.setEnabled(false);

        nuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaz/Icons/nuevo.png"))); // NOI18N
        nuevo.setText("Nuevo");
        nuevo.setToolTipText("Crear artículo nuevo");

        modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaz/Icons/modificar.png"))); // NOI18N
        modificar.setText("Modificar");
        modificar.setToolTipText("Modificar artículo seleccionado");
        modificar.setEnabled(false);
        modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarActionPerformed(evt);
            }
        });

        guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaz/Icons/guardar.png"))); // NOI18N
        guardar.setText("Guardar");
        guardar.setToolTipText("Guardar cambios realizados");
        guardar.setEnabled(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(borrar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modificar)
                    .addComponent(guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {borrar, guardar, modificar, nuevo});

        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(borrar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(nuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(modificar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {borrar, guardar, modificar, nuevo});

        jLabel1.setText("Código");

        codigo.setToolTipText("Código del artículo");
        codigo.setDisabledTextColor(new java.awt.Color(16, 2, 245));
        codigo.setEnabled(false);

        jLabel3.setText("Nombre");

        marca.setToolTipText("Marca del artículo");
        marca.setDisabledTextColor(new java.awt.Color(16, 2, 245));
        marca.setEnabled(false);

        jLabel2.setText("Descripción");

        jScrollPane3.setPreferredSize(new java.awt.Dimension(210, 100));

        descripcion.setColumns(10);
        descripcion.setRows(10);
        descripcion.setToolTipText("Descripción del artículo");
        descripcion.setDisabledTextColor(new java.awt.Color(16, 2, 245));
        descripcion.setEnabled(false);
        jScrollPane3.setViewportView(descripcion);

        precioCompra.setToolTipText("Precio de compra");
        precioCompra.setDisabledTextColor(new java.awt.Color(16, 2, 245));
        precioCompra.setEnabled(false);

        jLabel5.setText("Precio Compra");

        jLabel7.setText("Stock");

        jLabel6.setText("Precio Venta");

        precioVenta.setToolTipText("Precio de venta");
        precioVenta.setDisabledTextColor(new java.awt.Color(16, 2, 245));
        precioVenta.setEnabled(false);

        jLabel9.setText("Stock minimo");

        proveedores.setEnabled(false);

        jLabel10.setText("Proveedor");

        jLabel8.setText("Marca");

        nombre.setToolTipText("Marca del artículo");
        nombre.setDisabledTextColor(new java.awt.Color(16, 2, 245));
        nombre.setEnabled(false);

        jLabel12.setText("Fecha ultima compra");

        ultimaCompra.setToolTipText("Precio de venta");
        ultimaCompra.setDisabledTextColor(new java.awt.Color(16, 2, 245));
        ultimaCompra.setEnabled(false);

        stock.setDisabledTextColor(new java.awt.Color(16, 2, 245));
        stock.setEnabled(false);

        stockMinimo.setDisabledTextColor(new java.awt.Color(16, 2, 245));
        stockMinimo.setEnabled(false);

        jLabel11.setText("Equivalencia");

        equivalencia1.setDisabledTextColor(new java.awt.Color(16, 2, 245));
        equivalencia1.setEnabled(false);

        equivalencia2.setDisabledTextColor(new java.awt.Color(16, 2, 245));
        equivalencia2.setEnabled(false);

        equivalencia3.setDisabledTextColor(new java.awt.Color(16, 2, 245));
        equivalencia3.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(codigo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(marca)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(80, 80, 80))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(6, 6, 6)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(proveedores, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(precioCompra)
                                            .addComponent(precioVenta))
                                        .addGap(0, 0, 0)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(13, 13, 13)
                                                .addComponent(jLabel7)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(stock, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(4, 4, 4))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel9)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(stockMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ultimaCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 84, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(equivalencia1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(equivalencia2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(equivalencia3)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(codigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(marca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addContainerGap())
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(precioCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(stock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(precioVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(stockMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel9)))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(proveedores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ultimaCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(equivalencia1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(equivalencia2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(equivalencia3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {equivalencia1, equivalencia2, equivalencia3});

        cantidadArticulos.setFont(new java.awt.Font("Century Schoolbook L", 1, 18)); // NOI18N
        cantidadArticulos.setForeground(new java.awt.Color(237, 2, 24));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(busqueda)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filtroEquiv)
                .addGap(8, 8, 8)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cantidadArticulos, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(exportar))
            .addComponent(jScrollPane1)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(exportar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(filtroEquiv)
                        .addComponent(jLabel4))
                    .addComponent(cantidadArticulos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jScrollPane2.setViewportView(jPanel2);

        getContentPane().add(jScrollPane2, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_modificarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable articulos;
    private javax.swing.JButton borrar;
    private javax.swing.JTextField busqueda;
    private javax.swing.JLabel cantidadArticulos;
    private javax.swing.JTextField codigo;
    private javax.swing.JTextArea descripcion;
    private javax.swing.JTextField equivalencia1;
    private javax.swing.JTextField equivalencia2;
    private javax.swing.JTextField equivalencia3;
    private javax.swing.JButton exportar;
    private javax.swing.JCheckBox filtroEquiv;
    private javax.swing.JButton guardar;
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField marca;
    private javax.swing.JButton modificar;
    private javax.swing.JTextField nombre;
    private javax.swing.JButton nuevo;
    private javax.swing.JTextField precioCompra;
    private javax.swing.JTextField precioVenta;
    private javax.swing.JComboBox proveedores;
    private javax.swing.JTextField stock;
    private javax.swing.JTextField stockMinimo;
    private javax.swing.JTextField ultimaCompra;
    // End of variables declaration//GEN-END:variables
}
