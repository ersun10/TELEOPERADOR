/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlteleoperador;

import Controlador.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;

/**
 *
 * @author luisj
 */
public class NuevoDato extends javax.swing.JDialog {

    private String tipoForm;
    private String nombreTabla;

    // PANELES
    private javax.swing.JPanel panelAdd;
    private javax.swing.JPanel panelDel;
    // COMPONENTES AÑADIR
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtGanancia;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblPrecio;
    private javax.swing.JLabel lblGanancia;
    private javax.swing.JButton jbtAdd;
    private java.awt.Font fuenteLabel;
    private java.awt.Font fuenteButton;
    private javax.swing.JComboBox<String> comboServicios;

    // COMPONENTES ELIMINAR
    private javax.swing.JComboBox<String> comboDatos;
    private javax.swing.JButton jbtDelete;

    //INSTANCIAS
    private final TarifasDAO tarifas;
    private final TarotistasDAO tarotistas;
    private final ServiciosDAO servicios;

    public NuevoDato(java.awt.Frame parent, boolean modal, String tipoForm) {
        super(parent, modal);
        this.setTitle("Añadir " + tipoForm);
        this.setSize(320, 350);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.tipoForm = tipoForm;
        tarifas = new TarifasDAO();
        servicios = new ServiciosDAO();
        tarotistas = new TarotistasDAO();
        nombreTabla = "TarifasLlamada";
        initComponents();
        initCombos(tipoForm, nombreTabla);
        cambioForm(tipoForm);
    }

    private void initComponents() {
        this.setLayout(null);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });

        txtNombre = new javax.swing.JTextField();
        txtPrecio = new javax.swing.JTextField();
        txtGanancia = new javax.swing.JTextField();
        lblNombre = new javax.swing.JLabel();
        lblPrecio = new javax.swing.JLabel();
        lblGanancia = new javax.swing.JLabel();
        comboDatos = new javax.swing.JComboBox<String>();
        jbtAdd = new javax.swing.JButton();
        jbtDelete = new javax.swing.JButton();
        panelAdd = new javax.swing.JPanel();
        panelDel = new javax.swing.JPanel();
        fuenteLabel = new java.awt.Font("Copperplate Gothic Bold", java.awt.Font.PLAIN, 12);
        fuenteButton = new java.awt.Font("Copperplate Gothic Bold", java.awt.Font.PLAIN, 15);
        comboServicios = new javax.swing.JComboBox<String>(servicios.getServicios());
        
        panelAdd.setLayout(null);
        panelAdd.setBounds(0, 0, 302, 180);
        panelAdd.setBackground(new java.awt.Color(157, 108, 255));
        panelDel.setLayout(null);
        panelDel.setBounds(0, 180, 302, 123);
        panelDel.setBackground(new java.awt.Color(157, 108, 255));
        comboServicios.setBounds(55, 20, 200, 30);
        comboServicios.setVisible(false);
        comboServicios.addItemListener(new java.awt.event.ItemListener() {
            @Override
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboServiciosActionPerformed(evt);
            }
        });
        lblNombre.setText("Nombre");
        lblNombre.setBounds(20, 50, 100, 30);
        lblNombre.setForeground(java.awt.Color.WHITE);
        txtNombre.setBounds(20, 80, 100, 30);
        lblPrecio.setText("Precio");
        lblPrecio.setBounds(180, 50, 100, 30);
        lblPrecio.setForeground(java.awt.Color.WHITE);
        txtPrecio.setBounds(180, 80, 100, 30);
        lblPrecio.setVisible(false);
        txtPrecio.setVisible(false);
        lblGanancia.setText("Ganancia operador");
        lblGanancia.setBounds(180, 110, 130, 30);
        lblGanancia.setForeground(java.awt.Color.WHITE);
        txtGanancia.setBounds(180, 140, 100, 30);
        lblGanancia.setVisible(false);
        txtGanancia.setVisible(false);
        jbtAdd.setText("Añadir");
        jbtAdd.setBounds(20, 140, 130, 30);
        jbtAdd.setFont(fuenteButton);
        jbtAdd.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtAddActionPerformed(evt);
            }
        });

        comboDatos.setBounds(55, 20, 200, 30);
        jbtDelete.setText("Borrar");
        jbtDelete.setBounds(55, 80, 200, 30);
        jbtDelete.setFont(fuenteButton);
        jbtDelete.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtDeleteActionPerformed(evt);
            }
        });
        
        // Añadiendo componentes
        panelAdd.add(comboServicios);
        panelAdd.add(lblNombre);
        panelAdd.add(txtNombre);
        panelAdd.add(lblPrecio);
        panelAdd.add(txtPrecio);
        panelAdd.add(lblGanancia);
        panelAdd.add(txtGanancia);
        panelAdd.add(jbtAdd);
        panelDel.add(comboDatos);
        panelDel.add(jbtDelete);

        this.getContentPane().add(panelAdd);
        this.getContentPane().add(panelDel);
        this.getRootPane().setDefaultButton(jbtAdd);

    }

    private void initCombos(String tipoForm, String nombreTabla) {

        if (tipoForm.compareTo("Tarifa") == 0) {
            comboDatos.setModel(tarifas.getNombres(nombreTabla));
        } else if (tipoForm.compareTo("Tarotista") == 0) {
            comboDatos.setModel(tarotistas.getNombres());
        } else if (tipoForm.compareTo("Servicio") == 0) {
            comboDatos.setModel(servicios.getServicios());
        }
    }

    private void exitForm(java.awt.event.WindowEvent evt) {
        this.setVisible(false);
        this.dispose();
    }

    private void jbtAddActionPerformed(java.awt.event.ActionEvent evt) {
        String nombre = "";
        Double precio = 0.00;
        Double ganancia = 0.00;

        if (tipoForm.compareTo("Tarifa") == 0) {
            if (txtNombre.getText().compareTo("") == 0 || txtPrecio.getText().compareTo("") == 0 || txtGanancia.getText().compareTo("") == 0) {
                javax.swing.JOptionPane.showMessageDialog(null, "Debe rellenar todos los campos", "Error", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            } else {
                try {
                    nombre = txtNombre.getText();
                    precio = Double.parseDouble(txtPrecio.getText());
                    ganancia = Double.parseDouble(txtGanancia.getText());
                    tarifas.setTarifa(nombre, precio, nombreTabla, ganancia);
                    txtNombre.setText("");
                    txtPrecio.setText("");
                    txtGanancia.setText("");
                } catch (NumberFormatException ex) {
                    javax.swing.JOptionPane.showMessageDialog(null, "Datos incorrectos, compruebe que ha introducido bien los valores", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                }

            }
        } else if (tipoForm.compareTo("Tarotista") == 0) {
            if (txtNombre.getText().compareTo("") == 0) {
                javax.swing.JOptionPane.showMessageDialog(null, "No has introducido ningún nombre", "Nombre vacío", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            } else {
                nombre = txtNombre.getText();
                tarotistas.setTarotista(nombre);
                txtNombre.setText("");
            }
        } else if (tipoForm.compareTo("Servicio") == 0) {
            if (txtNombre.getText().compareTo("") == 0) {
                javax.swing.JOptionPane.showMessageDialog(null, "No has introducido ningún nombre", "Nombre vacío", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            } else {
                nombre = txtNombre.getText();
                servicios.setServicios(nombre);
                String nombreTablaTarifa = "";
                nombreTablaTarifa = "Tarifas"+txtNombre.getText();
                servicios.crearTablaTarifa(nombreTablaTarifa);
                txtNombre.setText("");
            }
        }
        initCombos(tipoForm, nombreTabla);
    }
    
    private void comboServiciosActionPerformed(java.awt.event.ItemEvent evt){
        nombreTabla = "Tarifas" + comboServicios.getSelectedItem().toString();
        initCombos(tipoForm, nombreTabla);
        
    }

    private void jbtDeleteActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            String nombre = comboDatos.getSelectedItem().toString();
            if (tipoForm.compareTo("Tarifa") == 0) {
                tarifas.delServicios(nombre, nombreTabla);
            } else if (tipoForm.compareTo("Tarotista") == 0) {
                tarotistas.delServicios(nombre);
            } else if (tipoForm.compareTo("Servicio") == 0) {
                servicios.delServicios(nombre);
                servicios.delTablaTarifa("Tarifas"+nombre);
            }
            initCombos(tipoForm, nombreTabla);
        } catch (Exception ex) {

        }
    }

    private void cambioForm(String tipoForm) {
        if (tipoForm.compareTo("Tarifa") == 0) {
            lblPrecio.setVisible(true);
            txtPrecio.setVisible(true);
            lblGanancia.setVisible(true);
            txtGanancia.setVisible(true);
            comboServicios.setVisible(true);
        }
    }

}
