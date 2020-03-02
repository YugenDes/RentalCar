package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the veicoli database table.
 * 
 */
@Entity
@NamedQuery(name="Veicoli.findAll", query="SELECT v FROM Veicoli v")
public class Veicoli implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String targa;

	private double capacitaBagagliaio;

	private String colore;

	private String marca;

	private String modello;

	private int nPosti;

	private boolean prenotata;

	//bi-directional many-to-one association to Noleggi
	@OneToMany(mappedBy="veicoli")
	private List<Noleggi> noleggis;

	//bi-directional many-to-one association to Categorie
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idCategoria")
	private Categorie categorie;

	public Veicoli() {
	}
	
	public Veicoli(String targa, Categorie categoria, String marca, String modello, int nPosti, double capacitaBagagliaio,
			String colore) {
		this.targa = targa;
		this.categorie = categoria;
		this.marca = marca;
		this.modello = modello;
		this.nPosti = nPosti;
		this.capacitaBagagliaio = capacitaBagagliaio;
		this.colore = colore;
	}


	public String getTarga() {
		return this.targa;
	}

	public void setTarga(String targa) {
		this.targa = targa;
	}

	public double getCapacitaBagagliaio() {
		return this.capacitaBagagliaio;
	}

	public void setCapacitaBagagliaio(double capacitaBagagliaio) {
		this.capacitaBagagliaio = capacitaBagagliaio;
	}

	public String getColore() {
		return this.colore;
	}

	public void setColore(String colore) {
		this.colore = colore;
	}

	public String getMarca() {
		return this.marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModello() {
		return this.modello;
	}

	public void setModello(String modello) {
		this.modello = modello;
	}

	public int getNPosti() {
		return this.nPosti;
	}

	public void setNPosti(int nPosti) {
		this.nPosti = nPosti;
	}

	public boolean getPrenotata() {
		return this.prenotata;
	}

	public void setPrenotata(boolean prenotata) {
		this.prenotata = prenotata;
	}

	public List<Noleggi> getNoleggis() {
		return this.noleggis;
	}

	public void setNoleggis(List<Noleggi> noleggis) {
		this.noleggis = noleggis;
	}

	public Noleggi addNoleggi(Noleggi noleggi) {
		getNoleggis().add(noleggi);
		noleggi.setVeicoli(this);

		return noleggi;
	}

	public Noleggi removeNoleggi(Noleggi noleggi) {
		getNoleggis().remove(noleggi);
		noleggi.setVeicoli(null);

		return noleggi;
	}

	public Categorie getCategorie() {
		return this.categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

}