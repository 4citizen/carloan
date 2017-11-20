package it.Carloan.controller;

import it.Carloan.controller.FrontController.FrontController;
import it.Carloan.controller.model.Contratto;
import it.Carloan.database.Crud;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ClienteAnnullaController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Contratto> tabellaContratti;
    
    private static ObservableList<Contratto> Contrattistampati = FXCollections.observableArrayList();

    @FXML
    private TableColumn<Contratto, String> colonnaIdcontratto = new TableColumn<Contratto,String>("idcontratto");

    @FXML
    private TableColumn<Contratto, String> colonnaPrezzototale = new TableColumn<Contratto,String>("prezzototale");

    @FXML
    private TableColumn<Contratto, String> colonnaAcconto = new TableColumn<Contratto,String>("acconto");

    @FXML
    private TableColumn<Contratto, String> colonnaTarga = new TableColumn<Contratto,String>("targaauto");
    
    @FXML
    private TableColumn<Contratto, String> colonnaDatainizio = new TableColumn<Contratto,String>("datainizio");

    @FXML
    private Button btnAnnullaContratto;
    
    @FXML
    private Button btnGoToHomeCliente;
    
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
    void annullaContratto(ActionEvent event) throws IOException{
    	
    	Contratto prendidatabella = tabellaContratti.getSelectionModel().getSelectedItem();
    	
    	String tabella = prendidatabella.getIdcontratto().toString();
    	FrontController.idcontratto= tabella.substring(FrontController.sottostringafissa,FrontController.sottostringacontratto);
    	System.out.println("\n Id contratto : "+FrontController.idcontratto+"");
    	
    	tabella="";
    	
    	tabella = prendidatabella.getDatainizio().toString();
    	FrontController.datainizio = tabella.substring(FrontController.sottostringafissa,33);
    	System.out.println("\n Data inizio noleggio : "+FrontController.datainizio+"");
    	
    	tabella="";
    	
    	tabella = prendidatabella.getTargaauto().toString();
    	FrontController.targaauto = tabella.substring(FrontController.sottostringafissa,30);
    	System.out.println("\n Targa veicolo : "+FrontController.targaauto+"");
    	
    	Stage stage = new Stage();
	  	
    	try {
	    	FXMLLoader loader = new FXMLLoader(HomeController.class.getResource("view/ClienteAnnulla2.fxml"));
	    	
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
    void initialize() {
        assert tabellaContratti != null : "fx:id=\"tabellaContratti\" was not injected: check your FXML file 'ClientePaga.fxml'.";
        assert colonnaIdcontratto != null : "fx:id=\"colonnaIdcontratto\" was not injected: check your FXML file 'ClientePaga.fxml'.";
        assert colonnaPrezzototale != null : "fx:id=\"colonnaPrezzototale\" was not injected: check your FXML file 'ClientePaga.fxml'.";
        assert colonnaAcconto != null : "fx:id=\"colonnaAcconto\" was not injected: check your FXML file 'ClientePaga.fxml'.";
        assert colonnaTarga != null : "fx:id=\"colonnaTarga\" was not injected: check your FXML file 'ClientePaga.fxml'.";
        assert colonnaDatainizio != null : "fx:id=\"colonnaDatainizio\" was not injected: check your FXML file 'ClientePaga.fxml'.";
        
        assert btnAnnullaContratto != null : "fx:id=\"btnChiudicontratto\" was not injected: check your FXML file 'ClientePaga.fxml'.";

        
        tabellaContratti.setEditable(true);
        
        colonnaIdcontratto.setCellValueFactory(new PropertyValueFactory<Contratto,String>("idcontratto"));
	    colonnaIdcontratto.setMinWidth(50);
	    
	    colonnaPrezzototale.setCellValueFactory(new PropertyValueFactory<Contratto,String>("prezzototale"));
	    colonnaPrezzototale.setMinWidth(50);
	    
	    colonnaAcconto.setCellValueFactory(new PropertyValueFactory<Contratto,String>("acconto"));
	    colonnaAcconto.setMinWidth(50);
	    
	   
	    colonnaTarga.setCellValueFactory(new PropertyValueFactory<Contratto,String>("targaauto"));
	    colonnaTarga.setMinWidth(50);
	    
	    colonnaDatainizio.setCellValueFactory(new PropertyValueFactory<Contratto,String>("datainizio"));
	    colonnaDatainizio.setMinWidth(50);
	    
	    
	    Crud query = new Crud();
	    
	    try{
	    	ObservableList<Contratto> contrattidaDB = query.printContrattiannulla(FrontController.mailUtente);
	    	
	    	colonnaIdcontratto.setCellValueFactory(cellData -> cellData.getValue().getIdcontratto());
	    	colonnaPrezzototale.setCellValueFactory(cellData -> cellData.getValue().getPrezzototale());
	    	colonnaAcconto.setCellValueFactory(cellData -> cellData.getValue().getAcconto());
	    	colonnaTarga.setCellValueFactory(cellData -> cellData.getValue().getTargaauto());
	    	colonnaDatainizio.setCellValueFactory(cellData -> cellData.getValue().getDatainizio());
	    	
	    	  tabellaContratti.setItems(contrattidaDB);
	    	  
	    }catch (SQLException e) {
			System.out.println("Codice Errore: "+e.getErrorCode()+"\nMessaggio Errore: "+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
							e.printStackTrace();
							}  catch (Exception e) {
										System.out.println("Si e' verificato un errore imprevisto");
										e.printStackTrace();
													}
	    tabellaContratti.setEditable(false);
	    
        
    }
}
