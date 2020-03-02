package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

@Entity
@NamedQuery(name="Categorie.findAll", query="SELECT c FROM Categorie c")
public class Categorie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idCategoria;

	private String nomeCategoria;

	private float tariffaGiornaliera;

	private float tariffaMensile;

	private float tariffaSettimanale;

	//bi-directional many-to-one association to Veicoli
	@OneToMany(mappedBy="categorie", fetch=FetchType.EAGER)
	private List<Veicoli> veicolis;

	public Categorie() {
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

	public float getTariffaGiornaliera() {
		return this.tariffaGiornaliera;
	}

	public void setTariffaGiornaliera(float tariffaGiornaliera) {
		this.tariffaGiornaliera = tariffaGiornaliera;
	}

	public float getTariffaMensile() {
		return this.tariffaMensile;
	}

	public void setTariffaMensile(float tariffaMensile) {
		this.tariffaMensile = tariffaMensile;
	}

	public float getTariffaSettimanale() {
		return this.tariffaSettimanale;
	}

	public void setTariffaSettimanale(float tariffaSettimanale) {
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