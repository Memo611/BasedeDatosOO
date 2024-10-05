/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Gorca;

/**
 *
 * @author memo7
 */
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.IOException;

public class GestorDeArchivos {

    public static void guardarObjeto(Object objeto, String nombreArchivo) {
        try (FileOutputStream fos = new FileOutputStream(nombreArchivo); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(objeto);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object cargarObjeto(String nombreArchivo) {
        try (FileInputStream fis = new FileInputStream(nombreArchivo); ObjectInputStream ois = new ObjectInputStream(fis)) {
            return ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
