/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Gorca;

/**
 *
 * @author memo7
 */

import java.io.Serializable;

public class Cliente extends Persona implements Serializable {
       private static final long serialVersionUID = 3L; 
    private String idCliente;
    private String rfc;
    private String razonSocial;
    private String regimenFiscal;
    private String usoCFDI;
    
    
    // Constructor sin parámetros (necesario para la deserialización)
    public Cliente() {
        super();  // Llama al constructor sin parámetros de Persona (si lo tiene)
    }
    
    // Constructor
    public Cliente(String idCliente, String rfc, String razonSocial, String regimenFiscal, String usoCFDI, String direccion, String telefono) {
     // Llamada al constructor de Persona
        super(idCliente, razonSocial, direccion, telefono);
        this.idCliente = idCliente;
        this.razonSocial = razonSocial;
        this.rfc = rfc;
        this.regimenFiscal = regimenFiscal;
        this.usoCFDI = usoCFDI;  
    }    

    // Getters y setters
    public String getIdCliente() { return idCliente; }
    public void setIdCliente(String idCliente) {this.idCliente = idCliente; }
    
    public String getRfc() { return rfc; }
    public void setRfc(String rfc) { this.rfc = rfc;}
    
    public String getRazonSocial() {return razonSocial;}
    public void setRazonSocial(String razonSocial) { this.razonSocial = razonSocial;}

    public String getRegimenFiscal() { return regimenFiscal; }
    public void setRegimenFiscal(String regimenFiscal) { this.regimenFiscal = regimenFiscal;}

    public String getUsoCFDI() { return usoCFDI; }
    public void setUsoCFDI(String usoCFDI) { this.usoCFDI = usoCFDI;}
}
