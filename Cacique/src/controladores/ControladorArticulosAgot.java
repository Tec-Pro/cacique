/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controladores;

import abm.ManejoIp;
import interfaz.ArticuloGui;
import interfaz.ArticulosSinStock;
import java.math.RoundingMode;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelos.Articulo;
import modelos.Proveedor;
import org.javalite.activejdbc.Base;

/**
 *
 * @author nico
 */
public class ControladorArticulosAgot {
    ArticulosSinStock articuloSinStockGui;
    DefaultTableModel tablaArtDefault ;
    JTable tablaArticulos;
        private java.util.List<Articulo> listArticulos;
            private ArticuloGui articuloGui;



    public ControladorArticulosAgot(ArticulosSinStock articuloSinStockGui, ArticuloGui articuloGui) {
        this.articuloSinStockGui = articuloSinStockGui;
        this.articuloGui= articuloGui;
         tablaArtDefault = this.articuloSinStockGui.getTablaArticulosDefault();
        tablaArticulos = this.articuloSinStockGui.getArticulos();
        listArticulos = new LinkedList();
        Base.openTransaction();
        listArticulos = Articulo.where("es_articulo = 1 and stock_actual <= stock_minimo", (Object) null);
        Base.commitTransaction();
        articuloSinStockGui.getBusqueda().addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                busquedaKeyReleased(evt);
            }
        });
        articuloSinStockGui.getArticulos().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaArticulosClicked(evt);
            }
        });
    }
    
        public void busquedaKeyReleased(java.awt.event.KeyEvent evt) {
        realizarBusqueda();
    }

    public void realizarBusqueda() {
        Base.openTransaction();
        listArticulos = Articulo.where("(codigo like ? or descripcion like ? or marca like ? or id like ? or nombre like ? or id like ?) and stock_actual<=stock_minimo and es_articulo = 1 ", "%" + articuloSinStockGui.getBusqueda().getText() + "%", "%" + articuloSinStockGui.getBusqueda().getText() + "%", "%" + articuloSinStockGui.getBusqueda().getText() + "%", "%" + articuloSinStockGui.getBusqueda().getText() + "%", "%" + articuloSinStockGui.getBusqueda().getText() + "%", "%" + articuloSinStockGui.getBusqueda().getText() + "%");
                Base.commitTransaction();
        actualizarLista();

    }
    
    
       private void actualizarLista() {
        
        tablaArtDefault.setRowCount(0);
        Iterator<Articulo> it = listArticulos.iterator();
        while (it.hasNext()) {
            Articulo art = it.next();
            Object row[] = new String[8];
            row[0] = art.getString("codigo");
            row[1] = art.getString("nombre");
            row[2] = art.getString("marca");
            row[3] = art.getString("descripcion");
            row[4] = art.getString("id");
            row[5] = art.getString("stock_actual");
            row[6]= art.getString("stock_minimo");
            row[7]= dateToMySQLDate(art.getDate("ultima_compra"),true);
  
            tablaArtDefault.addRow(row);

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
       



    
    private void tablaArticulosClicked(java.awt.event.MouseEvent evt) {
        if (evt.getClickCount() == 2) {
            Base.openTransaction();
            Articulo articulo = Articulo.findFirst("codigo = ?", tablaArticulos.getValueAt(tablaArticulos.getSelectedRow(), 0));
            Proveedor papacito = articulo.parent(Proveedor.class);
            if (papacito == null) {
                articulo.setNombreProv("");
            } else {
                articulo.setNombreProv(papacito.getString("nombre"));
            }
            
Base.commitTransaction();
            articuloGui.CargarCampos(articulo);
            articuloGui.setVisible(true);
            articuloGui.toFront();
            
        }
    }
}
