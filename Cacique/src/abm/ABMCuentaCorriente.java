/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package abm;


import modelos.Corriente;
import org.javalite.activejdbc.Base;

/**
 *
 * @author jacinto
 */
public class ABMCuentaCorriente {

    public ABMCuentaCorriente() {
    }
    
    
    
     public boolean alta(Corriente c) {
            Base.openTransaction();
            Corriente nuevo = Corriente.create("id_cliente", c.get("id_cliente"), "id_venta",
                    c.get("id_venta"), "monto", c.get("monto"),
                    "descripcion",c.get("descripcion"),"fecha",c.getDate("fecha"));
            nuevo.saveIt();
            Base.commitTransaction();
            return true;
     }
   

    public boolean baja(Corriente c) {
        Corriente viejo = Corriente.findById(c.getId());
        if (viejo != null) {
            Base.openTransaction();
            viejo.delete();
            Base.commitTransaction();
            return true;
        }
        return false;
        
    }

    public boolean modificar(Corriente c) {
        Corriente viejo = Corriente.findById(c.getId());
        if (viejo != null) {
            Base.openTransaction();
            viejo.set("monto", c.get("monto"),
                    "descripcion",c.get("descripcion"),"fecha",c.getDate("fecha")).saveIt();
           Base.commitTransaction();
           return true;
        }
        return false;
    }
    
}
