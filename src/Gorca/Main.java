/* Base de Datos Creada Por Guillermo Garcia Y Eduardo Camacho */
package Gorca;

/**
 *
 * @author memo7
 *
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Main extends JFrame {

    private JTextField txtId, txtRazon, txtRfc, txtTelefono, txtDireccion;
    private JButton btnCrear, btnLeer, btnActualizar, btnEliminar, btnLimpiar;
    private JTextArea areaDatos;

    // Lista simple para almacenar clientes
    private ArrayList<Cliente> clientes = new ArrayList<>();
    private FuncionesCRUD funciones;

    public Main() {
        // Configuración básica de la ventana
        setTitle("CRUD de Clientes");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel superior con campos de texto
        JPanel panelFormulario = new JPanel(new GridLayout(5, 2));
        panelFormulario.add(new JLabel("ID Cliente:"));
        txtId = new JTextField();
        panelFormulario.add(txtId);
        panelFormulario.add(new JLabel("Razon Social:"));
        txtRazon = new JTextField();
        panelFormulario.add(txtRazon);
        panelFormulario.add(new JLabel("RFC:"));
        txtRfc = new JTextField();
        panelFormulario.add(txtRfc);
        panelFormulario.add(new JLabel("Direccion:"));
        txtDireccion = new JTextField();
        panelFormulario.add(txtDireccion);
        panelFormulario.add(new JLabel("Teléfono:"));
        txtTelefono = new JTextField();
        panelFormulario.add(txtTelefono);

        // Panel inferior con botones CRUD
        JPanel panelBotones = new JPanel();
        btnCrear = new JButton("Crear");
        btnLeer = new JButton("Leer");
        btnActualizar = new JButton("Actualizar");
        btnEliminar = new JButton("Eliminar");
        btnLimpiar = new JButton("Limpiar");

        panelBotones.add(btnCrear);
        panelBotones.add(btnLeer);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnLimpiar);

        // Área para mostrar datos
        areaDatos = new JTextArea();
        areaDatos.setPreferredSize(new Dimension(500, 200));
        JScrollPane scrollPane = new JScrollPane(areaDatos);

        // Agregar todo al frame
        add(panelFormulario, BorderLayout.NORTH);
        add(panelBotones, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);
        
        //Declaracion de intancia para Funciones CRUD
        funciones = new FuncionesCRUD(txtId, txtRazon, txtRfc, txtDireccion, txtTelefono, areaDatos, clientes);
        
        // Acciones de los botones
        btnCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                funciones.crearCliente();
            }
        });

        btnLeer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                funciones.leerClientes();
            }
        });

        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                funciones.actualizarCliente();
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                funciones.eliminarCliente();
            }
        });

        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                funciones.limpiarCampos();
            }
        });

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                funciones.guardarClientesEnArchivo(); 
            }
        });

        // Cargar los clientes desde el archivo al iniciar
        funciones.cargarClientesDesdeArchivo();

        setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }
}
