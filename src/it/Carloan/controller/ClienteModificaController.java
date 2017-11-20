package it.Carloan.controller;

import it.Carloan.controller.FrontController.FrontController;
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
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class ClienteModificaController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField labelEmail;

    @FXML
    private PasswordField labelPass1;

    @FXML
    private PasswordField labelPass2;

    @FXML
    private TextField labelNome;

    @FXML
    private TextField labelCognome;
    
    @FXML
    private TextField labelTelefono;

    @FXML
    private Button btnApplymod;

    
    @FXML
    void backToHomeCliente(ActionEvent event) throws IOException{
    	
    	Stage stage = new Stage();
    	  	
    	try {
	    	FXMLLoader loader = new FXMLLoader(HomeController.class.getResource("view/ClienteHome.fxml"));
	    	
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
    void Applymod(ActionEvent event) throws IOException {
    	
    	//verifico che tutti i campi siano compilati
    	if(labelPass1.getText().equals("")){
    		
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("ERRORE");
    		alert.setHeaderText(null);
    		alert.setContentText("Errore: Il campo Password NON può essere vuoto!");
    		alert.showAndWait();
    		}
    	else if(labelPass2.getText().equals("")){
    		
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("ERRORE");
    		alert.setHeaderText(null);
    		alert.setContentText("Errore: Il campo Verifica Password NON può essere vuoto!");
    		alert.showAndWait();
    		}
    	else if(labelNome.getText().equals("")){
    		
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("ERRORE");
    		alert.setHeaderText(null);
    		alert.setContentText("Errore: Il campo Nome NON può essere vuoto!");
    		alert.showAndWait();
    		}
    	else if(labelCognome.getText().equals("")){
    		
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("ERRORE");
    		alert.setHeaderText(null);
    		alert.setContentText("Errore: Il campo Cognome NON può essere vuoto!");
    		alert.showAndWait();
    		}
    	else if(labelTelefono.getText().equals("")){
    		
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("ERRORE");
    		alert.setHeaderText(null);
    		alert.setContentText("Errore: Il campo Telefono NON può essere vuoto!");
    		alert.showAndWait();
    		}
    	
    	else if (labelPass1.getText().equals(labelPass2.getText()) && (!labelPass1.getText().equals("")) && (!labelPass2.getText().equals(""))) {
    		System.out.println("OK!\n"+labelPass1.getText()+" - "+labelPass2.getText()+
    						   " Le password Corrispondono\n");
    		try {
    	
    			Crud query = new Crud();
    	
    			Cliente nuovo = new Cliente(labelEmail.getText(), labelPass1.getText(), labelNome.getText(), labelCognome.getText(), labelTelefono.getText());
    	
    			query.modCliente(nuovo);
    			
    			Alert alert = new Alert(AlertType.INFORMATION);
        		alert.setTitle("Modifica Dati Cliente");
        		alert.setHeaderText(null);
        		alert.setContentText("Congratulazioni, modifiche effettuate con successo. \nClicca per andare alla Home Cliente");
        		alert.showAndWait();
    			   
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
    			
    			} catch (SQLException e) {
    						System.out.println("Codice Errore: "+e.getErrorCode()+"\nMessaggio Errore: "+e.getMessage());
    									}
    		}
    	else {
    		System.out.println("Password diverse!\n"+labelPass1.getText()+" - "+labelPass2.getText());
    		
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("ERRORE");
    		alert.setHeaderText(null);
    		alert.setContentText("Errore: Le password inserite NON corrispondono!");
    		alert.showAndWait();
    	}

    }

    @FXML
    void initialize() {
        assert labelEmail != null : "fx:id=\"labelEmail\" was not injected: check your FXML file 'ClienteModifica.fxml'.";
        assert labelPass1 != null : "fx:id=\"labelPass1\" was not injected: check your FXML file 'ClienteModifica.fxml'.";
        assert labelPass2 != null : "fx:id=\"labelPass2\" was not injected: check your FXML file 'ClienteModifica.fxml'.";
        assert labelNome != null : "fx:id=\"labelNome\" was not injected: check your FXML file 'ClienteModifica.fxml'.";
        assert labelCognome != null : "fx:id=\"labelCognome\" was not injected: check your FXML file 'ClienteModifica.fxml'.";
        assert btnApplymod != null : "fx:id=\"btnApplymod\" was not injected: check your FXML file 'ClienteModifica.fxml'.";

       labelEmail.setText(FrontController.mailUtente);
    }
}
