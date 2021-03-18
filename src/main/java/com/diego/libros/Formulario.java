/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diego.libros;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author diego
 */
public class Formulario extends Application {
    public void start (Stage primaryStage) throws Exception{
        
        LayoutPane layoutPane = new LayoutPane();
        layoutPane.cargarPantalla("login", LoginControlador.class.getResource("Login.fxml"));
        layoutPane.cargarPantalla("libros", ControladorFormularioLibro.class.getResource("FormularioLibros.fxml"));
        layoutPane.cargarPantalla("registro", ControladorRegistro.class.getResource("Registro.fxml"));
        layoutPane.cargarPantalla("librosAdmin", ControladorFormularioLibroAdmin.class.getResource("FormularioLibrosAdmin.fxml"));
        layoutPane.cargarPantalla("chart", ControladorGrafico.class.getResource("Grafico.fxml"));
        
        layoutPane.mostrarComoPantallaActual("login");
        
        Scene escena = new Scene(layoutPane, 1000 , 500);
        primaryStage.setTitle("Gestor de colección de libros");
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("/img/libro.png"));
        primaryStage.setScene(escena);
        primaryStage.show();
        
    }
    public static void main(String[]  args){
        launch(args);
    }
}
