package database.interfaces;

import java.util.ArrayList;

import model.Veicoli;

public interface VeicoloDAO {
	
	public abstract ArrayList<Veicoli> getVeicoli();
	public abstract Veicoli getVeicoloPerTarga(String targa);
	public abstract ArrayList<Veicoli> getVeicoliPerCategoria(String cat);
	public abstract boolean addVeicolo(Veicoli obj);
	public abstract boolean removeVeicolo(Veicoli obj);

}
