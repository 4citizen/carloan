package it.Carloan.database;

import it.Carloan.controller.model.*;
import it.Carloan.controller.FrontController.FrontController;
import it.Carloan.database.MySQLConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicLong;
import java.sql.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import com.mysql.jdbc.PreparedStatement;



public class Crud {
	
	// PreparedStatement è più sicuro 
	java.sql.PreparedStatement query;
	private Connection conn;
	
	//prendo il nome di tutte le agenzie
	//tali nomi sono definiti in maniera unica (UNIQUE)....
	public ObservableList<String> searchAgenzia() throws SQLException,IOException  { 
    	
    	try {  
    	System.out.println("---- TEST searchAgenzia ----");
		MySQLConnection connessione = new MySQLConnection();
		    
		   this.conn= connessione.getDBConnection();
		   this.query = this.conn.prepareStatement("SELECT nome from agenzia order By nome;");
		    
			ResultSet agenzieList; //= stmt.executeQuery(Prova);

			agenzieList = this.query.executeQuery();
			
			ObservableList<String> AgenzieInDB = FXCollections.observableArrayList();

			while (agenzieList.next()) {
				//Agenzia agenzia = new Agenzia(); 
				
				String nome = agenzieList.getString("nome");
             	System.out.print("Nome Agenzia : "+nome+"\n");
             	
             	/*
				String idagenzia  = agenzieList.getString("idagenzia");
				System.out.print("ID Agenzia : "+idagenzia+"\n");
				
				String indirizzo  = agenzieList.getString("indirizzo");
				System.out.print("Indirizzo Agenzia : "+indirizzo+"\n");*/
				
				AgenzieInDB.add(nome);	
				 
			} 
			return AgenzieInDB;
    	} catch (SQLException sqle) {  
			throw new SQLException(sqle.getErrorCode()+":"+sqle.getMessage());  
		} finally { 
			if (this.query != null ) this.query.close();  
			if (conn != null ) conn.close();  
		}  
		
	}
	
	
public String prendiIdagenziaimpiegato() throws SQLException,IOException  { 
    	
    	try {  
    	System.out.println("---- Prendo l'id dell'agenzia in cui lavora il dipendente ---- \n");
		MySQLConnection connessione = new MySQLConnection();
		    
		   this.conn= connessione.getDBConnection();
		   this.query = this.conn.prepareStatement("SELECT agenzia from impiegato where idimpiegato = '"+FrontController.idimpiegato+"';");
		    
			ResultSet agenzieList; //= stmt.executeQuery(Prova);

			agenzieList = this.query.executeQuery();
			
			String Agenziaimpiegato = "";

			while (agenzieList.next()) {
				
				String id = agenzieList.getString("agenzia");
             	System.out.print("Id agenzia impiegato : "+id+"\n");
             	
             	Agenziaimpiegato = (id);	
				 
			} 
			return Agenziaimpiegato;
    	} catch (SQLException sqle) {  
			throw new SQLException(sqle.getErrorCode()+":"+sqle.getMessage());  
		} finally { 
			if (this.query != null ) this.query.close();  
			if (conn != null ) conn.close();  
		}  
		
	}
	
	
	public String prendiKmauto(String targa) throws SQLException,IOException  { 
		
		try {  
		System.out.println("\n---- Prendo i km percorsi da un auto ---- \n");
		MySQLConnection connessione = new MySQLConnection();
		    
		   this.conn= connessione.getDBConnection();
		   this.query = this.conn.prepareStatement("SELECT km_percorsi from auto where targa = '"+targa+"';");
		    
			ResultSet autoList; //= stmt.executeQuery(Prova);
	
			autoList = this.query.executeQuery();
			
			String kmpercorsidastampare = "";
	
			while (autoList.next()) {
				
				String kmpercorsi = autoList.getString("km_percorsi");
	         	System.out.print("km percorsi dall'auto : "+kmpercorsi+"\n");
	         	
	         	kmpercorsidastampare = (kmpercorsi);	
				 
			} 
			return kmpercorsidastampare;
		} catch (SQLException sqle) {  
			throw new SQLException(sqle.getErrorCode()+":"+sqle.getMessage());  
		} finally { 
			if (this.query != null ) this.query.close();  
			if (conn != null ) conn.close();  
		}  
		
	}



	public ObservableList<Contratto> printContratti() throws SQLException,IOException  { 
	
	try {  
	System.out.println("---- Stampo i contratti ----");
	MySQLConnection connessione = new MySQLConnection();
	    
	   this.conn= connessione.getDBConnection();
	   this.query = this.conn.prepareStatement("SELECT idcontratto,agenziaconsegna,tariffabase,prezzototale,mora,"
	   										 + "acconto,residuo,km_noleggio,targaauto,datalimite,datainizio from contratto "
	   										 + "where agenziaconsegna = '"+FrontController.agenziaimpiegato+"' AND"
	   										 	+ " chiuso=0;");
	    
		ResultSet contrattiList; //= stmt.executeQuery(Prova);

		contrattiList = this.query.executeQuery();
		
		ObservableList<Contratto> ContrattiInDB = FXCollections.observableArrayList();

		while (contrattiList.next()) {
			//Agenzia agenzia = new Agenzia(); 
			
			String id = contrattiList.getString("idcontratto");
         	System.out.print("ID contratto : "+id+"\n");
         	
         	String consegna = contrattiList.getString("agenziaconsegna");
         	System.out.print("Agenzia consegna  : "+consegna+"\n");
         	
         	String tariffabase = contrattiList.getString("tariffabase");
         	System.out.print("Tariffa base contratto  : "+tariffabase+"\n");
         	
         	String acconto = contrattiList.getString("acconto");
         	System.out.print("Acconto : "+acconto+"\n");
         	
         	String prezzot = contrattiList.getString("prezzototale");
         	System.out.print("Prezzo totale : "+prezzot+"\n");
         	
         	String morat = contrattiList.getString("mora");
         	System.out.print("Mora : "+morat+"\n");
         	
         	String residuot = contrattiList.getString("residuo");
         	System.out.print("residuo mancante : "+residuot+"\n");
         	
         	String chilonoleggio = contrattiList.getString("km_noleggio");
         	System.out.print("Tipo chilometraggio : "+chilonoleggio+"\n");
         	
         	String targa = contrattiList.getString("targaauto");
         	System.out.print("Targa auto : "+targa+"\n\n\n");
         	
         	String datalimite = contrattiList.getString("datalimite");
         	System.out.print("Data limite rientro  : "+datalimite+"\n\n\n");
         	
         	String datainizio = contrattiList.getString("datainizio");
         	System.out.print("Data inizio : "+datainizio+"\n\n\n");
         	
         	ContrattiInDB.add( new Contratto(id,consegna,tariffabase,acconto,prezzot,morat,residuot,chilonoleggio,targa,datalimite,datainizio));	
			 
		} 
		return ContrattiInDB;
	} catch (SQLException sqle) {  
		throw new SQLException(sqle.getErrorCode()+":"+sqle.getMessage());  
	} finally { 
		if (this.query != null ) this.query.close();  
		if (conn != null ) conn.close();  
	}  
	
	}
	
	
	
	public ObservableList<Contratto> printContratticliente(String emailcliente) throws SQLException,IOException  { 
		
		try {  
		System.out.println("---- Stampo i contratti ----");
		MySQLConnection connessione = new MySQLConnection();
		    
		   this.conn= connessione.getDBConnection();
		   this.query = this.conn.prepareStatement("SELECT idcontratto,prezzototale,mora,acconto,residuo,targaauto from contratto "
		   										 + "where mailcliente = '"+emailcliente+"' AND"
		   										 + " chiuso=1 AND residuo>0;");
		    
			ResultSet contrattiList; //= stmt.executeQuery(Prova);

			contrattiList = this.query.executeQuery();
			
			ObservableList<Contratto> ContrattiInDB = FXCollections.observableArrayList();

			while (contrattiList.next()) {
				//Agenzia agenzia = new Agenzia(); 
				
				String id = contrattiList.getString("idcontratto");
	         	System.out.print("ID contratto : "+id+"\n");
	         	
	         	String prezzot = contrattiList.getString("prezzototale");
	         	System.out.print("Prezzo totale : "+prezzot+"\n");
	         	
	         	String morat = contrattiList.getString("mora");
	         	System.out.print("Mora : "+morat+"\n");
	         	
	         	String consegna = "";
	         	
	         	String tariffabase ="";
	         	
	         	String acconto = contrattiList.getString("acconto");
	         	System.out.print("Acconto : "+acconto+"\n");

	         	String residuot = contrattiList.getString("residuo");
	         	System.out.print("residuo mancante : "+residuot+"\n");
	         	
	         	String chilonoleggio = "";
	         	
	         	String targa = contrattiList.getString("targaauto");
	         	System.out.print("Targa auto : "+targa+"\n\n\n");
	         	
	         	String datalimite = "";
	         	
	         	String datainizio = "";
	         	
	         	ContrattiInDB.add( new Contratto(id,consegna,tariffabase,acconto,prezzot,morat,residuot,chilonoleggio,targa,datalimite,datainizio));	
				 
			} 
			return ContrattiInDB;
		} catch (SQLException sqle) {  
			throw new SQLException(sqle.getErrorCode()+":"+sqle.getMessage());  
		} finally { 
			if (this.query != null ) this.query.close();  
			if (conn != null ) conn.close();  
		}  
		
		}
	
	
	public ObservableList<Contratto> printContrattiannulla (String emailcliente) throws SQLException,IOException  { 
		
		try {  
		System.out.println("---- Stampo i contratti ----");
		MySQLConnection connessione = new MySQLConnection();
		    
		   this.conn= connessione.getDBConnection();
		   this.query = this.conn.prepareStatement("SELECT idcontratto,prezzototale,acconto,residuo,targaauto,datainizio"
		   										 + " from contratto"
		   										 + " where mailcliente = '"+emailcliente+"' AND"
		   										 + " chiuso=0;");
		    
			ResultSet contrattiList; //= stmt.executeQuery(Prova);

			contrattiList = this.query.executeQuery();
			
			ObservableList<Contratto> ContrattiInDB = FXCollections.observableArrayList();

			while (contrattiList.next()) {
				//Agenzia agenzia = new Agenzia(); 
				
				String id = contrattiList.getString("idcontratto");
	         	System.out.print("ID contratto : "+id+"\n");
	         	
	         	String prezzot = contrattiList.getString("prezzototale");
	         	System.out.print("Prezzo totale : "+prezzot+"\n");
	         	
	         	String morat = "";
	         	
	         	String consegna = "";
	         	
	         	String tariffabase ="";
	         	
	         	String acconto = contrattiList.getString("acconto");
	         	System.out.print("Acconto : "+acconto+"\n");

	         	String residuot = "";
	         	
	         	String chilonoleggio = "";
	         	
	         	String targa = contrattiList.getString("targaauto");
	         	System.out.print("Targa auto : "+targa+"\n");
	         	
	         	String datalimite = "";
	         	
	         	String datainizio = contrattiList.getString("datainizio");
	         	System.out.print("Data inizio contratto : "+datainizio+"\n");
	         	
	         	ContrattiInDB.add( new Contratto(id,consegna,tariffabase,acconto,prezzot,morat,residuot,chilonoleggio,targa,datalimite,datainizio));	
				 
			} 
			return ContrattiInDB;
		} catch (SQLException sqle) {  
			throw new SQLException(sqle.getErrorCode()+":"+sqle.getMessage());  
		} finally { 
			if (this.query != null ) this.query.close();  
			if (conn != null ) conn.close();  
		}  
		
		}
	
	
	
	
	
	public String danomeaid(String nomeagenzia) throws SQLException,IOException  { 
    	
    	try {  
    	System.out.println("---- Dal nome di un'agenzia prendo l'ID ----");
		MySQLConnection connessione = new MySQLConnection();
		    
		   this.conn= connessione.getDBConnection();
		   this.query = this.conn.prepareStatement("SELECT idagenzia from agenzia where nome = '"+nomeagenzia+"';");
		    
			ResultSet agenzieList; //= stmt.executeQuery(Prova);

			agenzieList = this.query.executeQuery();
			
			String AgenzieInDB = "" ;

			while (agenzieList.next()) {
				//Agenzia agenzia = new Agenzia(); 
				
				String id = agenzieList.getString("idagenzia");
             	System.out.print("Id Agenzia : "+id+"\n");

				/*String idagenzia  = agenzieList.getString("idagenzia");
				System.out.print("ID Agenzia : "+idagenzia+"\n");
				
				String indirizzo  = agenzieList.getString("indirizzo");
				System.out.print("Indirizzo Agenzia : "+indirizzo+"\n");*/
				
				AgenzieInDB = id;	
				 
			} 
			return AgenzieInDB;
    	} catch (SQLException sqle) {  
			throw new SQLException(sqle.getErrorCode()+":"+sqle.getMessage());  
		} finally { 
			if (this.query != null ) this.query.close();  
			if (conn != null ) conn.close();  
		}  
		
	}
	
	
	

	public void insertCliente(Cliente nuovoCliente) throws SQLException {
		
		try {
			MySQLConnection nuovo_cliente = new MySQLConnection();
			
			this.conn=nuovo_cliente.getDBConnection();
			this.query = this.conn.prepareStatement("insert into cliente (mail,password,nome,cognome,telefono)"
					+ "values ('"+nuovoCliente.getMail()+"',"
					+ "'"+nuovoCliente.getPass()+"',"
					+ "'"+nuovoCliente.getNome()+"',"
					+ "'"+nuovoCliente.getCognome()+"',"
					+ "'"+nuovoCliente.getTelefono()+"');"
					);
			
			int risultato = this.query.executeUpdate();
			System.out.println("Risulato InsertCliente : "+risultato);
		} catch (SQLException e) {
			this.conn.rollback();
			System.out.println("Codice Errore: "+e.getErrorCode()+"\nMessaggio Errore: "+e.getMessage());
		} catch (Exception err) {
				this.conn.rollback();
				System.out.println("Errore Generico:\n");
				err.printStackTrace();
		} finally { 
			if (this.query != null ) this.query.close();  
			if (conn != null ) conn.close();  
		}
	}
	
	
public void insertContratto(long iDdapassare) throws SQLException {
		
		try {
			MySQLConnection nuovo_contratto = new MySQLConnection();
			
			this.conn=nuovo_contratto.getDBConnection();
			this.query = this.conn.prepareStatement("insert into contratto (idcontratto,agenziaritiro,agenziaconsegna,"
					+ "tariffabase,acconto,prezzototale,km_noleggio,mailcliente,tempo_noleggio,datainizio,datalimite,chiuso,targaauto)" 
					+ "values ('"+iDdapassare+"',"
					+ "'"+FrontController.idagenziaritiro+"',"
					+ "'"+FrontController.idagenziaconsegna+"',"
					+ "'"+FrontController.tariffabase+"',"
					+ "'"+FrontController.acconto+"',"
					+ "'"+FrontController.prezzofinale+"',"
					+ "'"+FrontController.tipochilometraggio+"',"
					+ "'"+FrontController.mailUtente+"',"
					+ "'"+FrontController.temponoleggio+"',"
					+ "'"+FrontController.datainizio+"',"
					+ "'"+FrontController.datalimite+"',"
					+ "'"+FrontController.chiuso+"',"
					+ "'"+FrontController.targaauto+"');"
					);
			
			int risultato = this.query.executeUpdate();
			System.out.println("Risulato InsertContratto : "+risultato);
		} catch (SQLException sqle) {  
			throw new SQLException("Errore : "+sqle.getErrorCode()+":"+sqle.getMessage());  
		} finally { 
			if (this.query != null ) this.query.close();  
			if (conn != null ) conn.close();  
		} 
	}
	
	
	
	
	public ObservableList<Auto> searchAuto() throws SQLException,IOException  { 
    	
    	try {  
    		
    	System.out.println("\n---- TEST searchAuto ----\n");
		MySQLConnection connessione = new MySQLConnection();
		this.conn= connessione.getDBConnection();
		   //prendo tutte le caratteristiche di un'auto
		//anche se sapremo a priori che qulora una macchina
		//non sia di fascia A avremo dei valori null in corrispondenza
		//di attributi che per le fasce B e C non saranno mai 
		//inizializzati
		this.query = this.conn.prepareStatement("SELECT * from auto where fascia = '"+FrontController.fasciaauto+"' AND "
				+ "agenzia = '"+FrontController.idagenziaritiro+"'"
						+ "and disponibile = 'si';");

			ResultSet autoList ;
			autoList = this.query.executeQuery();
			
			ObservableList<Auto> AutoInDB = FXCollections.observableArrayList();

			while (autoList.next()) {
				
				System.out.print("\n\nEcco gli attributi presi dal DB \n");
             	String targa = autoList.getString("targa");
             	System.out.print("Targa : "+targa+"\n");
             	
				String modello = autoList.getString("modello");
				System.out.print("Modello : "+modello+"\n");
				
			    String km_percorsi= autoList.getString("km_percorsi");
			    System.out.print("Km percorsi : "+km_percorsi+"\n");
			    
			    String disponibile = autoList.getString("disponibile");
			    System.out.print("Disponibile  : "+disponibile+"\n");
			    
				String fascia = autoList.getString("fascia");
				System.out.print("Fascia : "+fascia+"\n");
				
				String agenzia = autoList.getString("agenzia");
				System.out.print("Agenzia : "+agenzia+"\n");
				
				String vetrielettrici = autoList.getString("vetrielettrici");
				System.out.print("Vetrielettrici : "+vetrielettrici+"\n");
				
				String clima = autoList.getString("clima");
				System.out.print("Clima : "+clima+"\n");
				
				String decappottabile = autoList.getString("decappottabile");
				System.out.print("Decappottabile  : "+decappottabile+"\n");
				
				String cambioautomatico = autoList.getString("cambioautomatico");
				System.out.print("Cambioautomatico  : "+cambioautomatico+"\n");
				
				String airbag = autoList.getString("airbag");
				System.out.print("Airbag  : "+airbag+"\n");
				
				String antifurto = autoList.getString("antifurto");
				System.out.print("Antifurto  : "+antifurto+"\n");
				
				String autoradio = autoList.getString("autoradio");
				System.out.print("Autoradio  : "+autoradio+"\n");
				
				
				AutoInDB.add(new Auto (targa,modello,km_percorsi,disponibile,fascia,agenzia,vetrielettrici,clima,decappottabile,cambioautomatico,airbag,antifurto,autoradio));

			} 
			//restituisco una lista di auto che saranno
			//stampate all'interno della tabella
			return AutoInDB;
			
    	} catch (SQLException sqle) {  
			throw new SQLException(sqle.getErrorCode()+":"+sqle.getMessage());  
		} catch (Exception err) { 
			conn.rollback();
			System.out.println("GENERIC ERROR :Transaction is being rolled back");
			err.printStackTrace();
			throw new SQLException(err.getMessage()); 
		} 
    	finally { 
			if (this.query != null ) this.query.close();  
			if (conn != null ) conn.close();  
		}  
		
	}
	
	
	public int verificaLoginCliente(String mail, String pass) throws SQLException,IOException{
		int accesso=0;
		
		try {  
    		
	    	System.out.println("\n---- Login Cliente ----\n");
			MySQLConnection connessione = new MySQLConnection();
			
			this.conn= connessione.getDBConnection();
			this.query = this.conn.prepareStatement("select count(*) as ClienteTrovato "
					                              + "from cliente where mail ='" +mail+ "' and "
					                              + "password = '" +pass+ "';");

				ResultSet esitologin ;
				esitologin = this.query.executeQuery(); 
		
				while (esitologin.next()) {
					/*
					 * prendo cosa c'è dentro la cella della prima
					 * e unica colonna
					 * */
					accesso = esitologin.getInt(1);
				} 

	    	} catch (SQLException sqle) {  
				throw new SQLException(sqle.getErrorCode()+":"+sqle.getMessage());  
			} catch (Exception err) { 
				conn.rollback();
				System.out.println("GENERIC ERROR :Transaction is being rolled back");
				err.printStackTrace();
				throw new SQLException(err.getMessage()); 
			                        } 
	    	finally { 
				if (this.query != null ) this.query.close();  
				if (conn != null ) conn.close();  
			} 
		
		return accesso;
	}

	
	public void modCliente(Cliente modificato) throws SQLException,IOException {
		
		try {
			MySQLConnection clientedamod = new MySQLConnection();
			
			this.conn=clientedamod.getDBConnection();
			this.query = this.conn.prepareStatement("update cliente set "
					+ "password = '"+modificato.getPass()+"',"
					+ "nome = '"+modificato.getNome()+"',"
					+ "cognome = '"+modificato.getCognome()+"',"
					+ "telefono = '"+modificato.getTelefono()+"'"
					+ "where mail = '"+modificato.getMail()+"';");
			
			int risultato = this.query.executeUpdate();
			System.out.println("Risulato InsertCliente : "+risultato);
		} catch (SQLException sqle) {  
			throw new SQLException(sqle.getErrorCode()+":"+sqle.getMessage());  
		} catch (Exception err) { 
			conn.rollback();
			System.out.println("GENERIC ERROR :Transaction is being rolled back");
			err.printStackTrace();
			throw new SQLException(err.getMessage()); 
		                        } 
    	finally { 
			if (this.query != null ) this.query.close();  
			if (conn != null ) conn.close();  
		} 
	}
	
	
	
	public void chiudiContratto() throws SQLException,IOException {
		
		try {
			MySQLConnection contrattochiuso = new MySQLConnection();
			
			this.conn=contrattochiuso.getDBConnection();
			this.query = this.conn.prepareStatement("update contratto set "
					+ "datarientro = '"+FrontController.datarientro+"',"
					+ "residuo = '"+FrontController.residuo+"',"
					+ "mora = '"+FrontController.mora+"',"
					+ "prezzototale = '"+FrontController.prezzofinale+"',"
					+ "chiuso = '"+1+"'"
					+ " where idcontratto = '"+FrontController.idcontratto+"';");
			
			int risultato = this.query.executeUpdate();
			System.out.println("Risulato Chiusura Contratto  : "+risultato);
		} catch (SQLException sqle) {  
			throw new SQLException(sqle.getErrorCode()+":"+sqle.getMessage());  
		} catch (Exception err) { 
			conn.rollback();
			System.out.println("GENERIC ERROR :Transaction is being rolled back");
			err.printStackTrace();
			throw new SQLException(err.getMessage()); 
		                        } 
    	finally { 
			if (this.query != null ) this.query.close();  
			if (conn != null ) conn.close();  
		} 
	}
	
	
	
	public void chiudiContrattoannullato() throws SQLException,IOException {
		
		try {
			MySQLConnection contrattochiuso = new MySQLConnection();
			
			this.conn=contrattochiuso.getDBConnection();
			this.query = this.conn.prepareStatement("update contratto set "
					+ "residuo = '"+FrontController.residuo+"',"
					+ "mora = '"+FrontController.mora+"',"
					+ "chiuso = '"+1+"'"
					+ " where idcontratto = '"+FrontController.idcontratto+"';");
			
			int risultato = this.query.executeUpdate();
			System.out.println("Risulato Chiusura Contratto  : "+risultato);
		} catch (SQLException sqle) {  
			throw new SQLException(sqle.getErrorCode()+":"+sqle.getMessage());  
		} catch (Exception err) { 
			conn.rollback();
			System.out.println("GENERIC ERROR :Transaction is being rolled back");
			err.printStackTrace();
			throw new SQLException(err.getMessage()); 
		                        } 
    	finally { 
			if (this.query != null ) this.query.close();  
			if (conn != null ) conn.close();  
		} 
	}
	

	//prendo in ingresso la targa dell'auto che mi servirà
	//per effettuare l'aggiornamento della sua disponibilità
	//questo metodo sarà utilizzato sia in fase di compilazione
	//di un nuovo contratto sia quando verrà chiuso servirà 
	//per poter far visualizzare nuovamente l'auto in fase 
	//di una nuova prenotazione
	public void updateAuto (String targa,String stato) throws SQLException,IOException {
		
		try {
			MySQLConnection autodamod = new MySQLConnection();
			
			this.conn=autodamod.getDBConnection();
			this.query = this.conn.prepareStatement("update auto set "
					+ "disponibile = '"+stato+"'"
					+ "where targa = '"+targa+"';");
			
			int risultato = this.query.executeUpdate();
			System.out.println("Risulato updateAuto : "+risultato);
		} catch (SQLException sqle) {  
			throw new SQLException(sqle.getErrorCode()+":"+sqle.getMessage());  
		} catch (Exception err) { 
			conn.rollback();
			System.out.println("GENERIC ERROR :Transaction is being rolled back");
			err.printStackTrace();
			throw new SQLException(err.getMessage()); 
		                        } 
    	finally { 
			if (this.query != null ) this.query.close();  
			if (conn != null ) conn.close();  
		} 
		
		
	}



	public void updateAutoa (String targa) throws SQLException,IOException {
	
	try {
		MySQLConnection autodamod = new MySQLConnection();
		
		this.conn=autodamod.getDBConnection();
		this.query = this.conn.prepareStatement("update auto set "
				+ "agenzia = '"+FrontController.agenziaconsegna+"'"
				+ "where targa = '"+targa+"';");
		
		int risultato = this.query.executeUpdate();
		System.out.println("Risulato updateAuto : "+risultato);
	} catch (SQLException sqle) {  
		throw new SQLException(sqle.getErrorCode()+":"+sqle.getMessage());  
	} catch (Exception err) { 
		conn.rollback();
		System.out.println("GENERIC ERROR :Transaction is being rolled back");
		err.printStackTrace();
		throw new SQLException(err.getMessage()); 
	                        } 
	finally { 
		if (this.query != null ) this.query.close();  
		if (conn != null ) conn.close();  
			}	
	}
	
	
	public void updateContrattoc (String idcontratto) throws SQLException,IOException {
		
		try {
			MySQLConnection autodamod = new MySQLConnection();
			
			this.conn=autodamod.getDBConnection();
			this.query = this.conn.prepareStatement("update contratto set residuo = 0 "
					+ "where idcontratto = '"+idcontratto+"';");
			
			int risultato = this.query.executeUpdate();
			System.out.println("Risulato Chiusura definitiva contratto : "+risultato);
		} catch (SQLException sqle) {  
			throw new SQLException(sqle.getErrorCode()+":"+sqle.getMessage());  
		} catch (Exception err) { 
			conn.rollback();
			System.out.println("GENERIC ERROR :Transaction is being rolled back");
			err.printStackTrace();
			throw new SQLException(err.getMessage()); 
		                        } 
		finally { 
			if (this.query != null ) this.query.close();  
			if (conn != null ) conn.close();  
				}	
		}
	
	
	
	public void updateAutokm (String targa) throws SQLException,IOException {
		
		try {
			MySQLConnection autodamod = new MySQLConnection();
			
			this.conn=autodamod.getDBConnection();
			this.query = this.conn.prepareStatement("update auto set "
					+ "km_percorsi = '"+FrontController.kmpercorsi+"'"
					+ "where targa = '"+targa+"';");
			
			int risultato = this.query.executeUpdate();
			System.out.println("Risulato updateAuto : "+risultato);
		} catch (SQLException sqle) {  
			throw new SQLException(sqle.getErrorCode()+":"+sqle.getMessage());  
		} catch (Exception err) { 
			conn.rollback();
			System.out.println("GENERIC ERROR :Transaction is being rolled back");
			err.printStackTrace();
			throw new SQLException(err.getMessage()); 
		                        } 
		finally { 
			if (this.query != null ) this.query.close();  
			if (conn != null ) conn.close();  
		} 
		
		
	}

	
	public int verificaLoginImpiegato(String idaccesso, String pass) throws SQLException,IOException{
		int accesso=0;
		
		try {  
    		
	    	System.out.println("\n---- Login Impiegato ----\n");
			MySQLConnection connessione = new MySQLConnection();
			
			this.conn= connessione.getDBConnection();
			this.query = this.conn.prepareStatement("select count(*) as ImpiegatoTrovato "
					                              + "from impiegato where idimpiegato ='" +idaccesso+ "' and "
					                              + "password = '" +pass+ "';");

				ResultSet esitologin ;
				esitologin = this.query.executeQuery(); 
		
				while (esitologin.next()) {
					/*
					 * prendo cosa c'è dentro la cella della prima
					 * e unica colonna
					 * */
					accesso = esitologin.getInt(1);
				} 

	    	} catch (SQLException sqle) {  
				throw new SQLException(sqle.getErrorCode()+":"+sqle.getMessage());  
			} catch (Exception err) { 
				conn.rollback();
				System.out.println("GENERIC ERROR :Transaction is being rolled back");
				err.printStackTrace();
				throw new SQLException(err.getMessage()); 
			                        } 
	    	finally { 
				if (this.query != null ) this.query.close();  
				if (conn != null ) conn.close();  
			} 
		
		return accesso;
	}
	
	
	public int prendiidcontratto() throws SQLException,IOException{
		int maxidcontratto= 0;
		
		try {  
    		
	    	System.out.println("\n---- Login Cliente ----\n");
			MySQLConnection connessione = new MySQLConnection();
			
			this.conn= connessione.getDBConnection();
			this.query = this.conn.prepareStatement("select max(idcontratto) as 'idmax' from contratto;");

				ResultSet esitologin ;
				esitologin = this.query.executeQuery(); 

				
				while (esitologin.next()) {
					/*
					 * prendo cosa c'è dentro la cella della prima
					 * e unica colonna
					 * */
					maxidcontratto = esitologin.getInt(1);
				} 

				
	    	} catch (SQLException sqle) {  
				throw new SQLException(sqle.getErrorCode()+":"+sqle.getMessage());  
			} catch (Exception err) { 
				conn.rollback();
				System.out.println("GENERIC ERROR :Transaction is being rolled back");
				err.printStackTrace();
				throw new SQLException(err.getMessage()); 
			                        } 
	    	finally { 
				if (this.query != null ) this.query.close();  
				if (conn != null ) conn.close();  
			} 
		
		return maxidcontratto;
	}
	
	
	
	
	
}

