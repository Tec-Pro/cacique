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
import modelos.ArticulosVentas;
import modelos.ClientesArticulos;
import modelos.Venta;
import net.sf.jasperreports.engine.util.Pair;
import org.javalite.activejdbc.LazyList;

/**
 *
 * @author jacinto
 */
public class ABMVenta {

    int ultimoIdVenta;

    public ABMVenta() {
    }

    //FUNCIONA CORRECTAMENTE
    public boolean alta(Venta v) {
        boolean resultOp = true;
        if (v == null) {
            resultOp = false;
        } else {
            Integer idCliente = (Integer) v.get("cliente_id");
            Venta venta = Venta.create("monto", v.get("monto"), "cliente_id", idCliente, "fecha", v.get("fecha"), "pago", v.get("pago"), "pago_id", v.get("pago_id"));
            resultOp = resultOp && venta.saveIt();//guardo la venta
            int idVenta = venta.getInteger("id");
            ultimoIdVenta = idVenta;
            resultOp = resultOp && cargarProductosVendidos(idVenta, v.getProductos(), v.getPreciosFinales());//guardo los productos vendidos
            resultOp = resultOp && actualizarAdquisicionCliente(idCliente, v.getProductos());//actualizo la tabla de productos adquiridos por clientes
            resultOp = resultOp && actualizarStock(v.getProductos());//actualizo el stock de productos vendidos
        }
        return resultOp;
    }

    //FUNCIONA CORRECTAMENTE
    public boolean bajaConDevolucion(Venta v) {
        boolean resultOp = true;
        Integer idVenta = v.getInteger("id");//saco el idVenta
        Venta venta = Venta.findById(idVenta);//la busco en BD y la traigo
        if (venta == null) {
            resultOp = false;
        } else {
            Integer idCliente = (Integer) venta.get("cliente_id");//saco el idcliente de esa venta
            LinkedList<Pair> viejosProductos = buscarProductosVendidos(idVenta); //saco los viejos productos de la venta
            resultOp = resultOp && devolucionStock(viejosProductos);//actualizo el stock por haber sacado los viejos productos
            resultOp = resultOp && eliminarAdquisicionCliente(idCliente, viejosProductos);//actualizo los productos adquiridos quitando los viejos productos
            ArticulosVentas.delete("venta_id = ?", idVenta);//elimino todos los productosvendidos
            resultOp = resultOp && venta.delete(); //elimino la venta
        }
        return resultOp;
    }


    /*Funcion que calcula el precio actual de los productos que se fiaron y
     * paga la cuenta.
     */
    public boolean pagar(Venta v, BigDecimal monto) {
        if (v == null) {
            return false;
        } else {
            v.set("pago", true);
            v.set("monto", monto);//seteo el monto de la venta total en el modelo
            LinkedList<BigDecimal> preciosFinales = new LinkedList();
            LinkedList<Pair> pairList = buscarProductosVendidos(v.getInteger("id"));
            Iterator<Pair> itr = pairList.iterator();
            while (itr.hasNext()) {
                Pair p = itr.next();
                Articulo art = (Articulo) p.first();
                BigDecimal precioActual = art.getBigDecimal("precio_venta");
                preciosFinales.add(precioActual);
            }
            return v.saveIt() && cargarProductosVendidos(v.getInteger("id"), pairList, preciosFinales);
        }
    }

//FUNCIONA CORRECTAMENTE
    /*Recibe lista de pares <Producto,cantidad> retorna precio total de la venta de todos
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
    private boolean cargarProductosVendidos(int idVenta, LinkedList<Pair> productos, LinkedList<BigDecimal> preciosFinales) {
        boolean resultOp = true;
        Iterator itr = productos.iterator();
        Articulo prod;
        Pair par;
        BigDecimal cant;
        Venta v = Venta.findById(idVenta);
        Iterator itr2 = preciosFinales.iterator();
        while (itr.hasNext()) {
            par = (Pair) itr.next(); //saco el par de la lista
            BigDecimal precioFinal = (BigDecimal) itr2.next();
            prod = (Articulo) par.first(); //saco el producto del par
            cant = ((BigDecimal) par.second()).setScale(2, RoundingMode.CEILING);//saco la cantidad del par
            ArticulosVentas prodVendido = ArticulosVentas.create("venta_id", idVenta, "articulo_id", prod.get("id"), "cantidad", cant, "precio_final", precioFinal);
            resultOp = resultOp && prodVendido.saveIt();
        }
        return resultOp;
    }

    //FUNCIONA CORRECTAMENTE
    /*Agrego los productos adquiridos por el cliente a la tabla adquirio,
     * retorna un booleano que es true si las adquisiciones se actualizaron
     * con exito
     */
    private boolean actualizarAdquisicionCliente(int idCliente, LinkedList<Pair> productos) {
        boolean resultOp = true;
        Iterator itr = productos.iterator();
        Articulo prod;
        Pair par;
        BigDecimal cant;
        while (itr.hasNext()) {
            par = (Pair) itr.next(); //saco el par de la lista
            prod = (Articulo) par.first(); //saco el producto del par
            cant = ((BigDecimal) par.second()).setScale(2, RoundingMode.CEILING);//saco la cantidad del par
            ClientesArticulos prodAdquirido;
            prodAdquirido = ClientesArticulos.findFirst("cliente_id = ? AND articulo_id = ?", idCliente, prod.get("id"));
            if (prodAdquirido == null) { // sino lo agrego a la tabla
                prodAdquirido = ClientesArticulos.create("cliente_id", idCliente, "articulo_id", prod.get("id"), "cantidad", cant);
                resultOp = resultOp && prodAdquirido.saveIt();
            } else { //si existe modifico la cantidad
                cant = ((prodAdquirido.getBigDecimal("cantidad")).add(cant)).setScale(2, RoundingMode.CEILING);//asigno a cant el valor nuevo de cantidad
                ClientesArticulos.update("cantidad = ?", "cliente_id = ? AND articulo_id = ?", cant, idCliente, prod.get("id"));
            }
        }
        return resultOp;
    }

    //FUNCIONA CORRECTAMENTE
    /*Busca los productos adquiridos por el cliente y actualiza su cantidad tras la eliminacion
     * o modificacion de una venta, si la cantidad del producto adquirido es 0 lo borra de la tabla
     * sino decrementa en la cantidad que este fue adquirido
     */
    public boolean eliminarAdquisicionCliente(int idCliente, LinkedList<Pair> productos) {
        boolean resultOp = true;
        Iterator itr = productos.iterator();
        Articulo prod;
        Pair par;
        BigDecimal cant;
        while (itr.hasNext()) {
            par = (Pair) itr.next(); //saco el par de la lista
            prod = (Articulo) par.first(); //saco el producto del par
            cant = ((BigDecimal) par.second()).setScale(2, RoundingMode.CEILING);//saco la cantidad del par
            ClientesArticulos prodAdquirido;
            prodAdquirido = ClientesArticulos.findFirst("cliente_id = ? AND articulo_id = ?", idCliente, prod.get("id"));
            if (prodAdquirido == null) { //si no existe lo informo
                System.out.println("ERROR - PRODUCTO NO ENCONTRADO EN TABLA DE ADQUISICIONES DE CLIENTE");
            } else {
                if ((prodAdquirido.getBigDecimal("cantidad")).subtract(cant).signum() > 0) {
                    cant = ((prodAdquirido.getBigDecimal("cantidad")).subtract(cant)).setScale(2, RoundingMode.CEILING);//asigno a cant el valor nuevo de cantidad
                    ClientesArticulos.update("cantidad = ?", "cliente_id = ? AND articulo_id = ?", cant, idCliente, prod.get("id"));
                } else {
                    if ((prodAdquirido.getBigDecimal("cantidad")).subtract(cant).signum() == 0) {
                        ClientesArticulos.delete("cliente_id = ? AND articulo_id = ?", idCliente, prod.get("id"));
                    } else {
                        resultOp = false;
                        System.out.println("ERROR LA CANTIDAD DE PRODUCTOS ADQUIRIDOS ES MENOR A LA VENDIDA");
                    }
                }
            }
        }
        return resultOp;
    }

    //FUNCIONA CORRECTAMENTE
    /*Retorna una lista de pares producto-cantidad de una compra(la busca en
     * productos_comprados y a su vez
     * elimina estos productos de la base de la misma tabla
     */
    private LinkedList<Pair> buscarProductosVendidos(int idVenta) {
        BigDecimal cant;
        ArticulosVentas prodVendido;
        Articulo prod;
        LinkedList<Pair> listaDePares = new LinkedList();
        LazyList<ArticulosVentas> productos = ArticulosVentas.find("venta_id = ?", idVenta);
        Iterator itr = productos.iterator();
        while (itr.hasNext()) {
            prodVendido = (ArticulosVentas) itr.next(); //saco el modelo de la lista
            prod = Articulo.findById(prodVendido.get("articulo_id"));//saco el producto del modelo
            cant = prodVendido.getBigDecimal("cantidad").setScale(2, RoundingMode.CEILING);//saco la cantidad del modelo
            Pair par = new Pair(prod, cant); //creo el par producto-cantidad
            listaDePares.add(par);//agrego el par a la lista de pares
            ArticulosVentas.delete("venta_id = ? AND articulo_id= ?", prodVendido.getInteger("venta_id"), prodVendido.getInteger("articulo_id"));//elimino el modelo de la base de datos
        }
        return listaDePares;
    }

    public int getUltimoIdVenta() {
        return ultimoIdVenta;
    }

    //FUNCIONA CORRECTAMENTE   // VER LAS MOVIDAS DEL BIGDECIMAL
    //Actualiza el stock de los productos vendidos 
    private boolean actualizarStock(LinkedList<Pair> productos) {
        boolean resultOp = true;
        Iterator itr = productos.iterator();
        Articulo prodViejo;
        Pair par;
        BigDecimal cant;
        while (itr.hasNext()) {
            par = (Pair) itr.next(); //saco el par de la lista
            prodViejo = (Articulo) par.first(); //saco el producto del par
            cant = (BigDecimal) par.second();//saco la cantidad del par
            cant = prodViejo.getBigDecimal("stock_actual").subtract(cant);//asigno a cant el valor nuevo del stock
            resultOp = resultOp && prodViejo.setBigDecimal("stock_actual", cant).saveIt();//actualizo el stock del producto
        }
        return resultOp;
    }

    //FUNCIONA CORRECTAMENTE
    /*Actualiza el stock de los productos vendidos cuando se da de baja una venta
     * o cuando se modifica (incrementando el stock nuevamente)
     */
    public boolean devolucionStock(LinkedList<Pair> productos) {
        boolean resultOp = true;
        Iterator itr = productos.iterator();
        Articulo prodViejo;
        Pair par;
        BigDecimal cant;
        while (itr.hasNext()) {
            par = null;
            par = (Pair) itr.next(); //saco el par de la lista
            prodViejo = (Articulo) par.first(); //saco el producto del par
            cant = (BigDecimal) par.second();//saco la cantidad del par
            cant = prodViejo.getBigDecimal("stock_actual").add(cant);//devuelvo el stock anterior a la venta del producto
            resultOp = resultOp && prodViejo.setInteger("stock_actual", cant).saveIt();//actualizo el stock del producto
        }
        return resultOp;
    }
}
