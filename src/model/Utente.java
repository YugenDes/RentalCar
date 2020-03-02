package model;

import java.io.Serializable;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the utente database table.
 * 
 */
@Entity
@NamedQuery(name = "Utente.findAll", query = "SELECT u FROM Utente u")
public class Utente implements Serializable {
	private static final long serialVersionUID = 1L;

	private String email;

	private String cognome;

	private String dataDiNascita;

	@Id
	private int idUtente;

	private String nome;

	private String password;

	// bi-directional many-to-one association to Noleggi
	@OneToMany(mappedBy = "utente")
	private List<Noleggi> noleggis;

	public Utente() {
	}

	public Utente(String tryEmail, String tryPassword, String tryNome, String tryCognome, String tryDate) {
		this.email = tryEmail;
		this.password = tryPassword;
		this.nome = tryNome;
		this.cognome = tryCognome;
		this.dataDiNascita = tryDate;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCognome() {
		return this.cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getDataDiNascita() {
		return this.dataDiNascita;
	}

	public void setDataDiNascita(String dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}

	public int getIdUtente() {
		return this.idUtente;
	}

	public void setIdUtente(int idUtente) {
		this.idUtente = idUtente;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Noleggi> getNoleggis() {
		return this.noleggis;
	}

	public void setNoleggis(List<Noleggi> noleggis) {
		this.noleggis = noleggis;
	}

	public Noleggi addNoleggi(Noleggi noleggi) {
		getNoleggis().add(noleggi);
		noleggi.setUtente(this);

		return noleggi;
	}

	public Noleggi removeNoleggi(Noleggi noleggi) {
		getNoleggis().remove(noleggi);
		noleggi.setUtente(null);

		return noleggi;
	}

	@Override
	public String toString() {
		return "Utente [email=" + email + ", cognome=" + cognome + ", dataDiNascita=" + dataDiNascita + ", idUtente="
				+ idUtente + ", nome=" + nome + ", password=" + password + "]";
	}

	public int giorniNoleggio() {
		int amount = 0;
		if (noleggis != null) {
			for (Noleggi noleggio : this.noleggis) {
				amount += ChronoUnit.DAYS.between(
						noleggio.getInizioNoleggio().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
						noleggio.getFineNoleggio().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
			}
		}
		return amount;
	}

}