package it.Carloan.controller.model;


import javafx.beans.property.SimpleStringProperty;

public class Contratto {
	
	private final SimpleStringProperty idcontratto;
	
	private final SimpleStringProperty agenziaritiro;
	private final SimpleStringProperty agenziaconsegna;
	private final SimpleStringProperty tariffabase ; // DB: Decimal (6,2)
	private final SimpleStringProperty acconto; // DB: Decimal (6,2)
	private final SimpleStringProperty residuo; // DB: Decimal (6,2)
	private final SimpleStringProperty prezzototale; // DB: Decimal (6,2)
	private final SimpleStringProperty mora;
	private final SimpleStringProperty km_noleggio; // Enum: limitato, illimitato
	private final SimpleStringProperty mailcliente;
	private SimpleStringProperty tempo_noleggio; // Enum: giornaliero, settimanale
	private final SimpleStringProperty datainizio;
	private final SimpleStringProperty datalimite;
	private final SimpleStringProperty datarientro;
	private final SimpleStringProperty chiuso; // *** boolean --> int
	private final SimpleStringProperty targaauto; // DB: Char(7)

	/*
		idcontratto int NOT NULL; ---> CHIAVE PRIMARIA
		agenziaritiro int NOT NULL;
		agenziaconsegna int NOT NULL;
		tariffabase decimal(6,2) NOT NULL;
		acconto decimal(6,2) NOT NULL;
		residuo decimal(6,2);
		prezzototale decimal(6,2) NOT NULL;
		mora int NOT NULL DEFAULT '50';
		km_noleggio enum ('limitato','illimitato');
		mailcliente varchar(35) NOT NULL;
		tempo_noleggio enum ('giornaliero','settimanale');
		datainizio date;
		datalimite date;
		datarientro date;
		chiuso tinyint(1);
		targaauto char(7) NOT NULL;
		
	*/
	
	//questo costruttore mi serve per poter inserire all'interno del 
	//DB un nuovo contratto
	public Contratto (
			String cidcontratto,
			String idagenziaritiro,
			String idagenziaconsegna,
			String ctargaauto,
			String ctariffabase,
			String cacconto,
			String cresiduo,
			String cprezzototale,
			String cmora,
			String ckm_noleggio,
			String cmailcliente,
			String ctempo_noleggio,
			String cdatainizio,
			String cdatalimite,
			String cdatarientro,
			String cchiuso){
		    
		this.idcontratto = new SimpleStringProperty (cidcontratto);
		this.agenziaritiro = new SimpleStringProperty(idagenziaritiro);
		this.agenziaconsegna = new SimpleStringProperty(idagenziaconsegna);
		this.tariffabase = new SimpleStringProperty(ctariffabase);
		this.acconto = new SimpleStringProperty(cacconto);
		this.residuo = new SimpleStringProperty(cresiduo);
		this.prezzototale = new SimpleStringProperty(cprezzototale);
		this.mora =new SimpleStringProperty (cmora);
		this.km_noleggio = new SimpleStringProperty(ckm_noleggio);
		this.mailcliente = new SimpleStringProperty(cmailcliente);
		this.tempo_noleggio = new SimpleStringProperty(ctempo_noleggio);
		this.datainizio = new SimpleStringProperty(cdatainizio);
		this.datalimite = new SimpleStringProperty(cdatalimite);
		this.datarientro = new SimpleStringProperty(cdatarientro);
		this.chiuso = new SimpleStringProperty(cchiuso);
		this.targaauto = new SimpleStringProperty(ctargaauto);

	}
	


//con questo costruttore inizializzo i dati che mi serviranno per 
	//poi chiudere un contratto
	public Contratto(String id, String consegna,String tariffabaset, String accontoc,
			String prezzot,String morat,String residuot, String chilonoleggio, String targa,
			String datalimitet,String datainiziot) {
		
		this.idcontratto = new SimpleStringProperty(id);
		this.agenziaconsegna = new SimpleStringProperty(consegna);
		this.tariffabase = new SimpleStringProperty(tariffabaset);
		this.acconto = new SimpleStringProperty(accontoc);
		this.prezzototale = new SimpleStringProperty(prezzot);
		this.mora = new SimpleStringProperty (morat);
		this.residuo = new SimpleStringProperty(residuot);
		this.km_noleggio = new SimpleStringProperty(chilonoleggio);
		this.targaauto = new SimpleStringProperty(targa);
		this.datalimite = new SimpleStringProperty(datalimitet);
		this.datainizio = new SimpleStringProperty(datainiziot);
		
		
		this.tempo_noleggio= new SimpleStringProperty("");
		this.agenziaritiro = new SimpleStringProperty("");
		
		this.mailcliente = new SimpleStringProperty("");
		this.tempo_noleggio = new SimpleStringProperty("");
		this.datarientro = new SimpleStringProperty("");
		this.chiuso = new SimpleStringProperty("");
		}




	public SimpleStringProperty getIdcontratto() {
		return idcontratto;
	}
	public void setIdcontratto(String cidcontratto) {
		idcontratto.set(cidcontratto);
	}
	
	
	public SimpleStringProperty getAgenziaritiro() {
		return agenziaritiro;
	}
	public void setAgenziaritiro(String cagenziaritiro) {
		agenziaritiro.set(cagenziaritiro);
	}
	
	public SimpleStringProperty getAgenziaconsegna() {
		return agenziaconsegna;
	}
	public void setAgenziaconsegna(String cagenziaconsegna) {
		agenziaconsegna.set(cagenziaconsegna);
	}
	
	
	public SimpleStringProperty getTargaauto() {
		return targaauto;
	}
	public void setTargaauto(String ctargaauto) {
		targaauto.set(ctargaauto);
	}
	
	
	public SimpleStringProperty getTariffabase() {
		return tariffabase;
	}
	public void setTariffabase(String ctariffabase) {
		tariffabase.set(ctariffabase);
	}
	
	
	public SimpleStringProperty getAcconto() {
		return acconto;
	}
	public void setAcconto(String cacconto) {
		acconto.set(cacconto);
	}
	
	
	public SimpleStringProperty getResiduo() {
		return residuo;
	}
	public void setResiduo(String cresiduo) {
		residuo.set(cresiduo);
	}
	
	
	public SimpleStringProperty getPrezzototale() {
		return prezzototale;
	}
	public void setPrezzototale(String cprezzototale) {
		prezzototale.set(cprezzototale);
	}
	
	
	public SimpleStringProperty getMora() {
		return mora;
	}
	public void setMora(String cmora) {
		mora.set(cmora);
	}
	
	
	public SimpleStringProperty getKm_noleggio() {
		return km_noleggio;
	}
	public void setKm_noleggio(String ckm_noleggio) {
		km_noleggio.set(ckm_noleggio);
	}
	
	
	public SimpleStringProperty getMailcliente() {
		return mailcliente;
	}
	public void setMailcliente(String cmailcliente) {
		mailcliente.set(cmailcliente);
	}
	
	
	public SimpleStringProperty getTempo_noleggio() {
		return tempo_noleggio;
	}
	public void setTempo_noleggio(String ctempo_noleggio) {
		tempo_noleggio.set(ctempo_noleggio);
	}
	
	
	public SimpleStringProperty getDatainizio() {
		return datainizio;
	}
	public void setDatainizio(String cdatainizio) {
		datainizio.set(cdatainizio);
	}
	
	
	public SimpleStringProperty getDatalimite() {
		return datalimite;
	}
	public void setDatalimite(String cdatalimite) {
		datalimite.set(cdatalimite);
	}
	
	
	public SimpleStringProperty getDatarientro() {
		return datarientro;
	}
	public void setDatarientro(String cdatarientro) {
		datarientro.set(cdatarientro);
	}

	
	public SimpleStringProperty getChiuso() {
		return chiuso;
	}
	public void setChiuso(String cchiuso) {
		chiuso.set(cchiuso);
	}
	
	
}
