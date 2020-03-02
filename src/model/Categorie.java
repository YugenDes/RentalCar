package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the categorie database table.
 * 
 */
@Entity
@NamedQuery(name="Categorie.findAll", query="SELECT c FROM Categorie c")
public class Categorie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idCategoria;

	private String nomeCategoria;

	private double tariffaGiornaliera;

	private double tariffaMensile;

	private double tariffaSettimanale;

	//bi-directional many-to-one association to Veicoli
	@OneToMany(mappedBy="categorie")
	private List<Veicoli> veicolis;

	public Categorie() {
	}
	
	public Categorie(String nomeCategoria, double tariffaGiornaliera, double tariffaSettimanale, double tariffaMensile) {
		this.nomeCategoria = nomeCategoria;
		this.tariffaGiornaliera = tariffaGiornaliera;
		this.tariffaMensile = tariffaMensile;
		this.tariffaSettimanale = tariffaSettimanale;
	}
	
	public int getIdCategoria() {
		return this.idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getNomeCategoria() {
		return this.nomeCategoria;
	}

	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}

	public double getTariffaGiornaliera() {
		return this.tariffaGiornaliera;
	}

	public void setTariffaGiornaliera(double tariffaGiornaliera) {
		this.tariffaGiornaliera = tariffaGiornaliera;
	}

	public double getTariffaMensile() {
		return this.tariffaMensile;
	}

	public void setTariffaMensile(double tariffaMensile) {
		this.tariffaMensile = tariffaMensile;
	}

	public double getTariffaSettimanale() {
		return this.tariffaSettimanale;
	}

	public void setTariffaSettimanale(double tariffaSettimanale) {
		this.tariffaSettimanale = tariffaSettimanale;
	}

	public List<Veicoli> getVeicolis() {
		return this.veicolis;
	}

	public void setVeicolis(List<Veicoli> veicolis) {
		this.veicolis = veicolis;
	}

	public Veicoli addVeicoli(Veicoli veicoli) {
		getVeicolis().add(veicoli);
		veicoli.setCategorie(this);

		return veicoli;
	}

	public Veicoli removeVeicoli(Veicoli veicoli) {
		getVeicolis().remove(veicoli);
		veicoli.setCategorie(null);

		return veicoli;
	}

}