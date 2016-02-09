/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import abm.ManejoIp;
import abm.ManejoUsuario;
import interfaz.AplicacionGui;
import interfaz.LoginGui;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.javalite.activejdbc.Base;

/**
 *
 * @author nico
 */
public class ControladorLogin extends Thread {
    
    private String user;
    private char[] pass;
    private ManejoUsuario mu;
    private AplicacionGui app;
    private LoginGui log;
    
    public ControladorLogin(AplicacionGui app) {
        this.app = app;
    }
    
    public void run() {
        mu = new ManejoUsuario();
        mu.crearUsuario();
        log = new LoginGui();
        log.setLocationRelativeTo(null);
        log.setVisible(true);
        log.getPass().requestFocus();
        log.getPass().addKeyListener(new KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
 
                if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    log.getPass().setText("");
                    log.getPass().requestFocus();
                }
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    user = log.getUser().getText();
                    pass = log.getPass().getPassword();
                    if(Base.hasConnection()){
                        Base.close();
                    }
                     if(!Base.hasConnection()){
                            Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/cacique", "tecpro", "tecpro");
                    }
                    if (mu.login(user, pass)) {
                        log.dispose();
                        app.setVisible(true);
                        if(Base.hasConnection())
                            Base.close();                
                        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://"+ManejoIp.ipServer+"/cacique", "tecpro", "tecpro");
                        try {
                            Base.connection().setAutoCommit(true);
                        } catch (SQLException ex) {
                            Logger.getLogger(ControladorLogin.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        EmailThread emailThread = new EmailThread();
                        emailThread.run();
                    } else {
                        log.getPass().setText("");
                        JOptionPane.showMessageDialog(app, "INTENTE NUEVAMENTE", "¡DATOS INCORRECTOS!", JOptionPane.ERROR_MESSAGE);
                    }
                    
                    
                }
            }
        });
    }
    
    public LoginGui getLog() {
        return log;
    }
    

    

}
