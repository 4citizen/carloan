package it.Carloan.controller;

import it.Carloan.controller.FrontController.FrontController;
import it.Carloan.controller.model.Auto;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class ImpiegatoHomeController {
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Contratto> tabellaContratti;
    
    /*
     * ObservableList prende tutti i campi dal db 
     * e li imposta in modo tale da stamparli 
     * nelle eventuali colonne
     * */
    private static ObservableList<Contratto> Contrattistampati = FXCollections.observableArrayList();

    @FXML
    TableColumn<Contratto, String> colonnaIdcontratto = new TableColumn<Contratto,String>("idcontratto");

    @FXML
    TableColumn<Contratto, String> colonnaAgenziaconsegna= new TableColumn<Contratto,String>("agenziaconsegna");
    
    @FXML
    TableColumn<Contratto, String> colonnaPrezzobase= new TableColumn<Contratto,String>("tariffabase");

    @FXML
    TableColumn<Contratto, String> colonnaAcconto= new TableColumn<Contratto,String>("acconto");
    
    @FXML
    TableColumn<Contratto, String> colonnaPrezzofinale= new TableColumn<Contratto,String>("prezzototale");

    @FXML
    TableColumn<Contratto, String> colonnaTipokm = new TableColumn<Contratto,String>("km_noleggio");

    @FXML
    TableColumn<Contratto, String> colonnaTarga = new TableColumn<Contratto,String>("targaauto");
    
    @FXML
    TableColumn<Contratto, String> colonnaLimiterientro = new TableColumn<Contratto,String>("datalimite");

    @FXML
    TableColumn<Contratto, String> colonnaInizionoleggio = new TableColumn<Contratto,String>("datainizio");
    
    @FXML
    private Button btnChiudicontratto;

    @FXML
    void backToHome(ActionEvent event) throws IOException{
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("LOGOUT");
		alert.setHeaderText(null);
		alert.setContentText("Hai effettuato il LOGOUT.\nClicca per tornare alla Homepage.");
		alert.showAndWait();
		
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
    void chiusuraContratto(ActionEvent event) {

    	Contratto prendidatabella = tabellaContratti.getSelectionModel().getSelectedItem();
    	
    	String tabella = prendidatabella.getIdcontratto().toString();
    	//System.out.println("\n\n sottostringa : "+FrontController.sottostringacontratto+"\n");
    	FrontController.idcontratto= tabella.substring(FrontController.sottostringafissa,FrontController.sottostringacontratto);
    	System.out.println("\n Id contratto : "+FrontController.idcontratto+"");
    	
    	tabella="";
    	
    	tabella = prendidatabella.getAgenziaconsegna().toString();
    	FrontController.agenziaconsegna = tabella.substring(FrontController.sottostringafissa,24);
    	System.out.println("\n Agenzia Consegna : "+FrontController.agenziaconsegna+"");
    	
    	tabella="";
    	
    	tabella = prendidatabella.getPrezzototale().toString();
    	String daconvertire = tabella.substring(FrontController.sottostringafissa,26);
    	FrontController.prezzofinale = Integer.parseInt(daconvertire);
    	System.out.println("\n Prezzo finale contratto : "+FrontController.prezzofinale+"");
    	
    	tabella="";
    	daconvertire="";
    	
    	tabella = prendidatabella.getAcconto().toString();
    	daconvertire =tabella.substring(FrontController.sottostringafissa,25);
    	FrontController.acconto = Integer.parseInt(daconvertire);
    	System.out.println("\n Acconto contratto : "+FrontController.acconto+"");
    	
    	tabella="";
    	daconvertire="";
    	
    	tabella = prendidatabella.getTariffabase().toString();
    	daconvertire =tabella.substring(FrontController.sottostringafissa,26);
    	FrontController.tariffabase = Integer.parseInt(daconvertire);
    	System.out.println("\n Tariffa base noleggio : "+FrontController.tariffabase+"");
    	
    	tabella="";
    	
    	tabella = prendidatabella.getKm_noleggio().toString();
    	FrontController.tipochilometraggio = tabella.substring(FrontController.sottostringafissa,24);
    	System.out.println("\n Tipo chilometraggio : "+FrontController.tipochilometraggio+"");
    	
    	tabella="";
    	
    	tabella = prendidatabella.getTargaauto().toString();
    	FrontController.targaauto = tabella.substring(FrontController.sottostringafissa,30);
    	System.out.println("\n Targa veicolo : "+FrontController.targaauto+"");
    	
    	tabella="";
    	
    	tabella = prendidatabella.getDatalimite().toString();
    	FrontController.datalimite = tabella.substring(FrontController.sottostringafissa,33);
    	System.out.println("\n Data limite rientro : "+FrontController.datalimite+"");
    	
    	tabella="";
    	
    	tabella = prendidatabella.getDatainizio().toString();
    	FrontController.datainizio = tabella.substring(FrontController.sottostringafissa,33);
    	System.out.println("\n Data inizio noleggio : "+FrontController.datainizio+"");
    	
    	
    	if(FrontController.tipochilometraggio.equalsIgnoreCase("i")){
    		//vuol dire che il tipo di chilometraggio è illimitato
    		//posso prendere questa decisione perchè all'interno del 
    		//db il tipokm è memorizzato come enum
    		Stage stage = new Stage();
        	
        	try {
        	FXMLLoader loader = new FXMLLoader(HomeController.class.getResource("view/ImpiegatoChiusuraI.fxml"));
        	
        	Scene loginimpiegatoscena = new Scene(loader.load());
        	stage.setScene(loginimpiegatoscena);
        	stage.setTitle("Chiusura contratto km illimitati");
        	stage.show();
        	
        	((Node)event.getSource()).getScene().getWindow().hide();
        	} catch (Exception e) {
        		System.out.println("Si e' verificato un errore imprevisto");
            	
        		e.printStackTrace();
        	}
    	}
    	else if(FrontController.tipochilometraggio.equalsIgnoreCase("l")){
    		
    		Stage stage = new Stage();
        	
        	try {
        	FXMLLoader loader = new FXMLLoader(HomeController.class.getResource("view/ImpiegatoChiusuraL.fxml"));
        	
        	Scene loginimpiegatoscena = new Scene(loader.load());
        	stage.setScene(loginimpiegatoscena);
        	stage.setTitle("Chiusura contratto pagamento a km");
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
        assert tabellaContratti != null : "fx:id=\"tabellaContratti\" was not injected: check your FXML file 'ImpiegatoHome.fxml'.";
        assert colonnaIdcontratto != null : "fx:id=\"colonnaIdcontratto\" was not injected: check your FXML file 'ImpiegatoHome.fxml'.";
        assert colonnaAgenziaconsegna != null : "fx:id=\"colonnaAgenziaconsegna\" was not injected: check your FXML file 'ImpiegatoHome.fxml'.";
        assert colonnaPrezzobase != null : "fx:id=\"colonnaAgenziaconsegna\" was not injected: check your FXML file 'ImpiegatoHome.fxml'.";
        assert colonnaPrezzofinale != null : "fx:id=\"colonnaPrezzofinale\" was not injected: check your FXML file 'ImpiegatoHome.fxml'.";
        assert colonnaAcconto != null : "fx:id=\"colonnaAcconto\" was not injected: check your FXML file 'ImpiegatoHome.fxml'.";
        assert colonnaTipokm != null : "fx:id=\"colonnaAcconto1\" was not injected: check your FXML file 'ImpiegatoHome.fxml'.";
        assert colonnaTarga != null : "fx:id=\"colonnaTarga\" was not injected: check your FXML file 'ImpiegatoHome.fxml'.";
        assert colonnaLimiterientro != null : "fx:id=\"colonnaTarga\" was not injected: check your FXML file 'ImpiegatoHome.fxml'.";
        assert colonnaInizionoleggio != null : "fx:id=\"colonnaTarga\" was not injected: check your FXML file 'ImpiegatoHome.fxml'.";
        
        assert btnChiudicontratto != null : "fx:id=\"btnChiudicontratto\" was not injected: check your FXML file 'ImpiegatoHome.fxml'.";

        
        //verranno stampati tutti i contratti che un certo dipendente 
        //sarà autorizzato a chiudere 
        //(potrà chiudere solo i contratti presso l'agenzia per cui lavora)
        
        tabellaContratti.setEditable(true);
        
        colonnaIdcontratto.setCellValueFactory(new PropertyValueFactory<Contratto,String>("idcontratto"));
	    colonnaIdcontratto.setMinWidth(50);
        
	    colonnaAgenziaconsegna.setCellValueFactory(new PropertyValueFactory<Contratto,String>("agenziaconsegna"));
	    colonnaAgenziaconsegna.setMinWidth(50);
	    
	    colonnaPrezzobase.setCellValueFactory(new PropertyValueFactory<Contratto,String>("tariffabase"));
	    colonnaPrezzobase.setMinWidth(50);
	            
	    colonnaAcconto.setCellValueFactory(new PropertyValueFactory<Contratto,String>("acconto"));
	    colonnaAcconto.setMinWidth(50);
	    
	    colonnaPrezzofinale.setCellValueFactory(new PropertyValueFactory<Contratto,String>("prezzototale"));
	    colonnaPrezzofinale.setMinWidth(50);
	    
	    colonnaTipokm.setCellValueFactory(new PropertyValueFactory<Contratto,String>("km_noleggio"));
	    colonnaTipokm.setMinWidth(50);
	    
	    colonnaTarga.setCellValueFactory(new PropertyValueFactory<Contratto,String>("targaauto"));
	    colonnaTarga.setMinWidth(50);
	    
	    colonnaLimiterientro.setCellValueFactory(new PropertyValueFactory<Contratto,String>("datalimite"));
	    colonnaLimiterientro.setMinWidth(50);
	    
	    colonnaInizionoleggio.setCellValueFactory(new PropertyValueFactory<Contratto,String>("datainizio"));
	    colonnaInizionoleggio.setMinWidth(50);
	    
	    Crud query = new Crud();
	    
	    try{
	    	ObservableList<Contratto> contrattidaDB = query.printContratti();
	    	
	    	colonnaIdcontratto.setCellValueFactory(cellData -> cellData.getValue().getIdcontratto());
	    	colonnaAgenziaconsegna.setCellValueFactory(cellData -> cellData.getValue().getAgenziaconsegna());
	    	colonnaPrezzobase.setCellValueFactory(cellData -> cellData.getValue().getTariffabase());
	    	colonnaAcconto.setCellValueFactory(cellData -> cellData.getValue().getAcconto());
	    	colonnaPrezzofinale.setCellValueFactory(cellData -> cellData.getValue().getPrezzototale());
	    	colonnaTipokm.setCellValueFactory(cellData -> cellData.getValue().getKm_noleggio());
	    	colonnaTarga.setCellValueFactory(cellData -> cellData.getValue().getTargaauto());
	    	colonnaLimiterientro.setCellValueFactory(cellData -> cellData.getValue().getDatalimite());
	    	colonnaInizionoleggio.setCellValueFactory(cellData -> cellData.getValue().getDatainizio());
	    	
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
        
    }
}
