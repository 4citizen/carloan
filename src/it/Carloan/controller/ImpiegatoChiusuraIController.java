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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.LocalDateStringConverter;
import it.Carloan.database.Crud;
import it.Carloan.controller.FrontController.FrontController;
import it.Carloan.controller.model.Cliente;

public class ImpiegatoChiusuraIController {

	
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField areaPrezzototale;

    @FXML
    private DatePicker dataLimite;

    @FXML
    private TextField areaAcconto;

    @FXML
    private DatePicker dataRientro;
    
    @FXML
    private DatePicker dataInizio;

    @FXML
    private TextField areaSaldo;

    @FXML
    private Button btnChiusuracontratto;

    @FXML
    private Button btnGoToImpiegatoHome;
    
    @FXML
    void GoToHomeImpiegato(ActionEvent event) {
    	Stage stage = new Stage();
    	try {
	    	FXMLLoader loader = new FXMLLoader(HomeController.class.getResource("view/ImpiegatoHome.fxml"));
	    	
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
    void chiudicontratto(ActionEvent event) {

        
        LocalDate datalimitepresa = dataLimite.getValue();
        LocalDate datarientropresa = dataRientro.getValue();
        
    	
        Period numGiorni = Period.between(datalimitepresa,datarientropresa);
        int giorni=numGiorni.getDays();
        System.out.println("Differenza di giorni : "+giorni);
        if (giorni<0){
			 Alert alert = new Alert(AlertType.ERROR);
			 alert.setTitle("ERRORE");
			 alert.setHeaderText(null);
			 alert.setContentText("Devi selezionare una data di rientro non inferiore alla data limite!");
			 alert.showAndWait();
		}
        else if (giorni==0){

			 Alert alert = new Alert(AlertType.INFORMATION);
			 alert.setTitle("");
			 alert.setHeaderText(null);
			 alert.setContentText("Hai selezionato come data rientro la data limite.\nIl cliente NON pagherà la mora!");
			 alert.showAndWait();
			 
			 LocalDate datarientro = dataRientro.getValue();
		     FrontController.datarientro= datarientro.toString();

			 FrontController.mora = 0;
			 FrontController.residuo = (FrontController.prezzofinale - FrontController.acconto)+FrontController.mora;
			 
			 Alert alert2 = new Alert(AlertType.INFORMATION);
			 alert2.setTitle("");
			 alert2.setHeaderText(null);
			 alert2.setContentText("Il prezzo finale del contratto sarà : "+FrontController.residuo+"€");
			 alert2.showAndWait();
			 
			 Crud query = new Crud();
			 
			 try{
			 
				 query.chiudiContratto();
			 }catch (SQLException e) {
					System.out.println("Codice Errore: "+e.getErrorCode()+"\nMessaggio Errore: "+e.getMessage());
					e.printStackTrace();
				} catch (IOException e) {
									e.printStackTrace();
									}  catch (Exception e) {
												System.out.println("Si e' verificato un errore imprevisto");
												e.printStackTrace();
															}
			 
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
			 
			//aggiorno l'agenzia in cui sarà presente l'auto consegnata
			 Crud query3 = new Crud();
			 try{
				 System.out.println("Agenzia consegna : "+FrontController.agenziaconsegna);
				 query3.updateAutoa(FrontController.targaauto);
			 }catch (SQLException e) {
					System.out.println("Codice Errore: "+e.getErrorCode()+"\nMessaggio Errore: "+e.getMessage());
					e.printStackTrace();
				} catch (IOException e) {
									e.printStackTrace();
									}  catch (Exception e) {
												System.out.println("Si e' verificato un errore imprevisto");
												e.printStackTrace();
															}
			 
			 
			 //si torna alla homepage dell'impiegato
			 Stage stage = new Stage();
	        	
	        	try {
	        	FXMLLoader loader = new FXMLLoader(HomeController.class.getResource("view/ImpiegatoHome.fxml"));
	        	
	        	Scene loginimpiegatoscena = new Scene(loader.load());
	        	stage.setScene(loginimpiegatoscena);
	        	stage.show();
	        	
	        	((Node)event.getSource()).getScene().getWindow().hide();
	        	} catch (Exception e) {
	        		System.out.println("Si e' verificato un errore imprevisto");
	            	
	        		e.printStackTrace();
	        	}	 
			 
				 
			}
        else if (giorni>0){
			 Alert alert = new Alert(AlertType.WARNING);
			 alert.setTitle("");
			 alert.setHeaderText(null);
			 alert.setContentText("Hai selezionato una data superiore alla data limite.\nIl cliente pagherà la mora!");
			 alert.showAndWait();
			 
			 
			 
			 FrontController.mora = 50;
			 FrontController.residuo = (FrontController.prezzofinale - FrontController.acconto)+FrontController.mora;
			 LocalDate datarientro = dataRientro.getValue();
		     FrontController.datarientro= datarientro.toString();

					
			Alert alert2 = new Alert(AlertType.INFORMATION);
			alert2.setTitle("");
			alert2.setHeaderText(null);
			alert2.setContentText("Il prezzo finale del contratto sarà : "+FrontController.residuo+"€");
			alert2.showAndWait(); 
			
			//chiudo il contratto
			 Crud query = new Crud();
			 
			 try{
			 
				 query.chiudiContratto();
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
			 
			//aggiorno l'agenzia in cui sarà presente l'auto consegnata
			 Crud query3 = new Crud();
			 try{
				 System.out.println("Agenzia consegna : "+FrontController.agenziaconsegna);
				 query3.updateAutoa(FrontController.targaauto);
			 }catch (SQLException e) {
					System.out.println("Codice Errore: "+e.getErrorCode()+"\nMessaggio Errore: "+e.getMessage());
					e.printStackTrace();
				} catch (IOException e) {
									e.printStackTrace();
									}  catch (Exception e) {
												System.out.println("Si e' verificato un errore imprevisto");
												e.printStackTrace();
															}
			 
			 
			 //si torna alla homepage dell'impiegato
			 Stage stage = new Stage();
	        	
	        	try {
	        	FXMLLoader loader = new FXMLLoader(HomeController.class.getResource("view/ImpiegatoHome.fxml"));
	        	
	        	Scene loginimpiegatoscena = new Scene(loader.load());
	        	stage.setScene(loginimpiegatoscena);
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
        assert areaPrezzototale != null : "fx:id=\"areaPrezzototale\" was not injected: check your FXML file 'ImpiegatoChiusuraI.fxml'.";
        assert dataLimite != null : "fx:id=\"dataLimite\" was not injected: check your FXML file 'ImpiegatoChiusuraI.fxml'.";
        assert areaAcconto != null : "fx:id=\"areaAcconto\" was not injected: check your FXML file 'ImpiegatoChiusuraI.fxml'.";
        assert dataRientro != null : "fx:id=\"dataRientro\" was not injected: check your FXML file 'ImpiegatoChiusuraI.fxml'.";
        assert areaSaldo != null : "fx:id=\"areaSaldo\" was not injected: check your FXML file 'ImpiegatoChiusuraI.fxml'.";
        assert btnChiusuracontratto != null : "fx:id=\"btnChiusuracontratto\" was not injected: check your FXML file 'ImpiegatoChiusuraI.fxml'.";

        
        
        /*DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd");
        LocalDate date = LocalDate.parse(FrontController.datalimite, formatter);
        System.out.println("Data convertita da stringa : "+date);*/
        
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
        
        daconvertire="";
        
        daconvertire = FrontController.datalimite.substring(0,4);
        anno = Integer.parseInt(daconvertire);
        System.out.println("Anno preso dal frontController : "+anno);
        
        daconvertire="";
        daconvertire = FrontController.datalimite.substring(5,7);
        mesi = Integer.parseInt(daconvertire);
        System.out.println("Mesi presi dal frontController : "+mesi );
        
        daconvertire="";
        daconvertire = FrontController.datalimite.substring(8,10);
        giorni = Integer.parseInt(daconvertire);
        System.out.println("Giorni presi dal frontController : "+giorni );
        
        dataLimite.setValue(LocalDate.of(anno, mesi, giorni));
        
        dataRientro.setValue(dataLimite.getValue());
    	
        
        areaPrezzototale.setText(String.valueOf(FrontController.prezzofinale));
        areaAcconto.setText(String.valueOf(FrontController.acconto));
        
        int prezzodasaldare=0;
        
        prezzodasaldare = FrontController.prezzofinale-FrontController.acconto;
        areaSaldo.setText(String.valueOf(prezzodasaldare));
        
        
    }
	
}
