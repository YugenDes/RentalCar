package database.interfaces;

import java.util.ArrayList;

import model.Categorie;

public interface CategoriaDAO {
	
	public abstract ArrayList<Categorie> getCategorie();
	public abstract boolean addCategoria(Categorie obj);
	public abstract Categorie getCategoria(int id);
	public abstract Categorie getCategoria(String name);
}
