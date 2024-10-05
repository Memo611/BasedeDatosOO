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

public class Cliente implements Serializable {
       private static final long serialVersionUID = 1L; 
    private String idCliente;
    private String rfc;
    private String razonSocial;
    private String regimenFiscal;
    private String usoCFDI;
    private String direccion;
    private String telefono;

    // Constructor
    public Cliente(String idCliente, String rfc, String razonSocial, String regimenFiscal, String usoCFDI, String direccion, String telefono1) {
        this.idCliente = idCliente;
        this.rfc = rfc;
        this.razonSocial = razonSocial;
        this.regimenFiscal = regimenFiscal;
        this.usoCFDI = usoCFDI;
        this.direccion = direccion;
        this.telefono = telefono1;
    }    

    // Getters y setters
    public String getIdCliente() { return idCliente; }
    public String getRfc() { return rfc; }
    public String getRazonSocial() {return razonSocial;}
    public String getRegimenFiscal() { return regimenFiscal; }
    public String getUsoCFDI() { return usoCFDI; }
    public String getDireccion() { return direccion; }
    public String getTelefono() { return telefono; }
    
    public void setIdCliente(String idCliente) {this.idCliente = idCliente; }
    public void setRfc(String rfc) { this.rfc = rfc;}
    public void setRazonSocial(String razonSocial) { this.razonSocial = razonSocial;}
    public void setRegimenFiscal(String regimenFiscal) { this.regimenFiscal = regimenFiscal;}
    public void setUsoCFDI(String usoCFDI) { this.usoCFDI = usoCFDI;}
    public void setDireccion(String direccion) { this.direccion = direccion;}
    public void setTelefono(String telefono) { this.telefono = telefono;}
}
