/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Conexion;
import java.util.regex.*;

/**
 *
 * @author luisj
 */
public class ClientesDAO {

    private String consulta;
    private java.sql.Connection con;
    private java.sql.Statement st;
    private java.sql.PreparedStatement ps;
    private java.sql.ResultSet result;
    private javax.swing.table.DefaultTableModel table;

    // Instancias
    private final Conexion conexion;

    public ClientesDAO() {
        conexion = new Conexion();
    }

    public javax.swing.table.DefaultTableModel getDatos() {
        consulta = "SELECT * FROM Registros ORDER BY ID DESC;";
        table = new javax.swing.table.DefaultTableModel();
        String[] cabeceras = {"ID", "Cliente", "Teléfono", "Tarotista", "Servicio", "Tarifa", "Hora", "Fecha", "Pago"};
        for (int i = 0; i < cabeceras.length; i++) {
            table.addColumn(cabeceras[i]);
        }
        try {
            con = conexion.abrirConexion();
            st = con.createStatement();
            result = st.executeQuery(consulta);
            while (result.next()) {
                table.addRow(new Object[]{result.getInt("ID"), result.getString("Nombre"), result.getInt("Teléfono"), result.getString("Tarotista"), result.getString("Servicio"),
                    result.getString("Tarifa"), result.getString("Hora"), result.getString("Fecha"), result.getString("MetodoPago")});
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        try {
            result.close();
            st.close();
            conexion.cerrarConexion(con);
        } catch (Exception ex) {
            System.out.print("No se pudo cerrar las conexiones");
        }

        return table;
    }

    public void insertarDatos(String nombre, int telefono, String tarotista, String servicio, String tarifa, String hora, String fecha, Double precio, Double ganancia,
            String referencia, String metodoPago, String codigoPago) {
        consulta = "INSERT INTO Registros VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            con = conexion.abrirConexion();
            ps = con.prepareStatement(consulta);

            ps.setString(1, nombre);
            ps.setInt(2, telefono);
            ps.setString(3, tarotista);
            ps.setString(4, servicio);
            ps.setString(5, tarifa);
            ps.setString(6, hora);
            ps.setString(7, fecha);
            ps.setDouble(8, precio);
            ps.setDouble(9, ganancia);
            ps.setString(10, referencia);
            ps.setString(11, metodoPago);
            ps.setString(12, codigoPago);
            ps.executeUpdate();
        } catch (Exception ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            
        }
        try {
            ps.clearParameters();
            ps.close();
            conexion.cerrarConexion(con);
        } catch (Exception ex) {
            System.out.println("No se pudieron cerrar las conexiones");
        }
    }

    public javax.swing.table.DefaultTableModel buscarRegistro(int telefono) {
        javax.swing.table.DefaultTableModel modelo = new javax.swing.table.DefaultTableModel();
        String consulta = "SELECT * FROM Registros WHERE Teléfono = " + telefono + " ORDER BY Fecha ASC;";
        String[] cabeceras = {"ID", "Cliente", "Teléfono", "Tarotista", "Servicio", "Tarifa", "Hora", "Fecha", "Referencia", "Pago", "Código"};

        for (int i = 0; i < cabeceras.length; i++) {
            modelo.addColumn(cabeceras[i]);
        }
        try {
            con = conexion.abrirConexion();
            st = con.createStatement();
            result = st.executeQuery(consulta);
            if (result != null) {
                while (result.next()) {
                    modelo.addRow(new Object[]{result.getInt("ID"), result.getString("Nombre"), result.getInt("Teléfono"), result.getString("Tarotista"),
                        result.getString("Servicio"), result.getString("Tarifa"), result.getString("Hora"), result.getString("Fecha"), result.getString("Referencia"),
                        result.getString("MetodoPago"), result.getString("CódigoPago")});
                }
            }
        } catch (Exception ex) {
            ex.getMessage();
        }
        try {
            st.close();
            conexion.cerrarConexion(con);
        } catch (java.sql.SQLException ex) {
            ex.getMessage();
        }

        return modelo;
    }

    public boolean borrarRegistro(int indice) {
        boolean correcto = false;
        String consulta = "DELETE FROM Registros WHERE ID = '" + indice + "'";
        try {
            con = conexion.abrirConexion();
            st = con.createStatement();
            st.execute(consulta);
            correcto = true;
        } catch (Exception ex) {
            ex.getMessage();
            correcto = false;
        }
        try {
            st.close();
            conexion.cerrarConexion(con);
        } catch (Exception ex) {
            ex.getMessage();
        }
        return correcto;
    }

    public String comprobarNombreExistente(String telefono) {
        String comprobacion = "";
        String consulta = "SELECT Nombre FROM Registros WHERE Teléfono = " + telefono;
        try {
            con = conexion.abrirConexion();
            st = con.createStatement();
            result = st.executeQuery(consulta);
            if (result != null) {
                comprobacion = result.getString("Nombre");
            }
        } catch (Exception ex) {
            ex.getMessage();
        }

        try {
            st.close();
            conexion.cerrarConexion(con);
        } catch (java.sql.SQLException ex) {
            ex.getMessage();
        }

        return comprobacion;   
    }
    
    public javax.swing.table.DefaultTableModel getResumen(String fecha){
        javax.swing.table.DefaultTableModel modelo = new javax.swing.table.DefaultTableModel();
        String consulta = "SELECT * from Registros where Fecha LIKE '%" + fecha + "'";
        String[] cabeceras = {"ID", "Cliente", "Teléfono", "Tarotista", "Servicio", "Tarifa", "Hora", "Fecha", "Referencia", "Pago", "Código"};

        for (int i = 0; i < cabeceras.length; i++) {
            modelo.addColumn(cabeceras[i]);
        }
        try{
        con = conexion.abrirConexion();
        st = con.createStatement();
        result = st.executeQuery(consulta);
        if(result != null){
            while(result.next()){
               modelo.addRow(new Object[]{result.getInt("ID"), result.getString("Nombre"), result.getInt("Teléfono"), result.getString("Tarotista"),
                        result.getString("Servicio"), result.getString("Tarifa"), result.getString("Hora"), result.getString("Fecha"), result.getString("Referencia"),
                        result.getString("MetodoPago"), result.getString("CódigoPago")}); 
            }
        }
        } catch(Exception ex){
            System.out.print(ex.getMessage());
        }
        try{
         result.close();
         st.close();
         conexion.cerrarConexion(con);
        }catch(java.sql.SQLException ex){
            javax.swing.JOptionPane.showMessageDialog(null, "No se encontraron datos", "Error", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        }
        
        return modelo;
    }
    
    public Double getGanancias(String fecha){
        Double ganancia = 0.00;
        String consulta = "";
        if(fecha.compareTo("TOTAL") == 0){
            consulta = "SELECT * FROM Registros";
        } else {
            consulta = "SELECT * FROM Registros WHERE fecha LIKE '%" + fecha + "'";
        }
        
        try{
            con = conexion.abrirConexion();
            st = con.createStatement();
            result = st.executeQuery(consulta);
            if(result != null){
                while(result.next()){
                    ganancia += result.getDouble("GananciaTeleoperador");
                }
            }
        }catch(Exception ex){
            System.out.print(ex.getMessage());
        }
        try{
            result.close();
            st.close();
            conexion.cerrarConexion(con);
        }catch(java.sql.SQLException ex){
            System.out.print(ex.getMessage());
        }
        
        
        return ganancia;
    }
    
    public Object[] getDatosFila(int id){
        String consulta = "SELECT * FROM Registros WHERE ID = " + id;
        int idRegistro = 0;
        String nombreCliente = "";
        int telefonoCliente = 0;
        String nombreTarotista = "";
        String nombreServicio = "";
        String nombreTarifa = "";
        String hora = "";
        String fecha = "";
        Double precio = 0.0;
        Double gananciaTeleoperador = 0.0;
        String referenciaWhats = "";
        String metodoPago = "";
        String codigoPago = "";
        try{
            con = conexion.abrirConexion();
            st = con.createStatement();
            result = st.executeQuery(consulta);
            if(result!=null){
                while(result.next()){
                 idRegistro = result.getInt("ID");
                 nombreCliente = result.getString("Nombre");
                 telefonoCliente = result.getInt("Teléfono");
                 nombreTarotista = result.getString("Tarotista");
                 nombreServicio = result.getString("Servicio");
                 nombreTarifa = result.getString("Tarifa");
                 hora = result.getString("Hora");
                 fecha = result.getString("Fecha");
                 precio = result.getDouble("Precio");
                 gananciaTeleoperador = result.getDouble("GananciaTeleoperador");
                 referenciaWhats = result.getString("Referencia");
                 metodoPago = result.getString("MetodoPago");
                 codigoPago = result.getString("CódigoPago");
                }
            } 
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        try{
         result.close();
         st.close();
         conexion.cerrarConexion(con);
        }catch(java.sql.SQLException ex){
            System.out.println(ex.getMessage());
        }
        Object[] datos = new Object[]{idRegistro, nombreCliente, telefonoCliente, nombreTarotista, nombreServicio, nombreTarifa, hora, fecha, precio, gananciaTeleoperador,
        referenciaWhats, metodoPago, codigoPago};
        return datos;
    }

}
