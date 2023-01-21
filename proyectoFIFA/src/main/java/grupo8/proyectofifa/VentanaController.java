/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package grupo8.proyectofifa;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author laque
 */
public class VentanaController implements Initializable {

    private ArrayList<worldCup> listaWorldCup;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Principal.cargarimagen (ivinicio,Principal.pathImg+"fifaBrasil.jpg", 400, 258, false, false);
        Principal.cargarimagen (ivcopa,Principal.pathImg+"Brazil2014.png", 88, 100, false, false);
        labeltitulo.setId("texto-titulo");
        listaWorldCup = worldCup.generarLista();
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
    
    //metodo que muestra la consulta historica de copas mundiales.
    @FXML
    public void abrircopas(MouseEvent e){
        root.getChildren().clear();
        labeltitulo.setText("Consulta Histórica de copas\n               Mundiales");
        ComboBox cbanio = new ComboBox();
        
        for(worldCup dato: listaWorldCup){
            cbanio.getItems().addAll(dato.getYear());
        }
        
        Button btnConsulta = new Button("Consultar");
        HBox hbox0 = new HBox();
        VBox vbox0 = new VBox();
        HBox hbox1 = new HBox();
        btnConsulta.setOnAction(btn -> mostrarDatos((String) cbanio.getValue(), hbox1));
        hbox0.getChildren().addAll(new Label("Año:"), cbanio, btnConsulta);
        vbox0.getChildren().addAll(labeltitulo, hbox0, hbox1);
        hbox0.setAlignment(Pos.CENTER);
        hbox0.setSpacing(40);
        vbox0.setAlignment(Pos.CENTER);
        vbox0.setLayoutX(125);
        root.getChildren().addAll(vbox0);
    }
    
    public void mostrarDatos(String anio, HBox hbox1){
        hbox1.getChildren().clear();
        if(anio != null){
            VBox vbPremios = new VBox();
            HBox hbPuestos = new HBox();
            VBox vbPaises = new VBox();
            VBox vbCopas = new VBox();
            VBox vbColumnaCopas = new VBox();
            HBox hbFilaCopas1 = new HBox();
            HBox hbFilaCopas2 = new HBox();
            HBox hbFilaCopas3 = new HBox();
            VBox vbDatosGenerales = new VBox();
            HBox hbGanador = new HBox();
            HBox hbSegundo = new HBox();
            HBox hbTercero = new HBox();
            HBox hbCuarto = new HBox();
            ImageView ivbandera1 = new ImageView();
            ImageView ivbandera2 = new ImageView();
            ImageView ivbandera3 = new ImageView();
            ImageView ivbandera4 = new ImageView();
        
            for(worldCup dato: listaWorldCup){
                if(anio.equals(dato.getYear())){
                    ivbandera1.setImage(dato.generarBandera(0));
                    hbGanador.getChildren().addAll(ivbandera1, new Label(dato.getPaises().get(0)));
                    ivbandera2.setImage(dato.generarBandera(1));
                    hbSegundo.getChildren().addAll(ivbandera2, new Label(dato.getPaises().get(1)));
                    ivbandera3.setImage(dato.generarBandera(2));
                    hbTercero.getChildren().addAll(ivbandera3, new Label(dato.getPaises().get(2)));
                    ivbandera4.setImage(dato.generarBandera(3));
                    hbCuarto.getChildren().addAll(ivbandera4, new Label(dato.getPaises().get(3)));
                    vbDatosGenerales.getChildren().addAll(new Label("\n  Datos generales\n\n  Goles Anotados: "+dato.getDatosNumericos().get(0)+"\n  Equipos: "+dato.getDatosNumericos().get(1)+"\n  Partidos Jugados: "+dato.getDatosNumericos().get(2)+"\n  Asistencia: "+dato.getDatosNumericos().get(3)));
                }
            }

            try(FileInputStream input = new FileInputStream(Principal.pathImg+"copa.png")){
                Image imagen = new Image(input, 10, 14, false, false);
                hbFilaCopas1.getChildren().addAll(new ImageView(imagen));
                hbFilaCopas2.getChildren().addAll(new ImageView(imagen), new ImageView(imagen));
                vbColumnaCopas.getChildren().addAll(hbFilaCopas1, hbFilaCopas2);
                vbColumnaCopas.setPadding(new Insets(50, 0, 0, 0));
                hbFilaCopas3.getChildren().addAll(new ImageView(imagen), new ImageView(imagen), new ImageView(imagen), new ImageView(imagen), new ImageView(imagen));
                hbFilaCopas3.setPadding(new Insets(15, 0, 0, 0));
            }catch(IOException ioe){
                System.out.println("No se pudo cargar imagen");
            }
            vbCopas.getChildren().addAll(vbColumnaCopas, hbFilaCopas3);

            vbPaises.getChildren().addAll(hbGanador, hbSegundo, hbTercero, hbCuarto);
            hbPuestos.getChildren().addAll(new Label("Ganador  \nSegundo  \nTercero  \nCuarto  "), vbPaises);
            vbPremios.getChildren().addAll(new Label("Premios\n\n\n"), hbPuestos);
            hbox1.getChildren().addAll(vbPremios, vbCopas, vbDatosGenerales);
            hbox1.setAlignment(Pos.CENTER);
        }
    }
    
    //metodo que permite abrir la ventana consulta Partido cuando se da click en el boton de consulta de copas mundiales.
    @FXML
    public void abrirpartido(MouseEvent e){
        try{
            Principal.cargarVentana("consultaPartido.fxml", "Ventana de consulta de partidos");
        }catch(IOException ioe){
            System.out.println("No se pudo cargar ventana");
        }
    }
}
