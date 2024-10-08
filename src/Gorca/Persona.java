package Gorca;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author memo7
 */
import java.io.Serializable;

public class Persona {

    private String id;
    private String nombre;
    private String direccion;
    private String telefono;
    
    
    //Constructor sin parametros
    public Persona(){
        
    }
    //Contructor
    public Persona(String id, String nombre, String direccion, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    //Getters y Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getNombre() { return id; }
    public void setNombre(String nombre) { this.nombre = nombre;}
    
    public String getDireccion() {return direccion; }
    public void setDireccion( String direccion ) { this.direccion = direccion; }
    
    public String getTelefono() {return telefono;}
    public void setTelefono(String telefono) { this.telefono = telefono;}
}

