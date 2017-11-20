
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class ClientePaga2Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField areaPrezzototale;

    @FXML
    private TextField areaAcconto;

    @FXML
    private TextField areaSaldo;
    
    @FXML
    private TextField areaDasaldare;

    @FXML
    private Button btnPaga;

    @FXML
    void pagaContratto(ActionEvent event) {
    	
    	int saldopreso=0;
    	//FrontController.prezzofinale = Integer.parseInt(daconvertire);
    	
    	saldopreso = Integer.parseInt(areaDasaldare.getText());
    	
    	if(areaDasaldare.getText().equals("")){
        	Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("ERRORE");
    		alert.setHeaderText(null);
    		alert.setContentText("Impossibile procedere con il Pagamento!\nInserisci un importo...");
    		alert.showAndWait();
        }
        else if(saldopreso<0){
        	Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("ERRORE");
    		alert.setHeaderText(null);
    		alert.setContentText("Impossibile procedere con il Pagamento!\nImpossibile accettare un importo negativo...");
    		alert.showAndWait(); 
        	}
        else if((FrontController.residuo-saldopreso)<0){
        	Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("ERRORE");
    		alert.setHeaderText(null);
    		alert.setContentText("Hai inserito un importo maggiore del saldo dovuto!");
    		alert.showAndWait(); 
        	}
        else if((FrontController.residuo-saldopreso)>0){
        	Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("ERRORE");
    		alert.setHeaderText(null);
    		alert.setContentText("Hai inserito un importo insufficiente per il Pagamento!");
    		alert.showAndWait(); 
        	}
        else if((FrontController.residuo-saldopreso)==0){
        	Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("ERRORE");
    		alert.setHeaderText(null);
    		alert.setContentText("Hai inserito un importo da saldare corretto!\nIl seguente contratto verrà chiuso!");
    		alert.showAndWait();
        	Crud query = new Crud();
			 
			 try{
			 
				 query.updateContrattoc(FrontController.idcontratto);
			 }catch (SQLException e) {
					System.out.println("Codice Errore: "+e.getErrorCode()+"\nMessaggio Errore: "+e.getMessage());
					e.printStackTrace();
				} catch (IOException e) {
									e.printStackTrace();
									}  catch (Exception e) {
												System.out.println("Si e' verificato un errore imprevisto");
												e.printStackTrace();
															}
			 System.out.println("ho chiuso il contratto definitivamente.");
			 
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
    }

    @FXML
    void initialize() {
        assert areaPrezzototale != null : "fx:id=\"areaPrezzototale\" was not injected: check your FXML file 'ClientePaga2.fxml'.";
        assert areaAcconto != null : "fx:id=\"areaAcconto\" was not injected: check your FXML file 'ClientePaga2.fxml'.";
        assert areaSaldo != null : "fx:id=\"areaSaldo\" was not injected: check your FXML file 'ClientePaga2.fxml'.";
        assert btnPaga != null : "fx:id=\"btnPaga\" was not injected: check your FXML file 'ClientePaga2.fxml'.";

        areaPrezzototale.setText(String.valueOf(FrontController.prezzofinale));
        
        areaAcconto.setText(String.valueOf(FrontController.acconto));
        
        areaSaldo.setText(String.valueOf(FrontController.residuo));
    
    }
}
