/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package abm;

import java.util.Arrays;
import java.util.Calendar;
import javax.swing.JOptionPane;
import modelos.Demo;
import modelos.Usuario;
import org.javalite.activejdbc.Base;

/**
 *
 * @author jacinto
 */
public class ManejoUsuario {

    public void crearUsuario() {
        if (Usuario.findAll().isEmpty()) {
            Usuario.createIt();
            Demo demo = new Demo();
            demo = Demo.createIt("fecha", Calendar.getInstance().getTime(), "activado", false);

        } else {
            Base.openTransaction();
            Demo demo = (Demo) Demo.findAll().get(0);
            if (!demo.getBoolean("activado")) {

                
                String cod=JOptionPane.showInputDialog(null,"Ingrese codigo de activación","");
                
                if (cod.equals("t3cpr0")) {
                    demo.setBoolean("activado", true);
                    demo.saveIt();
                } else {
                    JOptionPane.showMessageDialog(null, "codigo incorrecto, se seguirá con la demo, ");
                    if (demo.getInteger("dias") == 0) {
                        int opt = JOptionPane.showConfirmDialog(null, "Demo finalizada, debe activar el programa, se cerrará el programa", "Aviso", JOptionPane.CLOSED_OPTION);
                        System.exit(0);
                    } else {
                      java.sql.Date fechaHoy= new java.sql.Date( Calendar.getInstance().getTime().getTime());
                      java.sql.Date fechaUlt= demo.getDate("fecha");
                        if (fechaUlt.before(fechaHoy) && !fechaUlt.toString().equals(fechaHoy.toString())) {
                            demo.set("dias", demo.getInteger("dias") - 1);
                            demo.set("fecha", Calendar.getInstance().getTime());
                            JOptionPane.showMessageDialog(null, "DEMO: QUEDAN "+demo.getInteger("dias")+" DÍAS");
                            
                        } else {
                            if (fechaUlt.after(fechaHoy)) {
                                int opt = JOptionPane.showConfirmDialog(null, "Demo finalizada, debe activar el programa, se cerrará el programa", "Aviso", JOptionPane.CLOSED_OPTION);
                                 demo.set("dias",0);
                                 demo.saveIt();
                                 Base.commitTransaction();
                                System.exit(0);
                            }
                        }
                    }

                }
            }
            demo.saveIt();
                                Base.commitTransaction();

        }

    }

    public void modificarNombre(String nombre) {
        Usuario u = Usuario.findById(1);
        Base.openTransaction();
        u.set("nombre", nombre);
        u.save();
        Base.commitTransaction();
    }

    public void modificarPass(String pass) {
        Usuario u = Usuario.findById(1);
        Base.openTransaction();
        u.set("pass", pass);
        u.save();
        Base.commitTransaction();
    }

    public boolean modificarDatos(String user, String pass) {
        Usuario u = Usuario.findById(1);
        Base.openTransaction();
        u.set("nombre", user, "pass", pass);
        boolean ret = u.save();
        Base.commitTransaction();
        return ret;
    }

    public boolean login(String user, char[] pass) {
        Usuario u = Usuario.first("nombre = ?", user);
        if (u != null) {
            char[] correct = u.getString("pass").toCharArray();
            if (user.equals(u.getString("nombre")) && Arrays.equals(pass, correct)) {
                return true;
            }
        }
        return false;
    }
}
