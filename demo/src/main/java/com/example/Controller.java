package com.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private TextField tf_name;

    @FXML
    private Button btnAdd;

    private Stage mainWindow;

    public void setMainWindow(Stage mainWindow){
        this.mainWindow = mainWindow;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarDatosApi();
    }

    public void cargarDatosApi(){
        String apiUrl = "https://magicloops.dev/api/loop/471f635b-0b4c-44e5-b811-a2f823baa79f/run?input=I+love+Magic+Loops%21"; 
        
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");
            
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) { 
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                
                System.out.println("Respuesta de la API:");
                System.out.println(response.toString());
            } else {
                System.out.println("Error en la conexión: " + responseCode);
            }
            
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}