/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import abm.ManejoIp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author nico
 */
public class ControladorJReport {

    private JasperReport reporte;
    private final String logo = "/reporte/logo.png";
     private final String logoPres = "/reporte/logo1.png";

    public ControladorJReport(String jasper) throws JRException, ClassNotFoundException, SQLException {
        reporte = (JasperReport) JRLoader.loadObject(getClass().getResource("/reporte/" + jasper));//cargo el reporte
    }

    //listado de clientes productos y proveedores.
    public void mostrarReporte() throws ClassNotFoundException, SQLException, JRException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://"+ManejoIp.ipServer+"/cacique", "tecpro", "tecpro");
        Map parametros = new HashMap();
        parametros.clear();
        parametros.put("logo", this.getClass().getResourceAsStream(logo));
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, connection);
        JasperViewer.viewReport(jasperPrint, false);
       
    }

    public void mostrarTrabajo(int trabajo) throws ClassNotFoundException, SQLException, JRException {
        Class.forName("com.mysql.jdbc.Driver");

        Connection connection = DriverManager.getConnection("jdbc:mysql://"+ManejoIp.ipServer+"/cacique", "tecpro", "tecpro");
        Map parametros = new HashMap();
        parametros.clear();
        parametros.put("trabajo", trabajo);
        parametros.put("logo", this.getClass().getResourceAsStream(logo));
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, connection);
        JasperViewer.viewReport(jasperPrint, false);
    }
    
        public void mostrarPresupuesto(int presu) throws ClassNotFoundException, SQLException, JRException {
        Class.forName("com.mysql.jdbc.Driver");

        Connection connection = DriverManager.getConnection("jdbc:mysql://"+ManejoIp.ipServer+"/cacique", "tecpro", "tecpro");
        Map parametros = new HashMap();
        parametros.clear();
        System.out.println(presu);
        parametros.put("numFactura", presu);
         parametros.put("logo", this.getClass().getResourceAsStream(logoPres));
                  

        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, connection);
        JasperViewer.viewReport(jasperPrint, false);
        connection.close();
    }
        
        
    public void mostrarPago(int pago) throws ClassNotFoundException, SQLException, JRException {
        Class.forName("com.mysql.jdbc.Driver");

        Connection connection = DriverManager.getConnection("jdbc:mysql://"+ManejoIp.ipServer+"/cacique", "tecpro", "tecpro");
        Map parametros = new HashMap();
        parametros.clear();
        parametros.put("pago", pago);
        parametros.put("logo", this.getClass().getResourceAsStream(logo));
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, connection);
        JasperViewer.viewReport(jasperPrint, false);
    }
    
        public void mostrarPagoProv(int pago) throws ClassNotFoundException, SQLException, JRException {
        Class.forName("com.mysql.jdbc.Driver");

        Connection connection = DriverManager.getConnection("jdbc:mysql://"+ManejoIp.ipServer+"/cacique", "tecpro", "tecpro");
        Map parametros = new HashMap();
        parametros.clear();
        parametros.put("pago", pago);
        parametros.put("logo", this.getClass().getResourceAsStream(logo));
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, connection);
        JasperViewer.viewReport(jasperPrint, false);
    }
}
