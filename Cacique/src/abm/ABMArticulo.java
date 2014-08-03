/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package abm;

import modelos.Articulo;
import modelos.Proveedor;
import org.javalite.activejdbc.Base;

/**
 *
 * @author nico
 */
public class ABMArticulo {

    //busca por el codigo, no por el id
    public Articulo getArticulo(Articulo p) {
        return Articulo.first("codigo =?", p.get("codigo"));
    }

    //existe  el articulo?
    public boolean findArticulo(Articulo p) {
        return (Articulo.first("codigo = ?", p.get("codigo")) != null);
    }

    public boolean alta(Articulo art) {
        if (!findArticulo(art)) {
            Base.openTransaction();
            Articulo nuevo = Articulo.create(
                    "codigo", art.get("codigo"),
                    "nombre", art.get("nombre"),
                    "marca", art.get("marca"),
                    "stock_actual", art.get("stock_actual"),
                    "stock_minimo", art.get("stock_minimo"),
                    "precio_compra", art.get("precio_compra"),
                    "precio_venta", art.get("precio_venta"),
                    "descripcion", art.get("descripcion") //"ultima_compra",art.get("ultima_compra"), // es al pedo que vaya, no tiene compra todavía
                    //"proveedor_id",art.get("proveedor_id")
                    );
            Proveedor p = Proveedor.findFirst("nombre =?", art.getNombreProv());
            if (p != null) {
                p.add(nuevo);
            }

            nuevo.saveIt();

            Base.commitTransaction();
            return true;
        } else {
            System.out.println("Existe articulo");
            return false;
        }
    }

    public boolean baja(Articulo art) {
        boolean ret = false;
        if (findArticulo(art)) {
            Base.openTransaction();
            ret = art.delete();
            Base.commitTransaction();
        }
        return ret;
    }

    public boolean modificar(Articulo art) {
        boolean ret = false;
        Articulo viejo = Articulo.findFirst("codigo = ?", art.get("codigo"));
        if (viejo != null) {
            Base.openTransaction();
            viejo.set(
                    "codigo", art.get("codigo"),
                    "nombre", art.get("nombre"),
                    "marca", art.get("marca"),
                    "sotck_actual", art.get("stock_actual"),
                    "stock_minimo", art.get("stock_minimo"),
                    "precio_compra", art.get("precio_compra"),
                    "precio_venta", art.get("precio_venta"),
                    "descripcion", art.get("descripcion") //"ultima_compra",art.get("ultima_compra"), // es al pedo que vaya, no tiene compra todavía
                    );
            ret = art.saveIt();
            Proveedor p = Proveedor.findFirst("nombre =?", art.getNombreProv());
            if (p != null) {
                p.add(art);
            }

            Base.commitTransaction();
        }
        return ret;
    }
}
