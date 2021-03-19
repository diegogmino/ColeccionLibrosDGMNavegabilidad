/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.diego.libros;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;

/**
 * 
 * @author diego
 */
public class ControladorGrafico extends ControladorConNavegabilidad implements Initializable {

    LibroDao libroDao;
    
    @FXML
    private PieChart chart;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        libroDao = new LibroDao();
        cargarDatosEnElChart();
    }
    
    public void cargarDatosEnElChart() {
        // Obtenemos la inforación a través del DAO
        Map<String, Integer> generos = libroDao.contarGeneros();
        ObservableList<PieChart.Data> datosParaElChart = FXCollections.observableArrayList();
        
        // Iteramos sobre el mapa y llenamos la colección para el chart
        generos.forEach((genero, cantidad)-> {
            
            PieChart.Data data = new PieChart.Data(genero, cantidad);
            datosParaElChart.add(data);
            
        });
        
        chart.setData(datosParaElChart);
        
    }
    
    public void refrescar() {
        cargarDatosEnElChart();
    }
    
    public void volver() {
        this.layout.mostrarPantallaVolver();
    }

}
