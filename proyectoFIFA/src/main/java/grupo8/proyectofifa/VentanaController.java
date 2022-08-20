/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package grupo8.proyectofifa;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author laque
 */
public class VentanaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Principal.cargarimagen (ivinicio,Principal.pathImg+"fifaBrasil.jpg", 400, 258, false, false);
        Principal.cargarimagen (ivcopa,Principal.pathImg+"Brazil2014.png", 88, 100, false, false);
        labeltitulo.setId("texto-titulo");
    }  
    
    @FXML
    private AnchorPane root;
    
    @FXML
    private ImageView ivinicio;
    
    @FXML
    private ImageView ivcopa;
    
    @FXML
    private Label labeltitulo;
    
    @FXML
    private Button btncopas;    
    
    // metodo que permite abrir la ventana consultaPartido cuando se da click en el boton
    @FXML
    public void abrircopas(MouseEvent e){
        try{
            Principal.cargarVentana("consultaPartido.fxml", "Ventana de consulta de partidos");
        }catch(IOException ioe){
            System.out.println("No se pudo cargar ventana");
        }
    }
}
