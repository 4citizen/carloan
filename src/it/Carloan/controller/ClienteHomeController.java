package it.Carloan.controller;

import it.Carloan.controller.FrontController.*;
import it.Carloan.database.Crud;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicLong;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class ClienteHomeController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnGoPrenota;
    
    @FXML
    private Button btnLogout;
    
    @FXML
    private Label Cliente;

    @FXML
    private Button btnGoAnnulla;

    @FXML
    private Button btnGoModifica;

    @FXML
    private Button btnGoPaga;
    
    
    @FXML
    void goToPrenota(ActionEvent event) throws IOException{
    	
    	Stage stage = new Stage();
    	  	
    	try {
	    	FXMLLoader loader = new FXMLLoader(HomeController.class.getResource("view/ClientePrenota.fxml"));
	    	
	    	Scene clienteprenota = new Scene(loader.load());
	    	stage.setScene(clienteprenota);
	    	
	    	stage.show();
	    	
	    	((Node)event.getSource()).getScene().getWindow().hide();
    	} catch (Exception e) {
    		System.out.println("Si e' verificato un errore imprevisto");
        	e.printStackTrace();
    	}
    }
    
    
    @FXML
    void goToModifica(ActionEvent event) throws IOException{
    	
    	Stage stage = new Stage();
    	  	
    	try {
	    	FXMLLoader loader = new FXMLLoader(HomeController.class.getResource("view/ClienteModifica.fxml"));
	    	
	    	Scene clientemodifica = new Scene(loader.load());
	    	stage.setScene(clientemodifica);
	    	
	    	stage.show();
	    	
	    	((Node)event.getSource()).getScene().getWindow().hide();
    	} catch (Exception e) {
    		System.out.println("Si e' verificato un errore imprevisto");
        	e.printStackTrace();
    	}
    }

    
    @FXML
    void goToPaga(ActionEvent event) throws IOException{
    	
    	Stage stage = new Stage();
    	  	
    	try {
	    	FXMLLoader loader = new FXMLLoader(HomeController.class.getResource("view/ClientePaga.fxml"));
	    	
	    	Scene clientepaga = new Scene(loader.load());
	    	stage.setScene(clientepaga);
	    	
	    	stage.show();
	    	
	    	((Node)event.getSource()).getScene().getWindow().hide();
    	} catch (Exception e) {
    		System.out.println("Si e' verificato un errore imprevisto");
        	e.printStackTrace();
    	}
    }
    
    @FXML
    void gotoAnnulla(ActionEvent event) throws IOException{
    	
    	Stage stage = new Stage();
	  	
    	try {
	    	FXMLLoader loader = new FXMLLoader(HomeController.class.getResource("view/ClienteAnnulla.fxml"));
	    	
	    	Scene clientepaga = new Scene(loader.load());
	    	stage.setScene(clientepaga);
	    	
	    	stage.show();
	    	
	    	((Node)event.getSource()).getScene().getWindow().hide();
    	} catch (Exception e) {
    		System.out.println("Si e' verificato un errore imprevisto");
        	e.printStackTrace();
    	}
    	
    }
    
    
    @FXML
    void goToHomepage(ActionEvent event) throws IOException{
    	
    	
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("LOGOUT");
		alert.setHeaderText(null);
		alert.setContentText("Hai effettuato il LOGOUT.\nClicca per tornare alla Homepage.");
		alert.showAndWait();
		
    	System.out.print("Logout di : "+FrontController.mailUtente+" avvenuto");
    	
    	//elimino la mail dell'utente che ha deciso
    	//di effettuare il logout
    	FrontController.mailUtente="";
    	
    	//elimino anche tutto ciò che l'utente
    	//ha o non ha inizializzato
    	
    	FrontController.temponoleggio="";
    	FrontController.fasciaauto="";
    	FrontController.tipochilometraggio="";
    	
    	FrontController.agenziaritiro="";
    	FrontController.idagenziaritiro="";
    	FrontController.agenziaconsegna="";
    	FrontController.idagenziaconsegna="";	
    	
    	FrontController.datainizio="";
    	FrontController.datalimite="";
    	FrontController.datarientro="";
    	
    	FrontController.disponibilitaSI="";
    	FrontController.disponibilitaNO="";
    	FrontController.targaauto="";
    	FrontController.residuo=0;
    	FrontController.mora=0;

    	
    	Stage stage = new Stage();
    	  	
    	try {
	    	FXMLLoader loader = new FXMLLoader(HomeController.class.getResource("view/Home.fxml"));
	    	
	    	Scene clientepaga = new Scene(loader.load());
	    	stage.setScene(clientepaga);
	    	
	    	stage.show();
	    	
	    	((Node)event.getSource()).getScene().getWindow().hide();
    	} catch (Exception e) {
    		System.out.println("Si e' verificato un errore imprevisto");
        	e.printStackTrace();
    	}
    }
    
    
    @FXML
    void initialize() {
    	
    	//visualizzo 
    	Cliente.setText(FrontController.mailUtente);
    	
        assert btnGoPrenota != null : "fx:id=\"btnGoPrenota\" was not injected: check your FXML file 'ClienteHome.fxml'.";
        assert btnGoAnnulla != null : "fx:id=\"btnGoAnnulla\" was not injected: check your FXML file 'ClienteHome.fxml'.";
        assert btnGoModifica != null : "fx:id=\"btnGoModifica\" was not injected: check your FXML file 'ClienteHome.fxml'.";
        assert btnGoPaga != null : "fx:id=\"btnGoPaga\" was not injected: check your FXML file 'ClienteHome.fxml'.";
        
        
    }
}
