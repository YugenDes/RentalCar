package database.interfaces;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

import model.Categorie;
import model.Noleggi;
import model.Utente;
import model.Veicoli;

public interface NoleggioDAO {
	public abstract ArrayList<Noleggi> getNoleggio();
	public abstract Noleggi getNoleggio(int id);
	public abstract boolean insertTempNoleggio(Noleggi obj);
	public abstract ArrayList<Noleggi> getNoleggioGiorno(Date date);
	public abstract Noleggi getNoleggio(Date date,String targa);
	public abstract ArrayList<Noleggi> getNoleggio(Utente utente);
	public abstract ArrayList<Veicoli> getMacchineTime(LocalDateTime inizio, LocalDateTime fine,int idCategoria);
	public abstract ArrayList<Categorie> getCatgorieTime(LocalDateTime inizio,LocalDateTime fine);
}
