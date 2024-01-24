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
public class TarotistasDAO {

    private java.sql.Connection con;
    private java.sql.PreparedStatement ps;
    private java.sql.Statement st;
    private java.sql.ResultSet result;
    private String consulta;

    // Instancia
    private final Conexion conexion;

    public TarotistasDAO() {
        conexion = new Conexion();
    }

    public javax.swing.DefaultComboBoxModel<String> getNombres() {
        javax.swing.DefaultComboBoxModel<String> comboModel = new javax.swing.DefaultComboBoxModel<String>();
        consulta = "SELECT * FROM Tarotistas ORDER BY Nombre";
        try {
            con = conexion.abrirConexion();
            st = con.createStatement();
            result = st.executeQuery(consulta);
            while (result.next()) {
                comboModel.addElement(result.getString("Nombre"));
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

    public boolean setTarotista(String nombre) {
        boolean ejecutado = false;
        consulta = "INSERT INTO Tarotistas VALUES (NULL, ?)";
        try {
            con = conexion.abrirConexion();
            ps = con.prepareStatement(consulta);
            ps.setString(1, nombre);
            ps.executeUpdate();
            ejecutado = true;

        } catch (Exception ex) {
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
    
     public void delServicios(String nombre){
        String consulta = "DELETE FROM Tarotistas WHERE Nombre = '" + nombre + "'";
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
