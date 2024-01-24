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
public class ServiciosDAO {

    Conexion conexion;
    java.sql.PreparedStatement ps;
    java.sql.Connection con;
    java.sql.ResultSet result;
    java.sql.Statement st;
    private boolean correcto;

    public ServiciosDAO() {
        conexion = new Conexion();
        correcto = false;
    }

    public javax.swing.DefaultComboBoxModel<String> getServicios() {
        String consulta = "SELECT * FROM Servicios";
        javax.swing.DefaultComboBoxModel<String> modelCombo = new javax.swing.DefaultComboBoxModel<String>();

        try {
            con = conexion.abrirConexion();
            st = con.createStatement();
            result = st.executeQuery(consulta);
            while (result.next()) {
                modelCombo.addElement(result.getString("Nombre"));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        try {
            result.close();
            st.close();
            conexion.cerrarConexion(con);
        } catch (Exception ex) {
            ex.getMessage();
        }
        return modelCombo;
    }

    public boolean setServicios(String nombre) {
        String consulta = "INSERT INTO Servicios VALUES (Null, ?)";
        try {
            con = conexion.abrirConexion();
            ps = con.prepareStatement(consulta);
            ps.setString(1, nombre);
            ps.executeUpdate();
            correcto = true;
        } catch (Exception ex) {
            javax.swing.JOptionPane.showMessageDialog(null, "Nombre incorrecto o ya existente en la base de datos", "Nombre existente", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            correcto = false;
        }

        try {
            ps.clearParameters();;
            ps.close();
            conexion.cerrarConexion(con);
        } catch (Exception ex) {
            ex.getMessage();
        }
        return correcto;

    }

    public void delServicios(String nombre) {
        String consulta = "DELETE FROM Servicios WHERE Nombre = '" + nombre + "'";
        try {
            con = conexion.abrirConexion();
            st = con.createStatement();
            st.executeUpdate(consulta);
        } catch (Exception ex) {
            System.out.print(ex.getMessage());
        }

        try {
            st.close();
            conexion.cerrarConexion(con);
        } catch (java.sql.SQLException ex) {
            System.out.print(ex.getMessage());
        }
    }

    public void crearTablaTarifa(String nombre) {
        String consulta = "CREATE TABLE \"" + nombre + "\" (\n"
                + "	\"ID\"	INTEGER NOT NULL,\n"
                + "	\"Nombre\"	TEXT NOT NULL UNIQUE,\n"
                + "	\"Precio\"	REAL NOT NULL,\n"
                + "	\"GananciaTeleoperador\"	REAL NOT NULL,\n"
                + "	PRIMARY KEY(\"ID\" AUTOINCREMENT)\n"
                + ")";
        try{
            con = conexion.abrirConexion();
            st = con.createStatement();
            st.execute(consulta);
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        
        try{
         st.close();
         conexion.cerrarConexion(con);
        }catch(java.sql.SQLException ex){
            System.out.println(ex.getMessage());
        }

    }
    
    public void delTablaTarifa(String nombre){
        String consulta = "DROP TABLE " + nombre;
        
        try{
        con = conexion.abrirConexion();
        st = con.createStatement();
        st.execute(consulta);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        try{
            st.close();
            conexion.cerrarConexion(con);
        }catch(java.sql.SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

}
