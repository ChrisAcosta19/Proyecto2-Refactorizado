/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo8.proyectofifa;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javafx.scene.image.Image;

/**
 *
 * @author laque
 */
public class worldCup {
    private String year;
    private ArrayList<String> paises;
    private ArrayList<String> datosNumericos;
    
    public worldCup(String year, ArrayList<String> paises, ArrayList<String> datosNumericos){
        this.year = year;
        this.paises = paises;
        this.datosNumericos = datosNumericos;
    }

    public String getYear() {
        return year;
    }

    public ArrayList<String> getPaises() {
        return paises;
    }

    public ArrayList<String> getDatosNumericos() {
        return datosNumericos;
    }
    
    public Image generarBandera(int index){
        Image image = null;
        try(FileInputStream input = new FileInputStream("src/main/resources/banderas/"+ paises.get(index) +".png")){
            image = new Image(input, 18, 14, false, false);
            
        }catch(IOException ioe){
            System.out.println("No se puede cargar imagen");
        }
        return image;
    }
    
    public static ArrayList<worldCup> generarLista(){
        
        ArrayList<worldCup> listaWorldCup = new ArrayList<worldCup>();
        
        try(BufferedReader lector = new BufferedReader(new FileReader("src/main/resources/archivos/WorldCups.csv"))){
            lector.readLine();
            String linea;
            String[] contenido;
            while((linea = lector.readLine()) != null){
                contenido = linea.split(",");
                ArrayList<String> paises = new ArrayList<String>();
                ArrayList<String> datosNumericos = new ArrayList<String>();
                for(int i = 2; i<6; i++){
                    paises.add(contenido[i]);
                    datosNumericos.add(contenido[i+4]);
                }
                listaWorldCup.add(new worldCup(contenido[0], paises, datosNumericos));
            }
        }catch(IOException ioe){
            System.out.println("No se pudo leer el archivo");
        }
        return listaWorldCup;
    }
}