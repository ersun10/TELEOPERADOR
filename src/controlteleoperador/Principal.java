/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlteleoperador;

import Controlador.ClientesDAO;
import Controlador.ExcelExport;
import Controlador.ServiciosDAO;
import Controlador.TarifasDAO;
import Controlador.TarotistasDAO;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;

/**
 *
 * @author luisj
 */
public class Principal extends javax.swing.JFrame {

    private String fecha;
    private javax.swing.JLabel logo;
    private javax.swing.ImageIcon icono;
    private javax.swing.JPanel panelLogo;
    private javax.swing.JPanel panelComponentes;
    private javax.swing.JPanel panelTabla;

    // MENU
    private javax.swing.JMenuBar barraMenu;
    private javax.swing.JMenu menuArchivo;
    private javax.swing.JMenu menuOpciones;
    private javax.swing.JMenu menuAyuda;
    private javax.swing.JMenuItem itemSalir;
    private javax.swing.JMenuItem itemAgregarTarotista;
    private javax.swing.JMenuItem itemAgregarGanancia;
    private javax.swing.JMenuItem itemAgregarTarifa;
    private javax.swing.JMenuItem itemInstrucciones;
    private javax.swing.JMenuItem itemAcerca;

    // CAPTURA DE DATOS
    private javax.swing.JLabel labelCliente;
    private javax.swing.JLabel labelTelefono;
    private javax.swing.JLabel labelTarotista;
    private javax.swing.JLabel labelServicio;
    private javax.swing.JLabel labelTarifa;
    private javax.swing.JLabel labelMetodoPago;
    private javax.swing.JLabel labelCodigoPago;
    private javax.swing.JLabel labelHora;
    private javax.swing.JLabel labelFecha;
    private javax.swing.JLabel labelPrecio;

    private javax.swing.JTextField txtCliente;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JComboBox<String> comboTarotista;
    private javax.swing.JComboBox<String> comboMetodoPago;
    private javax.swing.JComboBox<String> comboServicio;
    private javax.swing.JComboBox<String> comboTarifa;
    private javax.swing.JTextField txtHora;
    private javax.swing.JTextField txtCodigoPago;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtPrecio;
    private java.awt.Font fuenteLabel;
    private java.awt.Font fuenteButton;
    private javax.swing.JButton jbtaddFicha;
    private javax.swing.JButton jbtdeleteFicha;
    private javax.swing.JButton jbtbuscar;
    private javax.swing.JButton jbtresumen;
    private javax.swing.JButton jbtGanancia;
    private javax.swing.JButton jbtExportar;

    // TABLA
    private javax.swing.JScrollPane scrollTabla;
    private javax.swing.JTable tabla;

    // CONSULTAS
    private String tablaTarifas;
    private String tarifaSeleccionada;
    private int findTelefono;
    private String fechaResumen;

    // INSTANCIAS
    private final ClientesDAO clientes;
    private final TarifasDAO tarifas;
    private final ServiciosDAO servicios;
    private final TarotistasDAO tarotistas;
    private final ExcelExport exportar;

    public Principal() {
        this.setTitle("Tarot Estrella");
        this.setSize(780, 900);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setResizable(false);
        tablaTarifas = "TarifasLlamada";
        tarifaSeleccionada = "15 Minutos";
        fechaResumen = "TOTAL";
        tarotistas = new TarotistasDAO();
        clientes = new ClientesDAO();
        tarifas = new TarifasDAO();
        servicios = new ServiciosDAO();
        exportar = new ExcelExport();
        icono = new javax.swing.ImageIcon(this.getClass().getResource("logo.png"));
        tabla = new javax.swing.JTable();
        findTelefono = 0;
        initComponents();
        initCombos();
        actualizarPrecio();
        initTable();
    }

    private void initComponents() {

        this.setLayout(null);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });

        // Evento para validad campo de teléfono
        java.awt.event.KeyListener tel = new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTelefonoValidate(evt);
            }
        };
        java.awt.event.KeyListener fecha = new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFechaValidate(evt);
            }
        };
        java.awt.event.KeyListener hora = new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtHoraValidate(evt);
            }
        };

        java.awt.event.ItemListener combos = new java.awt.event.ItemListener() {
            @Override
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                combosItemStateChanged(evt);
            }
        };

        java.awt.event.ItemListener comboPrecio = new java.awt.event.ItemListener() {
            @Override
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cambiarPrecio(evt);
            }
        };

        java.awt.event.ActionListener itemsMenu = new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemsActionPerformed(evt);
            }
        };

        java.awt.event.MouseListener mostrarDatosTabla = new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mostrarDatosDoubleClick(evt);
            }
        };

        // Iniciando componentes
        panelLogo = new javax.swing.JPanel();
        logo = new javax.swing.JLabel();
        panelComponentes = new javax.swing.JPanel();
        panelTabla = new javax.swing.JPanel();
        barraMenu = new javax.swing.JMenuBar();
        menuArchivo = new javax.swing.JMenu();
        menuOpciones = new javax.swing.JMenu();
        menuAyuda = new javax.swing.JMenu();
        itemSalir = new javax.swing.JMenuItem();
        itemAgregarTarifa = new javax.swing.JMenuItem();
        itemAgregarTarotista = new javax.swing.JMenuItem();
        itemAgregarGanancia = new javax.swing.JMenuItem();
        itemInstrucciones = new javax.swing.JMenuItem();
        itemAcerca = new javax.swing.JMenuItem();
        scrollTabla = new javax.swing.JScrollPane();
        labelCliente = new javax.swing.JLabel();
        labelTelefono = new javax.swing.JLabel();
        labelTarotista = new javax.swing.JLabel();
        labelServicio = new javax.swing.JLabel();
        labelTarifa = new javax.swing.JLabel();
        labelHora = new javax.swing.JLabel();
        labelCodigoPago = new javax.swing.JLabel();
        labelMetodoPago = new javax.swing.JLabel();
        labelFecha = new javax.swing.JLabel();
        labelPrecio = new javax.swing.JLabel();
        txtCliente = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        comboTarotista = new javax.swing.JComboBox<String>();
        comboMetodoPago = new javax.swing.JComboBox<String>();
        comboServicio = new javax.swing.JComboBox<String>();
        comboTarifa = new javax.swing.JComboBox<String>();
        txtHora = new javax.swing.JTextField();
        txtCodigoPago = new javax.swing.JTextField();
        txtFecha = new javax.swing.JTextField();
        txtPrecio = new javax.swing.JTextField();
        fuenteLabel = new java.awt.Font("Copperplate Gothic Bold", java.awt.Font.PLAIN, 20);
        fuenteButton = new java.awt.Font("Copperplate Gothic Bold", java.awt.Font.PLAIN, 15);
        jbtaddFicha = new javax.swing.JButton();
        jbtdeleteFicha = new javax.swing.JButton();
        jbtbuscar = new javax.swing.JButton();
        jbtresumen = new javax.swing.JButton();
        jbtGanancia = new javax.swing.JButton();
        jbtExportar = new javax.swing.JButton();

        // Propiedades
        panelLogo.setBounds(110, 10, 450, 120);
        panelLogo.setLayout(null);
        panelLogo.setBackground(new java.awt.Color(40, 6, 108));
        panelComponentes.setLayout(null);
        panelComponentes.setBounds(20, 150, 720, 300);
        panelComponentes.setBackground(new java.awt.Color(157, 108, 255));
        panelComponentes.setBorder(new javax.swing.border.LineBorder(java.awt.Color.WHITE, 1));
        logo.setBounds(0, 0, 450, 150);
        if (icono != null) {
            logo.setIcon(icono);
            repaint();
        }
        panelTabla.setBounds(20, 460, 720, 350);
        panelTabla.setLayout(null);
        scrollTabla.setBounds(0, 0, 720, 350);
        tabla.addMouseListener(mostrarDatosTabla);

        labelCliente.setText("Cliente");
        labelCliente.setBounds(20, 20, 200, 30);
        labelCliente.setForeground(java.awt.Color.WHITE);
        labelCliente.setFont(fuenteLabel);
        txtCliente.setBounds(20, 50, 150, 30);
        labelTelefono.setText("Teléfono");
        labelTelefono.setBounds(200, 20, 200, 30);
        labelTelefono.setForeground(java.awt.Color.WHITE);
        labelTelefono.setFont(fuenteLabel);
        txtTelefono.setBounds(200, 50, 150, 30);
        txtTelefono.addKeyListener(tel);
        labelTarotista.setText("Tarotista");
        labelTarotista.setBounds(380, 20, 200, 30);
        labelTarotista.setForeground(java.awt.Color.WHITE);
        labelTarotista.setFont(fuenteLabel);
        comboTarotista.setBounds(380, 50, 150, 30);
        labelMetodoPago.setText("Pago");
        labelMetodoPago.setBounds(560, 20, 200, 30);
        labelMetodoPago.setForeground(java.awt.Color.WHITE);
        labelMetodoPago.setFont(fuenteLabel);
        comboMetodoPago.setBounds(560, 50, 150, 30);
        comboMetodoPago.addItem("Bizum");
        comboMetodoPago.addItem("PayPal");
        comboMetodoPago.addItem("Tarjeta");
        comboMetodoPago.addItem("Transferencia");
        comboMetodoPago.addItemListener(new java.awt.event.ItemListener() {
            @Override
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboMetodoPagoActionPerformed(evt);
            }
        });
        labelServicio.setText("Servicio");
        labelServicio.setBounds(20, 100, 200, 30);
        labelServicio.setForeground(java.awt.Color.WHITE);
        labelServicio.setFont(fuenteLabel);
        comboServicio.setBounds(20, 130, 150, 30);
        comboServicio.addItemListener(combos);
        labelTarifa.setText("Tarifa");
        labelTarifa.setBounds(200, 100, 200, 30);
        labelTarifa.setForeground(java.awt.Color.WHITE);
        labelTarifa.setFont(fuenteLabel);
        comboTarifa.setBounds(200, 130, 150, 30);
        comboTarifa.addItemListener(comboPrecio);
        labelHora.setText("Hora");
        labelHora.setBounds(380, 100, 200, 30);
        labelHora.setForeground(java.awt.Color.WHITE);
        labelHora.setFont(fuenteLabel);
        txtHora.setBounds(380, 130, 150, 30);
        txtHora.setToolTipText("00:00");
        labelCodigoPago.setText("Código pago");
        labelCodigoPago.setBounds(560, 100, 200, 30);
        labelCodigoPago.setForeground(java.awt.Color.WHITE);
        labelCodigoPago.setFont(fuenteLabel);
        txtCodigoPago.setBounds(560, 130, 150, 30);
        txtCodigoPago.setEnabled(false);
        txtHora.addKeyListener(hora);
        labelFecha.setText("Fecha");
        labelFecha.setBounds(20, 180, 200, 30);
        labelFecha.setForeground(java.awt.Color.WHITE);
        labelFecha.setFont(fuenteLabel);
        txtFecha.setBounds(20, 210, 150, 30);
        txtFecha.setToolTipText("00-00-0000");
        txtFecha.addKeyListener(fecha);
        labelPrecio.setText("Precio");
        labelPrecio.setBounds(200, 180, 200, 30);
        labelPrecio.setForeground(java.awt.Color.WHITE);
        labelPrecio.setFont(fuenteLabel);
        txtPrecio.setBounds(200, 210, 150, 30);
        txtPrecio.setEditable(false);
        txtPrecio.setToolTipText("0.00");
        jbtaddFicha.setText("Agregar ficha");
        jbtaddFicha.setBounds(20, 260, 150, 30);
        jbtaddFicha.setFont(fuenteButton);
        jbtaddFicha.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtaddFichaActionPerformed(evt);
            }
        });
        jbtdeleteFicha.setText("Borrar ficha");
        jbtdeleteFicha.setBounds(200, 260, 150, 30);
        jbtdeleteFicha.setFont(fuenteButton);
        jbtdeleteFicha.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtdeleteFichaActionPerformed(evt);
            }
        });
        jbtbuscar.setText("Buscar...");
        jbtbuscar.setBounds(380, 260, 150, 30);
        jbtbuscar.setFont(fuenteButton);
        jbtbuscar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtbuscarActionPerformed(evt);
            }
        });
        jbtresumen.setText("Resumen");
        jbtresumen.setBounds(560, 210, 150, 30);
        jbtresumen.setFont(fuenteButton);
        jbtresumen.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtresumenActionPerformed(evt);
            }
        });
        jbtGanancia.setText("Ganancia");
        jbtGanancia.setBounds(560, 260, 150, 30);
        jbtGanancia.setFont(fuenteButton);
        jbtGanancia.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtGananciaActionPerformed(evt);
            }
        });
        jbtExportar.setText("Exportar");
        jbtExportar.setBounds(380, 210, 150, 30);
        jbtExportar.setFont(fuenteButton);
        jbtExportar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtExportarActionPerformed(evt);
            }
        });

        // Creando el menú
        menuArchivo.setText("Archivo");
        menuOpciones.setText("Opciones");
        menuAyuda.setText("Ayuda");
        itemSalir.setText("Salir");
        itemSalir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.KeyEvent.CTRL_MASK));
        itemSalir.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemSalirActionPerformed(evt);
            }
        });
        itemAgregarTarifa.setText("Nueva tarifa");
        itemAgregarTarifa.addActionListener(itemsMenu);
        itemAgregarTarotista.setText("Nuevo tarotista");
        itemAgregarTarotista.addActionListener(itemsMenu);
        itemAgregarGanancia.setText("Nuevo servicio");
        itemAgregarGanancia.addActionListener(itemsMenu);
        itemInstrucciones.setText("Instrucciones");
        itemAcerca.setText("Acerca de...");
        itemAcerca.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemAcercaDeActionPerformed(evt);
            }
        });

        barraMenu.add(menuArchivo);
        barraMenu.add(menuOpciones);
        barraMenu.add(menuAyuda);

        menuArchivo.add(itemSalir);
        menuOpciones.add(itemAgregarTarifa);
        menuOpciones.add(itemAgregarTarotista);
        menuOpciones.add(itemAgregarGanancia);
        //menuAyuda.add(itemInstrucciones);
        menuAyuda.add(itemAcerca);

        // Añadiendo a los paneles
        scrollTabla.setViewportView(tabla);
        panelLogo.add(logo);
        panelComponentes.add(labelCliente);
        panelComponentes.add(labelTelefono);
        panelComponentes.add(labelTarotista);
        panelComponentes.add(labelServicio);
        panelComponentes.add(labelTarifa);
        panelComponentes.add(labelHora);
        panelComponentes.add(labelMetodoPago);
        panelComponentes.add(labelCodigoPago);
        panelComponentes.add(labelFecha);
        panelComponentes.add(labelPrecio);
        panelComponentes.add(txtCliente);
        panelComponentes.add(txtTelefono);
        panelComponentes.add(comboTarotista);
        panelComponentes.add(comboMetodoPago);
        panelComponentes.add(comboServicio);
        panelComponentes.add(comboTarifa);
        panelComponentes.add(txtHora);
        panelComponentes.add(txtCodigoPago);
        panelComponentes.add(txtFecha);
        panelComponentes.add(txtPrecio);
        panelComponentes.add(jbtaddFicha);
        panelComponentes.add(jbtdeleteFicha);
        panelComponentes.add(jbtExportar);
        panelComponentes.add(jbtbuscar);
        panelComponentes.add(jbtresumen);
        panelComponentes.add(jbtGanancia);
        panelTabla.add(scrollTabla);

        // Añadiendo componentes
        this.getRootPane().setJMenuBar(barraMenu);
        this.getContentPane().add(panelLogo);
        this.getContentPane().add(panelComponentes);
        this.getContentPane().add(panelTabla);
        this.getContentPane().setBackground(new java.awt.Color(40, 6, 108));

    }

    private void exitForm(java.awt.event.WindowEvent evt) {
        System.exit(0);
    }

    private void itemSalirActionPerformed(java.awt.event.ActionEvent evt) {
        System.exit(0);
    }

    private void itemAcercaDeActionPerformed(java.awt.event.ActionEvent evt) {

        String acerca = "SOFTWARE DESARROLLADO PARA EL GABINETE DE TAROTESTRELLA\n"
                + "Versión: 1.0\n"
                + "\n"
                + "Para más información o sugerencias puede ponerse en contacto con el\n"
                + "desarrollador a través de la siguiente dirección de correo eléctronico:\n"
                + "\n"
                + "luisjose_ev@hotmail.com";
        javax.swing.JOptionPane.showMessageDialog(null, acerca, "Información", javax.swing.JOptionPane.INFORMATION_MESSAGE);
    }

    public void initCombos() {
        comboTarotista.setModel(tarotistas.getNombres());
        comboServicio.setModel(servicios.getServicios());
        comboTarifa.setModel(tarifas.getNombres(tablaTarifas));

    }

    private void actualizarCombo() {
        comboTarifa.setModel(tarifas.getNombres(tablaTarifas));
    }

    private void combosItemStateChanged(java.awt.event.ItemEvent evt) {
        String seleccion = comboServicio.getSelectedItem().toString();

        tablaTarifas = "Tarifas" + seleccion;
        actualizarCombo();
        tarifaSeleccionada = comboTarifa.getItemAt(0);
        actualizarPrecio();
    }

    private void comboMetodoPagoActionPerformed(java.awt.event.ItemEvent evt) {
        String seleccion = comboMetodoPago.getSelectedItem().toString();

        if (seleccion.equals("Bizum")) {
            txtCodigoPago.setEnabled(false);
        } else if (seleccion.equals("PayPal")) {
            txtCodigoPago.setEnabled(false);
        } else if (seleccion.equals("Tarjeta")) {
            txtCodigoPago.setEnabled(true);
        } else if (seleccion.equals("Transferencia")) {
            txtCodigoPago.setEnabled(false);
        }
    }

    private void actualizarPrecio() {
        String texto;
        texto = (tarifas.getPrecio(tablaTarifas, tarifaSeleccionada)).toString();
        txtPrecio.setText(texto);
    }

    private void cambiarPrecio(java.awt.event.ItemEvent evt) {
        tarifaSeleccionada = comboTarifa.getSelectedItem().toString();
        txtPrecio.setText(tarifas.getPrecio(tablaTarifas, tarifaSeleccionada).toString());
    }

    public void initTable() {
        tabla.setModel(clientes.getDatos());

        for (int i = 0; i < tabla.getColumnCount(); i++) {
            Class<?> clase_columna = tabla.getColumnClass(i);
            tabla.setDefaultEditor(clase_columna, null);
        }
    }

    private void mostrarDatosDoubleClick(java.awt.event.MouseEvent evt) {
        if (evt.getClickCount() == 2) {
            int id = Integer.parseInt(tabla.getValueAt(tabla.getSelectedRow(), 0).toString());
            Object[] resultados = clientes.getDatosFila(id);
            String texto = "";
            String[] descripciones = {"ID", "CLIENTE", "TELÉFONO", "TAROTISTA", "SERVICIO", "TARIFA", "HORA", "FECHA", "PRECIO", "GANANCIA TELEOPERADOR", "REFERENCIA",
                "MÉTODO DE PAGO", "CÓDIGO DE PAGO"};
            for (int i = 0; i < resultados.length; i++) {
                texto += descripciones[i] + ":  " + resultados[i] + "\n";
            }
            javax.swing.JTextArea jtxtDatos = new javax.swing.JTextArea(texto);
            jtxtDatos.setEditable(false);
            javax.swing.JOptionPane.showMessageDialog(null, jtxtDatos, "Datos del registro nº: " + id, javax.swing.JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void jbtaddFichaActionPerformed(java.awt.event.ActionEvent evt) {

        if (txtCliente.getText().compareTo("") == 0 || txtTelefono.getText().compareTo("") == 0 || txtHora.getText().compareTo("") == 0 || txtFecha.getText().compareTo("") == 0
                || comboTarotista.getSelectedItem() == null || comboServicio.getSelectedItem() == null || comboTarifa.getSelectedItem() == null) {
            javax.swing.JOptionPane.showMessageDialog(null, "Debe rellenar todos los campos", "Datos insuficientes", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        if (txtCodigoPago.isEnabled() && txtCodigoPago.getText().compareTo("") == 0) {
            javax.swing.JOptionPane.showMessageDialog(null, "Inserte el código generado en el tpv virtual", "Código de pago", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (!validarHora(txtHora.getText())) {
            javax.swing.JOptionPane.showMessageDialog(null, "La hora ingresada no es correcta.", "Fecha incorrecta", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (!validarFecha(txtFecha.getText())) {
            javax.swing.JOptionPane.showMessageDialog(null, "La fecha ingresada no es correcta.", "Fecha incorrecta", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        String tel = txtTelefono.getText();
        
        
        if (clientes.comprobarNombreExistente(txtTelefono.getText()).compareTo("") != 0) {
            if (clientes.comprobarNombreExistente(txtTelefono.getText()).compareTo(txtCliente.getText()) != 0) {
                javax.swing.JOptionPane.showMessageDialog(null, "El teléfono indicado ya tiene asignado otro cliente", "Teléfono con otro nombre", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }

        Double ganancia = tarifas.getGanancia(tablaTarifas, tarifaSeleccionada);

        String referencia = "";
        if (comboServicio.getSelectedItem().toString().compareTo("Whatsapp") == 0) {
            referencia = txtCliente.getText() + "/" + txtFecha.getText() + "/" + txtHora.getText();
            javax.swing.JOptionPane.showMessageDialog(null, "Referencia de whatsapp generada:\n\n" + referencia, "Referencia", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        }
        try {
            
            clientes.insertarDatos(txtCliente.getText(), Integer.parseInt(tel), comboTarotista.getSelectedItem().toString(),
                    comboServicio.getSelectedItem().toString(), comboTarifa.getSelectedItem().toString(), txtHora.getText(), txtFecha.getText(),
                    Double.parseDouble(txtPrecio.getText()), ganancia, referencia, comboMetodoPago.getSelectedItem().toString(), txtCodigoPago.getText());

        } catch (Exception ex) {
            javax.swing.JOptionPane.showMessageDialog(null, "Error al ingresar los datos", "Error", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            System.out.println(ex.getMessage());
        }

        if (jbtbuscar.getText().compareTo("Buscar...") == 0) {
            initTable();
        } else if (jbtbuscar.getText().compareTo("Volver") == 0) {
            tabla.setModel(clientes.buscarRegistro(findTelefono));
        }

        // Reiniciamos los componentes
        txtCliente.setText("");
        txtTelefono.setText("");
        jbtresumen.setText("Resumen");
        comboTarotista.setSelectedIndex(0);
        comboTarifa.setSelectedIndex(0);
        comboServicio.setSelectedIndex(0);
        txtHora.setText("");
        txtFecha.setText("");
        tablaTarifas = "TarifasLlamada";
        tarifaSeleccionada = "15 Minutos";
        txtCodigoPago.setText("");
        comboMetodoPago.setSelectedIndex(0);

        actualizarPrecio();
    }

    private void jbtdeleteFichaActionPerformed(java.awt.event.ActionEvent evt) {
        javax.swing.table.DefaultTableModel modelo = (javax.swing.table.DefaultTableModel) tabla.getModel();
        int indice = 0;
        if (tabla.getSelectedRow() != -1) {
            indice = (Integer) modelo.getValueAt(tabla.getSelectedRow(), 0);
        }
        if (indice == 0) {
            javax.swing.JOptionPane.showMessageDialog(null, "No has seleccionado ningún registro", "Error", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            return;
        } else {
            clientes.borrarRegistro(indice);
        }

        if (jbtbuscar.getText().compareTo("Buscar...") == 0) {
            initTable();
        } else if (jbtbuscar.getText().compareTo("Volver") == 0) {
            tabla.setModel(clientes.buscarRegistro(findTelefono));
        }

    }

    private void jbtbuscarActionPerformed(java.awt.event.ActionEvent evt) {
        jbtresumen.setText("Resumen");
        String accion = jbtbuscar.getText();
        findTelefono = 0;
        if (accion.compareTo("Buscar...") == 0) {

            String txtfindTelefono = javax.swing.JOptionPane.showInputDialog("Teléfono del cliente a buscar:");

            if (txtfindTelefono == null) {
                return;
            }

            try {
                findTelefono = Integer.parseInt(txtfindTelefono);
            } catch (NumberFormatException ex) {
                javax.swing.JOptionPane.showMessageDialog(null, "El teléfono introducido no es correcto", "Error", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            if (findTelefono == 0) {
                return;
            }
            jbtbuscar.setText("Volver");
            tabla.setModel(clientes.buscarRegistro(findTelefono));
        } else if (accion.compareTo("Volver") == 0) {
            jbtbuscar.setText("Buscar...");
            initTable();
        }
    }

    private void jbtExportarActionPerformed(java.awt.event.ActionEvent evt) {
        javax.swing.JFileChooser dlgGuardar = new javax.swing.JFileChooser();
        // Guardamos en una variable tipo int si el usuario acepta o no
        int opcion = dlgGuardar.showSaveDialog(this);
        // Variable donde almacenaremos la ruta
        String ruta = "";
        if (opcion == javax.swing.JFileChooser.APPROVE_OPTION) {
            ruta = dlgGuardar.getSelectedFile().getAbsolutePath();
            exportar.exportarTabla(ruta, tabla);
        } else {
            return;
        }
    }

    private void jbtresumenActionPerformed(java.awt.event.ActionEvent evt) {
        fechaResumen = "";
        try {

            if (jbtresumen.getText().compareTo("Resumen") == 0) {
                fechaResumen = javax.swing.JOptionPane.showInputDialog("Introduce el mes/año de que desea realizar el resumen:");
                if (fechaResumen.compareTo("") == 0 || fechaResumen == null) {
                    return;
                }
                tabla.setModel(clientes.getResumen(fechaResumen));
                jbtresumen.setText("Total");
                jbtbuscar.setText("Buscar...");
            } else if (jbtresumen.getText().compareTo("Total") == 0) {
                jbtresumen.setText("Resumen");
                initTable();
            }
        } catch (Exception ex) {
            System.out.print(ex.getMessage());
        }

    }

    private void jbtGananciaActionPerformed(java.awt.event.ActionEvent evt) {
        // PARA REDONDEAR A DOS DECIMALES
        java.text.DecimalFormat df = new java.text.DecimalFormat("#.##");
        df.setRoundingMode(java.math.RoundingMode.CEILING);
        if (jbtresumen.getText().compareTo("Total") == 0) {
            javax.swing.JOptionPane.showMessageDialog(null, "La ganancia del teleoperador en la fecha indicada ha sido de: " + df.format(clientes.getGanancias(fechaResumen)) + " €.", "Fecha: " + fechaResumen, javax.swing.JOptionPane.INFORMATION_MESSAGE);
        } else if (jbtresumen.getText().compareTo("Resumen") == 0) {
            javax.swing.JOptionPane.showMessageDialog(null, "La ganancia TOTAL del teleoperador ha sido de: " + df.format(clientes.getGanancias("TOTAL")) + " €.", "Fecha: " + fechaResumen, javax.swing.JOptionPane.INFORMATION_MESSAGE);
        }

    }

    private void itemsActionPerformed(java.awt.event.ActionEvent evt) {
        javax.swing.JMenuItem obj = (javax.swing.JMenuItem) evt.getSource();
        String tipoForm = "";
        tipoForm = obj.getText();
        if (tipoForm.compareTo("Nueva tarifa") == 0) {
            tipoForm = "Tarifa";
        } else if (tipoForm.compareTo("Nuevo tarotista") == 0) {
            tipoForm = "Tarotista";
        } else if (tipoForm.compareTo("Nuevo servicio") == 0) {
            tipoForm = "Servicio";
        }
        new NuevoDato(this, true, tipoForm).setVisible(true);
        tablaTarifas = "TarifasLlamada";
        initCombos();
    }

    private void txtTelefonoValidate(java.awt.event.KeyEvent evt) {
        String texto = txtTelefono.getText();
        // Almacenamos el texto
        char[] fuente = texto.toCharArray();
        //Reiniciamos la variable
        texto = "";
        int j = 0;
        // Para almacenar el resultado
        char[] resultado = new char[fuente.length];

        for (int i = 0; i < fuente.length; i++) {
            if (fuente[i] >= '0' && fuente[i] <= '9') {
                if (j < 9) {
                    resultado[j++] = fuente[i];
                    texto += resultado[i];
                }
            } else {
                Toolkit.getDefaultToolkit().beep();
            }
        }

        txtTelefono.setText(texto);
    }

    private void txtHoraValidate(java.awt.event.KeyEvent evt) {
        String texto = txtHora.getText();
        // Alamacena el texto
        char[] fuente = texto.toCharArray();
        texto = "";
        char[] resultado = new char[fuente.length];
        int contador = 0;

        for (int i = 0; i < fuente.length; i++) {
            if (fuente[i] >= '0' && fuente[i] <= '9' || fuente[i] == ':') {
                if (contador < 5) {
                    resultado[contador++] = fuente[i];
                    texto += resultado[i];
                }
            } else {
                Toolkit.getDefaultToolkit().beep();
            }
        }
        txtHora.setText(texto);
    }

    private void txtFechaValidate(java.awt.event.KeyEvent evt) {

        String texto = txtFecha.getText();
        // Almacenamos el texto
        char[] fuente = texto.toCharArray();
        //Reiniciamos la variable
        texto = "";
        int j = 0;
        // Para almacenar el resultado
        char[] resultado = new char[fuente.length];

        for (int i = 0; i < fuente.length; i++) {
            if (fuente[i] >= '0' && fuente[i] <= '9' || fuente[i] == '-') {
                if (j < 10) {
                    resultado[j++] = fuente[i];
                    texto += resultado[i];
                }
            } else {
                Toolkit.getDefaultToolkit().beep();
            }
        }

        txtFecha.setText(texto);

    }

    private boolean validarFecha(String texto) {
        String patron = "^(0[1-9]|[12][0-9]|3[01])[\\-](0[1-9]|1[012])[\\-]([0123][0-9])\\d{2}$";
        return texto.matches(patron);
    }

    private boolean validarHora(String texto) {
        String patron = "^([01][0-9]|[2][0-3])[\\:]([012345][0-9])$";
        return texto.matches(patron);
    }

    private boolean validarTelefono(String texto) {
        String patron = "[0-9]{9}";
        return texto.matches(patron);
    }

    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                }
            }
        } catch (Exception ex) {
        }

        new Principal();
    }

}
