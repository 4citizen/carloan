package it.Carloan.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.LocalDateStringConverter;
import it.Carloan.database.Crud;
import it.Carloan.controller.FrontController.FrontController;
import it.Carloan.controller.model.Cliente;

public class ClienteAnnulla2Controller {

	
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private DatePicker dataOdierna;
    
    @FXML
    private DatePicker dataInizio;

    @FXML
    private Button btnAnnulla;

    @FXML
    void GoToAnnulla(ActionEvent event) throws IOException{
    	
    	Stage stage = new Stage();
    	  	
    	try {
	    	FXMLLoader loader = new FXMLLoader(HomeController.class.getResource("view/ClienteAnnulla.fxml"));
	    	
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
    void annullaContratto(ActionEvent event) {

        
        LocalDate datainiziopresa = dataInizio.getValue();
        LocalDate dataodiernapresa = dataOdierna.getValue();
        
    	Period numGiorni = Period.between(dataodiernapresa,datainiziopresa);
        int giorni=numGiorni.getDays();
        System.out.println("Differenza di giorni : "+giorni);
        
        if (giorni<0){
			 Alert alert = new Alert(AlertType.ERROR);
			 alert.setTitle("ERRORE");
			 alert.setHeaderText(null);
			 alert.setContentText("Impossibile annullare questo contratto perché è gia in uso...");
			 alert.showAndWait();
			 
			 //si torna alla homepage del cliente
			 Stage stage = new Stage();
	        	
	        	try {
	        	FXMLLoader loader = new FXMLLoader(HomeController.class.getResource("view/ClienteHome.fxml"));
	        	
	        	Scene loginimpiegatoscena = new Scene(loader.load());
	        	stage.setScene(loginimpiegatoscena);
	        	stage.show();
	        	
	        	((Node)event.getSource()).getScene().getWindow().hide();
	        	} catch (Exception e) {
	        		System.out.println("Si e' verificato un errore imprevisto");
	            	
	        		e.printStackTrace();
	        	}
        			}
        else if (giorni==0){
        		Alert alert = new Alert(AlertType.ERROR);
        		alert.setTitle("ERRORE");
        		alert.setHeaderText(null);
        		alert.setContentText("Impossibile annullare un contratto nella data in cui esso inizia...");
        		alert.showAndWait();
			 //si torna alla homepage del cliente
			 Stage stage = new Stage();
	        	
	        	try {
	        	FXMLLoader loader = new FXMLLoader(HomeController.class.getResource("view/ClienteHome.fxml"));
	        	
	        	Scene homeclientescena = new Scene(loader.load());
	        	stage.setScene(homeclientescena);
	        	stage.show();
	        	
	        	((Node)event.getSource()).getScene().getWindow().hide();
	        	} catch (Exception e) {
	        		System.out.println("Si e' verificato un errore imprevisto");
	            	
	        		e.printStackTrace();
	        	}	 
				 		}
        else if (giorni>=1){
        		Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Annullamento Contratto");
				alert.setHeaderText(null);
				alert.setContentText("OK! Questo contratto può essere annullato\nL'acconto lasciato non potrà essere restituito!.");
				alert.showAndWait();
			 
			 
			 FrontController.mora = 0;
			 FrontController.residuo = 0;
			 
			//chiudo il contratto
			 Crud query = new Crud();
			 
			 try{
			 
				 query.chiudiContrattoannullato();
			 }catch (SQLException e) {
					System.out.println("Codice Errore: "+e.getErrorCode()+"\nMessaggio Errore: "+e.getMessage());
					e.printStackTrace();
				} catch (IOException e) {
									e.printStackTrace();
									}  catch (Exception e) {
												System.out.println("Si e' verificato un errore imprevisto");
												e.printStackTrace();
															}
			 
			 //aggiorno lo stato dell'auto
			 Crud query2 = new Crud();
			 try{
				 FrontController.disponibilitaSI="si";
				 query2.updateAuto(FrontController.targaauto,FrontController.disponibilitaSI);
			 }catch (SQLException e) {
					System.out.println("Codice Errore: "+e.getErrorCode()+"\nMessaggio Errore: "+e.getMessage());
					e.printStackTrace();
				} catch (IOException e) {
									e.printStackTrace();
									}  catch (Exception e) {
												System.out.println("Si e' verificato un errore imprevisto");
												e.printStackTrace();
															}
			 //si torna alla homepage del cliente
			 Stage stage = new Stage();
	        	
	        	try {
	        	FXMLLoader loader = new FXMLLoader(HomeController.class.getResource("view/ClienteHome.fxml"));
	        	
	        	Scene homeclientescena = new Scene(loader.load());
	        	stage.setScene(homeclientescena);
	        	stage.show();
	        	
	        	((Node)event.getSource()).getScene().getWindow().hide();
	        	} catch (Exception e) {
	        		System.out.println("Si e' verificato un errore imprevisto");
	            	
	        		e.printStackTrace();
	        	}
			}
    }

    @FXML
    void initialize() throws ParseException {
    	assert dataInizio != null : "fx:id=\"dataInizio\" was not injected: check your FXML file 'ClienteAnnulla2.fxml'.";
    	assert dataOdierna != null : "fx:id=\"dataOdierna\" was not injected: check your FXML file 'ClienteAnnulla2.fxml'.";
        
        assert btnAnnulla != null : "fx:id=\"btnAnnulla\" was not injected: check your FXML file 'ClienteAnnulla2.fxml'.";

        
        int giorni;
        int mesi;
        int anno;

        
        String daconvertire = FrontController.datainizio.substring(0,4);
        anno = Integer.parseInt(daconvertire);
        System.out.println("Anno preso dal frontController : "+anno);
        
        daconvertire="";
        daconvertire = FrontController.datainizio.substring(5,7);
        mesi = Integer.parseInt(daconvertire);
        System.out.println("Mesi presi dal frontController : "+mesi );
        
        daconvertire="";
        daconvertire = FrontController.datainizio.substring(8,10);
        giorni = Integer.parseInt(daconvertire);
        System.out.println("Giorni presi dal frontController : "+giorni );
        
        dataInizio.setValue(LocalDate.of(anno, mesi, giorni));
        
        dataOdierna.setValue(LocalDate.now());
    	 
        
    }	
}
