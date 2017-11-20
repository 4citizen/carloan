package it.Carloan.controller.model;

import javafx.beans.property.SimpleStringProperty;

public class Auto {
	
	/*
	 * gli int verranno dichiarati come stringhe
	 * per poterli stampare e poi basterà un 
	 * parsetoInt poterli poi inserire nel DB
	 * */
	
	private final SimpleStringProperty targa;//char(7) NOT NULL; -> CHIAVE PRIMARIA.
	private final SimpleStringProperty modello; //varchar(45) NOT NULL;
	private final SimpleStringProperty km_percorsi; //decimal(8,2);
	private final SimpleStringProperty disponibile; // enum ('si','manutenzione ordinaria','manutenzione straordinaria','no');
	private final SimpleStringProperty fascia; // char;
	private final SimpleStringProperty agenzia; // int NOT NULL;
	private final SimpleStringProperty vetrielettrici; // tinyint(1);
	private final SimpleStringProperty clima; // tinyint(1);
	private final SimpleStringProperty decappottabile; // tinyint(1);	
	private final SimpleStringProperty cambioautomatico; // tinyint(1);
	private final SimpleStringProperty airbag; // tinyint(1);
	private final SimpleStringProperty antifurto; // tinyint(1);
	private final SimpleStringProperty autoradio; // tinyint(1);
	
	/*
	private final SimpleStringProperty porte; // tinyint(1);
	private final SimpleStringProperty alimentazione; // enum ('Diesel','Benzina','GPL','metano');
	private final SimpleStringProperty euro; // enum ('Euro 4','Euro 5','Euro 6','0 E');
	private final SimpleStringProperty trazione; // enum ('Integrale','Anteriore','Posteriore');
	private final SimpleStringProperty numposti; // enum ('2','4','5','6');	
    */
	
	
	public Auto(
			String ftarga, 
			String fmodello,
			String fkm_percorsi, 
			String fdisponibile,
			String ffascia, 
			String fagenzia,
			String fvetrielettrici,
			String fclima, 
			String fdecappottabile,
		    String fcambioautomatico, 
		    String fairbag,
		    String fantifurto,
			String fautoradio 
			)
			{
		super();
		this.targa = new SimpleStringProperty(ftarga);
		this.modello = new SimpleStringProperty(fmodello);
		this.km_percorsi = new SimpleStringProperty(fkm_percorsi);
		this.disponibile = new SimpleStringProperty(fdisponibile);
		this.fascia = new SimpleStringProperty(ffascia);
		this.agenzia = new SimpleStringProperty(fagenzia);
		this.vetrielettrici = new SimpleStringProperty(fvetrielettrici);
		this.clima = new SimpleStringProperty(fclima);
		this.decappottabile = new SimpleStringProperty(fdecappottabile);
		this.cambioautomatico = new SimpleStringProperty(fcambioautomatico);
		this.airbag = new SimpleStringProperty(fairbag);
		this.antifurto = new SimpleStringProperty(fantifurto);
		this.autoradio = new SimpleStringProperty(fautoradio);
		}


	public SimpleStringProperty getTarga() {
		return targa;
	}
    public void setTarga(String ftarga) {
		targa.set(ftarga);
	}

    
	public SimpleStringProperty getModello() {
		return modello;
	}
    public void setModello(String fmodello) {
		modello.set(fmodello);
	}

    
	public SimpleStringProperty getKm_percorsi() {
		return km_percorsi;
	}
    public void setKm_percorsi(String fkm_percorsi) {
		km_percorsi.set(fkm_percorsi);
	}

    
	public SimpleStringProperty getDisponibile() {
		return disponibile;
	}
	public void setDisponibile(String fdisponibile) {
		disponibile.set(fdisponibile);
	}

	
	public SimpleStringProperty getFascia() {
		return fascia;
	}
	public void setFascia(String ffascia) {
		fascia.set(ffascia);
	}

	
	public SimpleStringProperty getAgenzia() {
		return agenzia;
	}
	public void setAgenzia(String fagenzia) {
		agenzia.set(fagenzia);
	}

	public SimpleStringProperty getVetrielettrici() {
		return vetrielettrici;
	}
	public void setVetrielettrici(String fvetrielettrici) {
		vetrielettrici.set(fvetrielettrici);
	}

	
	public SimpleStringProperty getClima() {
		return clima;
	}
	public void setClima(String fclima) {
		clima.set(fclima);
	}

	
	public SimpleStringProperty getDecappottabile() {
		return decappottabile;
	}
	public void setDecappottabile(String fdecappottabile) {
		decappottabile.set(fdecappottabile);
	}


	public SimpleStringProperty getCambioautomatico() {
		return cambioautomatico;
	}
	public void setCambioautomatico(String fcambioautomatico) {
		cambioautomatico.set(fcambioautomatico);
	}


	public SimpleStringProperty getAirbag() {
		return airbag;
	}
	public void setAirbag(String fairbag) {
	    airbag.set(fairbag);
	}

	
	public SimpleStringProperty getAntifurto() {
		return antifurto;
	}
	public void setAntifurto(String fantifurto) {
		antifurto.set(fantifurto);
	}

	
	public SimpleStringProperty getAutoradio() {
		return autoradio;
	}
	public void setAutoradio(String fautoradio) {
		autoradio.set(fautoradio);
	}
	

	/*
		Targa char(7) NOT NULL; -> CHIAVE PRIMARIA.
		modello varchar(45) NOT NULL;
		km_percorsi decimal(8,2);
		disponibile enum ('si','manutenzione ordinaria','manutenzione straordinaria');
		fascia char;
		agenzia int NOT NULL;
		porte tinyint(1);
		vetrielettrici tinyint(1);
		clima tinyint(1);
		decappottabile tinyint(1);
		alimentazione enum ('Diesel','Benzina','GPL','metano');
		cambioautomatico tinyint(1);
		euro enum ('Euro 4','Euro 5','Euro 6','0 E');
		airbag tinyint(1);
		antifurto tinyint(1);
		autoradio tinyint(1);
		trazione enum ('Integrale','Anteriore','Posteriore');
		numposti enum ('2','4','5','6');
	*/

}
