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

    private JTextField txtId, txtNombre, txtRfc, txtTelefono, txtDireccion;
    private JButton btnCrear, btnLeer, btnActualizar, btnEliminar, btnLimpiar;
    private JTextArea areaDatos;

    // Lista simple para almacenar clientes
    private ArrayList<Cliente> clientes = new ArrayList<>();

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
        txtNombre = new JTextField();
        panelFormulario.add(txtNombre);
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

        // Acciones de los botones
        btnCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearCliente();
            }
        });

        btnLeer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                leerClientes();
            }
        });

        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarCliente();
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarCliente();
            }
        });

        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                guardarClientesEnArchivo();
            }
        });

        // Cargar los clientes desde el archivo al iniciar
        cargarClientesDesdeArchivo();

        setVisible(true);
    }

    // Método para crear un cliente
    private void crearCliente() {
        String id = txtId.getText();
        String nombre = txtNombre.getText();
        String rfc = txtRfc.getText();
        String direccion = txtDireccion.getText();
        String telefono = txtTelefono.getText();

        Cliente cliente = new Cliente(id, rfc, nombre, "", "", direccion, telefono);

        clientes.add(cliente);
        areaDatos.setText("Cliente creado: " + cliente.getRazonSocial() + "\n");
        leerClientes();
    }

    // Método para leer todos los clientes
    private void leerClientes() {
        areaDatos.setText("");  // Limpiar el área de texto
        for (Cliente cliente : clientes) {
            areaDatos.append("ID: " + cliente.getIdCliente() + " - "
                    + "Razon Social: " + cliente.getRazonSocial() + " - "
                    + "RFC: " + cliente.getRfc() + " - "
                    + "Direccion: " + cliente.getDireccion() + " - "
                    + "Telefono: " + cliente.getTelefono() + "\n"
                    + "---------------------------------------------------------------------------------------------------------------------------------------------------------" + "\n");
        }
    }

    // Método para actualizar un cliente
    private void actualizarCliente() {
        String id = txtId.getText();
        for (Cliente cliente : clientes) {
            if (cliente.getIdCliente().equals(id)) {
                cliente.setRazonSocial(txtNombre.getText());
                cliente.setRfc(txtRfc.getText());
                cliente.setDireccion(txtDireccion.getText());
                cliente.setTelefono(txtTelefono.getText());
                areaDatos.setText("Cliente actualizado: " + cliente.getRazonSocial() + "\n");
                return;
            }
        }
        areaDatos.setText("Cliente no encontrado\n");
        leerClientes();
    }

    // Método para eliminar un cliente
    private void eliminarCliente() {
        String id = txtId.getText();
        Cliente clienteAEliminar = null;
        for (Cliente cliente : clientes) {
            if (cliente.getIdCliente().equals(id)) {
                clienteAEliminar = cliente;
                break;
            }
        }
        if (clienteAEliminar != null) {
            clientes.remove(clienteAEliminar);
            areaDatos.setText("Cliente eliminado: " + clienteAEliminar.getRazonSocial() + "\n");
        } else {
            areaDatos.setText("Cliente no encontrado\n");
        }
        leerClientes();
    }

    // Método para limpiar los campos de texto
    private void limpiarCampos() {
        txtId.setText("");
        txtNombre.setText("");
        txtRfc.setText("");
        txtTelefono.setText("");
        areaDatos.setText("");
    }

    private void guardarClientesEnArchivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("clientes.dat"))) {
            oos.writeObject(clientes);  // Guardar la lista completa de clientes
            areaDatos.setText("Datos guardados en el archivo.\n");
        } catch (Exception e) {
            areaDatos.setText("Error al guardar los datos: " + e.getMessage() + "\n");
        }
    }

    @SuppressWarnings("unchecked")
    private void cargarClientesDesdeArchivo() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("clientes.dat"))) {
            clientes = (ArrayList<Cliente>) ois.readObject();  // Cargar la lista de clientes
            areaDatos.setText("Datos cargados del archivo.\n");
             leerClientes();
        } catch (Exception e) {
            areaDatos.setText("No se pudieron cargar los datos: " + e.getMessage() + "\n");
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
