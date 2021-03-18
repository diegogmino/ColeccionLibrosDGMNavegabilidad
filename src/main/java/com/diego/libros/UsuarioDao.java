/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.diego.libros;

import static com.diego.libros.LibroDao.PASSWORD_BDD;
import static com.diego.libros.LibroDao.URL_CONEXION;
import static com.diego.libros.LibroDao.USUARIO_BDD;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * 
 * @author diego
 */
public class UsuarioDao {
    
    public static final String URL_CONEXION = "jdbc:h2:./usuarios";
    public static final String USUARIO_BDD = "root";
    public static final String PASSWORD_BDD = "";
    
    public UsuarioDao() {
        crearTablasSiNoExiste();
    }
    
    private void crearTablasSiNoExiste() {
       try (Connection conexionBD = DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD)) {
           Statement statement = conexionBD.createStatement();
           String sentencia = "CREATE TABLE IF NOT EXISTS usuarios "
                   + "(id INTEGER auto_increment,"
                   + "nombre VARCHAR(200), "
                   + "apellidos VARCHAR(200),"
                   + "nacimiento DATE,"
                   + "mail VARCHAR(400),"
                   + "rango INTEGER(1),"
                   + "usuario VARCHAR(400),"
                   + "contrasena VARCHAR(50) )";
           statement.executeUpdate(sentencia);                 
       } catch (Exception e) {
           e.printStackTrace();
       }
    }
    
    public Usuario existeUsuario(String usuario, String contrasena) {
        
         List<Usuario> listaUsuarios = new ArrayList<>();
          try (Connection conexionDB = DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD)) {
              Statement statement = conexionDB.createStatement();
              String sql = "SELECT * FROM usuarios WHERE usuario ='" + usuario + "' AND contrasena='" + contrasena + "';";
              ResultSet resultSet = statement.executeQuery(sql);
              
              Usuario usuarioDevolver = new Usuario();
              
              if(resultSet.next()) {
                  
                  usuarioDevolver.setNombre(resultSet.getString("nombre"));
                  usuarioDevolver.setApellidos(resultSet.getString("apellidos"));
                  usuarioDevolver.setRango(resultSet.getInt("rango"));
                  
                  return usuarioDevolver;
                  
              } else{
                  
                  usuarioDevolver.setNombre(null);
                  
                  return usuarioDevolver;
                  
              }
              
          } catch (Exception e) {
              throw new RuntimeException("Ocurrió un error al consultar los usuarios: " + e.getMessage());
        }
    }
    
    public void insertar(Usuario usuario) {
        
        try ( Connection conexionDB = DriverManager.getConnection(URL_CONEXION, USUARIO_BDD, PASSWORD_BDD)) {
            Statement statement = conexionDB.createStatement();
            String sql = "INSERT INTO usuarios (nombre, apellidos, nacimiento, mail, rango, usuario, contrasena) "
                    + "VALUES ('" + usuario.getNombre() + "', '" +usuario.getApellidos()+ "','" + usuario.getNacimiento() +"','" + usuario.getMail() + "'," + usuario.getRango() + ", '" + usuario.getUsuario() + "','" + usuario.getContrasena() + "')";                                                                                  
            statement.executeUpdate(sql); 
        } catch (Exception e) {
            throw new RuntimeException("Ocurrió un error al insertar un usuario: " + e.getMessage());
        }
        
        
    }

}
