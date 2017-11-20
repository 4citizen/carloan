package it.Carloan.controller;

import it.Carloan.controller.FrontController.FrontController;
import it.Carloan.database.Crud;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ImpiegatoLoginController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Hyperlink hylGoHome;
    
    @FXML
    private TextField idaccesso;
    
   @FXML
    private TextField passwordimpiegato;
   
   @FXML
   private Button btnaccessoimpiegato;
   
    
    @FXML
    void goToHomeImpiegato(ActionEvent event) throws IOException{
    	
    	
    	if(idaccesso.getText().equals("")){
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("ERRORE");
    		alert.setHeaderText(null);
    		alert.setContentText("Campo ID vuoto!");
    		alert.showAndWait();
    		}
    	
    	else if(passwordimpiegato.getText().equals("")){
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("ERRORE");
    		alert.setHeaderText(null);
    		alert.setContentText("Campo Password vuoto!");
    		alert.showAndWait();
    	}
    	
    	else {
    		  try {
    		       Crud query = new Crud();
    		       /*
    		        * so che non sono vuote pertanto posso prenderle
    		        * */
    		       String accesso = idaccesso.getText();
    		       String pass = passwordimpiegato.getText();
    		       int esitologin;
    		       
    		       esitologin = query.verificaLoginImpiegato(accesso, pass);
    		       
    		       if(esitologin==1){
    		    	   
    		    	   FrontController.idimpiegato = accesso;
    		    	   
    		    	//mi serve l'id dell'agenzia per cui lavora l'impiegato
    		    	//mi servirà per chiudere i contratti
    		    	   try{
    		    	   Crud query2 = new Crud();
    		    	   
    		    	   FrontController.agenziaimpiegato = query2.prendiIdagenziaimpiegato();
    		    	   
    		    	   
    		    	   } catch (SQLException e) {
   						System.out.println("Codice Errore: "+e.getErrorCode()+"\nMessaggio Errore: "+e.getMessage());
   									}
    		    	   
    		    	   
    		    	   Stage stage = new Stage();
    		    	   try {
    		    			FXMLLoader loader = new FXMLLoader(HomeController.class.getResource("view/ImpiegatoHome.fxml"));
    			    	
    		    			Scene homecliente = new Scene(loader.load());
    		    			stage.setScene(homecliente);
    			    	
    		    			stage.show();
    			    	
    		    			((Node)event.getSource()).getScene().getWindow().hide();
    		    		} catch (Exception e) {
    		    								System.out.println("Si e' verificato un errore imprevisto");
    		    								e.printStackTrace();
    		    								}
    		       }
    		       else{
    		    	    Alert alert = new Alert(AlertType.ERROR);
    		    		alert.setTitle("ERRORE");
    		    		alert.setHeaderText(null);
    		    		alert.setContentText("Le credenziali inserite NON corrispondono a nessun Impiegato!\nRiprova...");
    		    		alert.showAndWait();
    		    	    }
    			} catch (SQLException e) {
    						System.out.println("Codice Errore: "+e.getErrorCode()+"\nMessaggio Errore: "+e.getMessage());
    									}

    		}
    }
    
    
    
    @FXML
    void backToHome(ActionEvent event) throws IOException{
    	Stage stage = new Stage();
       	
    	try {
	    	FXMLLoader loader = new FXMLLoader(HomeController.class.getResource("view/Home.fxml"));
	    	
	    	Scene homescena = new Scene(loader.load());
	    	stage.setScene(homescena);
	    	
	    	stage.show();
	    	
	    	((Node)event.getSource()).getScene().getWindow().hide();
    	} catch (Exception e) {
    		System.out.println("Si e' verificato un errore imprevisto");
        	e.printStackTrace();
    	}
    }

    @FXML
    void initialize() {
        assert hylGoHome != null : "fx:id=\"hylGoHome\" was not injected: check your FXML file 'ImpiegatoLogin.fxml'.";

    }
}
