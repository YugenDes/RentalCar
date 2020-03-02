package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the utente database table.
 * 
 */
@Entity
@NamedQuery(name="Utente.findAll", query="SELECT u FROM Utente u")
public class Utente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idUtente;

	private String cognome;

	private String dataDiNascita;

	private String email;

	private String nome;

	private String password;

	//bi-directional many-to-one association to Noleggi
	@OneToMany(mappedBy="utente", fetch=FetchType.EAGER)
	private List<Noleggi> noleggis;

	public Utente() {
	}

	public int getIdUtente() {
		return this.idUtente;
	}

	public void setIdUtente(int idUtente) {
		this.idUtente = idUtente;
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

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
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

}