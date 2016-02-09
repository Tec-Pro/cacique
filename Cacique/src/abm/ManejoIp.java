/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package abm;

import interfaz.ConfigurarServerGui;
import java.util.Arrays;
import modelos.Ip;
import org.javalite.activejdbc.Base;
import org.javalite.activejdbc.LazyList;

/**
 *
 * @author nico
 */
public class ManejoIp {

    public static String ipServer="";
    public static  boolean servidor;
    
    public void crearIp() {
        if (Ip.findAll().isEmpty()) {
                    new ConfigurarServerGui(null, true).setVisible(true);
        }
    }

    public void modificarIp(String ipNueva) {
        Ip ip = (Ip)Ip.findAll().get(0);;
        Base.openTransaction();
        ip.set("remoto", ipNueva);
        ip.save();
        Base.commitTransaction();
    }

    public void modificarIsServidor(boolean si) {
        Ip ip =(Ip)Ip.findAll().get(0);;
        Base.openTransaction();
        ip.setBoolean("servidor", si);
        ip.save();
        Base.commitTransaction();
    }

    public boolean modificarDatos(String ipNueva, boolean isServer) {
        LazyList<Ip> ips =Ip.findAll();
        if(!ips.isEmpty()){
            Ip u= ips.get(0);
        
        
        Base.openTransaction();
        System.out.println(ipNueva);
        u.set("remoto", ipNueva, "servidor", isServer);
        boolean ret = u.saveIt();
        System.out.println(ret);
        System.out.println(ManejoIp.ipServer);
        Base.commitTransaction();
        return true;
        }
        else{
            Ip u;
          Base.openTransaction();
        u= Ip.create("remoto", ipNueva, "servidor", isServer);
        boolean ret = u.save();
        Base.commitTransaction();
        return ret;  
        }
        
       
    }

    public void conseguirDatos(){
        Ip u = (Ip)Ip.findAll().get(0);
        ipServer= u.getString("remoto");
        servidor= u.getBoolean("servidor");
    }
}
