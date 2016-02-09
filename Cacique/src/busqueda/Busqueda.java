/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package busqueda;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import modelos.Articulo;
import modelos.ArticulosCompras;
import modelos.ArticulosPresupuestos;
import modelos.ArticulosVentas;
import modelos.Cliente;
import modelos.ClientesArticulos;
import modelos.Compra;
import modelos.Presupuesto;
import modelos.Proveedor;
import modelos.Venta;
import org.javalite.activejdbc.Base;

/**
 *
 * @author jacinto
 */
public class Busqueda {

    /*
     * No hace falta distinguir entre mayúsculas y minúsculas.
     */
    /**
     *
     * @param id
     * @return Cliente asociado a esa id.
     */
    public Cliente buscarCliente(Object id) {
        Base.openTransaction();
        Cliente result = Cliente.findById(id);
        Base.commitTransaction();
        return result;
    }

    public List<Cliente> buscarCliente(String nombre) {
        Base.openTransaction();
        List<Cliente> result;
        result = Cliente.where("nombre like ?", "%" + nombre + "%");
        Base.commitTransaction();
        return result;
    }

    /**
     * @param nombre,
     * @param apellido e
     * @param id del cliente. Filtra los que tienen nombre a los pasados y
     * @return lista filtrada de clientes.
     */
    public List<Cliente> filtroCliente(String nombre, String codigo) {
        List<Cliente> result;
        Base.openTransaction();
        result = Cliente.where("nombre like ? and id like ?", "%" + nombre + "%", codigo + "%");
        Base.commitTransaction();
        return result;
    }

    /**
     * @param idcliente,
     * @param fecha desde (Pasada como string),
     * @param fecha hasta (Pasada como String). Filtra las ventas de los
     * clientes que empiezan con idcliente entre las fechas pasadas.
     * @return lista filtrada de ventas.
     */
    public List<Venta> filtroVenta(String idcliente, String desde, String hasta) {
        List<Venta> result;
        Base.openTransaction();
        result = Venta.where("cliente_id like ? and (fecha between ? and ?)", idcliente, desde, hasta);
        System.out.println(idcliente+ " "+ desde +" "+hasta);
        Base.commitTransaction();
        return result;
    }

       public List<Presupuesto> filtroPresupuesto(String idcliente, String desde, String hasta) {
        List<Presupuesto> result;
        Base.openTransaction();
        result = Presupuesto.where("cliente_id like ? and (fecha between ? and ?)", idcliente, desde, hasta);
        System.out.println(idcliente+ " "+ desde +" "+hasta);
        Base.commitTransaction();
        return result;
    }
    
    /**
     * @param idproveedor,
     * @param fecha desde (Pasada como string),
     * @param fecha hasta (Pasada como String). Filtra las compras a los
     * proveedores que empiezan con idproveedores entre las fechas pasadas.
     * @return lista filtrada de compras.
     */
    public List<Compra> filtroCompra(String idproveedor, String desde, String hasta) {
        List<Compra> result;
        Base.openTransaction();
        result = Compra.where("proveedor_id like ? and fecha between ? and ?", idproveedor + "%", desde, hasta);
        Base.commitTransaction();
        return result;
        
    }

    /**
     * @param cuil,
     * @param nombre,
     * @param apellido. Filtra los proveedores que tienen lo pasado en nombre.
     * @return lista filtrada de proveedores.
     */
    public List<Proveedor> filtroProveedor(String nombre, String codigo) {
        Base.openTransaction();
        List<Proveedor> result;
        result = Proveedor.where("nombre like ? and id like ?", "%" + nombre + "%", "%" + codigo + "%");
        Base.commitTransaction();
        return result;
    }

    /**
     * @param idcompra,
     * @param idproducto. Filtra los productos comprados en idcompra o las
     * compras que contienen idproducto o ambas.
     * @returns lista filtrada de productos comprados.
     */
    public List<ArticulosCompras> filtroComprados(String idcompra, String idproducto) {
        Base.openTransaction();
        List<ArticulosCompras> result;
        result = ArticulosCompras.where("compra_id like ? and producto_id like ?", idcompra + "%", idproducto + "%");
        Base.commitTransaction();
        return result;
    }

    /**
     * @param idventa,
     * @param idproducto. Filtra los productos vendidos en idventa o las ventas
     * que contienen idproducto o ambas.
     * @returns lista filtrada de productos vendidos.
     */
    public List<ArticulosVentas> filtroVendidos(String idventa, String idproducto) {
        List<ArticulosVentas> result;
        Base.openTransaction();
        result = ArticulosVentas.where("venta_id like ? and producto_id like ?", idventa + "%", idproducto + "%");
        Base.commitTransaction();
        return result;
    }
    
     public LinkedList<ArticulosPresupuestos> filtroPresupuestados(String idventa, String idproducto) {
        List<ArticulosPresupuestos> result;
        Base.openTransaction();
        result = ArticulosPresupuestos.where("presupuesto_id like ? and articulo_id like ?", idventa + "%", idproducto + "%");
        Iterator it = result.iterator();
        LinkedList<ArticulosPresupuestos> result2 = new LinkedList<ArticulosPresupuestos>();
        while (it.hasNext()) {
            ArticulosPresupuestos ap = (ArticulosPresupuestos) it.next();
            result2.add(ap);
        }
        Base.commitTransaction();
        return result2;
    }


    public List<ArticulosVentas> filtroVendidos(String idventa) {
        Base.openTransaction();
        List<ArticulosVentas> result;
        result = ArticulosVentas.where("venta_id like ?", idventa);
        Base.commitTransaction();
        return result;
    }

    /**
     * @param idcliente,
     * @param idproducto. Filtra los productos adquiridos por idcliente o los
     * clientes que adquirieron idproducto.
     * @returns lista filtrada de productos adquiridos.
     */
    public List<ClientesArticulos> filtroAdquiridos(String idcliente, String idproducto) {
        List<ClientesArticulos> result;
        Base.openTransaction();
        result = ClientesArticulos.where("cliente_id like ? and producto_id like ?", idcliente + "%", idproducto + "%");
        Base.commitTransaction();
        return result;
    }

    /**
     * @param idcliente. Devuelve todos los productos asociados a un cliente.
     * @returns lista filtrada de productos.
     */
    public List<Articulo> productosAdquiridos(String idcliente) {
        Base.openTransaction();
        List<ClientesArticulos> adquiridos;
        List<Articulo> result = new LinkedList<Articulo>();
        adquiridos = ClientesArticulos.where("cliente_id like ?", "%" + idcliente + "%");
        Iterator<ClientesArticulos> it = adquiridos.iterator();
        while (it.hasNext()) {
            ClientesArticulos a = it.next();
            Articulo p = Articulo.first("codigo = ?", a.get("producto_id"));
            result.add(p);
        }
        Base.commitTransaction();
        return result;
    }

    public List<Articulo> productosVendidos(String idventa) {
        List<ArticulosVentas> vendidos;
        Base.openTransaction();
        List<Articulo> result = new LinkedList<Articulo>();
        vendidos = ArticulosVentas.where("venta_id like ?", "%" + idventa + "%");
        Iterator<ArticulosVentas> it = vendidos.iterator();
        while (it.hasNext()) {
            ArticulosVentas pv = it.next();
            Articulo p = Articulo.first("codigo = ?", pv.get("producto_id"));
            result.add(p);
        }
        Base.commitTransaction();
        return result;
    }

    public List<Articulo> provee(String cuil) {
        Base.openTransaction();
        Proveedor p = Proveedor.findFirst("nombre= ?", cuil);
        List<Articulo> ret=Articulo.where("es_articulo=1 and proveedor_id = ?", p.getId());
        Base.commitTransaction();
        return ret;
    }

    /**
     * @param Devuelve todos los proveedores de la base de datos.
     * @returns lista de todos los proveedores.
     */
    public List<Proveedor> proveedores() {
        Base.openTransaction();
        List<Proveedor> ret=Proveedor.findAll();
        Base.commitTransaction();
        return ret;
    }

    /**
     * @param codigo,
     * @param equivalencia_farm Filtra aquellos que empiecen con el código
     * pasado y que contengan el equivalente en fram y marca.
     * @return lista filtrada de productos
     */
    public List<Articulo> filtroProducto(String codigo, String fram) {
        List<Articulo> result;
        Base.openTransaction();
        result = Articulo.where("es_articulo=1 and codigo like ? and equivalencia_fram like?", "%" + codigo + "%", "%" + fram + "%");
        Base.commitTransaction();
        return result;
    }

    public List<Articulo> filtroProducto(String codigo) {
        Base.openTransaction();
        List<Articulo> ret=Articulo.where("es_articulo=1 and codigo like ?", "%" + codigo + "%");
        Base.commitTransaction();
        return ret;
    }

    public List<Articulo> filtroProducto2(String fram) {
        Base.openTransaction();
        List<Articulo> ret=Articulo.where("es_articulo=1 and equivalencia_fram like ?", "%" + fram + "%");
        Base.commitTransaction();
        return ret;
    }
       
}

