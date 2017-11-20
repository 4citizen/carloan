package it.Carloan.controller.model;

import javafx.beans.property.SimpleStringProperty;

public class Agenzia {
	
	private SimpleStringProperty nome;
	private SimpleStringProperty idagenzia;
	private SimpleStringProperty indirizzo;
	
	public Agenzia(String aidagenzia, String anome, String aindirizzo){
		
		this.nome = new SimpleStringProperty(anome);
		this.idagenzia = new SimpleStringProperty(aidagenzia);
		this.indirizzo = new SimpleStringProperty(aindirizzo);
	}

	
	public Agenzia(String anome,String aidagenzia) {
		
		this.nome = new SimpleStringProperty(anome);
		this.idagenzia = new SimpleStringProperty(aidagenzia);
		
	}


	public SimpleStringProperty getNome() {
		return nome;
	}
	public void setNome(String anome) {
		nome.set(anome);
	}
	

	public SimpleStringProperty getIdagenzia() {
		return idagenzia;
	}
	public void setidagenzia(String aidagenzia) {
		idagenzia.set(aidagenzia);
	}
	
	public SimpleStringProperty getIndirizzo() {
		return indirizzo;
	}
	public void setIndirizzo(String aindirizzo) {
		indirizzo.set(aindirizzo);
	}

	/*@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idagenzia;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Agenzia other = (Agenzia) obj;
		if (idagenzia != other.idagenzia)
			return false;
		return true;
	}
*/
	
}
