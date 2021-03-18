/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.diego.libros;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * 
 * @author diego
 */
public class ControladorRegistro extends ControladorConNavegabilidad implements Initializable {

    @FXML
     TextField nombre;       
    
    @FXML
     TextField apellidos;       
    
    @FXML
     TextField usuario;       
    
    @FXML
     PasswordField contrasena;
    
    @FXML
     PasswordField repetirContrasena;
    
    @FXML
     DatePicker nacimiento;       
    
    @FXML
     TextField mail;
    
    @FXML
     Button btnCrearCuenta;       
    
    UsuarioDao usuarioDao;
    
    boolean nombreEs = false, mailEs = false, usuarioEs = false, contrasenaEs = false, repetirContrasenaEs = false;
    
    LocalDate date = LocalDate.parse("1900-01-01");
    
    public void crearCuenta() {
        
        if(contrasena.getText().equals(repetirContrasena.getText())) {
            Usuario usuarioRegistro = new Usuario();
            usuarioRegistro.setNombre(nombre.getText());
            if(apellidos.getText().isEmpty()) {
                usuarioRegistro.setApellidos("");
            } else {
                usuarioRegistro.setApellidos(apellidos.getText());
            }
            usuarioRegistro.setUsuario(usuario.getText());
            usuarioRegistro.setContrasena(contrasena.getText());
            
             if(nacimiento.getValue() == null) { 
                          usuarioRegistro.setNacimiento(date);    
                     } else {
                          usuarioRegistro.setNacimiento(nacimiento.getValue());
                     }
            
            usuarioRegistro.setMail(mail.getText());
            usuarioRegistro.setRango(1);
            
            usuarioDao.insertar(usuarioRegistro);
            
            this.layout.mostrarComoPantallaActual("login");
            
        } else {
            System.out.println("Las contraseñas no coinciden");
        }
              
    }
    
    public void volver() {
        this.layout.mostrarComoPantallaActual("login");
        
        nombre.clear();
        apellidos.clear();
        nacimiento.setValue(null);
        mail.clear();
        usuario.clear();
        contrasena.clear();
        repetirContrasena.clear();
    }
    
    public void escribirNombre() {
        // Método que escucha cada vez que se pulsa una tecla en el campo de nombre y comprueba si está vacio o no
        nombreEs = !nombre.getText().isEmpty();
        activarCrearCuenta();
   }
    
    public void escribirUsuario() {
        // Método que escucha cada vez que se pulsa una tecla en el campo de usuario y comprueba si está vacio o no
        usuarioEs = !usuario.getText().isEmpty();
        activarCrearCuenta();
    }
    
    public void escribirContrasena() {
        // Método que escucha cada vez que se pulsa una tecla en el campo de contraseña y comprueba si está vacio o no
        contrasenaEs =!contrasena.getText().isEmpty();
        activarCrearCuenta();
    }
    
    public void escribirRepetirContrasena() {
        // Método que escucha cada vez que se pulsa una tecla en el campo de repetir contraseña  y comprueba si está vacio o no
        repetirContrasenaEs = !repetirContrasena.getText().isEmpty();
        activarCrearCuenta();
    }
    
    public void escribirMail() {
        // Método que escucha cada vez que se pulsa una tecla en el campo de mail y comprueba si está vacio o no
        mailEs = !mail.getText().isEmpty();
        activarCrearCuenta();
    }
    
    private void activarCrearCuenta() {
        
        if((nombreEs == true) && (usuarioEs == true) && (contrasenaEs == true) && (repetirContrasenaEs == true) && (mailEs == true)) {
            btnCrearCuenta.setDisable(false);
        } else {
            btnCrearCuenta.setDisable(true);
        }
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        usuarioDao = new UsuarioDao();
        btnCrearCuenta.setDisable(true);
        
        
        
    }

    

}
