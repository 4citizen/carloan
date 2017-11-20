package it.Carloan.controller;

import it.Carloan.controller.FrontController.*;
import it.Carloan.controller.model.Cliente;
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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class ClienteLoginController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Hyperlink btnRegCliente;

    @FXML
    private Hyperlink hylGoHome;
    
    @FXML
    private TextField labelEmail;
    
    @FXML
    private PasswordField labelPassword;
    
    
    /*
     * Qualora non si disponga delle credenziali per 
     * accedere al sistema come utente 
     * viene chiesta la registrazione
     * */
    @FXML
    void goRegCliente(ActionEvent event) throws IOException{
    	
    	Stage stage = new Stage();
    	 	
    	try {
	    	FXMLLoader loader = new FXMLLoader(HomeController.class.getResource("view/ClienteReg.fxml"));
	    	
	    	Scene loginimpiegatoscena = new Scene(loader.load());
	    	stage.setScene(loginimpiegatoscena);
	    	
	    	stage.show();
	    	
	    	((Node)event.getSource()).getScene().getWindow().hide();
    	} catch (Exception e) {
    		System.out.println("Si e' verificato un errore imprevisto");
        	
    		e.printStackTrace();
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
    
    
    /*
     * qualora un cliente disponga delle credenziali
     * può accedere alla sua home 
     * */
    @FXML
    void goToHomeCliente(ActionEvent event) throws IOException{
    	
    	
    	if(labelEmail.getText().equals("")){
    		//il campo email non deve essere vuoto
    		
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("ERRORE!");
    		alert.setHeaderText(null);
    		alert.setContentText("Errore: Il campo E-Mail NON può essere vuoto!");
    		alert.showAndWait();
    		}
    	
    	else if(labelPassword.getText().equals("")){
    		//analogamente il campo password
    		
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("ERRORE!");
    		alert.setHeaderText(null);
    		alert.setContentText("Errore: Il campo Password NON può essere vuoto!");
    		alert.showAndWait();
    		}
    	
    	else {
    		  try {
    		       Crud query = new Crud();
    		       /*
    		        * so che non sono vuote pertanto posso prenderle
    		        * */
    		       String mail = labelEmail.getText();
    		       String pass = labelPassword.getText();
    		       int esitologin;
    		       
    		       esitologin = query.verificaLoginCliente(mail, pass);

    		       if(esitologin==1){
    		    	   
    		    	   FrontController.mailUtente = mail;
    		    	   
    		    	   Stage stage = new Stage();
    		    	   try {
    		    			FXMLLoader loader = new FXMLLoader(HomeController.class.getResource("view/ClienteHome.fxml"));
    			    	
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
    		    	   	Alert alert = new Alert(AlertType.INFORMATION);
    		    		alert.setTitle("");
    		    		alert.setHeaderText(null);
    		    		alert.setContentText("Le credenziali inserite NON corrispondono a nessun Cliente. Riprova.");
    		    		alert.showAndWait();
    		    	    }
    			} catch (SQLException e) {
    						System.out.println("Codice Errore: "+e.getErrorCode()+"\nMessaggio Errore: "+e.getMessage());
    									}

    		}
    }
    
    @FXML
    void initialize() {
        assert btnRegCliente != null : "fx:id=\"btnRegCliente\" was not injected: check your FXML file 'ClienteLogin.fxml'.";

    }
}
