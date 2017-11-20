package it.Carloan.controller;

import it.Carloan.controller.FrontController.FrontController;
import it.Carloan.database.Crud;

import java.io.IOException;

//import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
//import javafx.stage.Modality;
import javafx.stage.Stage;

public class HomeController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnLogCliente;
    
    @FXML
    private Button btnTestQuery;

    @FXML
    private Button btnLogImpiegato;

    @FXML
    void goLogCliente(ActionEvent event) throws IOException{
    	
    	
    	Stage stage = new Stage();
    	
    	try {
    	FXMLLoader loader = new FXMLLoader(HomeController.class.getResource("view/ClienteLogin.fxml"));
    	
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
    void goLogImpiegato(ActionEvent event) throws IOException{

		Stage stage = new Stage();
    	
    	try {
    	FXMLLoader loader = new FXMLLoader(HomeController.class.getResource("view/ImpiegatoLogin.fxml"));
    	
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
    void initialize() {
        assert btnLogCliente != null : "fx:id=\"btnLogCliente\" was not injected: check your FXML file 'Home.fxml'.";
        assert btnLogImpiegato != null : "fx:id=\"btnLogImpiegato\" was not injected: check your FXML file 'Home.fxml'.";

        
        Crud query = new Crud();
        int idmassimo=0;
        try{
		
			idmassimo = query.prendiidcontratto();

    		 
    	} catch (Exception e) {
			System.out.println("Si e' verificato un errore imprevisto");
			e.printStackTrace();
			}
        
        if(idmassimo==0){
        	
        	 //non vi cono contratti nel DB 
         	 System.out.println("\nNon vi sono contratti nella tabella\n");
         	 FrontController.idcontrattounico = 1;
         	 FrontController.sottostringacontratto=24;
        }
        
        else if(idmassimo<=9){
        	FrontController.idcontrattounico = idmassimo;
         System.out.println("\nID massimo all'interno della tabella : "+FrontController.idcontrattounico);
      	 //in questo modo posso gestire fino a 9 contratti
      	 FrontController.sottostringacontratto=24;
      	}
       else if(idmassimo<=99){
    	   FrontController.idcontrattounico = idmassimo;
      	      		 //gestisco 99 contratti
      		FrontController.sottostringacontratto=25; 
      	 System.out.println("\nID massimo all'interno della tabella : "+FrontController.idcontrattounico);
       }

      	 else if(idmassimo<=999){
      		FrontController.idcontrattounico = idmassimo;
      		System.out.println("\nID massimo all'interno della tabella : "+FrontController.idcontrattounico);
      		 //gestisto 999 contratti
      		 FrontController.sottostringacontratto=26;
      	 						}
      	 
      	 
       		}

}