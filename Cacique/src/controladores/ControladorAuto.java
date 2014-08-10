/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controladores;

import abm.ABMAuto;
import interfaz.AutoGui;
import interfaz.Trabajos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelos.Articulo;
import modelos.Auto;
import modelos.Cliente;
import modelos.Proveedor;
import modelos.Trabajo;
import org.javalite.activejdbc.LazyList;
import org.javalite.activejdbc.Model;

/**
 *
 * @author nico
 */
public class ControladorAuto  implements ActionListener {
    
    AutoGui autoGui;
    DefaultTableModel autosDefault;
    DefaultTableModel trabajosDefault;
    private java.util.List<Auto> listAutos;
    private java.util.List<Cliente> listClientes;
    //private java.util.List<Trabajo> listTrabajos;
        private JTable tablaAutos;
        private JTable tablaTrabajos;
    private ABMAuto abmAuto;
    private Boolean isNuevo;
    private Boolean editandoInfo;
    private Auto auto;
    private Trabajos trabajoGui;
    
    public ControladorAuto(AutoGui autoGui, Trabajos trabajoGui) {
        this.autoGui = autoGui;
        this.trabajoGui= trabajoGui;
        isNuevo = true; //para saber si es nuevo o no
        editandoInfo = false; // se esta editando la info
        auto = new Auto();
        this.autoGui = autoGui;
        this.autoGui.setActionListener(this);
        tablaAutos= autoGui.getTablaAutos();
        tablaTrabajos= autoGui.getTablaTrabajos();
        autosDefault= autoGui.getAutosDefault();
        trabajosDefault= autoGui.getTrabajosDefault();
        listAutos= new LinkedList();
       //listTrabajos= new LinkedList();
        listClientes= new LinkedList();
        abmAuto= new ABMAuto();
        listAutos=Auto.findAll();
        actualizarLista();
        autoGui.getBusqueda().addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                busquedaKeyReleased(evt);
            }
        });
        autoGui.getBusquedaDuenio().addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                busquedaDuenioKeyReleased(evt);
            }
        });
        autoGui.getTablaAutos().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });
                autoGui.getTablaTrabajos().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClickedAuto(evt);
            }
        });

        
    }
    
    public void busquedaKeyReleased(java.awt.event.KeyEvent evt) {
        System.out.println("apreté el caracter: " + evt.getKeyChar());
        autoGui.getBusquedaDuenio().setText("");
        realizarBusqueda();
    }
    
     public void busquedaDuenioKeyReleased(java.awt.event.KeyEvent evt) {
        System.out.println("apreté el caracter: " + evt.getKeyChar());
                autoGui.getBusqueda().setText("");

        realizarBusquedaDuenio();
    }
     
     private void realizarBusquedaDuenio() {
                    LazyList<Cliente> duenios= Cliente.where("nombre like  ?", "%"+autoGui.getBusquedaDuenio().getText()+"%");
                    Iterator<Cliente> it = duenios.iterator();
                    listAutos=new LinkedList<>();
                    while(it.hasNext()){
                        Cliente cli= it.next();
                        
                        listAutos.addAll(cli.getAll(Auto.class));
                    }
                    actualizarLista();
            }
    
        private void realizarBusqueda() {
                    listAutos = Auto.where("id like ? or patente like ? or marca like ? or modelo like ? ", "%" + autoGui.getBusqueda().getText() + "%", "%" + autoGui.getBusqueda().getText() + "%", "%" + autoGui.getBusqueda().getText() + "%", "%" + autoGui.getBusqueda().getText() + "%");
                    actualizarLista();
            }

            public void cargarTodos() {
        
        listAutos = Auto.findAll();
        if (!listAutos.isEmpty()) {
            realizarBusqueda();
            System.out.println("cargue todo");
        }
        actualizarLista();
        
    }
            
              private void tablaMouseClickedAuto(java.awt.event.MouseEvent evt) {
        if (evt.getClickCount() == 2) {
            
            Trabajo t = Trabajo.findById(tablaTrabajos.getValueAt(tablaTrabajos.getSelectedRow(), 0));
            trabajoGui.setAutoModel(t.parent((Auto.class)));
            trabajoGui.setClienteModel(t.parent(Cliente.class));
            trabajoGui.cargarTrabajo(t);

            trabajoGui.bloquearCampos(false);
            
            trabajoGui.getModificar().setEnabled(true);
            trabajoGui.getGuardar().setEnabled(false);
                        trabajoGui.setVisible(true);
            trabajoGui.toFront();
        }
    }
            
             public void tablaMouseClicked(java.awt.event.MouseEvent evt) {
        if (evt.getClickCount() == 2) {
            //bloqueo los campos y habilito botones
            autoGui.habilitarCampos(false);
            autoGui.getBorrar().setEnabled(true);
            autoGui.getModificar().setEnabled(true);
            autoGui.getGuardar().setEnabled(false);
            autoGui.getNuevo().setEnabled(true);
            editandoInfo = false;
            autoGui.limpiarCampos();
            
            auto = Auto.findFirst("patente = ?", tablaAutos.getValueAt(tablaAutos.getSelectedRow(), 0));
            autoGui.CargarCampos(auto);
            

        }
    }
             
              private void actualizarLista() {
        
        autosDefault.setRowCount(0);
        Iterator<Auto> it = listAutos.iterator();
        while (it.hasNext()) {
            Auto auto = it.next();
            Object row[] = new String[5];
            row[0] = auto.getString("patente");
            row[1] = auto.getString("marca");
            row[2] = auto.getString("modelo");
            Cliente cli= auto.parent(Cliente.class);
            row[3] = cli.getString("nombre");
            row[4]= dateToMySQLDate(auto.getDate("ult_cambio_aceite"), true);
            
            autosDefault.addRow(row);

        }
                
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
             
    @Override
    public void actionPerformed(ActionEvent e) {
 if (e.getSource() == autoGui.getNuevo()) {
            System.out.println("Boton nuevo pulsado");
            autoGui.limpiarCampos();
            autoGui.habilitarCampos(true);
            isNuevo = true;
            editandoInfo = true;
            autoGui.getBorrar().setEnabled(false);
            autoGui.getModificar().setEnabled(false);
            autoGui.getGuardar().setEnabled(true);
            cargarClientes();
            autoGui.getDuenio().setSelectedItem(0);
        }    
    
    if (e.getSource() == autoGui.getGuardar() && editandoInfo && isNuevo) {
            System.out.println("Boton guardar pulsado");
            if (cargarDatosAuto(auto)) {
                
                if (abmAuto.alta(auto)) {

                    autoGui.habilitarCampos(false);
                    autoGui.limpiarCampos();
                    editandoInfo = false;
                    JOptionPane.showMessageDialog(autoGui, "¡Auto guardado exitosamente!");
                    autoGui.getNuevo().setEnabled(true);
                    autoGui.getGuardar().setEnabled(false);
                } else {
                    JOptionPane.showMessageDialog(autoGui, "codigo repetido, no se guardó el artículo", "Error!", JOptionPane.ERROR_MESSAGE);
                }
                
                realizarBusqueda();
            }
        }
    
    
    if (e.getSource() == autoGui.getBorrar()) {

            System.out.println("Boton borrar pulsado");
            autoGui.habilitarCampos(false);
            
            
            if (auto.getString("id") != null && !editandoInfo) {
                Integer resp = JOptionPane.showConfirmDialog(autoGui, "¿Desea borrar el auto " + autoGui.getPatente().getText(), "Confirmar borrado", JOptionPane.YES_NO_OPTION);
                if (resp == JOptionPane.YES_OPTION) {
                    
                    Boolean seBorro = abmAuto.baja(auto);
                    
                    if (seBorro) {
                        JOptionPane.showMessageDialog(autoGui, "¡Auto borrado exitosamente!");
                        autoGui.limpiarCampos();
                        realizarBusqueda();
                        autoGui.getBorrar().setEnabled(false);
                        autoGui.getModificar().setEnabled(false);
                    } else {
                        JOptionPane.showMessageDialog(autoGui, "Ocurrió un error, no se borró el auto", "Error!", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(autoGui, "No se seleccionó un artículo");
            }



        }
    
    if (e.getSource() == autoGui.getModificar()) {
            System.out.println("Boton modificar pulsado");
            
            autoGui.habilitarCampos(true);
            autoGui.getId().setEnabled(false);
            editandoInfo = true;
            isNuevo = false;
            autoGui.getBorrar().setEnabled(false);
            autoGui.getGuardar().setEnabled(true);
            autoGui.getModificar().setEnabled(false);
            cargarClientes();
            Cliente cli=auto.parent(Cliente.class);
            autoGui.getDuenio().setSelectedItem(cli.getString("id")+"-"+cli.getString("nombre"));
        }
    
    if (e.getSource() == autoGui.getGuardar() && editandoInfo && !isNuevo) {
            System.out.println("Boton guardar pulsado");
            if (cargarDatosAuto(auto)) {
                
                if (abmAuto.modificar(auto)) {
                    

                    autoGui.habilitarCampos(false);
                    autoGui.limpiarCampos();
                    editandoInfo = false;
                    JOptionPane.showMessageDialog(autoGui, "¡Auto modificado exitosamente!");
                    autoGui.getNuevo().setEnabled(true);
                    autoGui.getGuardar().setEnabled(false);
                } else {
                    JOptionPane.showMessageDialog(autoGui, "Ocurrió un error,revise los datos", "Error!", JOptionPane.ERROR_MESSAGE);
                }
                
                realizarBusqueda();
            }
        }
    }
    
    
    
    private boolean cargarDatosAuto(Auto auto) {
        boolean ret = true;
        try {
            String patente = autoGui.getPatente().getText();
            auto.set("patente", patente);
        } catch (ClassCastException e) {
            ret = false;
            JOptionPane.showMessageDialog(autoGui, "Error en la patente", "Error!", JOptionPane.ERROR_MESSAGE);
        }
        try {
            String marca = autoGui.getMarca().getText();
            auto.set("marca", marca);
        } catch (ClassCastException e) {
            ret = false;
            JOptionPane.showMessageDialog(autoGui, "Error en la marca", "Error!", JOptionPane.ERROR_MESSAGE);
        }
        
                try {
            String modelo = autoGui.getModelo().getText();
            auto.set("modelo", modelo);
        } catch (ClassCastException e) {
            ret = false;
            JOptionPane.showMessageDialog(autoGui, "Error en el modelo", "Error!", JOptionPane.ERROR_MESSAGE);
        }
        
       
        String duenio=autoGui.getDuenio().getSelectedItem().toString();
        if(duenio!=null){
            String idDuenio= duenio.split("-")[0];
            auto.set("cliente_id",idDuenio);
        }
        else{
            ret= false;
            JOptionPane.showMessageDialog(autoGui, "Error en el dueño", "Error!", JOptionPane.ERROR_MESSAGE);

        }
        
        return ret;
    }
    
        private void cargarClientes() {
        
        autoGui.getDuenio().removeAllItems();
        listClientes = Cliente.findAll();
        Iterator<Cliente> it = listClientes.iterator();
        while (it.hasNext()) {
            Cliente cli = it.next();
            autoGui.getDuenio().addItem(cli.getId().toString()+"-"+cli.get("nombre"));
        }
    }
    
}
