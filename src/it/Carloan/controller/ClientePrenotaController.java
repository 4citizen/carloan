package it.Carloan.controller;


import it.Carloan.controller.FrontController.FrontController;
import it.Carloan.controller.model.Agenzia;
import it.Carloan.controller.model.Auto;
import it.Carloan.database.Crud;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.time.LocalDate;
import java.util.Locale;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;



public class ClientePrenotaController {

	
     private	LocalDate today = LocalDate.now();

	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    //creo un gruppo di radiobutton per la fascia
    //riferita al tipo di noleggio
    @FXML
    final ToggleGroup groupFascia = new ToggleGroup();
    
    //riferita al tipo di chilometraggio
    @FXML
    final ToggleGroup groupKm = new ToggleGroup();
    
    //al tipo di noleggio Giornaliero,Settimanale
    @FXML
    final ToggleGroup groupTipo = new ToggleGroup();

    @FXML
    private RadioButton chkA;

    @FXML
    private RadioButton chkB;

    @FXML
    private RadioButton chkC;
    
    @FXML
    private RadioButton radiobuttonKmI;   
    
    @FXML
    private RadioButton radiobuttonKmL;   
    
    @FXML
    private RadioButton radiobuttonNoleggioG;   
    
    @FXML
    private RadioButton radiobuttonNoleggioS;
    
    @FXML
    private ComboBox<String> btnAgenziaritiro;
    
    @FXML
    private ComboBox<String> btnAgenziaconsegna;
    
    @FXML
    private Hyperlink hylbackToHomeCliente;
    
    @FXML
    private DatePicker inizionoleggio ; 
    
    @FXML
    private DatePicker finenoleggio;
    
    @FXML
    private Button btnScegliAuto;
 
    
    private ObservableList<String> agenziaRitiro = FXCollections.observableArrayList();
    private ObservableList<String> agenziaConsegna = FXCollections.observableArrayList();

    
    @FXML
    void initialize() throws SQLException, IOException {
        assert chkA != null : "fx:id=\"chkA\" was not injected: check your FXML file 'ClientePrenota.fxml'.";
        assert chkB != null : "fx:id=\"chkB\" was not injected: check your FXML file 'ClientePrenota.fxml'.";
        assert chkC != null : "fx:id=\"chkC\" was not injected: check your FXML file 'ClientePrenota.fxml'.";
    	
       // inizializzo i datepicker
       // imposto che non possa essere selezionata una data 
       // antecedente a quella odierna
    	
       final Callback<DatePicker, DateCell> dayCellFactory = 
                new Callback<DatePicker, DateCell>() {
        	
                    @Override
                    public DateCell call(final DatePicker datePicker) {
                    	
                        return new DateCell() {
                        	
                            @Override
                            public void updateItem(LocalDate item, boolean empty) {
                            	
                                super.updateItem(item, empty);
                        //non mi permette di selezionare giorni antecedenti
                        //alla data odierna
                                if (item.isBefore(today)){
		/*&& 
	item.isAfter(inizionoleggio.getValue().plusDays(7)) */
                
                                        setDisable(true);
                                        setStyle("-fx-background-color: #ffc0cb;");
                                		}  
                            													}
                        						};
                    												}
            					};
            inizionoleggio.setDayCellFactory(dayCellFactory);
            
            
            final Callback<DatePicker, DateCell> dayCellFactory2 = 
                    new Callback<DatePicker, DateCell>() {
            	
                        @Override
                        public DateCell call(final DatePicker datePicker) {
                        	
                            return new DateCell() {
                            	
                                @Override
                                public void updateItem(LocalDate item, boolean empty) {
                                	
                                    super.updateItem(item, empty);
                            //non mi permette di selezionare giorni antecedenti
                            //alla data odierna
                                    if (item.isBefore(today)){
    		/*&& 
    	item.isAfter(inizionoleggio.getValue().plusDays(7)) */
                    
                                            setDisable(true);
                                            setStyle("-fx-background-color: #ffc0cb;");
                                    		}  
                                													}
                            						};
                        												}
                					};
                finenoleggio.setDayCellFactory(dayCellFactory2);
                 
            
        //creo un gruppo di radio button
        //imposto per convenzione il primo come selezionato
        chkA.setToggleGroup(groupFascia);
        chkA.setSelected(true);
        
        chkB.setToggleGroup(groupFascia);
        chkC.setToggleGroup(groupFascia);
        
        //analogamente per il tipo noleggio
        radiobuttonNoleggioG.setToggleGroup(groupTipo);
        radiobuttonNoleggioG.setSelected(true);
        
        radiobuttonNoleggioS.setToggleGroup(groupTipo);
        
        //anche per il tipo di chilometraggio
        radiobuttonKmI.setToggleGroup(groupKm);
        radiobuttonKmI.setSelected(true);
        
        radiobuttonKmL.setToggleGroup(groupKm);

        Crud query = new Crud();
        
    	try{
			
    		agenziaRitiro = query.searchAgenzia();
    		agenziaConsegna = query.searchAgenzia();
    		
    		
    		 btnAgenziaritiro.setItems(agenziaRitiro);
    		 btnAgenziaconsegna.setItems(agenziaConsegna);
    		 
    	} catch (Exception e) {
			System.out.println("Si e' verificato un errore imprevisto");
			e.printStackTrace();
			}

    	
    	
    }
    
    
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
    void scegliAuto(ActionEvent event) throws IOException{
    	
    	int errore = 1;
    	
    	while(errore!=0){
    	//setto i valori nel frontcontroller riferiti al tipo di noleggio
    	if (radiobuttonNoleggioG.isSelected()){
    		
    		FrontController.temponoleggio = "giornaliero";
    	}
    	else if (radiobuttonNoleggioS.isSelected()){
    		
    		FrontController.temponoleggio = "settimanale";
    	}
    	
    	//setto i valori riferiti alla fascia dell'auto selezionata
    	if (chkA.isSelected()){
    		
    		FrontController.fasciaauto = "A";
    		FrontController.tariffabase= 200;
    	}
    	else if (chkB.isSelected()){
    		
    		FrontController.fasciaauto = "B";
    		FrontController.tariffabase= 150;
    	}
    	else if (chkC.isSelected()){
    		
    		FrontController.fasciaauto = "C";
    		FrontController.tariffabase= 100;
    	}
    	
    	//setto i valori riferiti al tipo di chilometraggio
    	if (radiobuttonKmI.isSelected()){
    		
    		FrontController.tipochilometraggio = "illimitato";
    	}
    	else if (radiobuttonKmL.isSelected()){
    		
    		FrontController.tipochilometraggio = "limitato";
    	}
    	
    	int controllo=0;
    	if (btnAgenziaritiro.getSelectionModel().getSelectedIndex()==-1){
    		controllo=1;
    		 	}
    	else if (btnAgenziaconsegna.getSelectionModel().getSelectedIndex()==-1){
    		controllo=2;
	 	}
    	
    	if(controllo==1){
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("ERRORE");
    		alert.setHeaderText(null);
    		alert.setContentText("NON hai scelto l'agenzia di Ritiro!");
    		alert.showAndWait();
    		
    		controllo=0;
    		errore = 1;
    	}
    	else if(controllo==2){
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("ERRORE");
    		alert.setHeaderText(null);
    		alert.setContentText("NON hai scelto l'agenzia di Consegna!");
    		alert.showAndWait();
    		controllo=0;
    		errore = 1;
    	}
    	else{
    		FrontController.agenziaritiro = btnAgenziaritiro.getValue();
    		FrontController.agenziaconsegna = btnAgenziaconsegna.getValue();
    		//ora che ho i nomi delle agenzie 
    		//devo passare ai rispettivi ID
    		Crud query = new Crud();
            
        	try{
        		//String idagenziaritiro="";
        		FrontController.idagenziaritiro = query.danomeaid(FrontController.agenziaritiro);
        		//= Integer.parseInt(idagenziaritiro);
        		
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
        		//String idagenziaconsegna="";
        		FrontController.idagenziaconsegna= query2.danomeaid(FrontController.agenziaconsegna);
        		// = Integer.parseInt(idagenziaconsegna);
        		
        	}catch (SQLException e) {
				System.out.println("Codice Errore: "+e.getErrorCode()+"\nMessaggio Errore: "+e.getMessage());
				e.printStackTrace();
			} catch (IOException e) {
								e.printStackTrace();
								}  catch (Exception e) {
											System.out.println("Si e' verificato un errore imprevisto");
											e.printStackTrace();
														}
    		
    		
    		//System.out.print("\n\nAgenzia di ritiro scelta : "+FrontController.agenziaritiro);
    		//System.out.print("\nAgenzia di consegna scelta : "+FrontController.agenziaritiro);
    	}
   
    	//verifico che siano state
    	//inserite entrambe le date
    	
    	LocalDate datainizio = inizionoleggio.getValue();
    	String datainiziopresa = datainizio.toString();
    	
    	LocalDate datafine = finenoleggio.getValue();
    	String datafinepresa = datafine.toString();
    	
    	if (inizionoleggio.getValue()==null){
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("ERRORE");
    		alert.setHeaderText(null);
    		alert.setContentText("Inserire Data di Inizio!");
    		alert.showAndWait();
			 
			 errore = 1;
		}
		
    	else if (finenoleggio.getValue()==null){
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("ERRORE");
    		alert.setHeaderText(null);
    		alert.setContentText("Inserire Data di Fine Noleggio!");
    		alert.showAndWait();
			 errore = 1;
		}
    	
    	//arrivati qui siamo sicuri che l'utente ha selezionato
    	//entrambe le date
    	//verifico ora se la data rientro è stata selezionata
    	//in maniera corretta in base al tipo di noleggio (giornaliero o settimanale)
    	else if (FrontController.temponoleggio.equals("giornaliero")){
			//siamo sicuri che la data di inizio sia stata scelta
    		//e sappiamo che il cliente vuole un tipo
    		//di noleggio giornaliero
    		Period numGiorni = Period.between(datainizio,datafine);
    		int giorni=numGiorni.getDays();
    		
    		if (giorni!=1){
    			 Alert alert = new Alert(AlertType.ERROR);
    	    	 alert.setTitle("ERRORE");
    	    	 alert.setHeaderText(null);
    	    	 alert.setContentText("Inserimento Data Rientro:\nHai scelto un tipo di noleggio GIORNALIERO, pertanto\nla data di rientro deve essere il giorno successivo al: "+datainizio);
    	    	 alert.showAndWait();
    			 errore = 1;
    		}
    		else{
    			 errore = 0;
    				FrontController.datainizio = datainiziopresa;
    				FrontController.datalimite = datafinepresa;
    			 }
    	}
    		
    	else if (FrontController.temponoleggio.equals("settimanale")){
    			//siamo sicuri che la data di inizio sia stata scelta
        		//e sappiamo che il cliente vuole un tipo
        		//di noleggio settimanale
        		Period numGiorni2 = Period.between(datainizio,datafine);
        		int giorni2=numGiorni2.getDays();
        		
        		if (giorni2!=7){
        			 Alert alert = new Alert(AlertType.ERROR);
        	    	 alert.setTitle("ERRORE");
        	    	 alert.setHeaderText(null);
        	    	 alert.setContentText("Inserimento Data Rientro:\nHai scelto un tipo di noleggio SETTIMANALE, pertanto\nla data di rientro deve essere selezionata\n7 giorni dopo la data di inizio noleggio: "+datainizio);
        	    	 alert.showAndWait();
        			 errore = 1;
        		}
        		else {
        			 errore = 0;
        		FrontController.datainizio = datainiziopresa;
        		FrontController.datalimite = datafinepresa;
        				}
																}
    			if ( errore ==0){
    			System.out.print("\nTutti i campi sono stati compilati in maniera corretta.\n");
    			System.out.print("\nAgenzia di ritiro scelta : "+FrontController.agenziaritiro);
    			System.out.print("\nAgenzia di consegna scelta : "+FrontController.agenziaconsegna);
    			System.out.print("\nFascia auto scelta : "+FrontController.fasciaauto);
    			System.out.print("\nemail cliente: "+FrontController.mailUtente);
    			System.out.print("\nTariffa base : "+FrontController.tariffabase);
    			System.out.print("\nTempo noleggio : "+FrontController.temponoleggio);
    			System.out.print("\nTipo chilometraggio : "+FrontController.tipochilometraggio);
    			System.out.print("\nData inizio noleggio  : "+FrontController.datainizio);
    			System.out.print("\nData Limite rientro : "+FrontController.datalimite);
    			
    			System.out.print("\n\nID Agenzia ritiro : "+FrontController.idagenziaritiro);
    			System.out.print("\n\nID agenzia Consegna : "+FrontController.idagenziaconsegna);
    			
    			Alert alert = new Alert(AlertType.INFORMATION);
    			alert.setTitle("");
    			alert.setHeaderText(null);
    			alert.setContentText("Congratulazioni. Tutti i dati sono stati acquisiti.\nOra seleziona un'auto...");
    			alert.showAndWait();
    			 
    			Stage stage = new Stage();
        	  	
    	    	try {
    		    	FXMLLoader loader = new FXMLLoader(HomeController.class.getResource("view/ClientePrenota2.fxml"));
    		    	
    		    	Scene homescena = new Scene(loader.load());
    		    	stage.setScene(homescena);
    		    	
    		    	stage.show();
    		    	
    		    	((Node)event.getSource()).getScene().getWindow().hide();
    	    	} catch (Exception e) {
    	    		System.out.println("Si e' verificato un errore imprevisto");
    	        	e.printStackTrace();
    	    	}
    			
    			}
    			else {
    				System.out.print("\nC'è stato qualche errore nella compilazione dei dati del contratto");
    				errore = 0;
    					}
    	};
    	
    } //parentesi fine scegliAuto
    
    
}//parentesi fine classe controller
