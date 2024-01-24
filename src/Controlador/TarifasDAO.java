/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Conexion;

/**
 *
 * @author luisj
 */
public class TarifasDAO {

    private String[] nombres;
    private java.sql.Connection con;
    private java.sql.PreparedStatement ps;
    private java.sql.Statement st;
    private java.sql.ResultSet result;
    private String consulta;

    // Instancia
    private final Conexion conexion;

    public TarifasDAO() {
        conexion = new Conexion();
    }

    public javax.swing.DefaultComboBoxModel<String> getNombres(String tablaTarifas) {
        consulta = "SELECT * FROM " + tablaTarifas;
        javax.swing.DefaultComboBoxModel<String> comboModel = new javax.swing.DefaultComboBoxModel<String>();
        try {
            con = conexion.abrirConexion();
            st = con.createStatement();
            result = st.executeQuery(consulta);
            int contador = 0;
            if (con == null) {
                System.out.println("Sin conexión");
            }
            if (result != null) {
                while (result.next()) {
                    comboModel.addElement(result.getString("Nombre"));
                }
            } else {
                System.out.println("No se pudo acceder a los datos");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        try {
            result.close();
            st.close();
            conexion.cerrarConexion(con);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return comboModel;

    }

    public boolean setTarifa(String nombre, Double precio, String tablaTarifas, Double GananciaTele) {
        boolean ejecutado = false;
        consulta = "INSERT INTO " + tablaTarifas + " VALUES (NULL, ?, ?, ?)";
        try {
            con = conexion.abrirConexion();
            ps = con.prepareStatement(consulta);
            ps.setString(1, nombre);
            ps.setDouble(2, precio);
            ps.setDouble(3, GananciaTele);
            ps.executeUpdate();
            ejecutado = true;

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            javax.swing.JOptionPane.showMessageDialog(null, "Nombre incorrecto o ya existente en la base de datos", "Nombre existente", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            ejecutado = false;
        }

        try {
            ps.clearParameters();
            ps.close();
            conexion.cerrarConexion(con);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
         }

        return ejecutado;
    }
    
    public Double getPrecio(String tablaTarifa, String tarifaSeleccionada){
        String consulta = "SELECT Precio FROM " + tablaTarifa + " WHERE Nombre = '" + tarifaSeleccionada + "'";
        Double precio = 0.00;
        try{
        con = conexion.abrirConexion();
        st = con.createStatement();
        result = st.executeQuery(consulta);
        if(result != null){
           precio = result.getDouble("Precio");
        }
        }catch(Exception ex){
            ex.getMessage();
        }
        try{
        result.close();
        st.close();
        conexion.cerrarConexion(con);
        }catch(Exception ex){
            ex.getMessage();
        }
       return precio; 
    }
    
    public Double getGanancia(String tablaTarifa, String tarifaSeleccionada){
        String consulta = "SELECT GananciaTeleoperador FROM " + tablaTarifa + " WHERE Nombre = '" + tarifaSeleccionada + "'";
        Double ganancia = 0.00;
        try{
        con = conexion.abrirConexion();
        st = con.createStatement();
        result = st.executeQuery(consulta);
        if(result != null){
           ganancia = result.getDouble("GananciaTeleoperador");
        }
        }catch(Exception ex){
            ex.getMessage();
        }
        try{
        result.close();
        st.close();
        conexion.cerrarConexion(con);
        }catch(Exception ex){
            ex.getMessage();
        }
       return ganancia; 
    }
     public void delServicios(String nombre, String nombreTabla){
        String consulta = "DELETE FROM " + nombreTabla + " WHERE Nombre = '" + nombre + "'";
        try{
        con = conexion.abrirConexion();
        st = con.createStatement();
        st.executeUpdate(consulta);
        }catch(Exception ex){
            System.out.print(ex.getMessage());
        }
        
        try{
         st.close();
         conexion.cerrarConexion(con);
        }catch(java.sql.SQLException ex){
            System.out.print(ex.getMessage());
        }
    }
}
