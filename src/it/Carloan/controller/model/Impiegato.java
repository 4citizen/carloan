package it.Carloan.controller.model;

public class Impiegato {

	private int id_impiegato;
	private String password;
	private int agenzia;
	
	public Impiegato(int id_impiegato, String password, int agenzia) {
		super();
		this.id_impiegato = id_impiegato;
		this.password = password;
		this.agenzia = agenzia;
	}

	public int getId_impiegato() {
		return id_impiegato;
	}

	public void setId_impiegato(int id_impiegato) {
		this.id_impiegato = id_impiegato;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAgenzia() {
		return agenzia;
	}

	public void setAgenzia(int agenzia) {
		this.agenzia = agenzia;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id_impiegato;
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
		Impiegato other = (Impiegato) obj;
		if (id_impiegato != other.id_impiegato)
			return false;
		return true;
	}
	
	
}
