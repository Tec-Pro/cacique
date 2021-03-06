/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import com.toedter.calendar.JDateChooser;
import controladores.ControladorCuentaCorriente;
import controladores.ControladorJReport;
import controladores.RealizarPagoVentaControlador;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import modelos.Cliente;
import modelos.Corriente;
import modelos.Pago;
import net.sf.jasperreports.engine.JRException;
import org.javalite.activejdbc.Base;
import org.javalite.activejdbc.LazyList;
import org.javalite.activejdbc.Model;

/**
 *
 * @author nico
 */
public class PagarCorrienteGui extends javax.swing.JDialog {

    int idcliente;
    ControladorJReport reportePago;
    int idCuenta;
    ControladorCuentaCorriente co;
    boolean varias;

    /**
     * Creates new form PagarCorrienteGui
     */
    public  PagarCorrienteGui(java.awt.Frame parent, boolean modal, int idCliente, int idCuenta, ControladorCuentaCorriente co, boolean varias) {
        super(parent, modal);
        initComponents();
        idcliente = idCliente;
        this.idCuenta= idCuenta;
         Base.openTransaction();
        cliente.setText(Cliente.findById(idCliente).getString("nombre"));
        Base.commitTransaction();
        Calendar miCalendario = Calendar.getInstance();
        java.util.Date eldia = miCalendario.getTime();
        int diaHoy = miCalendario.get(Calendar.DAY_OF_MONTH);
        int mes = miCalendario.get(Calendar.MONTH);
        int anio = miCalendario.get(Calendar.YEAR);
        calendario.setDate(Date.valueOf(anio + "-" + (mes + 1) + "-" + diaHoy));
        try {
            reportePago=  new ControladorJReport("pago.jasper");
        } catch (JRException ex) {
            Logger.getLogger(PagarCorrienteGui.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PagarCorrienteGui.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PagarCorrienteGui.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.co= co;
        this.varias=varias;
    }

    public JTextField getCalendarioText() {
        return ((JTextField) calendario.getDateEditor().getUiComponent());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        cliente = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        calendario = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        monto = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        descripcion = new javax.swing.JTextArea();
        jLabel11 = new javax.swing.JLabel();
        aceptar = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel4.setFont(new java.awt.Font("Century Schoolbook L", 2, 15)); // NOI18N
        jLabel4.setText("Cobro realizado a :");

        cliente.setForeground(new java.awt.Color(255, 0, 0));

        jLabel6.setFont(new java.awt.Font("Century Schoolbook L", 2, 15)); // NOI18N
        jLabel6.setText("El día:");

        calendario.setDateFormatString("yyyy-MM-dd");

        jLabel2.setText("Monto");

        descripcion.setColumns(20);
        descripcion.setRows(4);
        jScrollPane1.setViewportView(descripcion);

        jLabel11.setText("Descripción adicional");

        aceptar.setText("Aceptar");
        aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aceptarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cliente, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(calendario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(24, 24, 24)
                                        .addComponent(monto))))
                            .addComponent(aceptar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel11))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(calendario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(monto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(aceptar)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void aceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aceptarActionPerformed
       if(!varias){
        try {
            if (monto.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Por favor ingrese el monto", "Error!", JOptionPane.ERROR_MESSAGE);
            } else {
                Base.openTransaction();
                float entrega = Float.valueOf(monto.getText());
                Pago pago = Pago.createIt("fecha", getCalendarioText().getText(),
                "monto", entrega,
                "cliente_id", idcliente,
                "descripcion", descripcion.getText());
                 Base.openTransaction();
                Corriente cuenta= Corriente.findById(idCuenta);
                Base.commitTransaction();
                Base.openTransaction();
                Cliente cli= Cliente.findById(idcliente);
                
                entrega+=cli.getFloat("cuenta_corriente_manual");
                Base.commitTransaction();
                float debe= cuenta.getFloat("monto")-(cuenta.getFloat("haber"));
                if(entrega>=debe){
                    cuenta.setFloat("haber", debe+cuenta.getFloat("haber"));
                    entrega= entrega-debe;
                     cli.set("cuenta_corriente_manual",entrega);
                    cli.saveIt();   
                }
                else{
                    cuenta.setFloat("haber", entrega+cuenta.getFloat("haber"));
                    entrega= 0;
                    cli.set("cuenta_corriente_manual",entrega);
                    cli.saveIt();   
                }
                    
                cuenta.saveIt();
                Base.commitTransaction();
                
                JOptionPane.showMessageDialog(this, "¡Cobro registrado exitosamente!");
                reportePago.mostrarPago( pago.getInteger("id"));
                co.cargarCuentas();
                co.actualizarTotalCuenta();
                this.dispose();
            }
            }catch (ClassNotFoundException ex) {
                        Logger.getLogger(RealizarPagoVentaControlador.class.getName()).log(Level.SEVERE, null, ex);
                    }catch (SQLException ex) {
                        Logger.getLogger(RealizarPagoVentaControlador.class.getName()).log(Level.SEVERE, null, ex);
                    }catch (JRException ex) {
                        Logger.getLogger(RealizarPagoVentaControlador.class.getName()).log(Level.SEVERE, null, ex);
                    }
        
       }
       else{
          try {
            if (monto.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Por favor ingrese el monto", "Error!", JOptionPane.ERROR_MESSAGE);
            } else {
                Base.openTransaction();
                float entrega = Float.valueOf(monto.getText());
                float entregaNoMod= entrega;
                String texto= "pagó cuentas corrientes: ";
                 Base.openTransaction();
                Cliente cli= Cliente.findById(idcliente);
                
                entrega+=cli.getFloat("cuenta_corriente_manual");
                Base.commitTransaction();
                 Base.openTransaction();
                LazyList<Corriente> cuentasCorrientes= Corriente.where("haber < monto and id_cliente = ? ", idcliente).orderBy("fecha");
                Base.commitTransaction();
                Iterator<Corriente> it=cuentasCorrientes.iterator();
                Base.openTransaction();
                while(it.hasNext() ){
                    
                    Corriente corr= it.next();
                    if(entrega>=0){
                    float debe= corr.getFloat("monto")-(corr.getFloat("haber"));
                    if(entrega-debe>=0){
                        corr.setBigDecimal("haber", corr.getFloat("haber")+(debe));
                        System.out.println("debe" +debe);
                        
                        corr.set("fecha_pago",dateToMySQLDate(Calendar.getInstance().getTime(), false));
                        corr.saveIt();
                        texto=texto.concat(corr.getId().toString());
                        texto=texto.concat(", ");
                        entrega=entrega-(debe);
                    }
                    else{
                        corr.setFloat("haber", entrega+(corr.getFloat("haber")));
                        entrega=0;
                        corr.saveIt();

                    }
                }  
                }
                Base.commitTransaction();
                Base.openTransaction();
                Pago pago = Pago.createIt("fecha", getCalendarioText().getText(),
                "monto", entregaNoMod,
                "cliente_id", idcliente,
                "descripcion", descripcion.getText()+ " "+ texto);
                cli.set("cuenta_corriente_manual",entrega);
                cli.saveIt();
                Base.commitTransaction();
                
                JOptionPane.showMessageDialog(this, "¡Cobro registrado exitosamente!");
                reportePago.mostrarPago( pago.getInteger("id"));
                co.cargarCuentas();
                this.dispose();
            }
            }catch (ClassNotFoundException ex) {
                        Logger.getLogger(RealizarPagoVentaControlador.class.getName()).log(Level.SEVERE, null, ex);
                    }catch (SQLException ex) {
                        Logger.getLogger(RealizarPagoVentaControlador.class.getName()).log(Level.SEVERE, null, ex);
                    }catch (JRException ex) {
                        Logger.getLogger(RealizarPagoVentaControlador.class.getName()).log(Level.SEVERE, null, ex);
                    }
         
       }
    

    }//GEN-LAST:event_aceptarActionPerformed

    
    /*va true si se quiere usar para mostrarla por pantalla es decir 12/12/2014 y false si va 
    para la base de datos, es decir 2014/12/12*/
    public String dateToMySQLDate(java.util.Date fecha, boolean paraMostrar) {
        if(fecha!=null){
        if(paraMostrar){
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(fecha);
        }
        else{
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(fecha);
        }
        }
        else{
            return "";
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton aceptar;
    private com.toedter.calendar.JDateChooser calendario;
    private javax.swing.JLabel cliente;
    private javax.swing.JTextArea descripcion;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField monto;
    // End of variables declaration//GEN-END:variables
}
