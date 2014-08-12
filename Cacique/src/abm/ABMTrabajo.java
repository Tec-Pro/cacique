/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package abm;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import modelos.Auto;
import modelos.Cliente;
import modelos.Trabajo;
import org.javalite.activejdbc.Base;
import org.javalite.activejdbc.LazyList;


/**
 *
 * @author jacinto
 */
public class ABMTrabajo {
    
     public Trabajo getTrabajo(Trabajo c) {
        return Trabajo.findById(c.getId());
    }

    public boolean findTrabajo(Trabajo c) {
        return (Trabajo.exists(c.getId())) ;
    }
    
    public boolean alta(Trabajo t, Auto a, Cliente c) {
        if (!findTrabajo(t)) {
            Base.openTransaction();
            Trabajo nuevo = Trabajo.create(
            "fecha", t.getDate("fecha"),
"kilometraje",t.getString("kilometraje"),
"observaciones",t.getString("observaciones"),
"aceite_caja",t.getBoolean("aceite_caja"),
"aceite_diferencial",t.getBoolean("aceite_diferencial"),
"filtro_aire",t.getBoolean("filtro_aire"),
"filtro_combustible",t.getBoolean("filtro_combustible"),
"filtro_aceite",t.getBoolean("filtro_aceite"),
"filtro_habitaculo",t.getBoolean("filtro_habitaculo"),
"liquido_freno",t.getBoolean("liquido_freno"),
"anticongelante",t.getBoolean("anticongelante"),
"correa_multicanal",t.getBoolean("correa_multicanal"),
"tensor_correa_multicanal",t.getBoolean("tensor_correa_multicanal"),
"correa_distribucion",t.getBoolean("correa_distribucion"),
"tensores",t.getBoolean("tensores"),
"bomba_agua",t.getBoolean("bomba_agua"),
"bateria",t.getBoolean("bateria"),
"tipo_bateria",t.getString("tipo_bateria"),
"importe_bateria",t.getBigDecimal("importe_bateria").toString(),
"descripcion_bateria",t.getString("descripcion_bateria"),
"costo",t.getBigDecimal("costo").toString(),
"descripcion_adicional",t.getString("descripcion_adicional"),
"cliente_id",c.getId(),
"aceite_motor", t.getBoolean("aceite_motor")
            );
            a.add(nuevo);
            nuevo.saveIt();
            if(t.getBoolean("aceite_motor")){
                a.set("ult_cambio_aceite",t.getDate("fecha")).saveIt();
            }
            Base.commitTransaction();
            return true;
        } else {
            return false;
        }
    }

    public boolean baja(Trabajo c) {
        Trabajo viejo = Trabajo.findById(c.getId());
        if (viejo != null) {
            Base.openTransaction();
            viejo.delete();
            Base.commitTransaction();
            return true;
        }
        return false;
    }

    public boolean modificar(Trabajo t, Auto a, Cliente c) {
        Trabajo viejo = Trabajo.findById(t.getId());
        if (viejo != null) {
            Base.openTransaction();
            viejo.set(
            "fecha", t.getDate("fecha"),
"kilometraje",t.getString("kilometraje"),
"observaciones",t.getString("observaciones"),
"aceite_caja",t.getBoolean("aceite_caja"),
"aceite_diferencial",t.getBoolean("aceite_diferencial"),
"filtro_aire",t.getBoolean("filtro_aire"),
"filtro_combustible",t.getBoolean("filtro_combustible"),
"filtro_aceite",t.getBoolean("filtro_aceite"),
"filtro_habitaculo",t.getBoolean("filtro_habitaculo"),
"liquido_freno",t.getBoolean("liquido_freno"),
"anticongelante",t.getBoolean("anticongelante"),
"correa_multicanal",t.getBoolean("correa_multicanal"),
"tensor_correa_multicanal",t.getBoolean("tensor_correa_multicanal"),
"correa_distribucion",t.getBoolean("correa_distribucion"),
"tensores",t.getBoolean("tensores"),
"bomba_agua",t.getBoolean("bomba_agua"),
"bateria",t.getBoolean("bateria"),
"tipo_bateria",t.getString("tipo_bateria"),
"importe_bateria",t.getBigDecimal("importe_bateria").toString(),
"descripcion_bateria",t.getString("descripcion_bateria"),
"costo",t.getBigDecimal("costo").toString(),
"descripcion_adicional",t.getString("descripcion_adicional"),
"aceite_motor", t.getBoolean("aceite_motor"),
"cliente_id",c.getId()
            );
             a.add(viejo);
             boolean ret=viejo.saveIt();
               if(t.getBoolean("aceite_motor")){
                ret= a.set("ult_cambio_aceite",t.getDate("fecha")).saveIt();
            }     
                    Base.commitTransaction();
            return ret;
        }
        return false;
    }
    
    public static void main(String[] args){
        
        if (!Base.hasConnection()) {
            Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/cacique", "tecpro", "tecpro");
        }
        
        ABMTrabajo abm=new ABMTrabajo() ;
        Trabajo t = new Trabajo();
        
        t.set(       
                "fecha", Calendar.getInstance().getTime(),
"kilometraje","1283",
"observaciones","observaciones",
"aceite_caja",true,
"aceite_diferencial",false,
"filtro_aire",true,
"filtro_combustible",false,
"filtro_aceite",true,
"filtro_habitaculo",false,
"liquido_freno",true,
"anticongelante",false,
"correa_multicanal",true,
"tensor_correa_multicanal",false,
"correa_distribucion",true,
"tensores",true,
"bomba_agua",false,
"bateria",true,
"tipo_bateria","tipo de bateria",
"importe_bateria",new BigDecimal(12.123),
"descripcion_bateria","descrpcion bateria w",
"costo",new BigDecimal(BigInteger.ONE),
"descripcion_adicional","adicional");
         abm.alta(t,(Auto)Auto.findById(1), (Cliente)Cliente.findById(1));
         
    }
    
}
