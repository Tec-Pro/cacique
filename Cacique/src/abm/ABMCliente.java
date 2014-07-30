/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package abm;

import modelos.Cliente;
import org.javalite.activejdbc.Base;


/**
 *
 * @author jacinto
 */
public class ABMCliente {
    
     public Cliente getCliente(Cliente c) {
        return Cliente.first("nombre = ?", c.get("nombre"));
    }

    public boolean findCliente(Cliente c) {
        return (Cliente.first("nombre = ?", c.get("nombre")) != null);
    }
    
    public boolean alta(Cliente c) {
        if (!findCliente(c)) {
            Base.openTransaction();
            Cliente nuevo = Cliente.create("nombre", c.get("nombre"), "telefono",
                    c.get("telefono"), "celular", c.get("celular"),
                    "direccion",c.get("direccion"),"nacimiento", c.get("nacimiento"),
                    "facebook",c.get("facebook"),"email",c.get("email"),"dni",c.get("dni"));
            nuevo.saveIt();
            Base.commitTransaction();
            return true;
        } else {
            return false;
        }
    }

    public boolean baja(Cliente c) {
        Cliente viejo = Cliente.findById(c.getId());
        if (viejo != null) {
            Base.openTransaction();
            viejo.delete();
            Base.commitTransaction();
            return true;
        }
        return false;
    }

    public boolean modificar(Cliente c) {
        Cliente viejo = Cliente.findById(c.getId());
        if (viejo != null) {
            Base.openTransaction();
            viejo.set("nombre", c.get("nombre"), "telefono",
                    c.get("telefono"), "celular", c.get("celular"), "direccion",c.get("direccion"),"nacimiento", c.get("nacimiento"),
                    "facebook",c.get("facebook"),"email",c.get("email"),"dni",c.get("dni")).saveIt();
            Base.commitTransaction();
            return true;
        }
        return false;
    }
    
}
