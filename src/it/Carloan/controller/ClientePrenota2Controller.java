package it.Carloan.controller;

import it.Carloan.controller.FrontController.FrontController;
import it.Carloan.controller.model.Auto;
import it.Carloan.controller.model.Cliente;
import it.Carloan.controller.model.Contratto;
import it.Carloan.database.Crud;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ClientePrenota2Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Auto> tabellaAuto;// = new TableView<Auto>();
    
    /*
     * ObservableList prende tutti i campi dal db 
     * e li imposta in modo tale da stamparli 
     * nelle eventuali colonne
     * */
    private static ObservableList<Auto> Autostampate = FXCollections.observableArrayList();

    @FXML
    TableColumn<Auto,String> colonnaTarga = new TableColumn<Auto,String>("targa");

    @FXML
    TableColumn<Auto,String> colonnaModello = new TableColumn<Auto,String>("modello");

    @FXML
    TableColumn<Auto,String> colonnaKmpercorsi = new TableColumn<Auto,String>("km_percorsi");
    
    @FXML
    TableColumn<Auto,String> colonnaDisponibile = new TableColumn<Auto,String>("disponibile");

    @FXML
    TableColumn<Auto,String> colonnaFascia = new TableColumn<Auto,String>("fascia");

    @FXML
    TableColumn<Auto,String> colonnaAgenzia = new TableColumn<Auto,String>("agenzia");

    @FXML
    TableColumn<Auto,String> colonnaVetrielettrici = new TableColumn<Auto,String>("vetrielettrici");

    @FXML
    TableColumn<Auto,String> colonnaClima = new TableColumn<Auto,String>("clima");

    @FXML
    TableColumn<Auto,String> colonnaDecappottabile = new TableColumn<Auto,String>("decappottabile");
    
    @FXML
    TableColumn<Auto,String> colonnaCambioautomatico = new TableColumn<Auto,String>("cambioautomtico");

    @FXML
    TableColumn<Auto,String> colonnaAirbag = new TableColumn<Auto,String>("airbag");
    
    @FXML
    TableColumn<Auto,String> colonnaAntifurto = new TableColumn<Auto,String>("antifurto");

    @FXML
    TableColumn<Auto,String> colonnaAutoradio = new TableColumn<Auto,String>("autoradio");

    
    @FXML
    private TextField textPrezzobase;
    
    @FXML
    private TextField textAcconto;
    
    @FXML
    private Button btnStipulaContratto;
    

    @FXML
    void initialize() {
        assert tabellaAuto != null : "fx:id=\"tabellaAuto\" was not injected: check your FXML file 'ClientePrenota2.fxml'.";
        
        assert colonnaTarga != null : "fx:id=\"colonnaTarga\" was not injected: check your FXML file 'ClientePrenota2.fxml'.";
        assert colonnaModello != null : "fx:id=\"colonnaModello\" was not injected: check your FXML file 'ClientePrenota2.fxml'.";
        assert colonnaKmpercorsi != null : "fx:id=\"colonnaKmpercorsi\" was not injected: check your FXML file 'ClientePrenota2.fxml'.";
        assert colonnaDisponibile != null : "fx:id=\"colonnaDisponibile\" was not injected: check your FXML file 'ClientePrenota2.fxml'.";
        assert colonnaFascia != null : "fx:id=\"colonnaFascia\" was not injected: check your FXML file 'ClientePrenota2.fxml'.";
        assert colonnaAgenzia != null : "fx:id=\"colonnaAgenzia\" was not injected: check your FXML file 'ClientePrenota2.fxml'.";
        assert colonnaVetrielettrici != null : "fx:id=\"colonnaAlimentazione\" was not injected: check your FXML file 'ClientePrenota2.fxml'.";
        assert colonnaClima != null : "fx:id=\"colonnaCambio\" was not injected: check your FXML file 'ClientePrenota2.fxml'.";
        assert colonnaDecappottabile != null : "fx:id=\"colonnaNumposti\" was not injected: check your FXML file 'ClientePrenota2.fxml'.";
        assert colonnaCambioautomatico != null : "fx:id=\"colonnaNumposti1\" was not injected: check your FXML file 'ClientePrenota2.fxml'.";
        assert colonnaAirbag != null : "fx:id=\"colonnaNumposti2\" was not injected: check your FXML file 'ClientePrenota2.fxml'.";
        assert colonnaAntifurto != null : "fx:id=\"colonnaNumposti3\" was not injected: check your FXML file 'ClientePrenota2.fxml'.";
        assert colonnaAutoradio != null : "fx:id=\"colonnaNumposti4\" was not injected: check your FXML file 'ClientePrenota2.fxml'.";

        
        tabellaAuto.setEditable(true);
        
        colonnaTarga.setCellValueFactory(new PropertyValueFactory<Auto,String>("targa"));
	    colonnaTarga.setMinWidth(50);
        
	    colonnaModello.setCellValueFactory(new PropertyValueFactory<Auto,String>("modello"));
	    colonnaModello.setMinWidth(50);
	    
	    colonnaKmpercorsi.setCellValueFactory(new PropertyValueFactory<Auto,String>("km_percorsi"));
	    colonnaKmpercorsi.setMinWidth(50);
	    
	    colonnaDisponibile.setCellValueFactory(new PropertyValueFactory<Auto,String>("disponibile"));
	    colonnaDisponibile.setMinWidth(50);
        
	    colonnaFascia.setCellValueFactory(new PropertyValueFactory<Auto,String>("fascia"));
	    colonnaFascia.setMinWidth(50);
	    
	    colonnaAgenzia.setCellValueFactory(new PropertyValueFactory<Auto,String>("agenzia"));
	    colonnaAgenzia.setMinWidth(50);
	    
	    colonnaVetrielettrici.setCellValueFactory(new PropertyValueFactory<Auto,String>("vetrielettrici"));
	    colonnaVetrielettrici.setMinWidth(50);
	    
	    colonnaClima.setCellValueFactory(new PropertyValueFactory<Auto,String>("clima"));
	    colonnaClima.setMinWidth(50);
	    
	    colonnaDecappottabile.setCellValueFactory(new PropertyValueFactory<Auto,String>("decappottabile"));
	    colonnaDecappottabile.setMinWidth(50);
	    
	    colonnaCambioautomatico.setCellValueFactory(new PropertyValueFactory<Auto,String>("cambioautomatico"));
	    colonnaCambioautomatico.setMinWidth(50);
	    
	    colonnaAirbag.setCellValueFactory(new PropertyValueFactory<Auto,String>("airbag"));
	    colonnaAirbag.setMinWidth(50);
	    
	    colonnaAntifurto.setCellValueFactory(new PropertyValueFactory<Auto,String>("antifurto"));
	    colonnaAntifurto.setMinWidth(50);
	    
	    
	    /*
	     * una volta settate tutte le possibili colonne in base ad una
	     * eventuale macchina di fascia 'A'
	     * posso effettuare la query in base alle informazioni che sono
	     * state salvate precedentemente in modo tale che 
	     * verranno riempite solo e soltanto le colonne
	     * in base alle caratteristiche di ciascuna auto
	     * (per semplicità si è ipotizzato che la Fascia 'A' includa
	     * tutte le caratteristiche di 'B' così come 'B' include quelle di 'C'
	    */
	    
	    Crud query = new Crud();
	    
	    try{
			
			ObservableList<Auto> autodaDB = query.searchAuto();
	    
			colonnaTarga.setCellValueFactory(cellData -> cellData.getValue().getTarga());
			colonnaModello.setCellValueFactory(cellData -> cellData.getValue().getModello());
			colonnaKmpercorsi.setCellValueFactory(cellData -> cellData.getValue().getKm_percorsi());
			colonnaDisponibile.setCellValueFactory(cellData -> cellData.getValue().getDisponibile());
			colonnaFascia.setCellValueFactory(cellData -> cellData.getValue().getFascia());
			colonnaAgenzia.setCellValueFactory(cellData -> cellData.getValue().getAgenzia());
			colonnaVetrielettrici.setCellValueFactory(cellData -> cellData.getValue().getVetrielettrici());
			colonnaClima.setCellValueFactory(cellData -> cellData.getValue().getClima());
			colonnaDecappottabile.setCellValueFactory(cellData -> cellData.getValue().getDecappottabile());
			colonnaCambioautomatico.setCellValueFactory(cellData -> cellData.getValue().getCambioautomatico());
			colonnaAirbag.setCellValueFactory(cellData -> cellData.getValue().getAirbag());
			colonnaAntifurto.setCellValueFactory(cellData -> cellData.getValue().getAntifurto());
			colonnaAutoradio.setCellValueFactory(cellData -> cellData.getValue().getAutoradio());
			
			
			 tabellaAuto.setItems(autodaDB);
			 
	    }catch (SQLException e) {
				System.out.println("Codice Errore: "+e.getErrorCode()+"\nMessaggio Errore: "+e.getMessage());
				e.printStackTrace();
			} catch (IOException e) {
								e.printStackTrace();
								}  catch (Exception e) {
											System.out.println("Si e' verificato un errore imprevisto");
											e.printStackTrace();
														}
	    
	   
	    /*
	     * fascia A ------ 200 euro
	     * fascia B ------ 150 euro
	     * Fascia C ------ 100 euro
	     * 30 euro al giorno per fascia A
	     * 25 euro al giorno per fascia B
	     * 20 euro al giorno per fascia C
	     */
	    //imposto il valore di prezzo base che non potrà essere editato dall'utente
	     int prezzobase;
	     if(FrontController.temponoleggio.equalsIgnoreCase("settimanale")){

	    	if(FrontController.fasciaauto.equalsIgnoreCase("A")){
		    	prezzobase = FrontController.tariffabase + (30*7);
			    FrontController.prezzofinale = prezzobase;
		    	textPrezzobase.setText(String.valueOf(prezzobase));
		    											}
	    	else if(FrontController.fasciaauto.equalsIgnoreCase("B")){
	    		prezzobase = FrontController.tariffabase + (25*7);
	    		FrontController.prezzofinale = prezzobase;
	    		textPrezzobase.setText(String.valueOf(prezzobase));
			}
	    	else if(FrontController.fasciaauto.equalsIgnoreCase("C")){
	    		prezzobase = FrontController.tariffabase + (20*7);
	    		FrontController.prezzofinale = prezzobase;
	    		textPrezzobase.setText(String.valueOf(prezzobase));
			}
	    }
	    else if(FrontController.temponoleggio.equalsIgnoreCase("giornaliero")){

	    	if(FrontController.fasciaauto.equalsIgnoreCase("A")){
	    		prezzobase = FrontController.tariffabase + (30);
	    		FrontController.prezzofinale = prezzobase;
		    	textPrezzobase.setText(String.valueOf(prezzobase));
		    											}
	    	else if(FrontController.fasciaauto.equalsIgnoreCase("B")){
	    		prezzobase = FrontController.tariffabase + (25);
	    		FrontController.prezzofinale = prezzobase;
	    		textPrezzobase.setText(String.valueOf(prezzobase));
			}
	    	else if(FrontController.fasciaauto.equalsIgnoreCase("C")){
	    		prezzobase = FrontController.tariffabase + (20);
	    		FrontController.prezzofinale = prezzobase;
	    		textPrezzobase.setText(String.valueOf(prezzobase));
			}
	    }
			

	    
	    
    }
    
    
    
    @FXML
    void stipulaContratto(ActionEvent event) throws SQLException, IOException {
    	
    	int errore=0;
    	
    	//prendo il valore di tipo StringProperty dalla tabella
    	Auto targa = tabellaAuto.getSelectionModel().getSelectedItem();
    	//Auto disponibile = tabellaAuto.getSelectionModel().getSelectedItem();
    	
    	//il valore di tipo StringPrpperty lo passo come stringa
    	//per poi andarmi a prendere la sottostringa desiderata
    	//perchè all'interno del DB ciascuna targa è di 7 elementi
    	String provatarga = targa.getTarga().toString();
    	
    	//salvo tutto all'interno del frontcontroller.targaauto
    	FrontController.targaauto= provatarga.substring(FrontController.sottostringafissa,30);
    	
    	//salvo tutto all'interno del frontcontroller.disponibilitaauto
    	//FrontController.disponibilitaauto= disponibilitaauto.substring(23,25);

        	//System.out.println(FrontController.targaauto); 
        	//System.out.println("\n\nDisponibilita auto : "+FrontController.disponibilitaauto); 
        	provatarga = "";
        	
            if(textAcconto.getText().equals("")){
        		Alert alert = new Alert(AlertType.ERROR);
        		alert.setTitle("ERRORE");
        		alert.setHeaderText(null);
        		alert.setContentText("Il campo Acconto NON può essere vuoto!");
        		alert.showAndWait();
        		errore =1;
        		}
        	else {
        		  provatarga = textAcconto.getText();
        		  FrontController.acconto = Integer.parseInt(provatarga);
        		  //verifico che l'acconto inserito sia > 0 
        		  //e anche < del 60% del prezzo base totale 
        		  if(FrontController.acconto<=0){
        			  Alert alert = new Alert(AlertType.ERROR);
              		  alert.setTitle("ERRORE");
              	 	  alert.setHeaderText(null);
              		  alert.setContentText("Il campo Acconto NON può contenere valori negativi o nulli!");
              		  alert.showAndWait();
        			  errore = 1;
        		  }
        		  else if(FrontController.acconto<10 || FrontController.acconto>=100){
        			  Alert alert = new Alert(AlertType.ERROR);
              		  alert.setTitle("ERRORE");
              	 	  alert.setHeaderText(null);
              		  alert.setContentText("L'importo dell'acconto deve essere di almeno 10€ e al massimo di 99€");
              		  alert.showAndWait();
        			  errore = 1;
        		  }
        		  
        		  if(errore==0){
        		  try{
        		  //inserire il ritorno alla home con qualche conferma di avvenuta 
        		  //stipula del contratto
        		  Crud query = new Crud();
        				   
        		/*  String tariffabase="";
        		  tariffabase = String.valueOf(FrontController.tariffabase);
        		  
        		  String acconto="";
        		  acconto = String.valueOf(FrontController.acconto);
        		  
        		  FrontController.datarientro="";
        		  FrontController.mora="50";
        		  
        		  String prezzofinale="";
        		  prezzofinale = String.valueOf(FrontController.prezzofinale);
        		  
        		  Contratto nuovo = new Contratto(
        				  FrontController.idcontrattounico,FrontController.agenziaritiro,
        				  FrontController.agenziaconsegna,FrontController.targaauto,tariffabase,acconto,
        				  FrontController.residuo,prezzofinale,FrontController.mora,FrontController.tipochilometraggio,
        				  FrontController.mailUtente,FrontController.temponoleggio,FrontController.datainizio,
        				  FrontController.datalimite,FrontController.datarientro,FrontController.chiuso
        				  );
        		  */
        		  
        		  //incremento l'ID del contratto per un contratto successivo
          		   FrontController.incrementaid();
          		   
        		 //UUID.randomUUID().toString();
        		   int IDdapassare;
        		   IDdapassare =FrontController.nextId();
        		 
        		   FrontController.chiuso="0";
        		  query.insertContratto(IDdapassare);
        		  
        		  System.out.println("\n\n Contratto Creato!\n");
        		  
        		  if(IDdapassare<=9){
        	       	  //ci sono meno di 10 contratti nel DB
        	       	 System.out.println("\nci sono meno di 10 contratti nel DB\n");
        	       	 }
        	        else if(IDdapassare<=99){
        	       	 	FrontController.sottostringacontratto=25; 
        	        					}
        	        else if(IDdapassare<=999){
        	       	 	FrontController.sottostringacontratto=26; 
        	        					}
        		  
        		  
      			} catch (SQLException e) {
      						System.out.println("Codice Errore: "+e.getErrorCode()+"\nMessaggio Errore: "+e.getMessage());
      									}
        		  try{
        			  Crud query = new Crud();
        			  FrontController.disponibilitaNO = "no";
        			  query.updateAuto(FrontController.targaauto,FrontController.disponibilitaNO);
            		  System.out.println("\n\n Aggiornamento disponibilità auto effettuato !\n");
            		  
        		    }catch (SQLException e) {
  						System.out.println("Codice Errore: "+e.getErrorCode()+"\nMessaggio Errore: "+e.getMessage());
						}
        		  
        		  Alert alert = new Alert(AlertType.INFORMATION);
        		  alert.setTitle("");
        		  alert.setHeaderText(null);
        		  alert.setContentText("Congratulazioni, hai registrato un nuovo contratto.\nClicca per andare alla Home.");
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

        	}//parentesi if errore
        	}//parentesi else

            	//System.out.println("Si e' verificato un errore imprevisto");
            
            }

}
        

