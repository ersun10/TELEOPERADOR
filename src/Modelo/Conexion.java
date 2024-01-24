/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.DriverManager;

/**
 *
 * @author luisj
 */
public class Conexion {

    private String url;
    private String driver;
    private java.sql.Connection con;

    public Conexion() {
        url = "jdbc:sqlite:Database/Database.db";
        driver = "org.sqlite.JDBC";
    }

    public java.sql.Connection abrirConexion() {
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url);
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (java.sql.SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return con;

    }

    public void cerrarConexion(java.sql.Connection conexion) {
        try {
            conexion.close();
        } catch (java.sql.SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

}
