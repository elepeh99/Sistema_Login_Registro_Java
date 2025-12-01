/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

/**
 *
 * @author miguel
 */
public class Usuario {
    private String username;
    private String contrasena;
    private String nombreReal;
    private String cumpleanos;
    private String genero;
    
    public Usuario(String username, String contrasena, String nombreReal, String cumpleanos, String genero) {
        this.username = username;
        this.contrasena = contrasena;
        this.nombreReal = nombreReal;
        this.cumpleanos = cumpleanos;
        this.genero = genero;
    }
    
    public String getUsername() {
        return username;
    }

    public String getContrasena() {
        return contrasena;
    }

    public String getNombreReal() {
        return nombreReal;
    }

    public String getCumpleanos() {
        return cumpleanos;
    }

    public String getGenero() {
        return genero;
    }
    
}
