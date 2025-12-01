/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import java.util.ArrayList;

/**
 *
 * @author miguel
 */
public class UserManager {
    
    public static ArrayList<Usuario> listaUsuarios = new ArrayList<>();
    public static boolean registrar(String username, String contrasena, String nombreReal, String cumple, String genero) {
        for (Usuario u : listaUsuarios) {
            if (u.getUsername().equals(username)) {
                return false; 
            }
        }
        
        Usuario nuevoUsuario = new Usuario(username, contrasena, nombreReal, cumple, genero);
        listaUsuarios.add(nuevoUsuario);
        return true;
    }
    public static boolean validar(String username, String contrasena) {
        for (Usuario u : listaUsuarios) {
            if (u.getUsername().equals(username) && u.getContrasena().equals(contrasena)) {
                return true; 
            }
        }
        return false;
    }
    public static Usuario getUsuario(String username) {
        for (Usuario u : listaUsuarios) {
            if (u.getUsername().equals(username)) {
                return u; 
            }
        }
        return null;
    }    
}
