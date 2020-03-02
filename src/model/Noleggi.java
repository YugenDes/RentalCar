package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the noleggi database table.
 * 
 */
@Entity
@NamedQuery(name="Noleggi.findAll", query="SELECT n FROM Noleggi n")
public class Noleggi implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idNoleggio;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fineNoleggio;

	private float importoDovuto;

	@Temporal(TemporalType.TIMESTAMP)
	private Date inizioNoleggio;

	//bi-directional many-to-one association to Veicoli
	@ManyToOne
	@JoinColumn(name="targa")
	private Veicoli veicoli;

	//bi-directional many-to-one association to Utente
	@ManyToOne
	@JoinColumn(name="idUtente")
	private Utente utente;

	public Noleggi() {
	}

	public int getIdNoleggio() {
		return this.idNoleggio;
	}

	public void setIdNoleggio(int idNoleggio) {
		this.idNoleggio = idNoleggio;
	}

	public Date getFineNoleggio() {
		return this.fineNoleggio;
	}

	public void setFineNoleggio(Date fineNoleggio) {
		this.fineNoleggio = fineNoleggio;
	}

	public float getImportoDovuto() {
		return this.importoDovuto;
	}

	public void setImportoDovuto(float importoDovuto) {
		this.importoDovuto = importoDovuto;
	}

	public Date getInizioNoleggio() {
		return this.inizioNoleggio;
	}

	public void setInizioNoleggio(Date inizioNoleggio) {
		this.inizioNoleggio = inizioNoleggio;
	}

	public Veicoli getVeicoli() {
		return this.veicoli;
	}

	public void setVeicoli(Veicoli veicoli) {
		this.veicoli = veicoli;
	}

	public Utente getUtente() {
		return this.utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

}