/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package abm;

import modelos.Auto;
import org.javalite.activejdbc.Base;


/**
 *
 * @author jacinto
 */
public class ABMAuto {
    
     public Auto getAuto(Auto c) {
        return Auto.first("patente = ?", c.get("nombre"));
    }

    public boolean findAuto(Auto c) {
        return (Auto.first("patente = ?", c.get("patente")) != null);
    }
    
    public boolean alta(Auto c) {
        if (!findAuto(c)) {
            Base.openTransaction();
            Auto nuevo = Auto.create("patente",c.getString("patente"),"modelo", c.getString("modelo"),"marca", c.getString("marca"),"cliente_id", c.getString("cliente_id"));
            nuevo.saveIt();
            Base.commitTransaction();
            return true;
        } else {
            return false;
        }
    }

    public boolean baja(Auto c) {
        Auto viejo = Auto.findById(c.getId());
        if (viejo != null) {
            Base.openTransaction();
            viejo.delete();
            Base.commitTransaction();
            return true;
        }
        return false;
    }

    public boolean modificar(Auto c) {
        Auto viejo = Auto.findById(c.getId());
        if (viejo != null) {
            Base.openTransaction();
            viejo.set("patente",c.getString("patente"),"modelo", c.getString("modelo"),"marca", c.getString("marca"),"cliente_id", c.get("cliente_id")).saveIt();
            Base.commitTransaction();
            return true;
        }
        return false;
    }
    
}
