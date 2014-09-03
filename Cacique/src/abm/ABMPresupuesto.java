/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package abm;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Iterator;
import java.util.LinkedList;
import modelos.Articulo;
import modelos.ArticulosPresupuestos;
import modelos.Presupuesto;
import net.sf.jasperreports.engine.util.Pair;
import org.javalite.activejdbc.Base;
import org.javalite.activejdbc.LazyList;

/**
 *
 * @author jacinto
 */
public class ABMPresupuesto {

    int ultimoIdPresupuesto;

    public ABMPresupuesto() {
    }

    //FUNCIONA CORRECTAMENTE
    public boolean alta(Presupuesto v) {
        boolean resultOp = true;
        if (v == null) {
            resultOp = false;
        } else {
            Integer idCliente = (Integer) v.get("cliente_id");
            Presupuesto presupuesto = Presupuesto.create("monto", v.get("monto"), "cliente_id", idCliente, "fecha", v.get("fecha"),"patente",v.get("patente"),"realizado",v.get("realizado"));
            resultOp = resultOp && presupuesto.saveIt();//guardo la venta
            int idPresupuesto = presupuesto.getInteger("id");
            ultimoIdPresupuesto = idPresupuesto;
            resultOp = resultOp && cargarProductosPresupuestados(idPresupuesto, v.getProductos(), v.getPreciosFinales());//guardo los productos vendidos
        }
        return resultOp;
    }

    //FUNCIONA CORRECTAMENTE
    /*Elimino un presupuesto y los productos ligados a ella, sin hacer devolucion de stock,
     * ni actualizacion de tablas de adquisicion ni tabla de productos_vendidos
     */
    public boolean baja(Presupuesto v) {
        boolean resultOp = true;
        Integer idVenta = v.getInteger("id");//saco el idVenta
        Presupuesto presupuesto = Presupuesto.findById(idVenta);//la busco en BD y la traigo        
        if (presupuesto == null) {
            resultOp = false;
        } else {
            ArticulosPresupuestos.delete("presupuesto_id = ?", idVenta);//elimino todos los productosvendidos
            resultOp = resultOp && presupuesto.delete();//elimino el Presupuesto
        }
        return resultOp;
    }

//FUNCIONA CORRECTAMENTE
    /*Recibe lista de pares <Producto,cantidad> retorna precio total del Presupuesto de todos
     los productos de la lista, multiplicados por su cantidad correspondiente*/
    public BigDecimal calcularMonto(LinkedList<Pair> productos) {
        BigDecimal acumMonto = new BigDecimal(0);
        if (productos.isEmpty()) {
            return acumMonto;
        } else {
            Iterator itr = productos.iterator();
            Pair par;
            Articulo prod;
            BigDecimal cant;
            BigDecimal precioFinal;
            while (itr.hasNext()) {
                par = (Pair) itr.next(); //saco el par de la lista
                prod = (Articulo) par.first(); //saco el producto del par
                cant = ((BigDecimal) (((Pair) par.second()).first())).setScale(2, RoundingMode.CEILING);//saco la cantidad del par
                precioFinal = ((BigDecimal) prod.get("precio_venta")).setScale(2, RoundingMode.CEILING);
                acumMonto.add(precioFinal.multiply(cant)).setScale(2, RoundingMode.CEILING); //multiplico el precio del producto por la cantidad del mismo     
            }
            return acumMonto;
        }
    }

    //FUNCIONA CORRECTAMENTE
    //Carga los productos y cantidades en la tabla productos_vendidos
    private boolean cargarProductosPresupuestados(int idPresupuesto, LinkedList<Pair> productos, LinkedList<BigDecimal> preciosFinales) {
        boolean resultOp = true;
        Iterator itr = productos.iterator();
        Articulo prod;
        Pair par;
        BigDecimal cant;
        Presupuesto v = Presupuesto.findById(idPresupuesto);
        Iterator itr2 = preciosFinales.iterator();
        while (itr.hasNext()) {
            par = (Pair) itr.next(); //saco el par de la lista
            BigDecimal precioFinal = (BigDecimal) itr2.next();
            prod = (Articulo) par.first(); //saco el producto del par
            cant = ((BigDecimal) par.second()).setScale(2, RoundingMode.CEILING);//saco la cantidad del par
            ArticulosPresupuestos prodVendido = ArticulosPresupuestos.create("presupuesto_id", idPresupuesto, "articulo_id", prod.get("id"), "cantidad", cant, "precio_final", precioFinal);
            resultOp = resultOp && prodVendido.saveIt();
        }
        return resultOp;
    }

    //FUNCIONA CORRECTAMENTE
    /*Retorna una lista de pares producto-cantidad de una compra(la busca en
     * productos_comprados y a su vez
     * elimina estos productos de la base de la misma tabla
     */
    private LinkedList<Pair> buscarProductosPresupuestados(int idPresupuesto) {
        BigDecimal cant;
        ArticulosPresupuestos prodVendido;
        Articulo prod;
        LinkedList<Pair> listaDePares = new LinkedList();
        LazyList<ArticulosPresupuestos> productos = ArticulosPresupuestos.find("presupuesto_id = ?", idPresupuesto);
        Iterator itr = productos.iterator();
        while (itr.hasNext()) {
            prodVendido = (ArticulosPresupuestos) itr.next(); //saco el modelo de la lista
            prod = Articulo.findById(prodVendido.get("articulo_id"));//saco el producto del modelo
            cant = prodVendido.getBigDecimal("cantidad").setScale(2, RoundingMode.CEILING);//saco la cantidad del modelo
            Pair par = new Pair(prod, cant); //creo el par producto-cantidad
            listaDePares.add(par);//agrego el par a la lista de pares
            ArticulosPresupuestos.delete("presupuesto_id = ? AND articulo_id= ?", prodVendido.getInteger("presupuesto_id"), prodVendido.getInteger("articulo_id"));//elimino el modelo de la base de datos
        }
        return listaDePares;
    }

    public int getUltimoIdPresupuesto() {
        return ultimoIdPresupuesto;
    }
}