package database.interfaces;

import java.util.ArrayList;

import model.Utente;

public interface UtenteDAO {
	public abstract Integer getIdUtente(String email);
	public abstract ArrayList<Utente> getUtenti();
	public abstract boolean addUtente(Utente obj);
	public abstract Utente getUtente(String email);
	public abstract Utente getUtente(Integer id);
}
