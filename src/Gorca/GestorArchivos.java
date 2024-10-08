/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Gorca;

/**
 *
 * @author memo7
 */
import java.io.*;
import java.util.ArrayList;

public class GestorArchivos {

    // Método para guardar la lista de clientes en un archivo
    public void guardarClientesEnArchivo(ArrayList<Cliente> clientes) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("clientes.dat"))) {
            oos.writeObject(clientes);  // Guardar la lista completa de clientes
        }
    }

    // Método para cargar la lista de clientes desde un archivo
    @SuppressWarnings("unchecked")
    public ArrayList<Cliente> cargarClientesDesdeArchivo() throws IOException, ClassNotFoundException {
        ArrayList<Cliente> clientes;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("clientes.dat"))) {
            clientes = (ArrayList<Cliente>) ois.readObject();  // Cargar la lista de clientes
        }
        return clientes;
    }
    
}
