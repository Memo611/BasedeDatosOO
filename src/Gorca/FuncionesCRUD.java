package Gorca;

import javax.swing.*;
import java.util.ArrayList;
import java.io.IOException;

public class FuncionesCRUD {
    private JTextField txtId, txtRazon, txtRfc, txtDireccion, txtTelefono;
    private JTextArea areaDatos;
    private ArrayList<Cliente> clientes;
    private GestorArchivos gestorArchivos;

    // Constructor que recibe los componentes
    public FuncionesCRUD(JTextField txtId, JTextField txtRazon, JTextField txtRfc,
                         JTextField txtDireccion, JTextField txtTelefono, JTextArea areaDatos, ArrayList<Cliente> clientes) {
        this.txtId = txtId;
        this.txtRazon = txtRazon;
        this.txtRfc = txtRfc;
        this.txtDireccion = txtDireccion;
        this.txtTelefono = txtTelefono;
        this.areaDatos = areaDatos;
        this.clientes = clientes;
        this.gestorArchivos = new GestorArchivos();  // Instancia del gestor de archivos
    }

    // Método para crear un cliente
    public void crearCliente() {
        if (!validarCampos()) {
            return;  // Si la validación falla, termina la ejecución
        }

        // Obtener los valores de los campos de texto
        String id = txtId.getText().trim();
        String razonsocial = txtRazon.getText().trim();
        String rfc = txtRfc.getText().trim();
        String direccion = txtDireccion.getText().trim();
        String telefono = txtTelefono.getText().trim();

        // Crear el cliente solo si las validaciones pasan
        Cliente cliente = new Cliente(id, rfc, razonsocial, "", "", direccion, telefono);
        clientes.add(cliente);
        areaDatos.setText("Cliente creado: " + cliente.getRazonSocial() + "\n");
        leerClientes();
    }

    // Método para leer todos los clientes
    public void leerClientes() {
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
    public void actualizarCliente() {
        if (!validarCampos()) {
            return;  // Si la validación falla, termina la ejecución
        }

        String id = txtId.getText().trim();
        for (Cliente cliente : clientes) {
            if (cliente.getIdCliente().equals(id)) {
                cliente.setRazonSocial(txtRazon.getText().trim());
                cliente.setRfc(txtRfc.getText().trim());
                cliente.setDireccion(txtDireccion.getText().trim());
                cliente.setTelefono(txtTelefono.getText().trim());
                areaDatos.setText("Cliente actualizado: " + cliente.getRazonSocial() + "\n");
                leerClientes();
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Cliente no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
    }

    // Método para eliminar un cliente
    public void eliminarCliente() {
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
    public void limpiarCampos() {
        txtId.setText("");
        txtRazon.setText("");
        txtRfc.setText("");
        txtTelefono.setText("");
        txtDireccion.setText("");
        areaDatos.setText("");
        
        leerClientes();
    }

    // Método para validar todos los campos
    private boolean validarCampos() {
        String id = txtId.getText().trim();
        String razonsocial = txtRazon.getText().trim();
        String rfc = txtRfc.getText().trim();
        String direccion = txtDireccion.getText().trim();
        String telefono = txtTelefono.getText().trim();

        // Validaciones
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El ID del cliente no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (razonsocial.isEmpty()) {
            JOptionPane.showMessageDialog(null, "La razón social no puede estar vacía.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!validarRFC(rfc)) {
            JOptionPane.showMessageDialog(null, "El RFC ingresado no tiene un formato válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!telefono.matches("\\d{10}")) { // Valida si el teléfono tiene 10 dígitos numéricos
            JOptionPane.showMessageDialog(null, "El número de teléfono debe contener exactamente 10 dígitos.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (direccion.isEmpty()) {
            JOptionPane.showMessageDialog(null, "La dirección no puede estar vacía.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;  // Si todas las validaciones pasan, retorna true
    }

    // Método para validar el formato del RFC (ejemplo simple)
    private boolean validarRFC(String rfc) {
        // El RFC debe tener 13 caracteres y contener solo letras y números
        return rfc.matches("[A-ZÑ&]{3,4}\\d{6}[A-Z0-9]{3}");
    }
    
    
    // Método para guardar los clientes en un archivo
    public void guardarClientesEnArchivo() {
        try {
            gestorArchivos.guardarClientesEnArchivo(clientes);  // Guardar la lista de clientes usando GestorArchivos
            areaDatos.setText("Datos guardados en el archivo.\n");
        } catch (IOException e) {
            areaDatos.setText("Error al guardar los datos: " + e.getMessage() + "\n");
        }
    }
    
    // Método para cargar los clientes desde un archivo
    public void cargarClientesDesdeArchivo() {
        try {
            clientes = gestorArchivos.cargarClientesDesdeArchivo();  // Cargar la lista de clientes usando GestorArchivos
            areaDatos.setText("Datos cargados del archivo.\n");
            leerClientes();
        } catch (IOException | ClassNotFoundException e) {
            areaDatos.setText("No se pudieron cargar los datos: " + e.getMessage() + "\n");
        }
    }
}
