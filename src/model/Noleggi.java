package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

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

	private double importoDovuto;

	@Temporal(TemporalType.TIMESTAMP)
	private Date inizioNoleggio;

	//bi-directional many-to-one association to Veicoli
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="targa")
	private Veicoli veicoli;

	//bi-directional many-to-one association to Utente
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idUtente")
	private Utente utente;

	public Noleggi() {
	}
	
	public Noleggi(Veicoli veicoli, Date dataInizio, Utente utente) {
		this.veicoli = veicoli;
		this.inizioNoleggio = dataInizio;
		this.utente = utente;
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

	public double getImportoDovuto() {
		return this.importoDovuto;
	}

	public void setImportoDovuto(double importoDovuto) {
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
	
	public static double calcolaImportoDovuto(LocalDate inizio , LocalDate fine , Categorie c) {
		double importo = 0;
		Period p = Period.between(inizio, fine);
		importo = (p.getMonths()*c.getTariffaMensile()) + (((int)p.getDays()/7)*c.getTariffaMensile()) + (p.getDays()%7*c.getTariffaGiornaliera());
		return importo;
	}

	@Override
	public String toString() {
		return "Noleggi [idNoleggio=" + idNoleggio + ", fineNoleggio=" + fineNoleggio + ", importoDovuto="
				+ importoDovuto + ", inizioNoleggio=" + inizioNoleggio + ", veicoli=" + veicoli + ", utente=" + utente
				+ "]";
	}

	
	
}