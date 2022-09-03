/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo8.proyectofifa;

import java.io.FileInputStream;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 *
 * @author laque
 */
public class Principal extends Application{
    public static Scene scene;
    public static String pathImg = "src/main/resources/image/";
    public static String pathBandera = "src/main/resources/banderas/";
    public static String pathArc = "src/main/resources/archivos/";
    
    /* metodo para cargar imagen en un imageview, recibe la variable imageview, la ruta donde se encuentra la imagen, el
    ancho y alto de la imagen, y dos valores booleanos para saber si se desea ajustar el ancho o alto de la imagen*/
    public static void cargarimagen (ImageView iv, String direccion, double width, double height, boolean bln0, boolean bln1){
        try(FileInputStream input = new FileInputStream(direccion)){
            Image image = new Image(input, width, height, bln0, bln1);
            iv.setImage(image);
            
        }catch(IOException ioe){
            System.out.println("No se puede cargar imagen");
        }
    }
    
    //metodo estatico para crear una nueva pestaña con el nombre del archivo fxml y el titulo de la pestaña
    public static void cargarVentana(String nombreArchivo, String nombreVentana) throws IOException{
        Stage s = new Stage();
        FXMLLoader fxmlloader = new FXMLLoader(Principal.class.getResource(nombreArchivo));
        Parent root = fxmlloader.load();
        scene = new Scene(root);
        scene.getStylesheets().add(Principal.class.getResource("Login.css").toExternalForm());
        s.setScene(scene);
        s.show();
        s.setTitle(nombreVentana);
    }
    
    @Override
    public void start(Stage s)throws IOException{
        FXMLLoader fxmlloader = new FXMLLoader(Principal.class.getResource("ventana.fxml"));
        Parent root = fxmlloader.load();
        scene = new Scene(root);
        scene.getStylesheets().add(Principal.class.getResource("Login.css").toExternalForm());
        s.setScene(scene);
        s.show();
        s.setTitle("Copa mundial de la FIFA Brasil 2014");
    }
    
    public static void main(String[] args) {
        launch(args);
        
    }
}